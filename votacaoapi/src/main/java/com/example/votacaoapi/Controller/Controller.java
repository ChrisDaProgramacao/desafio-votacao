package com.example.votacaoapi.Controller;

import com.example.votacaoapi.Classes.Pauta;
import com.example.votacaoapi.Classes.Sessao;
import com.example.votacaoapi.Classes.Voto;
import com.example.votacaoapi.Services.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/votacao")
public class Controller {
    @Autowired
    private VotoService votacaoService;

    @PostMapping("/pauta")
    public ResponseEntity<Pauta> criarPauta(@RequestBody String descricao) {
        return ResponseEntity.ok(votacaoService.criarPauta(descricao));
    }

    @PostMapping("/sessao")
    public ResponseEntity<Sessao> abrirSessao(@RequestParam Long pautaId, @RequestParam Optional<Duration> duracao) {
        return ResponseEntity.ok(votacaoService.abrirSessao(pautaId, duracao));
    }

    @PostMapping("/voto")
    public ResponseEntity<Voto> votar(@RequestParam Long sessaoId, @RequestParam Long associadoId, @RequestParam boolean voto) {
        return ResponseEntity.ok(votacaoService.registrarVoto(sessaoId, associadoId, voto));
    }

    @GetMapping("/resultado")
    public ResponseEntity<Map<String, Long>> resultado(@RequestParam Long pautaId) {
        return ResponseEntity.ok(votacaoService.contabilizarVotos(pautaId));
    }
}