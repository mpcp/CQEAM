package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetdistributionsrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Fri Oct 14 14:37:55 CST 2011
 * Generated source version: 2.1.4
 * 
 */
 
@WebService(targetNamespace = "http://eip.zte.com/SB_FI_FA_PageInquiryAssetDistributionSrv", name = "SB_FI_FA_PageInquiryAssetDistributionSrv")
@XmlSeeAlso({com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetdistributionsrv.msgheader.ObjectFactory.class,ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBFIFAPageInquiryAssetDistributionSrv {

    @WebResult(name = "PageInquiryAssetDistributionSrvResponse", targetNamespace = "http://eip.zte.com/SB_FI_FA_PageInquiryAssetDistributionSrv", partName = "payload")
    @WebMethod(action = "process")
    public PageInquiryAssetDistributionSrvResponse process(
        @WebParam(partName = "payload", name = "PageInquiryAssetDistributionSrvRequest", targetNamespace = "http://eip.zte.com/SB_FI_FA_PageInquiryAssetDistributionSrv")
        PageInquiryAssetDistributionSrvRequest payload
    );
}
