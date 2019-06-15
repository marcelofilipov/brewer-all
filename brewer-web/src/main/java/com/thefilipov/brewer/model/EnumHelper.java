package com.thefilipov.brewer.model;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marcelo A. Filipov
 * @since 2014
 *
 */
public abstract class EnumHelper {

	private static Logger log = LoggerFactory.getLogger(EnumHelper.class);

	public static <T extends Enum<T>, V> String getDescricaoPorCodigo(Class<T> enumType, V value) {

		if (value == null) {
			return null;
		}

		try {
			Method getCodigo = enumType.getDeclaredMethod("getCodigo");
			Method getDescricao = enumType.getDeclaredMethod("getDescricao");

			for (T item : enumType.getEnumConstants()) {
				if (getCodigo.invoke(item).toString().equals(value.toString())) {
					return getDescricao.invoke(item).toString();
				}
			}
		} catch (NoSuchMethodException e) {
			log.error(
					"Este enum não contém o método esperado por este Helper. Os métodos esperados são: getCodigo(), getDescricao()",
					e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		// Caso não encontre o código no Enum, retorna ele mesmo
		return value.toString();
	}

	public static <T extends Enum<T>> T getEnumPorCodigo(int codigo, Class<T> enumType) {

		try {
			Method getCodigo = enumType.getDeclaredMethod("getCodigo");

			for (T t : enumType.getEnumConstants()) {
				if (getCodigo.invoke(t).toString().equals(String.valueOf(codigo))) {
					return t;
				}
			}
		} catch (NoSuchMethodException e) {
			log.error(
					"Este enum não contém o método esperado por este Helper. Os métodos esperados são: getCodigo(), getDescricao()",
					e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

}
