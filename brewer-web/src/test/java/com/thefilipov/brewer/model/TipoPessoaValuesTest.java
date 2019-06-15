package com.thefilipov.brewer.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TipoPessoaValuesTest {

	@Test
	public void testGetDescricaoPorCodigo() {
		assertEquals("Física", EnumHelper.getDescricaoPorCodigo(TipoPessoaValues.class, 1));
		assertEquals("Jurídica", EnumHelper.getDescricaoPorCodigo(TipoPessoaValues.class, 2));
	}

	@Test
	public void testGetEnumPorCodigo() {
		assertEquals(TipoPessoaValues.FISICA, EnumHelper.getEnumPorCodigo(1, TipoPessoaValues.class));
		assertEquals(TipoPessoaValues.JURIDICA, EnumHelper.getEnumPorCodigo(2, TipoPessoaValues.class));
	}	
	
	@Test
	public void testGetDescricaoByIdShort() {
		assertEquals("Física",TipoPessoaValues.getDescricaoById((short) 1));
		assertEquals("Jurídica",TipoPessoaValues.getDescricaoById((short) 2));
	}
	
	@Test
	public void testGetDescricaoByIdByte() {
		assertEquals("Física",TipoPessoaValues.getDescricaoById((byte) 1));
		assertEquals("Jurídica",TipoPessoaValues.getDescricaoById((byte) 2));
	}

	@Test
	public void testGetDescricaoByIdInteger() {
		assertEquals("Física",TipoPessoaValues.getDescricaoById(1));
		assertEquals("Jurídica",TipoPessoaValues.getDescricaoById(2));
	}
	
	@Test
	public void testGetDescricaoByIdLong() {
		assertEquals("Física",TipoPessoaValues.getDescricaoById(1L));
		assertEquals("Jurídica",TipoPessoaValues.getDescricaoById(2L));
	}	

	@Test
	public void testGetDocumentoByIdShort() {
		assertEquals("CPF",TipoPessoaValues.getDocumentoById((short) 1));
		assertEquals("CNPJ",TipoPessoaValues.getDocumentoById((short) 2));
	}
	
	@Test
	public void testGetDocumentoByIdByte() {
		assertEquals("CPF",TipoPessoaValues.getDocumentoById((byte) 1));
		assertEquals("CNPJ",TipoPessoaValues.getDocumentoById((byte) 2));
	}

	@Test
	public void testGetDocumentoByIdInteger() {
		assertEquals("CPF",TipoPessoaValues.getDocumentoById(1));
		assertEquals("CNPJ",TipoPessoaValues.getDocumentoById(2));
	}
	
	@Test
	public void testGetDocumentoByIdLong() {
		assertEquals("CPF",TipoPessoaValues.getDocumentoById(1L));
		assertEquals("CNPJ",TipoPessoaValues.getDocumentoById(2L));
	}	

	@Test
	public void testParse() {
		assertEquals(TipoPessoaValues.FISICA,TipoPessoaValues.parse(1));
		assertEquals(TipoPessoaValues.JURIDICA,TipoPessoaValues.parse(2));
	}	
	
}
