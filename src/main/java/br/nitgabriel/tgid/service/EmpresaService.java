package br.nitgabriel.tgid.service;

import br.nitgabriel.tgid.models.Empresa;
import br.nitgabriel.tgid.repositories.EmpresaRepository;
import br.nitgabriel.tgid.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa cadastrarEmpresa(Empresa empresa) {
        if (!Validator.isValidCNPJ(empresa.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        return empresaRepository.save(empresa);
    }

    public Empresa atualizarEmpresa(Long id, Empresa empresaAtualizada) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));
        if (!Validator.isValidCNPJ(empresaAtualizada.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        empresa.setCnpj(empresaAtualizada.getCnpj());
        empresa.setNome(empresaAtualizada.getNome());
        empresa.setSaldo(empresaAtualizada.getSaldo());
        empresa.setTaxaSistema(empresaAtualizada.getTaxaSistema() / 100);
        return empresaRepository.save(empresa);
    }

    public void excluirEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));
        empresaRepository.delete(empresa);
    }

    public Optional<Empresa> buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public List<Empresa> listarTodasEmpresas() {
        return empresaRepository.findAll();
    }
}