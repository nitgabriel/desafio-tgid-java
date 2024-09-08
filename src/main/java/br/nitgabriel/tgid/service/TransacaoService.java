package br.nitgabriel.tgid.service;

import br.nitgabriel.tgid.models.Cliente;
import br.nitgabriel.tgid.models.Empresa;
import br.nitgabriel.tgid.models.Transacao;
import br.nitgabriel.tgid.repositories.ClienteRepository;
import br.nitgabriel.tgid.repositories.EmpresaRepository;
import br.nitgabriel.tgid.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TransacaoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void realizarDeposito(Long clienteId, Long empresaId, double valor) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));

        double taxaSistema = empresa.getTaxaSistema();
        double valorComTaxa = valor - (valor * taxaSistema);
        cliente.setSaldo(cliente.getSaldo() + valor);
        empresa.setSaldo(empresa.getSaldo() + valorComTaxa);

        clienteRepository.save(cliente);
        empresaRepository.save(empresa);

        Transacao transacao = new Transacao(cliente, empresa, valor, LocalDateTime.now(), "DEPOSITO");
        transacaoRepository.save(transacao);

        try {
            enviarCallback(empresa, "Depósito realizado: " + valor);
        } catch (Exception e) {
            System.err.println("Erro ao enviar callback: " + e.getMessage());
        }

        try {
            enviarNotificacao(cliente, "Depósito realizado: " + valor);
        } catch (Exception e) {
            System.err.println("Erro ao enviar notificação: " + e.getMessage());
        }
    }

    public void realizarSaque(Long clienteId, Long empresaId, double valor) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow();

        double taxaSistema = empresa.getTaxaSistema();
        double valorComTaxa = valor + (valor * taxaSistema);
        if (cliente.getSaldo() >= valor && empresa.getSaldo() >= valorComTaxa) {
            cliente.setSaldo(cliente.getSaldo() - valor);
            empresa.setSaldo(empresa.getSaldo() - valorComTaxa);

            clienteRepository.save(cliente);
            empresaRepository.save(empresa);

            Transacao transacao = new Transacao(cliente, empresa, valor, LocalDateTime.now(), "SAQUE");
            transacaoRepository.save(transacao);

            enviarCallback(empresa, "Saque realizado: " + valor);
            enviarNotificacao(cliente, "Saque realizado: " + valor);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    private void enviarCallback(Empresa empresa, String mensagem) {
        try {
            restTemplate.postForObject("https://webhook.site/7f283d1e-f954-4e9e-a7d2-a02a6a0bb8bf", mensagem, String.class);
        } catch (Exception e) {
            System.err.println("Erro ao enviar callback: " + e.getMessage());
        }
    }

    private void enviarNotificacao(Cliente cliente, String mensagem) {
        try {
            restTemplate.postForObject("https://webhook.site/7f283d1e-f954-4e9e-a7d2-a02a6a0bb8bf", mensagem, String.class);
        } catch (Exception e) {
            System.err.println("Erro ao enviar notificação: " + e.getMessage());
        }
    }
}