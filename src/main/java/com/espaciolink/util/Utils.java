/*
 * Utils.java
 * Created on 1-Sept-2009
 *
 * Developed by:NDEVELOPER cia. ltda. www.ndeveloper.com
 * 
 */
package com.espaciolink.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.primefaces.event.FileUploadEvent;

/**
 * Clase con las utilidades generales.
 * 
 * @author Eduardo Proano
 * @version $Revision: 1.10 $
 */
public final class Utils {

	public static final HashMap<String, String> CARACTERES_NO_DESEADOS = new HashMap<String, String>();
	private static final String numeros = "0123456789";
	private static final Random r = new Random();
	private static int[] cedul = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
	private static int[] caso9 = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
	private static int[] caso6 = { 3, 2, 7, 6, 5, 4, 3, 2 };
	private static final String EMAIL_REGEX = "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.(([0-9]{1,3})|([a-zA-Z]{2,3})|(aero|coop|info|museum|name))$";
	private static Calendar cal;

	static {
		CARACTERES_NO_DESEADOS.put("\u00C1", "A");
		CARACTERES_NO_DESEADOS.put("\u00C9", "E");
		CARACTERES_NO_DESEADOS.put("\u00CD", "I");
		CARACTERES_NO_DESEADOS.put("\u00D3", "O");
		CARACTERES_NO_DESEADOS.put("\u00DA", "U");
		CARACTERES_NO_DESEADOS.put("\u00D1", "N");
		CARACTERES_NO_DESEADOS.put("\u00E1", "a");
		CARACTERES_NO_DESEADOS.put("\u00E9", "e");
		CARACTERES_NO_DESEADOS.put("\u00ED", "i");
		CARACTERES_NO_DESEADOS.put("\u00F3", "o");
		CARACTERES_NO_DESEADOS.put("\u00FA", "u");
		CARACTERES_NO_DESEADOS.put("\u00F1", "n");
		CARACTERES_NO_DESEADOS.put(" ", "_");

	}

	private static final String PATRON_ALPHANUMERICO = "^[a-zA-Z0-9]+$";

	private static final String PATRON_NUMERICO = "^[0-9]+$";

	/**
	 * Calcula la edad dada una fecha de nacimiento.
	 * 
	 * @param fechaNac
	 * @return edad
	 */
	public static int calcularEdad(final Date fechaNac) {

		Calendar hoy = Calendar.getInstance();
		Calendar nacimiento = Calendar.getInstance();

		hoy.setTime(new Date());
		nacimiento.setTime(fechaNac);

		int diffAnio = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
		int diffMes = hoy.get(Calendar.MONTH) - nacimiento.get(Calendar.MONTH);
		int diffDia = hoy.get(Calendar.DAY_OF_MONTH)
				- nacimiento.get(Calendar.DAY_OF_MONTH);

		// Si esta en ese a&#241;o pero todavia no los ha cumplido
		if ((diffMes < 0) || ((diffMes == 0) && (diffDia < 0))) {
			diffAnio = diffAnio - 1;
		}
		return diffAnio;
	}

	public static int compararAnio(final Date f1, final Date f2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(f1);
		c2.setTime(f2);
		return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
	}

	/**
	 * Convierte un String a Fecha dado el patron.
	 * 
	 * @param strFecha
	 * @param strPatron
	 * 
	 * @return the date
	 * 
	 * @throws ConversionException
	 * @throws ParseException
	 */
	public static Date convertirFecha(final String strFecha,
			final String strPatron) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(strPatron);
		return dateFormat.parse(strFecha);
	}

	/**
	 * Metodo que cambia un Date a String.
	 * 
	 * @param date
	 *            the date
	 * 
	 * @return the string
	 */
	public static String convertirTexto(final Date fecha, final String strPatron) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(strPatron);
		return dateFormat.format(fecha);
	}

	/**
	 * Incluir aqui­ la descripcion del metodo.
	 * 
	 * @param fuente
	 * @param destino
	 * @throws IOException
	 */

	public static void copy(final File fuente, final File destino)
			throws IOException {
		InputStream in = new FileInputStream(fuente);
		OutputStream out = new FileOutputStream(destino);

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	/**
	 * Elimina un objeto de una lista.
	 * 
	 * @param <T>
	 * @param lista
	 * @param objeto
	 * @return T
	 */
	public static <T> T eliminarObjetoDeLista(final List<T> lista,
			final T objeto) {
		int index = 0;
		for (T t : lista) {
			if (t.equals(objeto)) {
				break;
			}
			index++;
		}

		return lista.remove(index);
	}

	public static String generaNumeroAleatorio() {
		return generaNumeroAleatorio(10);
	}

	/**
	 * Incluir aqui­ la descripcion del metodo.
	 * 
	 * @param numChars
	 * @return
	 */

	public static String generaNumeroAleatorio(final int numChars) {
		char[] buf = new char[numChars];

		for (int i = 0; i < buf.length; i++) {

			buf[i] = numeros.charAt(r.nextInt(numeros.length()));

		}

		return new String(buf);
	}

	/**
	 * Save image to disk
	 * 
	 * @param contents
	 * @param fileName
	 * @param pathRepositorio
	 * @throws IOException
	 */
	public static void saveImage(final byte[] contents, final String fileName, final String pathRepositorio)
			throws IOException {

			BufferedImage img = ImageIO.read(new ByteArrayInputStream(contents));
			File outputFile = new File(fileName);
			ImageIO.write(img, "PNG", outputFile);

	}
	
	/**
	 * Incluir aqui­ la descripcion del metodo.
	 * 
	 * @param archivo
	 * @param nombreArchivo
	 * @param pathRepositorio
	 * @return
	 * @throws IOException
	 */

	public static String guardarArchivo(final File archivo,
			String nombreArchivo, final String pathRepositorio)
			throws IOException {

		// elimina C:/ del nombre de archivo
		int idx = nombreArchivo.indexOf("C:/");
		if (idx != -1) {
			nombreArchivo = new StringBuilder()
					.append(nombreArchivo.substring(0, idx))
					.append(nombreArchivo.substring(idx + 3)).toString();
		}

		StringBuffer sb = new StringBuffer();
		sb.append(pathRepositorio).append(File.separator);
		new File(sb.toString()).mkdirs();

		sb.append(nombreArchivo);

		File destino = new File(sb.toString());

		if (destino.exists()) {
			// BORRAR
			destino.delete();
			// destino.createNewFile();
		} else {
			// crear nuevo archivo vacio, solo logicamente, sin contenido
			destino.createNewFile();
		}

		// Copiar el contenido
		copy(archivo, destino);
		String pathArchivo = destino.getAbsolutePath();

		return Utils.replace("\\\\", "/", pathArchivo);

	}

	
	
	/**
	 * Representa la funcion LPAD de base de datos, que agrega caracteres a un
	 * string dado, con un size maximo.
	 * 
	 * @param valueToPad
	 *            the value to pad
	 * @param filler
	 *            the filler
	 * @param size
	 *            the size
	 * 
	 * @return the string
	 */
	public static String lpad(final String valueToPad, final String filler,
			final int size) {
		StringBuilder builder = new StringBuilder();

		while (builder.length() + valueToPad.length() < size) {
			builder.append(filler);
		}
		builder.append(valueToPad);
		return builder.toString();
	}

	
	
	/**
	 * Incluir aqui­ la descripcion del metodo.
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */

	public static double obtenerNumeroDeDiasEntreDosFechas(
			final Date fechaInicial, final Date fechaFinal) {

		return Math.floor((fechaFinal.getTime() - fechaInicial.getTime())
				/ (1000 * 60 * 60 * 24));
	}

	public static String removerCaracteresNoDeseados(String cadenaOriginal) {
		for (String key : Utils.CARACTERES_NO_DESEADOS.keySet()) {
			cadenaOriginal = replace(key, CARACTERES_NO_DESEADOS.get(key),
					cadenaOriginal);
		}
		cadenaOriginal = replace("[^\\\\a-zA-Z_0-9/._-]", "_", cadenaOriginal);
		return cadenaOriginal;
	}

	/**
	 * Se encarga de reemplazar las coincidencias de una cadena buscada por otra
	 * cadena
	 * 
	 * @param cadenaBuscada
	 * @param reemplazarConEsta
	 * @param cadenaOriginal
	 * @return
	 */
	public static String replace(final String cadenaBuscada,
			final String reemplazarConEsta, final String cadenaOriginal) {
		Pattern pattern = Pattern.compile(cadenaBuscada);
		Matcher matcher = pattern.matcher(cadenaOriginal);
		String output = matcher.replaceAll(reemplazarConEsta);
		return output;
	}

	/**
	 * Representa la funcion RPAD de base de datos, que agrega caracteres a un
	 * string dado, con un size maximo.
	 * 
	 * @param valueToPad
	 *            the value to pad
	 * @param filler
	 *            the filler
	 * @param size
	 *            the size
	 * 
	 * @return the string
	 */
	public static String rpad(final String valueToPad, final String filler,
			final int size) {
		StringBuilder builder = new StringBuilder();
		builder.append(valueToPad);

		while (builder.length() < size) {
			builder.append(filler);
		}
		return builder.toString();
	}

	/**
	 * Suma dias a una fecha
	 * 
	 * @param dias
	 * @param fecha
	 * @return fechaSumada
	 */
	public static Date sumarDiasAFecha(final int dias, final Date fecha) {

		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH) + dias);
		return c.getTime();
	}

	/**
	 * Transformar a frase.
	 * 
	 * @param texto
	 * 
	 * @return the string
	 */
	public static String transformarAFrase(final String texto) {
		String cadena = "";
		Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]*");
		Matcher matcher = pattern.matcher(texto);
		while (matcher.find()) {
			cadena = cadena.concat(" ").concat(matcher.group());
		}
		return cadena.trim();
	}

	/**
	 * Trunca la hora, minutos y segundos de una fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * 
	 * @return the date
	 */
	public static Date truncarFecha(final Date fecha) {
		if (fecha != null) {
			Calendar fechaTruncada = Calendar.getInstance();
			fechaTruncada.setTimeInMillis(fecha.getTime());
			fechaTruncada.set(Calendar.HOUR, 0);
			fechaTruncada.set(Calendar.MINUTE, 0);
			fechaTruncada.set(Calendar.SECOND, 0);
			fechaTruncada.set(Calendar.MILLISECOND, 0);
			fechaTruncada.set(Calendar.AM_PM, Calendar.AM);
			return fechaTruncada.getTime();
		}
		return null;
	}

	/**
	 * Truncar texto.
	 * 
	 * @param texto
	 * @param longitud
	 * 
	 * @return the string
	 */
	public static String truncarTexto(final String texto, final int longitud) {
		String textoTruncado = "";
		if (texto.length() > longitud) {
			textoTruncado = texto.substring(0, longitud);
		} else {
			textoTruncado = texto;
		}
		return textoTruncado;
	}

	/**
	 * Valida que la primera fecha sea anterior a la segunda
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return esCorrecto
	 */
	public static Boolean validarRangoFechas(final Date dateFrom,
			final Date dateTo) {
		Boolean esCorrecto = Boolean.FALSE;
		if (dateFrom.before(dateTo)) {
			esCorrecto = Boolean.TRUE;
		}

		return esCorrecto;
	}

	/**
	 * Valida que la primera fecha sea anterior a la segunda
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return esCorrecto
	 */
	public static Boolean validarRangoFechasMayorIgual(final Date dateFrom,
			final Date dateTo) {
		Boolean esCorrecto = Boolean.FALSE;
		if (dateFrom.compareTo(dateTo) >= 0) {
			esCorrecto = Boolean.TRUE;
		}

		return esCorrecto;
	}

	/**
	 * Validar cedula ruc.
	 * 
	 * @param identificacion
	 *            the identificacion
	 * @param longitud
	 *            the longitud
	 * 
	 * @return true, if successful
	 */
	public static boolean validarCedulaRuc(final String identificacion,
			final int longitud) {
		boolean error = false;

		if ((identificacion == null) || identificacion.equals("")) {
			return error;
		}
		if (!validarPorPatronNumerico(identificacion)) {
			error = true;
			return error;
		}
		final char[] arr = identificacion.toCharArray();
		if (arr.length != longitud) {
			error = true;
			return error;
		}
		if ((arr.length == 13)
				&& !identificacion.substring(10, 13).equals("001")) {
			error = true;
			return error;
		}
		int[] coeficientes = null;
		int indiceDigitoVerificador = 9;
		int modulo = 11;
		if (arr[2] == Character.valueOf('9')) {
			coeficientes = caso9;
		} else if (arr[2] == Character.valueOf('6')) {
			coeficientes = caso6;
			indiceDigitoVerificador = 8;
		} else if (arr[2] < Character.valueOf('6')) {
			coeficientes = cedul;
			modulo = 10;
		}
		return verificar(arr, coeficientes, indiceDigitoVerificador, modulo);
	}

	/**
	 * Verificar el ruc/cedula.
	 * 
	 * @param arr
	 *            the arr
	 * @param coeficientes
	 *            the coeficientes
	 * @param indiceDigitoVerificador
	 *            the indice digito verificador
	 * @param modulo
	 *            the modulo
	 * 
	 * @return true, if successful
	 */
	private static boolean verificar(final char[] arr,
			final int[] coeficientes, final int indiceDigitoVerificador,
			final int modulo) {
		boolean error = false;
		int sum = 0;
		int aux = 0;
		for (int i = 0; i < coeficientes.length; i++) {
			aux = new Integer(String.valueOf(arr[i])) * coeficientes[i];
			if ((modulo == 10) && (aux > 9)) {
				aux -= 9;
			}
			sum += aux;
		}
		int mod = sum % modulo;
		mod = mod == 0 ? modulo : mod;
		final int res = (modulo - mod);
		Integer valorVerificar = null;
		if (arr.length == 13) {
			valorVerificar = Integer.valueOf(String.valueOf(arr[arr.length
					- (13 - indiceDigitoVerificador)]));
		} else if (arr.length == 10) {
			valorVerificar = Integer.valueOf(String.valueOf(arr[arr.length
					- (10 - indiceDigitoVerificador)]));
		}
		if (res != valorVerificar.intValue()) {
			error = true;
		}
		return error;
	}

	/**
	 * Validar por patron.
	 * 
	 * @param patron
	 *            the patron
	 * @param valor
	 *            the valor
	 * 
	 * @return true, if successful
	 */
	public static boolean validarPorPatron(final String patron,
			final String valor) {
		final Pattern patter = Pattern.compile(patron);
		final Matcher matcher = patter.matcher(valor);
		return matcher.matches();
	}

	/**
	 * Validar por patron numerico.
	 * 
	 * @param valor
	 *            the valor
	 * 
	 * @return true, if successful
	 */
	public static boolean validarPorPatronNumerico(final String valor) {
		return validarPorPatron(PATRON_NUMERICO, valor);
	}

	/**
	 * Validar por patron alfa numerico.
	 * 
	 * @param valor
	 *            the valor
	 * 
	 * @return true, if successful
	 */
	public static boolean validarPorPatronAlfaNumerico(final String valor) {
		return validarPorPatron(PATRON_ALPHANUMERICO, valor);
	}

	/**
	 * This method checks if a String contains only numbers
	 */
	public static boolean containsOnlyNumbers(final String str) {

		// It can't contain only numbers if it's null or empty...
		if ((str == null) || (str.length() == 0)) {
			return false;
		}

		for (int i = 0; i < str.length(); i++) {

			// If we find a non-digit character we return false.
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Instancia un nuevo utils.
	 */
	private Utils() {

	}

	public static boolean validarMail(final String mail) {
		/** Create the correct mask */
		Pattern mask = null;

		mask = Pattern.compile(EMAIL_REGEX);

		/** Check to see if the value is an email */
		Matcher matcher = mask.matcher(mail);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}

	}

	public static Date setHourMinuteSecond(final Date date, final int hour,
			final int minute, final int second) {
		cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}

	public static BigDecimal roundDecimal(final BigDecimal value,
			final int decimal) {
		BigDecimal num = value;
		num = value.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		return num;
	}

	public static String stackTraceToString(final Throwable e) {
		java.io.StringWriter stringWriter = new java.io.StringWriter();
		java.io.PrintWriter printWriter = new java.io.PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}

//	/**
//	 * @param key
//	 * @param locale
//	 * @param arguments
//	 * @throws FileManagerException
//	 */
//	public static byte[] generarPdfReporteDesdeHtml(
//			final String contentPanelHtml,
//			final DireccionPaginaEnum direccionPaginaEnum)
//			throws FileManagerException {
//		FileManager fileManager = new FileManager();
//		return fileManager.generarPdfReporteDesdeHtml(contentPanelHtml,
//				DireccionPaginaEnum.LANDSCAPE);
//	}

	/**
	 * @param numero
	 * @return
	 */
	public static Long convertirALong(final String numero) {
		try {
			return Long.valueOf(numero);
		} catch (NumberFormatException e) {
			return 0L;
		}
	}

	/**
	 * @param numero
	 * @return
	 */
	public static Double convertirADouble(final String numero) {
		try {
			return Double.valueOf(numero);
		} catch (NumberFormatException e) {
			return 0D;
		}
	}

	/**
	 * @param numero
	 * @return
	 */
	public static Double convertirDeBigDecimalADouble(final BigDecimal numero) {
		try {
			return numero.doubleValue();
		} catch (NumberFormatException e) {
			return 0D;
		}
	}

}