
/*
 * 
 */

package com.sino.soa.td.eip.fi.fa.sb_fi_fa_transassetheaderinfosrv;

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
 * Mon Oct 10 20:48:55 CST 2011
 * Generated source version: 2.1.4
 * 查询资产头基本信息_TD(ODI) 
 */


@WebServiceClient(name = "SB_FI_FA_TDTransAssetHeaderInfoSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/odi/SB_FI_FA_TDTransAssetHeaderInfoSrv/1.0/SB_FI_FA_TDTransAssetHeaderInfoSrv?wsdl",
                  targetNamespace = "http://eip.zte.com/fi/SB_FI_FA_TransAssetHeaderInfoSrv") 
public class SBFIFATDTransAssetHeaderInfoSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/fi/SB_FI_FA_TransAssetHeaderInfoSrv", "SB_FI_FA_TDTransAssetHeaderInfoSrv");
    public final static QName SBFIFATransAssetHeaderInfoSrvPort = new QName("http://eip.zte.com/fi/SB_FI_FA_TransAssetHeaderInfoSrv", "SB_FI_FA_TransAssetHeaderInfoSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/odi/SB_FI_FA_TDTransAssetHeaderInfoSrv/1.0/SB_FI_FA_TDTransAssetHeaderInfoSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from "+"http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/odi/SB_FI_FA_TDTransAssetHeaderInfoSrv/1.0/SB_FI_FA_TDTransAssetHeaderInfoSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBFIFATDTransAssetHeaderInfoSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBFIFATDTransAssetHeaderInfoSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBFIFATDTransAssetHeaderInfoSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBFIFATransAssetHeaderInfoSrv
     */
    @WebEndpoint(name = "SB_FI_FA_TransAssetHeaderInfoSrvPort")
    public SBFIFATransAssetHeaderInfoSrv getSBFIFATransAssetHeaderInfoSrvPort() {
        return super.getPort(SBFIFATransAssetHeaderInfoSrvPort, SBFIFATransAssetHeaderInfoSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBFIFATransAssetHeaderInfoSrv
     */
    @WebEndpoint(name = "SB_FI_FA_TransAssetHeaderInfoSrvPort")
    public SBFIFATransAssetHeaderInfoSrv getSBFIFATransAssetHeaderInfoSrvPort(WebServiceFeature... features) {
        return super.getPort(SBFIFATransAssetHeaderInfoSrvPort, SBFIFATransAssetHeaderInfoSrv.class, features);
    }

}
