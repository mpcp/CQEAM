package com.sino.soa.td.eip.sc.pa.sb_sc_po_inquiryvendorinfosrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Wed Oct 12 21:09:08 CST 2011
 * Generated source version: 2.1.4
 * 
 */
 
@WebService(targetNamespace = "http://xmlns.oracle.com/SB_SC_PO_InquiryVendorInfoSrv", name = "SB_SC_PO_InquiryVendorInfoSrv")
@XmlSeeAlso({com.sino.soa.td.eip.sc.pa.sb_sc_po_inquiryvendorinfosrv.msgheader.ObjectFactory.class,ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBSCPOInquiryVendorInfoSrv {

    @WebResult(name = "InquiryVendorInfoSrvResponse", targetNamespace = "http://xmlns.oracle.com/SB_SC_PO_InquiryVendorInfoSrv", partName = "payload")
    @WebMethod(action = "process")
    public InquiryVendorInfoSrvResponse process(
        @WebParam(partName = "payload", name = "InquiryVendorInfoSrvRequest", targetNamespace = "http://xmlns.oracle.com/SB_SC_PO_InquiryVendorInfoSrv")
        InquiryVendorInfoSrvRequest payload
    );
}
