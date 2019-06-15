package com.thefilipov.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thefilipov.brewer.model.Venda;
import com.thefilipov.brewer.repository.helper.venda.VendasQueries;

public interface Vendas extends JpaRepository<Venda, Long>, VendasQueries {

}
