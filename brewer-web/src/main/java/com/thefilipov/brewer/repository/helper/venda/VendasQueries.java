package com.thefilipov.brewer.repository.helper.venda;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thefilipov.brewer.dto.VendaMesDTO;
import com.thefilipov.brewer.dto.VendaOrigemDTO;
import com.thefilipov.brewer.model.Venda;
import com.thefilipov.brewer.repository.filter.VendaFilter;

public interface VendasQueries {

	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable);
	public Venda buscarComItens(Long codigo);
	
	public BigDecimal valorTotalNoAno();
	public BigDecimal valorTotalNoMes();
	public BigDecimal valorTicketMedioNoAno();
	
	public List<VendaMesDTO> totalPorMes();
	public List<VendaOrigemDTO> totalPorOrigem();
	
}