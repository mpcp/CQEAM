package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Mon Sep 26 14:43:30 CST 2011
 * Generated source version: 2.1.4
 * 资产基本信息修改_接口
 */
 
@WebService(targetNamespace = "http://eip.zte.com/SB_FI_FA_UpdateAssetRetirmentSrv", name = "SB_FI_FA_UpdateAssetRetirmentSrv")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBFIFAUpdateAssetRetirmentSrv {

    @WebResult(name = "UpdateAssetRetirmentSrvResponse", targetNamespace = "http://eip.zte.com/SB_FI_FA_UpdateAssetRetirmentSrv", partName = "payload")
    @WebMethod(action = "process")
    public UpdateAssetRetirmentSrvResponse process(
        @WebParam(partName = "payload", name = "UpdateAssetRetirmentSrvRequest", targetNamespace = "http://eip.zte.com/SB_FI_FA_UpdateAssetRetirmentSrv")
        UpdateAssetRetirmentSrvRequest payload
    );
}
