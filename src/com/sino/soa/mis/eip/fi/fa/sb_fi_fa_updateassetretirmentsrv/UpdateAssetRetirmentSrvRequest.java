
package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MsgHeader" type="{http://eip.zte.com/SB_FI_FA_UpdateAssetRetirmentSrv}MsgHeader"/>
 *         &lt;element name="UpdateAssetRetirmentSrvInputCollection" type="{http://eip.zte.com/SB_FI_FA_UpdateAssetRetirmentSrv}UpdateAssetRetirmentSrvInputCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msgHeader",
    "updateAssetRetirmentSrvInputCollection"
})
@XmlRootElement(name = "UpdateAssetRetirmentSrvRequest")
public class UpdateAssetRetirmentSrvRequest {

    @XmlElement(name = "MsgHeader", required = true)
    protected MsgHeader msgHeader;
    @XmlElement(name = "UpdateAssetRetirmentSrvInputCollection", required = true)
    protected UpdateAssetRetirmentSrvInputCollection updateAssetRetirmentSrvInputCollection;

    /**
     * Gets the value of the msgHeader property.
     * 
     * @return
     *     possible object is
     *     {@link MsgHeader }
     *     
     */
    public MsgHeader getMsgHeader() {
        return msgHeader;
    }

    /**
     * Sets the value of the msgHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link MsgHeader }
     *     
     */
    public void setMsgHeader(MsgHeader value) {
        this.msgHeader = value;
    }

    /**
     * Gets the value of the updateAssetRetirmentSrvInputCollection property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateAssetRetirmentSrvInputCollection }
     *     
     */
    public UpdateAssetRetirmentSrvInputCollection getUpdateAssetRetirmentSrvInputCollection() {
        return updateAssetRetirmentSrvInputCollection;
    }

    /**
     * Sets the value of the updateAssetRetirmentSrvInputCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateAssetRetirmentSrvInputCollection }
     *     
     */
    public void setUpdateAssetRetirmentSrvInputCollection(UpdateAssetRetirmentSrvInputCollection value) {
        this.updateAssetRetirmentSrvInputCollection = value;
    }

}