package br.nitgabriel.tgid.service;

import br.nitgabriel.tgid.models.Cliente;
import br.nitgabriel.tgid.repositories.ClienteRepository;
import br.nitgabriel.tgid.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        cliente.setEmail("test@example.com");

        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteService.cadastrarCliente(cliente);

        assertNotNull(result);
        assertEquals("12345678901", result.getCpf());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void testCadastrarClienteEmailJaCadastrado() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        cliente.setEmail("test@example.com");

        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.cadastrarCliente(cliente);
        });

        assertEquals("E-mail já cadastrado", exception.getMessage());
    }

    @Test
    void testAtualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678901");
        cliente.setEmail("test@example.com");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setCpf("12345678901");
        clienteAtualizado.setEmail("updated@example.com");

        Cliente result = clienteService.atualizarCliente(1L, clienteAtualizado);

        assertNotNull(result);
        assertEquals("12345678901", result.getCpf());
        assertEquals("updated@example.com", result.getEmail());
    }

    @Test
    void testAtualizarClienteEmailJaCadastrado() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678901");
        cliente.setEmail("test@example.com");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.existsByEmail("updated@example.com")).thenReturn(true);

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setCpf("12345678901");
        clienteAtualizado.setEmail("updated@example.com");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.atualizarCliente(1L, clienteAtualizado);
        });

        assertEquals("E-mail já cadastrado", exception.getMessage());
    }

    @Test
    void testExcluirCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);

        clienteService.excluirCliente(1L);

        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    void testBuscarClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678901");
        cliente.setEmail("test@example.com");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.buscarClientePorId(1L);

        assertTrue(result.isPresent());
        assertEquals("12345678901", result.get().getCpf());
        assertEquals("test@example.com", result.get().getEmail());
    }

    @Test
    void testListarTodosClientes() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        cliente.setEmail("test@example.com");

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> result = clienteService.listarTodosClientes();

        assertFalse(result.isEmpty());
        assertEquals("12345678901", result.get(0).getCpf());
        assertEquals("test@example.com", result.get(0).getEmail());
    }
}