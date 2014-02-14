/*
 */
package com.espaciolink.business.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.espaciolink.persistence.dao.ElectronicInvoiceDao;
import com.espaciolink.persistence.entity.InvoiceHeader;
import com.espaciolink.persistence.enums.SRIStatusTypeEnum;
import com.espaciolink.persistence.exception.NotFoundException;
import com.espaciolink.persistence.exception.NotSaveException;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ElectronicInvoiceFacade {

	@EJB
	ElectronicInvoiceDao electronicInvoiceDao;

	/**
	 * @param roleId
	 * @return
	 * @throws NotFoundException
	 */
	public InvoiceHeader create(String sriStatusTypeString,
			String claveAccesoComprobante) throws NotSaveException {

		List<InvoiceHeader> list = new ArrayList<InvoiceHeader>();
		Boolean grabarNueva = true;
		try {
			list = electronicInvoiceDao
					.findByClaveAccesoComprobante(claveAccesoComprobante);
			if (list.size() > 0) {
				grabarNueva = false;
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
		}

		if (sriStatusTypeString.equals(SRIStatusTypeEnum.RECIBIDA.getName())
				&& grabarNueva) {
			InvoiceHeader invoiceHeader = new InvoiceHeader();
			invoiceHeader.setPermissionNameEnum(SRIStatusTypeEnum.RECIBIDA);
			invoiceHeader.setClaveAccesoComprobante(claveAccesoComprobante);
			return electronicInvoiceDao.createInvoiceHeader(invoiceHeader);
		}
		return null;

	}
	
	/**
	 * @param claveAccesoComprobante
	 * @param estado
	 * @param autorizacion
	 * @return
	 * @throws NotSaveException
	 */
	public InvoiceHeader actualizarAutorizacion(String claveAccesoComprobante, String estado, String autorizacion) throws NotSaveException {
		List<InvoiceHeader> list = new ArrayList<InvoiceHeader>();
		if(estado.equals(SRIStatusTypeEnum.AUTORIZADO.getName())){
			try {
				list = electronicInvoiceDao
						.findByClaveAccesoComprobante(claveAccesoComprobante);
				System.out.println(list.size());
				if (list.size() > 0) {
					System.out.println("si entra");
					InvoiceHeader invoiceHeader = list.get(0);
					invoiceHeader.setNumeroAutorizacion(autorizacion);
					invoiceHeader.setPermissionNameEnum(SRIStatusTypeEnum.AUTORIZADO);
					electronicInvoiceDao.updateInvoiceHeader(invoiceHeader);
					
					return invoiceHeader;
					
				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
		
		

	}

}
