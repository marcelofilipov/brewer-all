package com.thefilipov.brewer.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StatusVendaValuesTest {

	@Test
	public void testGetDescricaoPorCodigo() {
		assertEquals("Orçamento", EnumHelper.getDescricaoPorCodigo(StatusVendaValues.class, 1));
		assertEquals("Emitida", EnumHelper.getDescricaoPorCodigo(StatusVendaValues.class, 2));
		assertEquals("Cancelada", EnumHelper.getDescricaoPorCodigo(StatusVendaValues.class, 3));
	}

	@Test
	public void testGetEnumPorCodigo() {
		assertEquals(StatusVendaValues.ORCAMENTO, EnumHelper.getEnumPorCodigo(1, StatusVendaValues.class));
		assertEquals(StatusVendaValues.EMITIDA, EnumHelper.getEnumPorCodigo(2, StatusVendaValues.class));
		assertEquals(StatusVendaValues.CANCELADA, EnumHelper.getEnumPorCodigo(3, StatusVendaValues.class));
	}	
	
	@Test
	public void testGetDescricaoByIdShort() {
		assertEquals("Orçamento",StatusVendaValues.getDescricaoById((short) 1));
		assertEquals("Emitida",StatusVendaValues.getDescricaoById((short) 2));
		assertEquals("Cancelada",StatusVendaValues.getDescricaoById((short) 3));
	}
	
	@Test
	public void testGetDescricaoByIdByte() {
		assertEquals("Orçamento",StatusVendaValues.getDescricaoById((byte) 1));
		assertEquals("Emitida",StatusVendaValues.getDescricaoById((byte) 2));
		assertEquals("Cancelada",StatusVendaValues.getDescricaoById((byte) 3));
	}

	@Test
	public void testGetDescricaoByIdInteger() {
		assertEquals("Orçamento",StatusVendaValues.getDescricaoById(1));
		assertEquals("Emitida",StatusVendaValues.getDescricaoById(2));
		assertEquals("Cancelada",StatusVendaValues.getDescricaoById(3));
	}
	
	@Test
	public void testGetDescricaoByIdLong() {
		assertEquals("Orçamento",StatusVendaValues.getDescricaoById(1L));
		assertEquals("Emitida",StatusVendaValues.getDescricaoById(2L));
		assertEquals("Cancelada",StatusVendaValues.getDescricaoById(3L));
	}	

	@Test
	public void testParse() {
		assertEquals(StatusVendaValues.ORCAMENTO,StatusVendaValues.parse(1));
		assertEquals(StatusVendaValues.EMITIDA,StatusVendaValues.parse(2));
		assertEquals(StatusVendaValues.CANCELADA,StatusVendaValues.parse(3));
	}	

}
