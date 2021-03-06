
package com.sino.soa.mis.eip.sc.po.sb_sc_po_inquiryvendorinfosrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VendorSiteInfoCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VendorSiteInfoCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VENDOR_SITE_INFO_ITME" type="{http://xmlns.oracle.com/SB_SC_PO_InquiryVendorInfoSrv}VendorSiteInfoItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VendorSiteInfoCollection", propOrder = {
    "vendorsiteinfoitme"
})
public class VendorSiteInfoCollection {

    @XmlElement(name = "VENDOR_SITE_INFO_ITME")
    protected List<VendorSiteInfoItem> vendorsiteinfoitme;

    /**
     * Gets the value of the vendorsiteinfoitme property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vendorsiteinfoitme property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVENDORSITEINFOITME().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VendorSiteInfoItem }
     * 
     * 
     */
    public List<VendorSiteInfoItem> getVENDORSITEINFOITME() {
        if (vendorsiteinfoitme == null) {
            vendorsiteinfoitme = new ArrayList<VendorSiteInfoItem>();
        }
        return this.vendorsiteinfoitme;
    }

}
