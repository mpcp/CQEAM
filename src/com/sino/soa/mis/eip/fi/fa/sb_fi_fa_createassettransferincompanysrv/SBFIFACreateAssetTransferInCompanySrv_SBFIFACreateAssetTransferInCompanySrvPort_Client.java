
package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_createassettransferincompanysrv;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 2.1.4
 * Sun Oct 16 22:16:18 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBFIFACreateAssetTransferInCompanySrv_SBFIFACreateAssetTransferInCompanySrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/common/fa/SB_FI_FA_CreateAssetTransferInCompanySrv", "SB_FI_FA_CreateAssetTransferInCompanySrv");

    private SBFIFACreateAssetTransferInCompanySrv_SBFIFACreateAssetTransferInCompanySrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFACreateAssetTransferInCompanySrv_Service.WSDL_LOCATION;
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
      
        SBFIFACreateAssetTransferInCompanySrv_Service ss = new SBFIFACreateAssetTransferInCompanySrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFACreateAssetTransferInCompanySrv port = ss.getSBFIFACreateAssetTransferInCompanySrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_createassettransferincompanysrv.createassettransferincompanysrv.CreateAssetTransferInCompanySrvRequest _process_payload = null;
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_createassettransferincompanysrv.importsrvresponse.ImportSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}
