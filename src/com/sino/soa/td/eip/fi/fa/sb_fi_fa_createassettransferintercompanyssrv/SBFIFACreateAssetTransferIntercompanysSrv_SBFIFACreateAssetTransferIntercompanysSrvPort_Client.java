
package com.sino.soa.td.eip.fi.fa.sb_fi_fa_createassettransferintercompanyssrv;

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
 * Thu Sep 15 17:43:08 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBFIFACreateAssetTransferIntercompanysSrv_SBFIFACreateAssetTransferIntercompanysSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/common/fa/SB_FI_FA_CreateAssetTransferIntercompanysSrv", "SB_FI_FA_TDCreateAssetTransferIntercompanysSrv");

    private SBFIFACreateAssetTransferIntercompanysSrv_SBFIFACreateAssetTransferIntercompanysSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFATDCreateAssetTransferIntercompanysSrv.WSDL_LOCATION;
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
      
        SBFIFATDCreateAssetTransferIntercompanysSrv ss = new SBFIFATDCreateAssetTransferIntercompanysSrv(wsdlURL, SERVICE_NAME);
        SBFIFACreateAssetTransferIntercompanysSrv port = ss.getSBFIFACreateAssetTransferIntercompanysSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.td.eip.fi.fa.sb_fi_fa_createassettransferintercompanyssrv.createassettransferintercompanyssrv.CreateAssetTransferIntercompanysSrvRequest _process_payload = null;
        com.sino.soa.td.eip.fi.fa.sb_fi_fa_createassettransferintercompanyssrv.importsrvresponse.ImportSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}