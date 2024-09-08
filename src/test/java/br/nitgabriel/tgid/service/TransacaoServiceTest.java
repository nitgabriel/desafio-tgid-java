package br.nitgabriel.tgid.service;

import br.nitgabriel.tgid.models.Cliente;
import br.nitgabriel.tgid.models.Empresa;
import br.nitgabriel.tgid.repositories.ClienteRepository;
import br.nitgabriel.tgid.repositories.EmpresaRepository;
import br.nitgabriel.tgid.repositories.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransacaoServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRealizarDeposito() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setSaldo(100.0);

        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setSaldo(1000.0);
        empresa.setTaxaSistema(0.1);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        transacaoService.realizarDeposito(1L, 1L, 100.0);

        verify(clienteRepository).save(cliente);
        verify(empresaRepository).save(empresa);
        verify(transacaoRepository).save(any());
        verify(restTemplate, times(2)).postForObject(anyString(), anyString(), eq(String.class));
    }

    @Test
    void testRealizarSaque() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setSaldo(200.0);

        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setSaldo(1000.0);
        empresa.setTaxaSistema(0.1);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        transacaoService.realizarSaque(1L, 1L, 100.0);

        verify(clienteRepository).save(cliente);
        verify(empresaRepository).save(empresa);
        verify(transacaoRepository).save(any());
        verify(restTemplate, times(2)).postForObject(anyString(), anyString(), eq(String.class));
    }

    @Test
    void testRealizarSaqueSaldoInsuficienteEmpresa() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setSaldo(200.0);

        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setSaldo(50.0);
        empresa.setTaxaSistema(0.1);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        assertThrows(IllegalArgumentException.class, () -> {
            transacaoService.realizarSaque(1L, 1L, 100.0);
        });

        verify(clienteRepository, never()).save(any());
        verify(empresaRepository, never()).save(any());
        verify(transacaoRepository, never()).save(any());
        verify(restTemplate, never()).postForObject(anyString(), anyString(), eq(String.class));
    }
}