package com.sino.soa.mis.srv.transassetdistribution.srv;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */


import com.sino.base.util.StrUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_transassetdistributionsrv.SBFIFATransAssetDistributionSrv;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_transassetdistributionsrv.SBFIFATransAssetDistributionSrvProcessRequest;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_transassetdistributionsrv.SBFIFATransAssetDistributionSrvProcessResponse;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_transassetdistributionsrv.SBFIFATransAssetDistributionSrv_Service;

import javax.xml.namespace.QName;
import java.net.URL;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue May 05 09:21:39 CST 2009
 * Generated source version: 2.1.4
 * 查询资产分配行信息_非TD(ODI)
 */

public final class TransAssetDistributionSrv {
    private SrvReturnMessage returnMessage = new SrvReturnMessage();
    private String envCode="";
    private String bookTypeCode = "";
    private String stratLastUpdateDate = "";
    private String endLastUpdateDate = "";
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_TransAssetDistributionSrv", "SB_FI_FA_TransAssetDistributionSrv");

    public TransAssetDistributionSrv() {
    }

    public void excute() throws Exception {
        URL wsdlURL = SBFIFATransAssetDistributionSrv_Service.WSDL_LOCATION;


        SBFIFATransAssetDistributionSrv_Service ss = new SBFIFATransAssetDistributionSrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFATransAssetDistributionSrv port = ss.getSBFIFATransAssetDistributionSrvPort();

        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

        httpClientPolicy.setConnectionTimeout(5400000);//连接时间
        httpClientPolicy.setReceiveTimeout(5400000);//接收时间
        httpClientPolicy.setAllowChunking(false);
        http.setClient(httpClientPolicy);

        {
            System.out.println("SB_FI_FA_TransAssetDistributionSrv Invoking process...");
            SBFIFATransAssetDistributionSrvProcessRequest _process_payload = null;
            _process_payload = new SBFIFATransAssetDistributionSrvProcessRequest();
            _process_payload.setENVCODE(getEnvCode());
            _process_payload.setBOOKTYPECODE(this.bookTypeCode);
            _process_payload.setENDLASTUPDATEDATE(XMLGregorianCalendarUtil.getXMLGregorianCalendar(this.endLastUpdateDate));
            _process_payload.setSTARTLASTUPDATEDATE(XMLGregorianCalendarUtil.getXMLGregorianCalendar(this.stratLastUpdateDate));
            SBFIFATransAssetDistributionSrvProcessResponse _process__return = port.process(_process_payload);
            System.out.println("process.result=" + _process__return.getINSTANCEID() + "||" + _process__return.getERRORFLAG() + "||" + _process__return.getERRORMESSAGE());
            returnMessage.setErrorFlag(StrUtil.nullToString(_process__return.getERRORFLAG()));
            returnMessage.setErrorMessage(_process__return.getERRORMESSAGE());
            System.out.println("是否成功：---------"+returnMessage.getErrorFlag());
            System.out.println("错误信息：---------"+returnMessage.getErrorMessage());
        }

    }

    public static void main(String[] args) throws Exception {
        TransAssetDistributionSrv srv = new TransAssetDistributionSrv();
        srv.setEnvCode("EAMS_TransAssetDistributionSrv");
        srv.setBookTypeCode("SXMC_FA_4110");
        //srv.setStratLastUpdateDate("");
        //srv.setEndLastUpdateDate(""); 
        srv.excute();
        System.exit(0);
    }

    /**
     * @return the returnMessage
     */
    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }

    /**
     * @param returnMessage the returnMessage to set
     */
    public void setReturnMessage(SrvReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getEnvCode() {
        return envCode;
    }

    public void setEnvCode(String envCode) {
        this.envCode = envCode;
    }

    /**
     * @return the bookTypeCode
     */
    public String getBookTypeCode() {
        return bookTypeCode;
    }

    /**
     * @param bookTypeCode the bookTypeCode to set
     */
    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }



    /**
     * @return the stratLastUpdateDate
     */
    public String getStratLastUpdateDate() {
        return stratLastUpdateDate;
    }

    /**
     * @param stratLastUpdateDate the stratLastUpdateDate to set
     */
    public void setStratLastUpdateDate(String stratLastUpdateDate) {
        this.stratLastUpdateDate = stratLastUpdateDate;
    }

    /**
     * @return the endLastUpdateDate
     */
    public String getEndLastUpdateDate() {
        return endLastUpdateDate;
    }

    /**
     * @param endLastUpdateDate the endLastUpdateDate to set
     */
    public void setEndLastUpdateDate(String endLastUpdateDate) {
        this.endLastUpdateDate = endLastUpdateDate;
    }


}