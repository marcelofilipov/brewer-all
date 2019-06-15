package com.thefilipov.brewer.session;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.thefilipov.brewer.model.Cerveja;
import com.thefilipov.brewer.session.TabelaItensVenda;

public class TabelaItensVendaTest {

private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp() {
		this.tabelaItensVenda = new TabelaItensVenda(UUID.randomUUID().toString());
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Cerveja cerveja = new Cerveja();
		BigDecimal preco = new BigDecimal("8.90");
		cerveja.setPreco(preco);
		
		tabelaItensVenda.adicionarItem(cerveja, 1);
		
		assertEquals(preco, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		BigDecimal p1 = new BigDecimal("8.90");
		c1.setPreco(p1);
		
		Cerveja c2 = new Cerveja();
		c2.setCodigo(2L);
		BigDecimal p2 = new BigDecimal("4.99");
		c2.setPreco(p2);
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c2, 2);
		
		assertEquals(new BigDecimal("18.88"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveManterTamanhoDaListaParaMesmasCervejas() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setPreco(new BigDecimal("4.50"));
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c1, 1);
		
		assertEquals(1, tabelaItensVenda.total());
		assertEquals(new BigDecimal("9.00"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveAlterarQuantidadeDoItem() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setPreco(new BigDecimal("4.50"));
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.alterarQuantidadeItens(c1, 3);
		
		assertEquals(1, tabelaItensVenda.total());
		assertEquals(new BigDecimal("13.50"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveExcluirItem() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setPreco(new BigDecimal("8.90"));
		
		Cerveja c2 = new Cerveja();
		c2.setCodigo(2L);
		c2.setPreco(new BigDecimal("4.99"));

		Cerveja c3 = new Cerveja();
		c3.setCodigo(3L);
		c3.setPreco(new BigDecimal("2.00"));
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c2, 2);
		tabelaItensVenda.adicionarItem(c3, 1);
		
		tabelaItensVenda.excluirItem(c2);
		
		assertEquals(2, tabelaItensVenda.total());
		assertEquals(new BigDecimal("10.90"), tabelaItensVenda.getValorTotal());
	}

}
