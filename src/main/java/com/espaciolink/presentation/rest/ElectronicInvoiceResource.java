package com.espaciolink.presentation.rest;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espaciolink.business.facade.EnvelopedSignature;
import com.espaciolink.persistence.enums.PermissionNameEnum;
import com.espaciolink.security.AllowedPermission;
import com.espaciolink.security.Guard;
import com.espaciolink.security.enums.PageInformationEnum;

@Stateless
@Path("/generate-electronic-invoice")
@Interceptors(Guard.class)
public class ElectronicInvoiceResource {

    @EJB
    EnvelopedSignature envelopedSignature;

   

    @POST
    @Consumes("*/*")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{institutionId}")
    @AllowedPermission(pageInformationEnum= PageInformationEnum.NEW, permissionNameEnum= PermissionNameEnum.GENERATE_PDF_INVOICE)
    public String postInvoice(String invoiceHeader, @PathParam("institutionId") String institutionId) {
    	envelopedSignature.sign();
        return institutionId+" "+invoiceHeader;
    }
}