package br.nitgabriel.tgid.repositories;

import br.nitgabriel.tgid.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);

    boolean existsByEmail(String email);
}