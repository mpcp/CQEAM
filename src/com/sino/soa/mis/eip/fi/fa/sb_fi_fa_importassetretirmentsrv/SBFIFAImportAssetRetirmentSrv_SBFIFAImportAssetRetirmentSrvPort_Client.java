
package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_importassetretirmentsrv;

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
 * Wed Sep 07 18:48:07 CST 2011
 * Generated source version: 2.1.4
 * DSC:�ʲ����Ͻӿ�(2011-09-08)
 */

public final class SBFIFAImportAssetRetirmentSrv_SBFIFAImportAssetRetirmentSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SB_FI_FA_ImportAssetRetirmentSrv", "SB_FI_FA_ImportAssetRetirmentSrv");

    private SBFIFAImportAssetRetirmentSrv_SBFIFAImportAssetRetirmentSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFAImportAssetRetirmentSrv_Service.WSDL_LOCATION;
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
      
        SBFIFAImportAssetRetirmentSrv_Service ss = new SBFIFAImportAssetRetirmentSrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFAImportAssetRetirmentSrv port = ss.getSBFIFAImportAssetRetirmentSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvRequest _process_payload = null;
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}