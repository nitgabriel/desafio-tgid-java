package br.nitgabriel.tgid.service;

import br.nitgabriel.tgid.models.Empresa;
import br.nitgabriel.tgid.repositories.EmpresaRepository;
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

class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setCnpj("12345678000199");

        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Empresa result = empresaService.cadastrarEmpresa(empresa);

        assertNotNull(result);
        assertEquals("12345678000199", result.getCnpj());
    }

    @Test
    void testAtualizarEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setCnpj("12345678000199");

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Empresa empresaAtualizada = new Empresa();
        empresaAtualizada.setCnpj("12345678000199");

        Empresa result = empresaService.atualizarEmpresa(1L, empresaAtualizada);

        assertNotNull(result);
        assertEquals("12345678000199", result.getCnpj());
    }

    @Test
    void testExcluirEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));
        doNothing().when(empresaRepository).delete(empresa);

        empresaService.excluirEmpresa(1L);

        verify(empresaRepository, times(1)).delete(empresa);
    }

    @Test
    void testBuscarEmpresaPorId() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setCnpj("12345678000199");

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        Optional<Empresa> result = empresaService.buscarEmpresaPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("12345678000199", result.get().getCnpj());
    }

    @Test
    void testListarTodasEmpresas() {
        Empresa empresa = new Empresa();
        empresa.setCnpj("12345678000199");

        when(empresaRepository.findAll()).thenReturn(List.of(empresa));

        List<Empresa> result = empresaService.listarTodasEmpresas();

        assertFalse(result.isEmpty());
        assertEquals("12345678000199", result.get(0).getCnpj());
    }
}