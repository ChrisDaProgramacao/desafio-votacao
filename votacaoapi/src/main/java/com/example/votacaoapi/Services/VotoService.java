package com.example.votacaoapi.Services;

import com.example.votacaoapi.Classes.Pauta;
import com.example.votacaoapi.Classes.Sessao;
import com.example.votacaoapi.Classes.Voto;
import com.example.votacaoapi.Repositorios.PautaRepository;
import com.example.votacaoapi.Repositorios.SessaoRepository;
import com.example.votacaoapi.Repositorios.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Service
public class VotoService {
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private VotoRepository votoRepository;

    public Pauta criarPauta(String descricao) {
        Pauta pauta = new Pauta();
        pauta.setDescricao(descricao);
        return pautaRepository.save(pauta);
    }

    public Sessao abrirSessao(Long pautaId, Optional<Duration> duracao) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));
        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setDataAbertura(LocalDateTime.now());
        sessao.setDataFechamento(LocalDateTime.now().plus(duracao.orElse(Duration.ofMinutes(1))));
        return sessaoRepository.save(sessao);
    }

    public Voto registrarVoto(Long sessaoId, Long associadoId, boolean voto) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
        if (votoRepository.existsBySessaoAndAssociadoId(sessao, associadoId)) {
            throw new RuntimeException("Associado já votou nesta pauta");
        }
        Voto novoVoto = new Voto();
        novoVoto.setSessao(sessao);
        novoVoto.setAssociadoId(associadoId);
        novoVoto.setVoto(voto);
        return votoRepository.save(novoVoto);
    }

    public Map<String, Long> contabilizarVotos(Long pautaId) {
        Sessao sessao = sessaoRepository.findByPautaIdAndDataFechamentoBefore(pautaId, LocalDateTime.now())
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada ou ainda aberta"));
        long votosSim = votoRepository.countBySessaoAndVoto(sessao, true);
        long votosNao = votoRepository.countBySessaoAndVoto(sessao, false);
        return Map.of("Sim", votosSim, "Não", votosNao);
    }
}