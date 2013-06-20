
/*
 * 
 */

package com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv;

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
 * Thu Sep 08 09:33:26 CST 2011
 * Generated source version: 2.1.4
 * date:2011-09-08
 * DSC:查询资产地点接口_TD(service)
 */


@WebServiceClient(name = "SB_FI_FA_TDImportAssetRetirmentSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDImportAssetRetirmentSrv/1.0/SB_FI_FA_TDImportAssetRetirmentSrv?wsdl",
                  targetNamespace = "http://eip.zte.com/SB_FI_FA_ImportAssetRetirmentSrv") 
public class SBFIFATDImportAssetRetirmentSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/SB_FI_FA_ImportAssetRetirmentSrv", "SB_FI_FA_TDImportAssetRetirmentSrv");
    public final static QName SBFIFAImportAssetRetirmentSrvPort = new QName("http://eip.zte.com/SB_FI_FA_ImportAssetRetirmentSrv", "SB_FI_FA_ImportAssetRetirmentSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDImportAssetRetirmentSrv/1.0/SB_FI_FA_TDImportAssetRetirmentSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from "+"http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDImportAssetRetirmentSrv/1.0/SB_FI_FA_TDImportAssetRetirmentSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBFIFATDImportAssetRetirmentSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBFIFATDImportAssetRetirmentSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBFIFATDImportAssetRetirmentSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBFIFAImportAssetRetirmentSrv
     */
    @WebEndpoint(name = "SB_FI_FA_ImportAssetRetirmentSrvPort")
    public SBFIFAImportAssetRetirmentSrv getSBFIFAImportAssetRetirmentSrvPort() {
        return super.getPort(SBFIFAImportAssetRetirmentSrvPort, SBFIFAImportAssetRetirmentSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBFIFAImportAssetRetirmentSrv
     */
    @WebEndpoint(name = "SB_FI_FA_ImportAssetRetirmentSrvPort")
    public SBFIFAImportAssetRetirmentSrv getSBFIFAImportAssetRetirmentSrvPort(WebServiceFeature... features) {
        return super.getPort(SBFIFAImportAssetRetirmentSrvPort, SBFIFAImportAssetRetirmentSrv.class, features);
    }

}
