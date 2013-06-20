package com.sino.soa.td.srv.assetretire.srv;

import com.sino.base.util.StrUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_transretiredassetdetailsrv.SBFIFATransRetiredAssetDetailSrv;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_transretiredassetdetailsrv.SBFIFATransRetiredAssetDetailSrvProcessRequest;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_transretiredassetdetailsrv.SBFIFATransRetiredAssetDetailSrvProcessResponse;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_transretiredassetdetailsrv.SBFIFATDTransRetiredAssetDetailSrv;

import javax.xml.namespace.QName;
import java.net.URL;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue Aug 18 17:51:35 CST 2009
 * Generated source version: 2.1.4 
 * Function:报废资产信息读取_TD（ODI）  
 */

public final class TDTransRetiredAssetDetailSrv {
    private SrvReturnMessage returnMessage = new SrvReturnMessage();

    private String envCode = "";
    private String bookTypeCode = "";
    private String startRetireDate = "";
    private String endRetireDate = "";
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_TransRetiredAssetDetailSrv", "SB_FI_FA_TDTransRetiredAssetDetailSrv");

    public TDTransRetiredAssetDetailSrv() {
    }

    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }

    public String getEnvCode() {
        return envCode;
    }

    public void setEnvCode(String envCode) {
        this.envCode = envCode;
    }

    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    public String getStartRetireDate() {
        return startRetireDate;
    }

    public void setStartRetireDate(String startRetireDate) {
        this.startRetireDate = startRetireDate;
    }

    public String getEndRetireDate() {
        return endRetireDate;
    }

    public void setEndRetireDate(String endRetireDate) {
        this.endRetireDate = endRetireDate;
    }

    public void excute() throws Exception {
        URL wsdlURL = SBFIFATDTransRetiredAssetDetailSrv.WSDL_LOCATION;


        SBFIFATDTransRetiredAssetDetailSrv ss = new SBFIFATDTransRetiredAssetDetailSrv(wsdlURL, SERVICE_NAME);
        SBFIFATransRetiredAssetDetailSrv port = ss.getSBFIFATransRetiredAssetDetailSrvPort();

        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

        httpClientPolicy.setConnectionTimeout(5400000);//连接时间
        httpClientPolicy.setReceiveTimeout(5400000);//接收时间
        httpClientPolicy.setAllowChunking(false);
        http.setClient(httpClientPolicy);

        {
            System.out.println("SB_FI_FA_TransRetiredAssetDetailSrv Invoking process...");
            SBFIFATransRetiredAssetDetailSrvProcessRequest _process_payload = null;
            _process_payload = new SBFIFATransRetiredAssetDetailSrvProcessRequest();
            _process_payload.setBOOKTYPECODE(bookTypeCode);
            _process_payload.setENVCODE(envCode);
            _process_payload.setSTARTRETIREDATE(XMLGregorianCalendarUtil.getXMLGregorianCalendar(startRetireDate));
            _process_payload.setENDRETIREDATE(XMLGregorianCalendarUtil.getXMLGregorianCalendar(endRetireDate));
            SBFIFATransRetiredAssetDetailSrvProcessResponse _process__return = port.process(_process_payload);
            returnMessage.setErrorFlag(StrUtil.nullToString(_process__return.getERRORFLAG()));
            returnMessage.setErrorMessage(_process__return.getERRORMESSAGE());
            System.out.println("是否成功：---------" + returnMessage.getErrorFlag());
            System.out.println("错误信息：---------" + returnMessage.getErrorMessage());
        }
    }

    public static void main(String args[]) throws Exception {
        TDTransRetiredAssetDetailSrv srv = new TDTransRetiredAssetDetailSrv();
        srv.setEnvCode("SX_ZC_TransRetiredAssetDetailSrv");
        srv.setBookTypeCode("SXMC_FA_4188");
        srv.setStartRetireDate("2008-03-01");
        srv.setEndRetireDate("2008-08-01");
        srv.excute();
    }

}