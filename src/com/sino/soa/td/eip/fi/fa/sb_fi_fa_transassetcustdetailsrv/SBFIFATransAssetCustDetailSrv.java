package com.sino.soa.td.eip.fi.fa.sb_fi_fa_transassetcustdetailsrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Thu Oct 13 17:32:54 CST 2011
 * Generated source version: 2.1.4
 * 
 */
 
@WebService(targetNamespace = "http://eip.zte.com/fi/SB_FI_FA_TransAssetCustDetailSrv", name = "SB_FI_FA_TransAssetCustDetailSrv")
@XmlSeeAlso({com.sino.soa.td.eip.fi.fa.sb_fi_fa_transassetcustdetailsrv.odi_variables.ObjectFactory.class,ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBFIFATransAssetCustDetailSrv {

    @WebResult(name = "SB_FI_FA_TransAssetCustDetailSrvProcessResponse", targetNamespace = "http://eip.zte.com/fi/SB_FI_FA_TransAssetCustDetailSrv", partName = "payload")
    @WebMethod(action = "process")
    public SBFIFATransAssetCustDetailSrvProcessResponse process(
        @WebParam(partName = "payload", name = "SB_FI_FA_TransAssetCustDetailSrvProcessRequest", targetNamespace = "http://eip.zte.com/fi/SB_FI_FA_TransAssetCustDetailSrv")
        SBFIFATransAssetCustDetailSrvProcessRequest payload
    );
}
