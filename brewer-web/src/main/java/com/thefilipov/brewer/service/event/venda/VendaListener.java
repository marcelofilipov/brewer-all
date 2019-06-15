package com.thefilipov.brewer.service.event.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.thefilipov.brewer.model.Cerveja;
import com.thefilipov.brewer.model.ItemVenda;
import com.thefilipov.brewer.repository.Cervejas;

@Component
public class VendaListener {
	
	@Autowired
	private Cervejas cervejas;
	
	@EventListener
	public void vendaEmitida(VendaEvent vendaEvent) {
		for(ItemVenda item : vendaEvent.getVenda().getItens()) {
			Cerveja cerveja = cervejas.findOne(item.getCerveja().getCodigo());
			cerveja.setQtdeEstoque(cerveja.getQtdeEstoque() - item.getQuantidade());
			cervejas.save(cerveja);
		}
	}

}
