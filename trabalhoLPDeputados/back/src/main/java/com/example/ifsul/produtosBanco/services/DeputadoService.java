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
		
		public List<Deputado> listar() {
			return deputadoRepository.findAll();
		}

		public Deputado criar(Deputado deputado) {
			return deputadoRepository.save(deputado);
		}
/*
	public MensagemResponse cadastraDeputado(CadastraDeputadoRequest dados) {
			try{
				Deputado deputado = new Deputado();
				deputado.setEmail(dados.email());
				deputado.setSiglaUf(dados.siglaUf());
				deputado.setUrlFoto(dados.urlFoto());
				deputado.setIdLegislatura(dados.idLegislatura());
				deputado.setNome(dados.nome());
				deputado.setSiglaPartido(dados.siglaPartido());

				deputadoRepository.save(deputado);
				return new MensagemResponse(201, "Deputado cadastrado com sucesso!");
			}catch (Exception ex){
				return new MensagemResponse(400, "Problema com os dados de cadastro!");
			}
	}
	*/

    public List<ListarDeputadosResponse> listarDeputados() {
			var lista = deputadoRepository.findAll();
			return lista.stream()
					.map(ListarDeputadosResponse::new)
					.collect(Collectors.toList());
    }

	public DetalharDeputadoResponse detalharDeputado(Long id) {
			var deputado = deputadoRepository.getReferenceById(id);
			return new DetalharDeputadoResponse(deputado);
	}

	public List<ListarEventosResponse> listarEventos(Long id) {
			var deputado = deputadoRepository.getReferenceById(id);
			var lista = deputado.getEventos();

			return lista.stream()
					.map(ListarEventosResponse::new)
					.collect(Collectors.toList());
	}

	public MensagemResponse adicionarEvento(Long idDeputado, Long id){
			var deputado = deputadoRepository.getReferenceById(idDeputado);
			var evento = eventosRepository.getReferenceById(id);

			deputado.getEventos().add(evento);

			return new MensagemResponse(200, "Deputado vinculado com sucesso!");
	}
	public MensagemResponse vincularEvento(@NotNull Long idEvento, @NotNull Long idDeputado) {
	//		System.out.println("ID do Evento: " + idEvento);
	//		System.out.println("ID do Deputado: " + idDeputado);

			Optional<Eventos> optionalEvento = eventosRepository.findById(idEvento);
			Optional<Deputado> optionalDeputado = deputadoRepository.findById(idDeputado);

		if (optionalEvento.isPresent() && optionalDeputado.isPresent()) {
			Eventos evento = optionalEvento.get();
			Deputado deputado = optionalDeputado.get();

			evento.vincularEvento(deputado);
			eventosRepository.save(evento);
			deputadoRepository.save(deputado);
		} else {
			throw new RuntimeException("Evento ou Deputado não encontrado");
		}
		return new MensagemResponse(200, "Deputado vinculado com sucesso!");
	}
	public MensagemResponse deletarDepu(Long id){
		var deputado = deputadoRepository.getReferenceById(id);
		deputadoRepository.delete(deputado);

		return new MensagemResponse(202, "Deputado deletado com sucesso!");
	}
}
