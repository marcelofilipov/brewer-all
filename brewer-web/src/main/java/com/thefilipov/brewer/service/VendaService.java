package com.thefilipov.brewer.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thefilipov.brewer.model.StatusVendaValues;
import com.thefilipov.brewer.model.Venda;
import com.thefilipov.brewer.repository.Vendas;
import com.thefilipov.brewer.service.event.venda.VendaEvent;

@Service
public class VendaService {

	@Autowired
	private Vendas vendas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public Venda salvar(Venda venda) {
		if(venda.isSalvarProibido()) {
			throw new RuntimeException("Usuário tentando salvar uma venda cancelada.");
		}
		
		if(venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		} 
//		else {
//			Venda vendaExistente = vendas.findOne(venda.getCodigo());
//			venda.setDataCriacao(vendaExistente.getDataCriacao());
//		}

		if(venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega()
				, venda.getHorarioEntrega() != null ? venda.getHorarioEntrega() : LocalTime.NOON));
		}
		
		return vendas.saveAndFlush(venda);
	}

	@Transactional
	public void emitir(Venda venda) {
		venda.setStatus(StatusVendaValues.EMITIDA);
		salvar(venda);
		
		publisher.publishEvent(new VendaEvent(venda));
	}

	@PreAuthorize("#venda.usuario == principal.usuario or hasRole('CANCELAR_VENDA')")
	@Transactional
	public void cancelar(Venda venda) {
		Venda vendaExistente = vendas.findOne(venda.getCodigo());
		
		vendaExistente.setStatus(StatusVendaValues.CANCELADA);
		vendas.save(vendaExistente);
	}
	
}
