
/*
 * 
 */

package com.sino.soa.td.eip.fi.gl.sb_fi_gl_pageinquiryaccountbalancesrv;

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
 * Sat Oct 15 20:04:24 CST 2011
 * Generated source version: 2.1.4
 * 
 */


@WebServiceClient(name = "SB_FI_GL_TDPageInquiryAccountBalanceSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_GL_TDPageInquiryAccountBalanceSrv/3.0/SB_FI_GL_TDPageInquiryAccountBalanceSrv?wsdl",
                  targetNamespace = "http://mss.cmcc.com/SB_FI_GL_PageInquiryAccountBalanceSrv") 
public class SBFIGLTDPageInquiryAccountBalanceSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://mss.cmcc.com/SB_FI_GL_PageInquiryAccountBalanceSrv", "SB_FI_GL_TDPageInquiryAccountBalanceSrv");
    public final static QName SBFIGLPageInquiryAccountBalanceSrvPort = new QName("http://mss.cmcc.com/SB_FI_GL_PageInquiryAccountBalanceSrv", "SB_FI_GL_PageInquiryAccountBalanceSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_GL_TDPageInquiryAccountBalanceSrv/3.0/SB_FI_GL_TDPageInquiryAccountBalanceSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_GL_TDPageInquiryAccountBalanceSrv/3.0/SB_FI_GL_TDPageInquiryAccountBalanceSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBFIGLTDPageInquiryAccountBalanceSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBFIGLTDPageInquiryAccountBalanceSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBFIGLTDPageInquiryAccountBalanceSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBFIGLPageInquiryAccountBalanceSrv
     */
    @WebEndpoint(name = "SB_FI_GL_PageInquiryAccountBalanceSrvPort")
    public SBFIGLPageInquiryAccountBalanceSrv getSBFIGLPageInquiryAccountBalanceSrvPort() {
        return super.getPort(SBFIGLPageInquiryAccountBalanceSrvPort, SBFIGLPageInquiryAccountBalanceSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBFIGLPageInquiryAccountBalanceSrv
     */
    @WebEndpoint(name = "SB_FI_GL_PageInquiryAccountBalanceSrvPort")
    public SBFIGLPageInquiryAccountBalanceSrv getSBFIGLPageInquiryAccountBalanceSrvPort(WebServiceFeature... features) {
        return super.getPort(SBFIGLPageInquiryAccountBalanceSrvPort, SBFIGLPageInquiryAccountBalanceSrv.class, features);
    }

}
