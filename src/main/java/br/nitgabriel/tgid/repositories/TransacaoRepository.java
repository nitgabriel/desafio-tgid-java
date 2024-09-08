package br.nitgabriel.tgid.repositories;

import br.nitgabriel.tgid.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}