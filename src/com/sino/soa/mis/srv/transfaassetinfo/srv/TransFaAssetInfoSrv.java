package com.sino.soa.mis.srv.transfaassetinfo.srv;

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
//import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrv_Service;         
//import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrv;                  
//import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrvProcessRequest;    
//import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrvProcessResponse;   
import com.sino.soa.mis.eip.odi.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrv_Service;         
import com.sino.soa.mis.eip.odi.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrv;                  
import com.sino.soa.mis.eip.odi.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrvProcessRequest;    
import com.sino.soa.mis.eip.odi.sb_fi_fa_transfaassetinfosrv.SBFIFATransFaAssetInfoSrvProcessResponse;
import javax.xml.namespace.QName;
import java.net.URL;

/**
 * This class was generated by Apache CXF 2.1.4
 *  2011-10-12
 * Generated source version: 2.1.4
 * ��ѯ�ʲ�������Ϣ����(ODI) 
 *  02.17 ���(SOA���������� �ʲ��˲�����)
 */

public final class TransFaAssetInfoSrv {
    private SrvReturnMessage returnMessage = new SrvReturnMessage();
    private String envCode="";          //��������
    private String periodName = "";     //����ڼ� PERIOD_NAME
    private String bookTypeCode="";     //�ʲ��˲�
    
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_TransFaAssetInfoSrv", "SB_FI_FA_TransFaAssetInfoSrv");
    public TransFaAssetInfoSrv() {
    }

    public void excute() throws Exception {
    
        URL wsdlURL = SBFIFATransFaAssetInfoSrv_Service.WSDL_LOCATION;
        SBFIFATransFaAssetInfoSrv_Service ss = new SBFIFATransFaAssetInfoSrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFATransFaAssetInfoSrv port = ss.getSBFIFATransFaAssetInfoSrvPort();     

        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

        httpClientPolicy.setConnectionTimeout(1000000000);//����ʱ��
        httpClientPolicy.setReceiveTimeout(1000000000);//����ʱ��
        httpClientPolicy.setAllowChunking(false);
        http.setClient(httpClientPolicy);

        {
            System.out.println("SB_FI_FA_TransFaAssetInfoSrv Invoking process...");
            SBFIFATransFaAssetInfoSrvProcessRequest _process_payload = null;
            _process_payload = new SBFIFATransFaAssetInfoSrvProcessRequest();
            _process_payload.setENVCODE(getEnvCode());
            _process_payload.setPERIODNAME(this.periodName);
            _process_payload.setBOOKTYPECODE(this.bookTypeCode);
            SBFIFATransFaAssetInfoSrvProcessResponse _process__return = port.process(_process_payload);
            System.out.println("process.result=" + _process__return.getINSTANCEID() + "||" + _process__return.getERRORFLAG() + "||" + _process__return.getERRORMESSAGE());
            returnMessage.setErrorFlag(StrUtil.nullToString(_process__return.getERRORFLAG()));
            returnMessage.setErrorMessage(_process__return.getERRORMESSAGE());
            System.out.println("�Ƿ�ɹ���---------" + returnMessage.getErrorFlag());
            System.out.println("������Ϣ��---------" + returnMessage.getErrorMessage());
        }
    }

    public static void main(String[] args) throws Exception {
    	TransFaAssetInfoSrv srv = new TransFaAssetInfoSrv();
         srv.setEnvCode("EAMS_TransFaAssetInfoSrv");   //EAMS_TransFaAssetInfoSrv
         srv.setPeriodName("OCB-11");           //OCB-11 
        srv.excute();
    }

	public String getEnvCode() {
		return envCode;
	}

	public void setEnvCode(String envCode) {
		this.envCode = envCode;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public SrvReturnMessage getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(SrvReturnMessage returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getBookTypeCode() {
		return bookTypeCode;
	}

	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}



}