package com.thefilipov.brewer.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrigemValuesTest {

	@Test
	public void testGetDescricaoPorCodigo() {
		assertEquals("Nacional", EnumHelper.getDescricaoPorCodigo(OrigemValues.class, 1));
		assertEquals("Internacional", EnumHelper.getDescricaoPorCodigo(OrigemValues.class, 2));
	}

	@Test
	public void testGetEnumPorCodigo() {
		assertEquals(OrigemValues.NACIONAL, EnumHelper.getEnumPorCodigo(1, OrigemValues.class));
		assertEquals(OrigemValues.INTERNACIONAL, EnumHelper.getEnumPorCodigo(2, OrigemValues.class));
	}	
	
	@Test
	public void testGetDescricaoByIdShort() {
		assertEquals("Nacional",OrigemValues.getDescricaoById((short) 1));
		assertEquals("Internacional",OrigemValues.getDescricaoById((short) 2));
	}
	
	@Test
	public void testGetDescricaoByIdByte() {
		assertEquals("Nacional",OrigemValues.getDescricaoById((byte) 1));
		assertEquals("Internacional",OrigemValues.getDescricaoById((byte) 2));
	}

	@Test
	public void testGetDescricaoByIdInteger() {
		assertEquals("Nacional",OrigemValues.getDescricaoById(1));
		assertEquals("Internacional",OrigemValues.getDescricaoById(2));
	}
	
	@Test
	public void testGetDescricaoByIdLong() {
		assertEquals("Nacional",OrigemValues.getDescricaoById(1L));
		assertEquals("Internacional",OrigemValues.getDescricaoById(2L));
	}	

	@Test
	public void testParse() {
		assertEquals(OrigemValues.NACIONAL,OrigemValues.parse(1));
		assertEquals(OrigemValues.INTERNACIONAL,OrigemValues.parse(2));
	}	
	
}
