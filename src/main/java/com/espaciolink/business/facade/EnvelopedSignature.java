package com.espaciolink.business.facade;

import javax.ejb.Stateless;

import org.w3c.dom.Document;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.firmaJava.role.SimpleClaimedRole;
import es.mityc.javasign.EnumFormatoFirma;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;

/**
 * <p>
 * Clase de ejemplo para la firma XAdES-BES enveloped de un documento. El
 * ejemplo firmará el recurso indicado en la constante
 * <code>RESOURCE_TO_SIGN</code> y el nombre del fichero resultante será el
 * indicado por la constante <code>SIGN_FILE_NAME</code>.
 * </p>
 * <p>
 * Para realizar la firma se utilizará el almacén PKCS#12 definido en la
 * constante <code>GenericXMLSignature.PKCS12_FILE</code>, al que se accederá
 * mediante la password definida en la constante
 * <code>GenericXMLSignature.PKCS12_PASSWORD</code>. El directorio donde quedará
 * el archivo XML resultante será el indicado en al constante
 * <code>GenericXMLSignature.OUTPUT_DIRECTORY</code>
 * </p>
 * 
 */
@Stateless
public class EnvelopedSignature extends GenericXMLSignature {

	/**
	 * Recurso a firmar
	 */
//	private final static String RESOURCE_TO_SIGN = "com/espaciolink/business/facade/factura.xml";
	private final static String RESOURCE_TO_SIGN = "electronic-invoice.xml";

	/**
	 * <p>
	 * Fichero donde se desea guardar la firma
	 * </p>
	 */
	private final static String SIGN_FILE_NAME = "signed-electronic-invoice.xml";

	/**
	 * <p>
	 * Punto de entrada al programa
	 * </p>
	 * 
	 * @param args
	 *            Argumentos del programa
	 */
	public void sign() {
		EnvelopedSignature signature = new EnvelopedSignature();
		signature.execute();

	}

	@Override
	protected DataToSign createDataToSign() {
		DataToSign dataToSign = new DataToSign();
		dataToSign.setXadesFormat(EnumFormatoFirma.XAdES_BES);
		dataToSign.setEsquema(XAdESSchemas.XAdES_132);
		dataToSign.setXMLEncoding("UTF-8");
		dataToSign.addClaimedRol(new SimpleClaimedRole("Rol de firma"));
		dataToSign.setEnveloped(true);
		Document docToSign = getDocument(RESOURCE_TO_SIGN);
		dataToSign.setDocument(docToSign);
		
		dataToSign.addObject(new ObjectToSign(new InternObjectToSign("comprobante"),
				"Documento de ejemplo", null , "text/xml", null));
		dataToSign.setParentSignNode("comprobante");
		return dataToSign;
	}

	@Override
	protected String getSignatureFileName() {
		return SIGN_FILE_NAME;
	}

	public static String getResourceToSign() {
		return RESOURCE_TO_SIGN;
	}

	public static String getSignFileName() {
		return SIGN_FILE_NAME;
	}
	
	
	
}
