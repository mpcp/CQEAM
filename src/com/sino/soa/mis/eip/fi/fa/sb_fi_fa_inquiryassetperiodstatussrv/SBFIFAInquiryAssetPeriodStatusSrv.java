package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_inquiryassetperiodstatussrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue Sep 06 19:16:40 CST 2011
 * Generated source version: 2.1.4
 * DATE:2011-09-08
 * DES: 资产会计状态接口 
 */ 
 
@WebService(targetNamespace = "http://eip.zte.com/common/fa/SB_FI_FA_InquiryAssetPeriodStatusSrv", name = "SB_FI_FA_InquiryAssetPeriodStatusSrv")
@XmlSeeAlso({com.sino.soa.mis.eip.fi.fa.sb_fi_fa_inquiryassetperiodstatussrv.msgheader.ObjectFactory.class, com.sino.soa.mis.eip.fi.fa.sb_fi_fa_inquiryassetperiodstatussrv.inquiryassetperiodstatussrv.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBFIFAInquiryAssetPeriodStatusSrv {

    @WebResult(name = "InquiryAssetPeriodStatusSrvResponse", targetNamespace = "http://eip.zte.com/common/InquiryAssetPeriodStatusSrv", partName = "payload")
    @WebMethod(action = "process")
    public com.sino.soa.mis.eip.fi.fa.sb_fi_fa_inquiryassetperiodstatussrv.inquiryassetperiodstatussrv.InquiryAssetPeriodStatusSrvResponse process(
        @WebParam(partName = "payload", name = "InquiryAssetPeriodStatusSrvRequest", targetNamespace = "http://eip.zte.com/common/InquiryAssetPeriodStatusSrv")
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_inquiryassetperiodstatussrv.inquiryassetperiodstatussrv.InquiryAssetPeriodStatusSrvRequest payload
    );
}
