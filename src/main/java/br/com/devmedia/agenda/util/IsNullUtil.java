package br.com.devmedia.agenda.util;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * <b>Title:</b> IsNullUtil.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe utilitária para validação de valores Nulos ou vazios
 * </p>
 * 
 * <p>
 * <b>Company: </b> DEVMEDIA
 * </p>
 * 
 * @author DevMedia - http://www.devmedia.com.br/
 * 
 * @version 1.0.0
 */
public class IsNullUtil {

	private IsNullUtil() {

		throw new IllegalStateException("Class Not Instantiable");
	}

	public static boolean isNull(Object value) {

		return value == null;
	}

	public static boolean isNotNull(final Object value) {

		return !isNull(value);
	}

	public static boolean isNullOrEmpty(String value) {

		return ( value == null ) || ( value.trim().length() == 0 );
	}

	public static boolean isNotNullOrEmpty(final String value) {

		return !isNullOrEmpty(value);
	}

	public static boolean isNullOrEmpty(Object value) {

		return value == null;
	}

	public static boolean isNotNullOrEmpty(final Object value) {

		return !isNullOrEmpty(value);
	}

	public static <T> boolean isNullOrEmpty(Collection<T> collection) {

		return ( collection == null ) || ( collection.isEmpty() );
	}

	public static <T> boolean isNotNullOrEmpty(final Collection<T> collection) {

		return !isNullOrEmpty(collection);
	}

	public static boolean isNullOrEmpty(Number number) {

		return ( number == null ) || ( !( number.doubleValue() > 0 ) );
	}

	public static boolean isNotNullOrEmpty(final Number number) {

		return !isNullOrEmpty(number);
	}

	public static boolean isNullOrEmpty(Date data) {

		return data == null;
	}

	public static boolean isNotNullOrEmpty(final Date data) {

		return isNullOrEmpty(data);
	}

	public static <T> boolean isNullOrEmpty(Map<T, T> map) {

		return ( map == null ) || ( map.isEmpty() );
	}

	public static <T> boolean isNotNullOrEmpty(final Map<T, T> map) {

		return !isNullOrEmpty(map);
	}

	public static boolean isNullOrEmpty(File file) {

		return isNull(file) || file.length() == 0;
	}

	public static boolean isNotNullOrEmpty(final File file) {

		return !isNullOrEmpty(file);
	}

	public static boolean isNullOrEmpty(Object[] array) {

		return ( array == null ) || ( array.length == 0 );
	}

	public static boolean isNotNullOrEmpty(final Object[] array) {

		return !isNullOrEmpty(array);
	}

	public static boolean isNullOrEmptyParameters(Object... parameters) {

		if (!isNullOrEmpty(parameters)) {
			for (Object item : parameters) {
				if (isNullOrEmpty(item)) {
					return true;
				}
			}
		}
		return isNullOrEmpty(parameters);
	}

}
