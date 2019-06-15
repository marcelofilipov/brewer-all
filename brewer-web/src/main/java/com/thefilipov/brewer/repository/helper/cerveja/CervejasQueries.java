package com.thefilipov.brewer.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thefilipov.brewer.dto.CervejaDTO;
import com.thefilipov.brewer.dto.ValorItensEstoqueDTO;
import com.thefilipov.brewer.model.Cerveja;
import com.thefilipov.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	public List<CervejaDTO> porSkuOuNome(String skuOuNome);
	public ValorItensEstoqueDTO valorItensEstoque();
	
}
