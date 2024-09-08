package br.nitgabriel.tgid.controllers;

import br.nitgabriel.tgid.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
@Tag(name = "Transações", description = "Operações relacionadas a transações")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/deposito")
    @Operation(summary = "Realizar depósito", description = "Realiza um depósito na conta do cliente")
    public void realizarDeposito(@RequestParam Long clienteId, @RequestParam Long empresaId, @RequestParam double valor) {
        transacaoService.realizarDeposito(clienteId, empresaId, valor);
    }

    @PostMapping("/saque")
    @Operation(summary = "Realizar saque", description = "Realiza um saque da conta do cliente")
    public void realizarSaque(@RequestParam Long clienteId, @RequestParam Long empresaId, @RequestParam double valor) {
        transacaoService.realizarSaque(clienteId, empresaId, valor);
    }
}