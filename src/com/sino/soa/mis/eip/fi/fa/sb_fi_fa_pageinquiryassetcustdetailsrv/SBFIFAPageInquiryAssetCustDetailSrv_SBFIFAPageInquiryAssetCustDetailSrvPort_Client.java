
package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetcustdetailsrv;

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
 * Sat Oct 15 21:03:21 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBFIFAPageInquiryAssetCustDetailSrv_SBFIFAPageInquiryAssetCustDetailSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetCustDetailSrv", "SB_FI_FA_PageInquiryAssetCustDetailSrv");

    private SBFIFAPageInquiryAssetCustDetailSrv_SBFIFAPageInquiryAssetCustDetailSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFAPageInquiryAssetCustDetailSrv_Service.WSDL_LOCATION;
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
      
        SBFIFAPageInquiryAssetCustDetailSrv_Service ss = new SBFIFAPageInquiryAssetCustDetailSrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFAPageInquiryAssetCustDetailSrv port = ss.getSBFIFAPageInquiryAssetCustDetailSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetcustdetailsrv.PageInquiryAssetCustDetailSrvRequest _process_payload = null;
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetcustdetailsrv.PageInquiryAssetCustDetailSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}
