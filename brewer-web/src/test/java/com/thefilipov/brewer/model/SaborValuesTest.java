package com.thefilipov.brewer.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SaborValuesTest {

	@Test
	public void testGetDescricaoPorCodigo() {
		assertEquals("Adocicada", EnumHelper.getDescricaoPorCodigo(SaborValues.class, 1));
		assertEquals("Amarga", EnumHelper.getDescricaoPorCodigo(SaborValues.class, 2));
		assertEquals("Forte", EnumHelper.getDescricaoPorCodigo(SaborValues.class, 3));
		assertEquals("Frutada", EnumHelper.getDescricaoPorCodigo(SaborValues.class, 4));
		assertEquals("Suave", EnumHelper.getDescricaoPorCodigo(SaborValues.class, 5));
	}

	@Test
	public void testGetEnumPorCodigo() {
		assertEquals(SaborValues.ADOCICADA, EnumHelper.getEnumPorCodigo(1, SaborValues.class));
		assertEquals(SaborValues.AMARGA, EnumHelper.getEnumPorCodigo(2, SaborValues.class));
		assertEquals(SaborValues.FORTE, EnumHelper.getEnumPorCodigo(3, SaborValues.class));
		assertEquals(SaborValues.FRUTADA, EnumHelper.getEnumPorCodigo(4, SaborValues.class));
		assertEquals(SaborValues.SUAVE, EnumHelper.getEnumPorCodigo(5, SaborValues.class));
	}	
	
	@Test
	public void testGetDescricaoByIdShort() {
		assertEquals("Adocicada",SaborValues.getDescricaoById((short) 1));
		assertEquals("Amarga",SaborValues.getDescricaoById((short) 2));
		assertEquals("Forte",SaborValues.getDescricaoById((short) 3));
		assertEquals("Frutada",SaborValues.getDescricaoById((short) 4));
		assertEquals("Suave",SaborValues.getDescricaoById((short) 5));
	}
	
	@Test
	public void testGetDescricaoByIdByte() {
		assertEquals("Adocicada",SaborValues.getDescricaoById((byte) 1));
		assertEquals("Amarga",SaborValues.getDescricaoById((byte) 2));
		assertEquals("Forte",SaborValues.getDescricaoById((byte) 3));
		assertEquals("Frutada",SaborValues.getDescricaoById((byte) 4));
		assertEquals("Suave",SaborValues.getDescricaoById((byte) 5));
	}

	@Test
	public void testGetDescricaoByIdInteger() {
		assertEquals("Adocicada",SaborValues.getDescricaoById(1));
		assertEquals("Amarga",SaborValues.getDescricaoById(2));
		assertEquals("Forte",SaborValues.getDescricaoById(3));
		assertEquals("Frutada",SaborValues.getDescricaoById(4));
		assertEquals("Suave",SaborValues.getDescricaoById(5));
	}
	
	@Test
	public void testGetDescricaoByIdLong() {
		assertEquals("Adocicada",SaborValues.getDescricaoById(1L));
		assertEquals("Amarga",SaborValues.getDescricaoById(2L));
		assertEquals("Forte",SaborValues.getDescricaoById(3L));
		assertEquals("Frutada",SaborValues.getDescricaoById(4L));
		assertEquals("Suave",SaborValues.getDescricaoById(5L));
	}	

	@Test
	public void testParse() {
		assertEquals(SaborValues.ADOCICADA,SaborValues.parse(1));
		assertEquals(SaborValues.AMARGA,SaborValues.parse(2));
		assertEquals(SaborValues.FORTE,SaborValues.parse(3));
		assertEquals(SaborValues.FRUTADA,SaborValues.parse(4));
		assertEquals(SaborValues.SUAVE,SaborValues.parse(5));
	}	
	
}
