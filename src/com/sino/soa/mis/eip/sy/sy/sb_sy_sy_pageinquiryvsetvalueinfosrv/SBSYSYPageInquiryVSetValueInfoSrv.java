package com.sino.soa.mis.eip.sy.sy.sb_sy_sy_pageinquiryvsetvalueinfosrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Wed Sep 14 10:46:57 CST 2011
 * Generated source version: 2.1.4
 * 
 */
 
@WebService(targetNamespace = "http://eip.zte.com/common/sy/SB_SY_SY_PageInquiryVSetValueInfoSrv", name = "SB_SY_SY_PageInquiryVSetValueInfoSrv")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBSYSYPageInquiryVSetValueInfoSrv {

    @WebResult(name = "PageInquiryVSetValueInfoSrvResponse", targetNamespace = "http://eip.zte.com/common/sy/SB_SY_SY_PageInquiryVSetValueInfoSrv", partName = "payload")
    @WebMethod(action = "process")
    public PageInquiryVSetValueInfoSrvResponse process(
        @WebParam(partName = "payload", name = "PageInquiryVSetValueInfoSrvRequest", targetNamespace = "http://eip.zte.com/common/sy/SB_SY_SY_PageInquiryVSetValueInfoSrv")
        PageInquiryVSetValueInfoSrvRequest payload
    );
}
