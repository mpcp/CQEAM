
package com.sino.soa.mis.eip.sc.pa.sb_sc_pa_pageinquirytaskinfosrv;

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
 * Fri Oct 14 14:39:19 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBSCPAPageInquiryTaskInfoSrv_SBSCPAPageInquiryTaskInfoSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SC/SB_SC_PA_PageInquiryTaskInfoSrv", "SB_SC_PA_PageInquiryTaskInfoSrv");

    private SBSCPAPageInquiryTaskInfoSrv_SBSCPAPageInquiryTaskInfoSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBSCPAPageInquiryTaskInfoSrv_Service.WSDL_LOCATION;
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
      
        SBSCPAPageInquiryTaskInfoSrv_Service ss = new SBSCPAPageInquiryTaskInfoSrv_Service(wsdlURL, SERVICE_NAME);
        SBSCPAPageInquiryTaskInfoSrv port = ss.getSBSCPAPageInquiryTaskInfoSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.mis.eip.sc.pa.sb_sc_pa_pageinquirytaskinfosrv.PageInquiryTaskInfoSrvRequest _process_payload = null;
        com.sino.soa.mis.eip.sc.pa.sb_sc_pa_pageinquirytaskinfosrv.PageInquiryTaskInfoSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}
