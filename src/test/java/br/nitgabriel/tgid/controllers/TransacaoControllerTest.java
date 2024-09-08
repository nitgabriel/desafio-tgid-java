package br.nitgabriel.tgid.controllers;

import br.nitgabriel.tgid.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(TransacaoController.class)
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoService transacaoService;

    @Test
    void testRealizarDeposito() throws Exception {
        mockMvc.perform(post("/transacoes/deposito")
                .param("clienteId", "1")
                .param("empresaId", "1")
                .param("valor", "100.0"))
                .andExpect(status().isOk());

        verify(transacaoService).realizarDeposito(1L, 1L, 100.0);
    }

    @Test
    void testRealizarSaque() throws Exception {
        mockMvc.perform(post("/transacoes/saque")
                        .param("clienteId", "1")
                        .param("empresaId", "1")
                        .param("valor", "100.0"))
                .andExpect(status().isOk());

        verify(transacaoService).realizarSaque(1L, 1L, 100.0);
    }
}