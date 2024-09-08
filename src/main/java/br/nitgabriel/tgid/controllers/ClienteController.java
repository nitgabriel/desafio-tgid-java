package br.nitgabriel.tgid.controllers;

import br.nitgabriel.tgid.models.Cliente;
import br.nitgabriel.tgid.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
public class ClienteController {
    // O ideal seria criar um DTO
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Cadastrar cliente", description = "Cadastra um novo cliente")
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente", description = "Atualiza um cliente existente")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteService.atualizarCliente(id, clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente", description = "Exclui um cliente existente")
    public void excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Busca um cliente pelo ID")
    public Optional<Cliente> buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Lista todos os clientes")
    public List<Cliente> listarTodosClientes() {
        return clienteService.listarTodosClientes();
    }
}