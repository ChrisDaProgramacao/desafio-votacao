package com.example.votacaoapi.Repositorios;

import com.example.votacaoapi.Classes.Sessao;
import com.example.votacaoapi.Classes.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    long countBySessaoAndVoto(Sessao sessao, boolean voto);

    boolean existsBySessaoAndAssociadoId(Sessao sessao, Long associadoId);
}
