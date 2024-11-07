package com.example.votacaoapi.Repositorios;

import com.example.votacaoapi.Classes.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    Optional<Sessao> findByPautaIdAndDataFechamentoBefore(Long pautaId, LocalDateTime dataFechamento);
}