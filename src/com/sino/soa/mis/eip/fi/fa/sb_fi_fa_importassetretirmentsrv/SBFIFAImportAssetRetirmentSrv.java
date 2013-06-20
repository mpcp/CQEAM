package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_importassetretirmentsrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Wed Sep 07 18:48:07 CST 2011
 * Generated source version: 2.1.4
 * DATE: 2011-09-08 
 * Name: wangzhipeng
 * DSC: 资产报废接口(2011-09-08)_写入
 */
 
@WebService(targetNamespace = "http://eip.zte.com/SB_FI_FA_ImportAssetRetirmentSrv", name = "SB_FI_FA_ImportAssetRetirmentSrv")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBFIFAImportAssetRetirmentSrv {

    @WebResult(name = "ImportAssetRetirmentSrvResponse", targetNamespace = "http://eip.zte.com/SB_FI_FA_ImportAssetRetirmentSrv", partName = "payload")
    @WebMethod(action = "process")
    public ImportAssetRetirmentSrvResponse process(
        @WebParam(partName = "payload", name = "ImportAssetRetirmentSrvRequest", targetNamespace = "http://eip.zte.com/SB_FI_FA_ImportAssetRetirmentSrv")
        ImportAssetRetirmentSrvRequest payload
    );
}
