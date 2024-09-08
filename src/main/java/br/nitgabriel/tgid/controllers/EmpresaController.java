package br.nitgabriel.tgid.controllers;

import br.nitgabriel.tgid.models.Empresa;
import br.nitgabriel.tgid.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
@Tag(name = "Empresas", description = "Operações relacionadas a empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    @Operation(summary = "Cadastrar empresa", description = "Cadastra uma nova empresa com taxa em porcentagem")
    public Empresa cadastrarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.cadastrarEmpresa(empresa);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar empresa", description = "Atualiza uma empresa existente")
    public Empresa atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        return empresaService.atualizarEmpresa(id, empresaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir empresa", description = "Exclui uma empresa existente")
    public void excluirEmpresa(@PathVariable Long id) {
        empresaService.excluirEmpresa(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empresa por ID", description = "Busca uma empresa pelo ID")
    public Optional<Empresa> buscarEmpresaPorId(@PathVariable Long id) {
        return empresaService.buscarEmpresaPorId(id);
    }

    @GetMapping
    @Operation(summary = "Listar todas as empresas", description = "Lista todas as empresas")
    public List<Empresa> listarTodasEmpresas() {
        return empresaService.listarTodasEmpresas();
    }
}