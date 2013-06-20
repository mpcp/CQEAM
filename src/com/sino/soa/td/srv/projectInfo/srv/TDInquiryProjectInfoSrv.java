package com.sino.soa.td.srv.projectInfo.srv;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DTOException;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.util.StrUtil;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.td.eip.sc.pa.sb_sc_pa_pageinquiryprojectinfosrv.*;
import com.sino.soa.td.eip.sc.pa.sb_sc_pa_pageinquiryprojectinfosrv.msgheader.MsgHeader;
import com.sino.soa.td.srv.projectInfo.dto.TDSrvProjectInfoDTO;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * This class was generated by Apache CXF 2.1.4
 * Thu Apr 23 16:39:12 CST 2009
 * Generated source version: 2.1.4
 * DES: 查询项目信息服务(接口实现类)_TD
 */ 

public final class TDInquiryProjectInfoSrv {
    private SrvReturnMessage returnMessage = new SrvReturnMessage();
    private String segment1 = "";
    private String projectClass = "";
    private String projectType = "";
    private String orgName = "";
    private String orgCode="";
    private String projectStstus = "";
    private int currentpage = -1 ;
    private DTOSet ds = new DTOSet();

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SC/SB_SC_PA_PageInquiryProjectInfoSrv", "SB_SC_PA_TDPageInquiryProjectInfoSrv");

    public TDInquiryProjectInfoSrv() {
    }

    public void execute() throws CalendarException, DTOException {
        URL wsdlURL =SBSCPATDPageInquiryProjectInfoSrv.WSDL_LOCATION;
        //URL wsdlURL = SBFIFAPageInquiryAssetCategorySrv_Service.WSDL_LOCATION;
        SBSCPATDPageInquiryProjectInfoSrv ss = new SBSCPATDPageInquiryProjectInfoSrv(wsdlURL, SERVICE_NAME);
        SBSCPAPageInquiryProjectInfoSrv port = ss.getSBSCPAPageInquiryProjectInfoSrvPort();
	        Client client = ClientProxy.getClient(port);
	        HTTPConduit http = (HTTPConduit) client.getConduit();
	        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
	        httpClientPolicy.setConnectionTimeout(5400000);//连接时间
	        httpClientPolicy.setReceiveTimeout(5400000);//接收时间
	        httpClientPolicy.setAllowChunking(false);
	        http.setClient(httpClientPolicy);
        System.out.println("SB_SC_PA_TDPageInquiryProjectInfoSrv Invoking process...");
        PageInquiryProjectInfoSrvRequest _process_payload = null;
        _process_payload = new PageInquiryProjectInfoSrvRequest();
        
        MsgHeader msgHeader = new MsgHeader();         
	        msgHeader.setSOURCESYSTEMID("AMS");
	        msgHeader.setSOURCESYSTEMNAME("AMS");
	        msgHeader.setUSERID("ADMIN");
	        msgHeader.setUSERNAME("ADMIN");
	        msgHeader.setPAGESIZE(BigDecimal.valueOf(Long.parseLong("1000")));
            msgHeader.setCURRENTPAGE(BigDecimal.valueOf(currentpage));
            msgHeader.setTOTALRECORD(BigDecimal.valueOf(Long.parseLong("-1")));
          _process_payload.setORGCODE(orgCode);    //OU代码
          _process_payload.setSEGMENT1(segment1);  //项目编号

         _process_payload.setMsgHeader(msgHeader);

         PageInquiryProjectInfoSrvResponse _process__return = port.process(_process_payload);  //返回结果response
        returnMessage.setErrorFlag(StrUtil.nullToString(_process__return.getErrorFlag()));
        returnMessage.setErrorMessage(_process__return.getErrorMessage());
        System.out.println("totalRecord = " + _process__return.getTOTALRECORD()+"  页数："+_process__return.getTOTALPAGE());

        if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
            BigDecimal totalRecord = _process__return.getTOTALRECORD();
            BigDecimal pageSize = new BigDecimal(1000);   
            int totalPage = _process__return.getTOTALPAGE().intValue();
            for (int i = 1; i < totalPage + 1; i++) {
                msgHeader = new MsgHeader();
                msgHeader.setPAGESIZE(pageSize);
                msgHeader.setTOTALRECORD(totalRecord);
                msgHeader.setCURRENTPAGE(new BigDecimal(i));
                _process_payload = new PageInquiryProjectInfoSrvRequest();
                _process_payload.setORGCODE(orgCode);
                _process_payload.setSEGMENT1(segment1);
                _process_payload.setTEMPLATEFLAG("N");
                _process_payload.setPROJECTCLASS(projectClass);
                _process_payload.setPROJECTTYPE(projectType);
                _process_payload.setSTARTDATE(null);
                _process_payload.setCOMPLETIONDATE(null);
                _process_payload.setCREATIONDATE(null);
                _process_payload.setPROJECTSTATUSCODE(projectStstus);
                _process_payload.setMsgHeader(msgHeader);
                _process__return = port.process(_process_payload);
                if (_process__return.getErrorFlag().equalsIgnoreCase("Y")) {
                    List<PageInquiryProjectInfoSrvOutputItem> list = _process__return.getPageInquiryProjectInfoSrvOutputColection().getPageInquiryProjectInfoSrvOutputItem();
                    for (int j = 0; j < list.size(); j++) {
                    	TDSrvProjectInfoDTO dto = new TDSrvProjectInfoDTO();
                        PageInquiryProjectInfoSrvOutputItem item = list.get(j);
                        //dto.setProjectId(item.getPROJECTID().toString()); 
                        dto.setName(item.getNAME());
                        dto.setSegment1(item.getSEGMENT1());
                        dto.setDescription(item.getDESCRIPTION());
                        dto.setProjectClass(item.getPROJECTCLASS());
                        dto.setProjectManager(item.getPROJECTMANAGER());
                        dto.setProjectType(item.getPROJECTTYPE());
                        dto.setProjectStatusCode(item.getPROJECTSTATUSCODE());
                        dto.setStartDate(XMLGregorianCalendarUtil.getSimpleCalendarString(item.getSTARTDATE()));
                        dto.setCompletionDate(XMLGregorianCalendarUtil.getSimpleCalendarString(item.getCOMPLETIONDATE()));
                        dto.setPmProjectReference(item.getPMPROJECTREFERENCE());
                        dto.setPmProductCode(item.getPMPRODUCTCODE());
                        dto.setProjectClass(item.getPROJECTCLASS());
                        dto.setMisProjectId(String.valueOf(item.getPROJECTID()));
                        dto.setLastUpdateDate(XMLGregorianCalendarUtil.getSimpleCalendarString(item.getLASTUPDATEDATE()));
                        dto.setDescription(item.getDESCRIPTION());
                        dto.setProjectManager(item.getPROJECTMANAGER());
                        dto.setOrganizationId(new AdvancedNumber(item.getORGID()));
                        dto.setEnabledFlag("Y");
                        dto.setCreationDate(XMLGregorianCalendarUtil.getSimpleCalendarString(item.getCREATIONDATE()));
                        dto.setLastUpdateDate(XMLGregorianCalendarUtil.getSimpleCalendarString(item.getLASTUPDATEDATE()));
                        ds.addDTO(dto);
                    }
                }
            }
        }
    }

    public String toString() {
        String s = null;
        for (int i = 0; i < ds.getSize(); i++) {
        	TDSrvProjectInfoDTO dto = (TDSrvProjectInfoDTO) ds.getDTO(i);
            s = s + "dto:" + dto.getOrgCode() + "  TypeName:" + dto.getOrganizationId() + " SEGMENT1:" + dto.getSegment1() +" NAME:"+ dto.getName()  +"\n";
        }
        return s;
    } 
    
    public static void main(String args[]) throws Exception {
       TDInquiryProjectInfoSrv srv = new TDInquiryProjectInfoSrv();
              srv.setOrgCode("8110");    //查询
            //srv.setOrgName("OU_山西省公司");
            //srv.setProjectType("");
             // srv.setSegment1("L0910001");
            srv.execute();
        System.out.println(" " + srv.toString());
       
    }
    
    
/*    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }*/

    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }    
    
/*    public void setReturnMessage(SrvReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }*/

    public DTOSet getDs() {
        return ds;
    }

    /**
     * @param segment1 the segment1 to set
     */
    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    /**
     * @param projectClass the projectClass to set
     */
    public void setProjectClass(String projectClass) {
        this.projectClass = projectClass;
    }

    /**
     * @param projectType the projectType to set
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @param projectStstus the projectStstus to set
     */
    public void setProjectStstus(String projectStstus) {
        this.projectStstus = projectStstus;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

 

}