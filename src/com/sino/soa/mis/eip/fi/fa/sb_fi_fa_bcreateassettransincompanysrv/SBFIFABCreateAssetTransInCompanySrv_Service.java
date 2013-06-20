
/*
 * 
 */

package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_bcreateassettransincompanysrv;

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
 * Thu Sep 08 17:06:50 CST 2011
 * Generated source version: 2.1.4
 * 
 */


@WebServiceClient(name = "SB_FI_FA_BCreateAssetTransInCompanySrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/fi/SB_FI_FA_BCreateAssetTransInCompanySrv/1.0/SB_FI_FA_BCreateAssetTransInCompanySrv?wsdl",
                  targetNamespace = "http://eip.zte.com/SB_FI_FA_BCreateAssetTransInCompanySrv") 
public class SBFIFABCreateAssetTransInCompanySrv_Service extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/SB_FI_FA_BCreateAssetTransInCompanySrv", "SB_FI_FA_BCreateAssetTransInCompanySrv");
    public final static QName SBFIFABCreateAssetTransInCompanySrvPort = new QName("http://eip.zte.com/SB_FI_FA_BCreateAssetTransInCompanySrv", "SB_FI_FA_BCreateAssetTransInCompanySrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/fi/SB_FI_FA_BCreateAssetTransInCompanySrv/1.0/SB_FI_FA_BCreateAssetTransInCompanySrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/fi/SB_FI_FA_BCreateAssetTransInCompanySrv/1.0/SB_FI_FA_BCreateAssetTransInCompanySrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBFIFABCreateAssetTransInCompanySrv_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBFIFABCreateAssetTransInCompanySrv_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBFIFABCreateAssetTransInCompanySrv_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBFIFABCreateAssetTransInCompanySrv
     */
    @WebEndpoint(name = "SB_FI_FA_BCreateAssetTransInCompanySrvPort")
    public SBFIFABCreateAssetTransInCompanySrv getSBFIFABCreateAssetTransInCompanySrvPort() {
        return super.getPort(SBFIFABCreateAssetTransInCompanySrvPort, SBFIFABCreateAssetTransInCompanySrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBFIFABCreateAssetTransInCompanySrv
     */
    @WebEndpoint(name = "SB_FI_FA_BCreateAssetTransInCompanySrvPort")
    public SBFIFABCreateAssetTransInCompanySrv getSBFIFABCreateAssetTransInCompanySrvPort(WebServiceFeature... features) {
        return super.getPort(SBFIFABCreateAssetTransInCompanySrvPort, SBFIFABCreateAssetTransInCompanySrv.class, features);
    }

}
