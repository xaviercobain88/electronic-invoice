package com.espaciolink.presentation.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import recepcion.ws.sri.gob.ec.Comprobante;
import recepcion.ws.sri.gob.ec.RecepcionComprobantes;
import recepcion.ws.sri.gob.ec.RecepcionComprobantesService;
import recepcion.ws.sri.gob.ec.RespuestaSolicitud;
import autorizacion.ws.sri.gob.ec.Autorizacion;
import autorizacion.ws.sri.gob.ec.AutorizacionComprobantes;
import autorizacion.ws.sri.gob.ec.AutorizacionComprobantesService;
import autorizacion.ws.sri.gob.ec.RespuestaComprobante;

import com.espaciolink.business.facade.ElectronicInvoiceFacade;
import com.espaciolink.business.facade.EnvelopedSignature;
import com.espaciolink.persistence.entity.InvoiceHeader;
import com.espaciolink.persistence.enums.PermissionNameEnum;
import com.espaciolink.persistence.enums.SRIStatusTypeEnum;
import com.espaciolink.persistence.exception.NotSaveException;
import com.espaciolink.security.AllowedPermission;
import com.espaciolink.security.Guard;
import com.espaciolink.security.enums.PageInformationEnum;
import com.espaciolink.util.Utils;

/**
 * @author xavier
 * 
 */
@Stateless
@Path("/electronic-invoice")
//@Interceptors(Guard.class)
public class ElectronicInvoiceResource {

	@EJB
	EnvelopedSignature envelopedSignature;
	
	@EJB
	ElectronicInvoiceFacade electronicInvoiceFacade;

	@POST
	@Consumes("*/*")
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{institutionId}/{claveAccesoComprobante}")
	@AllowedPermission(pageInformationEnum = PageInformationEnum.NEW, permissionNameEnum = PermissionNameEnum.POST_INVOICE)
	public String postInvoice(String xmlEncoded,
			@PathParam("institutionId") String institutionId,
			@PathParam("claveAccesoComprobante") String claveAccesoComprobante) {

		try {
			String xml = Utils.decode(xmlEncoded);
			Utils.createFile(xml, EnvelopedSignature.getResourceToSign(), "/home/xavier/");
			envelopedSignature.sign();
			java.nio.file.Path path = Paths
					.get("/home/xavier/"+EnvelopedSignature.getSignFileName());

			byte[] data = Files.readAllBytes(path);
			RespuestaSolicitud respuestaSolicitud = validarComprobante(data);
			List<Comprobante> comprobantes = respuestaSolicitud
					.getComprobantes().getComprobante();
			System.out.println("--------------------------------------");
			System.out.println(comprobantes.size());
			System.out.println(respuestaSolicitud.getEstado());
				
			electronicInvoiceFacade.create(respuestaSolicitud.getEstado(), claveAccesoComprobante);
			

			
		} catch (IOException ex) {
			Logger.getLogger(ElectronicInvoiceResource.class.getName()).log(
					Level.SEVERE, null, ex);
			return "[\"claveAccesoComprobante\":"+claveAccesoComprobante+",\"estado\":"+SRIStatusTypeEnum.NO_RECIBIDA+"]";
		} 
		catch (NotSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(ElectronicInvoiceResource.class.getName()).log(
					Level.SEVERE, null, e);
			return "[\"claveAccesoComprobante\":"+claveAccesoComprobante+",\"estado\":"+SRIStatusTypeEnum.NO_RECIBIDA+"]";
		}

		return "[\"claveAccesoComprobante\":"+claveAccesoComprobante+",\"estado\":"+SRIStatusTypeEnum.RECIBIDA+"]";
	}

	/**
	 * Rest client method which returns the authorization for the invoice
	 * 
	 * @param institutionId
	 * @param claveAccesoComprobante
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{institutionId}/{claveAccesoComprobante}")
	@AllowedPermission(pageInformationEnum = PageInformationEnum.NEW, permissionNameEnum = PermissionNameEnum.GET_INVOICE)
	public String getAutorization(
			@PathParam("institutionId") String institutionId,
			@PathParam("claveAccesoComprobante") String claveAccesoComprobante) {
		InvoiceHeader invoiceHeader= new InvoiceHeader();
		RespuestaComprobante respuestaComprobante;
		respuestaComprobante = autorizacionComprobante(claveAccesoComprobante);
		List<Autorizacion> autorizaciones = respuestaComprobante
				.getAutorizaciones().getAutorizacion();
		for (Autorizacion autorizacion : autorizaciones) {
			System.out.println("*********************************");
			System.out.println(autorizacion.getClaveAcceso());
			System.out.println(autorizacion.getEstado());
			System.out.println(autorizacion.getNumeroAutorizacion());
			System.out.println("*********************************");
			try {
				invoiceHeader =electronicInvoiceFacade.actualizarAutorizacion(respuestaComprobante.getClaveAccesoConsultada(), autorizacion.getEstado(), autorizacion.getNumeroAutorizacion());
				if(invoiceHeader!=null){
					return "[\"claveAccesoComprobante\":"+claveAccesoComprobante+",\"estado\":"+SRIStatusTypeEnum.AUTORIZADO+",\"autorizacion\":"+invoiceHeader.getNumeroAutorizacion()+"]";
				}
			} catch (NotSaveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return "[\"claveAccesoComprobante\":"+claveAccesoComprobante+",\"estado\":"+SRIStatusTypeEnum.NO_AUTORIZADO+"]";
		
	}

	/**
	 * This method calls the web service RecepcionComprobantesService
	 * 
	 * @param xml
	 * @return
	 */
	private RespuestaSolicitud validarComprobante(byte[] xml) {
		RecepcionComprobantesService service = new RecepcionComprobantesService();
		RecepcionComprobantes port = service.getRecepcionComprobantesPort();
		return port.validarComprobante(xml);
	}

	/**
	 * This method calls the web service AutorizacionComprobantesService
	 * 
	 * @param claveAccesoComprobante
	 * @return
	 */
	private RespuestaComprobante autorizacionComprobante(
			String claveAccesoComprobante) {

		AutorizacionComprobantesService service = new AutorizacionComprobantesService();
		AutorizacionComprobantes port = service
				.getAutorizacionComprobantesPort();
		return port.autorizacionComprobante(claveAccesoComprobante);
	}

}