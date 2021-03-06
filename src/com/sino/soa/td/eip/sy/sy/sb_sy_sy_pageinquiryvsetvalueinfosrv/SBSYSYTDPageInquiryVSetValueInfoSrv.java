
/*
 * 
 */

package com.sino.soa.td.eip.sy.sy.sb_sy_sy_pageinquiryvsetvalueinfosrv;

import com.sino.soa.common.SOAConstant;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.1.4
 * Wed Sep 14 14:22:51 CST 2011
 * Generated source version: 2.1.4
 * 
 */


@WebServiceClient(name = "SB_SY_SY_TDPageInquiryVSetValueInfoSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_SY_SY_TDPageInquiryVSetValueInfoSrv/2.0/SB_SY_SY_TDPageInquiryVSetValueInfoSrv?wsdl",
                  targetNamespace = "http://eip.zte.com/common/sy/SB_SY_SY_PageInquiryVSetValueInfoSrv") 
public class SBSYSYTDPageInquiryVSetValueInfoSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/common/sy/SB_SY_SY_PageInquiryVSetValueInfoSrv", "SB_SY_SY_TDPageInquiryVSetValueInfoSrv");
    public final static QName SBSYSYPageInquiryVSetValueInfoSrvPort = new QName("http://eip.zte.com/common/sy/SB_SY_SY_PageInquiryVSetValueInfoSrv", "SB_SY_SY_PageInquiryVSetValueInfoSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_SY_SY_TDPageInquiryVSetValueInfoSrv/2.0/SB_SY_SY_TDPageInquiryVSetValueInfoSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_SY_SY_TDPageInquiryVSetValueInfoSrv/2.0/SB_SY_SY_TDPageInquiryVSetValueInfoSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBSYSYTDPageInquiryVSetValueInfoSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBSYSYTDPageInquiryVSetValueInfoSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBSYSYTDPageInquiryVSetValueInfoSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBSYSYPageInquiryVSetValueInfoSrv
     */
    @WebEndpoint(name = "SB_SY_SY_PageInquiryVSetValueInfoSrvPort")
    public SBSYSYPageInquiryVSetValueInfoSrv getSBSYSYPageInquiryVSetValueInfoSrvPort() {
        return super.getPort(SBSYSYPageInquiryVSetValueInfoSrvPort, SBSYSYPageInquiryVSetValueInfoSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBSYSYPageInquiryVSetValueInfoSrv
     */
    @WebEndpoint(name = "SB_SY_SY_PageInquiryVSetValueInfoSrvPort")
    public SBSYSYPageInquiryVSetValueInfoSrv getSBSYSYPageInquiryVSetValueInfoSrvPort(WebServiceFeature... features) {
        return super.getPort(SBSYSYPageInquiryVSetValueInfoSrvPort, SBSYSYPageInquiryVSetValueInfoSrv.class, features);
    }

}
