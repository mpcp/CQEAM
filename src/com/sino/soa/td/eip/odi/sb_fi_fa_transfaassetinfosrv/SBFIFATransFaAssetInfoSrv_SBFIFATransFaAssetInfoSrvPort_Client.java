
package com.sino.soa.td.eip.odi.sb_fi_fa_transfaassetinfosrv;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Fri Feb 17 16:43:19 CST 2012
 * Generated source version: 2.1.4
 * 
 */

public final class SBFIFATransFaAssetInfoSrv_SBFIFATransFaAssetInfoSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_TransFaAssetInfoSrv", "SB_FI_FA_TDTransFaAssetInfoSrv");

    private SBFIFATransFaAssetInfoSrv_SBFIFATransFaAssetInfoSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFATDTransFaAssetInfoSrv.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        SBFIFATDTransFaAssetInfoSrv ss = new SBFIFATDTransFaAssetInfoSrv(wsdlURL, SERVICE_NAME);
        SBFIFATransFaAssetInfoSrv port = ss.getSBFIFATransFaAssetInfoSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.td.eip.odi.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrvProcessRequest _process_payload = null;
        com.sino.soa.td.eip.odi.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrvProcessResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}
