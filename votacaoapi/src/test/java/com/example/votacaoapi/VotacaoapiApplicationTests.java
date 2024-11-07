package com.example.votacaoapi;

import com.example.votacaoapi.Classes.Pauta;
import com.example.votacaoapi.Classes.Sessao;
import com.example.votacaoapi.Classes.Voto;
import com.example.votacaoapi.Repositorios.PautaRepository;
import com.example.votacaoapi.Repositorios.SessaoRepository;
import com.example.votacaoapi.Repositorios.VotoRepository;
import com.example.votacaoapi.Services.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VotacaoapiApplicationTests {


	@Autowired
	private VotoService votacaoService;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private VotoRepository votoRepository;

	private Long pautaId;

	@BeforeEach
	public void setUp() {
		// Cria uma pauta de exemplo
		Pauta pauta = new Pauta();
		pauta.setDescricao("Pauta de Teste");
		pauta = pautaRepository.save(pauta);

		this.pautaId = pauta.getId();

		// Cria uma sessão para a pauta
		Sessao sessao = new Sessao();
		sessao.setPauta(pauta);
		sessao.setDataAbertura(LocalDateTime.now().minusMinutes(10));
		sessao.setDataFechamento(LocalDateTime.now().minusMinutes(1));
		sessaoRepository.save(sessao);

		// Cria votos na sessão
		Voto votoSim = new Voto();
		votoSim.setSessao(sessao);
		votoSim.setAssociadoId(1L);
		votoSim.setVoto(true); // Voto "Sim"
		votoRepository.save(votoSim);

		Voto votoNao = new Voto();
		votoNao.setSessao(sessao);
		votoNao.setAssociadoId(2L);
		votoNao.setVoto(false); // Voto "Não"
		votoRepository.save(votoNao);
	}

	@Test
	public void testContabilizarVotos() {
		Map<String, Long> resultado = votacaoService.contabilizarVotos(pautaId);

		assertEquals(1L, resultado.get("Sim"));
		assertEquals(1L, resultado.get("Não"));
	}
}
