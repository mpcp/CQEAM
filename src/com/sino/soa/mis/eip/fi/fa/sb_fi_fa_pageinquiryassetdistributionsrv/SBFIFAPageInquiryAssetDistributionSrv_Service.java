
/*
 * 
 */

package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetdistributionsrv;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

import com.sino.soa.common.SOAConstant;
 
/**
 * This class was generated by Apache CXF 2.1.4
 * Fri Oct 14 14:37:55 CST 2011
 * Generated source version: 2.1.4
 * ��ѯ�ʲ���������Ϣ����ҳ��
 */


@WebServiceClient(name = "SB_FI_FA_PageInquiryAssetDistributionSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/fi/SB_FI_FA_PageInquiryAssetDistributionSrv/2.0/SB_FI_FA_PageInquiryAssetDistributionSrv?wsdl",
                  targetNamespace = "http://eip.zte.com/SB_FI_FA_PageInquiryAssetDistributionSrv") 
public class SBFIFAPageInquiryAssetDistributionSrv_Service extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/SB_FI_FA_PageInquiryAssetDistributionSrv", "SB_FI_FA_PageInquiryAssetDistributionSrv");
    public final static QName SBFIFAPageInquiryAssetDistributionSrvPort = new QName("http://eip.zte.com/SB_FI_FA_PageInquiryAssetDistributionSrv", "SB_FI_FA_PageInquiryAssetDistributionSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/fi/SB_FI_FA_PageInquiryAssetDistributionSrv/2.0/SB_FI_FA_PageInquiryAssetDistributionSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from "+"http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/fi/SB_FI_FA_PageInquiryAssetDistributionSrv/2.0/SB_FI_FA_PageInquiryAssetDistributionSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBFIFAPageInquiryAssetDistributionSrv_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBFIFAPageInquiryAssetDistributionSrv_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBFIFAPageInquiryAssetDistributionSrv_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBFIFAPageInquiryAssetDistributionSrv
     */
    @WebEndpoint(name = "SB_FI_FA_PageInquiryAssetDistributionSrvPort")
    public SBFIFAPageInquiryAssetDistributionSrv getSBFIFAPageInquiryAssetDistributionSrvPort() {
        return super.getPort(SBFIFAPageInquiryAssetDistributionSrvPort, SBFIFAPageInquiryAssetDistributionSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBFIFAPageInquiryAssetDistributionSrv
     */
    @WebEndpoint(name = "SB_FI_FA_PageInquiryAssetDistributionSrvPort")
    public SBFIFAPageInquiryAssetDistributionSrv getSBFIFAPageInquiryAssetDistributionSrvPort(WebServiceFeature... features) {
        return super.getPort(SBFIFAPageInquiryAssetDistributionSrvPort, SBFIFAPageInquiryAssetDistributionSrv.class, features);
    }

}