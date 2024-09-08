package br.nitgabriel.tgid.service;

import br.nitgabriel.tgid.models.Cliente;
import br.nitgabriel.tgid.repositories.ClienteRepository;
import br.nitgabriel.tgid.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        if (!Validator.isValidCPF(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF inválido");
        }
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        if (!Validator.isValidCPF(clienteAtualizado.getCpf())) {
            throw new IllegalArgumentException("CPF inválido");
        }
        if (clienteRepository.existsByEmail(clienteAtualizado.getEmail()) && !cliente.getEmail().equals(clienteAtualizado.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setSaldo(clienteAtualizado.getSaldo());
        return clienteRepository.save(cliente);
    }

    public void excluirCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }
}