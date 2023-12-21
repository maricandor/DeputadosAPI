package com.example.ifsul.produtosBanco.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.ifsul.produtosBanco.dto.deputado.VincularEventoRequest;
import com.example.ifsul.produtosBanco.dto.evento.ListarEventosResponse;
import com.example.ifsul.produtosBanco.dto.deputado.CadastraDeputadoRequest;
import com.example.ifsul.produtosBanco.dto.deputado.DetalharDeputadoResponse;
import com.example.ifsul.produtosBanco.dto.deputado.ListarDeputadosResponse;
import com.example.ifsul.produtosBanco.dto.MensagemResponse;
import com.example.ifsul.produtosBanco.entities.Eventos;
import com.example.ifsul.produtosBanco.repositories.DeputadoRepo;
import com.example.ifsul.produtosBanco.entities.Deputado;
import com.example.ifsul.produtosBanco.repositories.EventosRepo;
import jakarta.persistence.EntityNotFoundException;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeputadoService {

	@Autowired
	private DeputadoRepo deputadoRepository;

	@Autowired
	private EventosRepo eventosRepository;

	// Retorna a lista de todos os deputados
	public List<Deputado> listar() {
		return deputadoRepository.findAll();
	}

	// Cria um novo deputado no banco de dados
	public Deputado criar(Deputado deputado) {
		return deputadoRepository.save(deputado);
	}

	// Retorna a lista de deputados no formato de resposta DTO
	public List<ListarDeputadosResponse> listarDeputados() {
		var lista = deputadoRepository.findAll();
		return lista.stream()
				.map(ListarDeputadosResponse::new)
				.collect(Collectors.toList());
	}

	// Retorna os detalhes de um deputado específico no formato de resposta DTO
	public DetalharDeputadoResponse detalharDeputado(Long id) {
		var deputado = deputadoRepository.getReferenceById(id);
		return new DetalharDeputadoResponse(deputado);
	}

	// Retorna a lista de eventos associados a um deputado no formato de resposta DTO
	public List<ListarEventosResponse> listarEventos(Long id) {
		var deputado = deputadoRepository.getReferenceById(id);
		var lista = deputado.getEventos();

		return lista.stream()
				.map(ListarEventosResponse::new)
				.collect(Collectors.toList());
	}

	// Vincula um evento a um deputado
	public MensagemResponse vincularEvento(@NotNull Long idDeputado, @NotNull Long idEvento) {
		Optional<Eventos> optionalEvento = eventosRepository.findById(idEvento);
		Optional<Deputado> optionalDeputado = deputadoRepository.findById(idDeputado);

		// Verifica se o evento e o deputado existem
		if (optionalEvento.isPresent() && optionalDeputado.isPresent()) {
			Eventos evento = optionalEvento.get();
			Deputado deputado = optionalDeputado.get();

			// Vincula o evento ao deputado
			evento.vincularEvento(deputado);
			eventosRepository.save(evento);
			deputadoRepository.save(deputado);
		} else {
			throw new RuntimeException("Evento ou Deputado não encontrado");
		}
		return new MensagemResponse(200, "Deputado vinculado com sucesso!");
	}

	// Deleta um deputado
	public MensagemResponse deletarDepu(Long id){
		var deputado = deputadoRepository.getReferenceById(id);
		deputadoRepository.delete(deputado);

		return new MensagemResponse(202, "Deputado deletado com sucesso!");
	}
}
