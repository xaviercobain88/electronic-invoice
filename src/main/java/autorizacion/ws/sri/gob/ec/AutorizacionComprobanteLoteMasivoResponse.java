
package autorizacion.ws.sri.gob.ec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for autorizacionComprobanteLoteMasivoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="autorizacionComprobanteLoteMasivoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RespuestaAutorizacionLoteMasivo" type="{http://ec.gob.sri.ws.autorizacion}respuestaLote" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autorizacionComprobanteLoteMasivoResponse", propOrder = {
    "respuestaAutorizacionLoteMasivo"
})
public class AutorizacionComprobanteLoteMasivoResponse {

    @XmlElement(name = "RespuestaAutorizacionLoteMasivo")
    protected RespuestaLote respuestaAutorizacionLoteMasivo;

    /**
     * Gets the value of the respuestaAutorizacionLoteMasivo property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaLote }
     *     
     */
    public RespuestaLote getRespuestaAutorizacionLoteMasivo() {
        return respuestaAutorizacionLoteMasivo;
    }

    /**
     * Sets the value of the respuestaAutorizacionLoteMasivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaLote }
     *     
     */
    public void setRespuestaAutorizacionLoteMasivo(RespuestaLote value) {
        this.respuestaAutorizacionLoteMasivo = value;
    }

}
