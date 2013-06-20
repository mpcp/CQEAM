
/*
 * 
 */

package com.sino.soa.td.eip.fi.fa.sb_fi_fa_pageinquiryassetcustdetailsrv;

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
 * Sun Oct 16 16:21:50 CST 2011
 * Generated source version: 2.1.4
 * 
 */


@WebServiceClient(name = "SB_FI_FA_TDPageInquiryAssetCustDetailSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDPageInquiryAssetCustDetailSrv/1.0/SB_FI_FA_TDPageInquiryAssetCustDetailSrv?wsdl",
                  targetNamespace = "http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetCustDetailSrv") 
public class SBFIFATDPageInquiryAssetCustDetailSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetCustDetailSrv", "SB_FI_FA_TDPageInquiryAssetCustDetailSrv");
    public final static QName SBFIFAPageInquiryAssetCustDetailSrvPort = new QName("http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetCustDetailSrv", "SB_FI_FA_PageInquiryAssetCustDetailSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDPageInquiryAssetCustDetailSrv/1.0/SB_FI_FA_TDPageInquiryAssetCustDetailSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDPageInquiryAssetCustDetailSrv/1.0/SB_FI_FA_TDPageInquiryAssetCustDetailSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBFIFATDPageInquiryAssetCustDetailSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBFIFATDPageInquiryAssetCustDetailSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBFIFATDPageInquiryAssetCustDetailSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBFIFAPageInquiryAssetCustDetailSrv
     */
    @WebEndpoint(name = "SB_FI_FA_PageInquiryAssetCustDetailSrvPort")
    public SBFIFAPageInquiryAssetCustDetailSrv getSBFIFAPageInquiryAssetCustDetailSrvPort() {
        return super.getPort(SBFIFAPageInquiryAssetCustDetailSrvPort, SBFIFAPageInquiryAssetCustDetailSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBFIFAPageInquiryAssetCustDetailSrv
     */
    @WebEndpoint(name = "SB_FI_FA_PageInquiryAssetCustDetailSrvPort")
    public SBFIFAPageInquiryAssetCustDetailSrv getSBFIFAPageInquiryAssetCustDetailSrvPort(WebServiceFeature... features) {
        return super.getPort(SBFIFAPageInquiryAssetCustDetailSrvPort, SBFIFAPageInquiryAssetCustDetailSrv.class, features);
    }

}
