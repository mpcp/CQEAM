package com.sino.soa.mis.srv.InquiryRetiredAssetDetail.srv;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.mis.srv.InquiryRetiredAssetDetail.dto.PageRetiredAssetDTO;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetdeprecationsrv.InquiryAssetDeprecationSrvRequest;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryretiredassetdetailsrv.SBFIFAPageInquiryRetiredAssetDetailSrv;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryretiredassetdetailsrv.SBFIFAPageInquiryRetiredAssetDetailSrv_Service;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryretiredassetdetailsrv.MsgHeader;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryretiredassetdetailsrv.PageInquiryRetiredAssetDetailSrvRequest;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryretiredassetdetailsrv.PageInquiryRetiredAssetDetailSrvResponse;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryretiredassetdetailsrv.PageInquiryRetiredAssetDetailSrvOutputItem;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue May 05 09:21:39 CST 2009
 * Generated source version: 2.1.4
 *  查询报废资产基本信息（分页）
 */

public final class PageInquiryRetiredAssetDetailSrv {
    private SrvReturnMessage returnMessage = new SrvReturnMessage();
    private DTOSet ds = new DTOSet();
    private String bookTypeCode = "";   //资产账簿
    private String localionDep = "";    //部门
    private String assetNumber = "";       
    private String tagNumber = "";      //资产标签号
    private String retirementTypeCode = "";
    private XMLGregorianCalendar dateRettredFrom = null;
    private XMLGregorianCalendar dateRettredTo = null;
    private XMLGregorianCalendar dateEffectiveFrom = null;
    private XMLGregorianCalendar dateEffectiveTo = null;
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_PageInquiryRetiredAssetDetailSrv", "SB_FI_FA_PageInquiryRetiredAssetDetailSrv");
    
    public PageInquiryRetiredAssetDetailSrv() throws DatatypeConfigurationException {

    }

    public void excute() throws Exception {
        URL wsdlURL = SBFIFAPageInquiryRetiredAssetDetailSrv_Service.WSDL_LOCATION;

        SBFIFAPageInquiryRetiredAssetDetailSrv_Service ss = new SBFIFAPageInquiryRetiredAssetDetailSrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFAPageInquiryRetiredAssetDetailSrv port = ss.getSBFIFAPageInquiryRetiredAssetDetailSrvPort();
	        Client client = ClientProxy.getClient(port);
	        HTTPConduit http = (HTTPConduit) client.getConduit();
	        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
	        httpClientPolicy.setConnectionTimeout(5400000);//连接时间
	        httpClientPolicy.setReceiveTimeout(5400000);//接收时间
	        httpClientPolicy.setAllowChunking(false);
	        http.setClient(httpClientPolicy);
        {
            System.out.println("PageInquiryRetiredAssetDetailSrv Invoking process...");
            PageInquiryRetiredAssetDetailSrvRequest _process_payload = null;
             MsgHeader msgHeader = new MsgHeader();
	            msgHeader.setSOURCESYSTEMID("EAM");
	            msgHeader.setSOURCESYSTEMNAME("EAM");
	            msgHeader.setUSERID("IBM");
	            msgHeader.setUSERNAME("IBM");
	            msgHeader.setPAGESIZE(new BigDecimal(1000));
	            msgHeader.setCURRENTPAGE(new BigDecimal(1));
	            msgHeader.setTOTALRECORD(new BigDecimal(-1));
	            _process_payload = new PageInquiryRetiredAssetDetailSrvRequest();
	            _process_payload.setBOOKTYPECODE(this.bookTypeCode);
	            _process_payload.setLOCATIONDEP(this.localionDep);
	            _process_payload.setASSETNUMBER(this.assetNumber);
	            _process_payload.setTAGNUMBER(this.tagNumber);
	            _process_payload.setDATEEFFECTIVEFROM(this.dateEffectiveFrom);
	            _process_payload.setDATEEFFECTIVETO(this.dateEffectiveTo);
	            _process_payload.setDATERETIREDFROM(this.dateRettredFrom);
	            _process_payload.setDATERETIREDTO(this.dateRettredTo);
                _process_payload.setMsgHeader(msgHeader);
            PageInquiryRetiredAssetDetailSrvResponse _process__return = port.process(_process_payload);
            returnMessage.setErrorFlag(StrUtil.nullToString(_process__return.getErrorFlag()));
            returnMessage.setErrorMessage(_process__return.getErrorMessage());
            System.out.println("总记录数="+_process__return.getTOTALRECORD()+" 总页数:"+_process__return.getTOTALPAGE()+" 每页记录数="+_process__return.getPAGESIZE());
        if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
            BigDecimal totalRecord = _process__return.getTOTALRECORD();
            BigDecimal pageSize = new BigDecimal(1000);
            int totalPage = _process__return.getTOTALPAGE().intValue();
            for (int i = 1; i < totalPage + 1; i++) {
                msgHeader = new MsgHeader();
	            msgHeader.setSOURCESYSTEMID("EAM");
	            msgHeader.setSOURCESYSTEMNAME("EAM");
	            msgHeader.setUSERID("IBM");
	            msgHeader.setUSERNAME("IBM");
                msgHeader.setPAGESIZE(pageSize);
                msgHeader.setTOTALRECORD(totalRecord);
                msgHeader.setCURRENTPAGE(new BigDecimal(i));
                _process_payload = new PageInquiryRetiredAssetDetailSrvRequest();
	            _process_payload.setBOOKTYPECODE(this.bookTypeCode);
	            _process_payload.setLOCATIONDEP(this.localionDep);
	            _process_payload.setASSETNUMBER(this.assetNumber);
	            _process_payload.setTAGNUMBER(this.tagNumber);
	            _process_payload.setDATEEFFECTIVEFROM(this.dateEffectiveFrom);
	            _process_payload.setDATEEFFECTIVETO(this.dateEffectiveTo);
	            _process_payload.setDATERETIREDFROM(this.dateRettredFrom);
	            _process_payload.setDATERETIREDTO(this.dateRettredTo);
                _process_payload.setMsgHeader(msgHeader);
                _process__return = port.process(_process_payload);       
            if (_process__return.getErrorFlag().equalsIgnoreCase("Y")) {	
                List<PageInquiryRetiredAssetDetailSrvOutputItem> items = _process__return.getPageInquiryRetiredAssetDetailSrvOutputCollection().getPageInquiryRetiredAssetDetailSrvOutputItem();
                for (int k = 0; k < items.size(); k++) {
                    PageInquiryRetiredAssetDetailSrvOutputItem item = items.get(k);
                    PageRetiredAssetDTO pageRetiredAssetDTO = new PageRetiredAssetDTO();
                    pageRetiredAssetDTO.setRetirementId(StrUtil.nullToString(item.getRETIREMENTID()));   //流水号
                    pageRetiredAssetDTO.setBookTypeCode(item.getBOOKTYPECODE());   //资产账簿
                    if (item.getASSETID() != null){
                        pageRetiredAssetDTO.setAssetId(item.getASSETID().toString());      //资产ID 
                     }   
                    pageRetiredAssetDTO.setAssetNumber(item.getASSETNUMBER());     //资产编号
                    pageRetiredAssetDTO.setTagNumber(item.getTAGNUMBER());         //资产标签号
                    pageRetiredAssetDTO.setDatePlacedInService(XMLGregorianCalendarUtil.getSimpleCalendarString(item.getDATEPLACEDINSERVICE()));   //资产投入使用日期
                    pageRetiredAssetDTO.setDateEffective(XMLGregorianCalendarUtil.getSimpleCalendarString(item.getDATEEFFECTIVE())); // 生效日期
                    pageRetiredAssetDTO.setDateRettred(XMLGregorianCalendarUtil.getSimpleCalendarString(item.getDATERETIRED()));     //报废日期
                    pageRetiredAssetDTO.setCostRetired(item.getCOSTRETIRED().toString());  //报废值
                    pageRetiredAssetDTO.setStatus(item.getSTATUS());                       //当前状态
                    if (item.getUNITS() != null){
                        pageRetiredAssetDTO.setUnits(item.getUNITS().toString());          // 数量
                     }
                    pageRetiredAssetDTO.setRetirementTypeCode(item.getRETIREMENTTYPECODE());      //报废类型
                    ds.addDTO(pageRetiredAssetDTO);
                }
              }
            }
          }
        }
    }
    
    public String toString() {
        String s = null;
        for (int i = 0; i < ds.getSize(); i++) {
        	PageRetiredAssetDTO dto = (PageRetiredAssetDTO) ds.getDTO(i);
            s = s + "流水号:"+dto.getRetirementId()+"  资产账簿:" + dto.getBookTypeCode() + "  资产ID:" + dto.getAssetId() + " 资产标签号:" + dto.getTagNumber() +" 报废值:"+ dto.getCostRetired()+" 报废类型:"+dto.getRetirementTypeCode()  +"\n";
        }
        return s;
    } 
    
    public static void main(String[] args) throws Exception {
        PageInquiryRetiredAssetDetailSrv srv = new PageInquiryRetiredAssetDetailSrv();
         srv.setBookTypeCode("SXMC_FA_4187");  //资产账簿名称
         //srv.setLocalionDep("10000002");
         srv.setTagNumber("4187-00000002");    //资产标签号
        srv.excute();
        System.out.println(srv.toString());
        //System.exit(0);
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

    /**
     * @return the ds
     */
    public DTOSet getDs() {
        return ds;
    }

    /**
     * @param ds the ds to set
     */
    public void setDs(DTOSet ds) {
        this.ds = ds;
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
     * @return the localionDep
     */
    public String getLocalionDep() {
        return localionDep;
    }

    /**
     * @param localionDep the localionDep to set
     */
    public void setLocalionDep(String localionDep) {
        this.localionDep = localionDep;
    }

    /**
     * @return the assetNumber
     */
    public String getAssetNumber() {
        return assetNumber;
    }

    /**
     * @param assetNumber the assetNumber to set
     */
    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    /**
     * @return the tagNumber
     */
    public String getTagNumber() {
        return tagNumber;
    }

    /**
     * @param tagNumber the tagNumber to set
     */
    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    /**
     * @return the retirementTypeCode
     */
    public String getRetirementTypeCode() {
        return retirementTypeCode;
    }

    /**
     * @param retirementTypeCode the retirementTypeCode to set
     */
    public void setRetirementTypeCode(String retirementTypeCode) {
        this.retirementTypeCode = retirementTypeCode;
    }

    /**
     * @return the dateRettredFrom
     */
    public XMLGregorianCalendar getDateRettredFrom() {
        return dateRettredFrom;
    }

     /**
     * @param dateRettredFrom the dateRettredFrom to set
     */
    public void setDateRettredFrom(String dateRettredFrom) throws CalendarException, DatatypeConfigurationException {
        this.dateRettredFrom = XMLGregorianCalendarUtil.getXMLGregorianCalendar(dateRettredFrom);
    }        

    /**
     * @return the dateRettredTo
     */
    public XMLGregorianCalendar getDateRettredTo() {
        return dateRettredTo;
    }

    /**
     * @param dateRettredTo the dateRettredTo to set
     */
    public void setDateRettredTo(String dateRettredTo) throws CalendarException, DatatypeConfigurationException {
        this.dateRettredTo = XMLGregorianCalendarUtil.getXMLGregorianCalendar(dateRettredTo);
    }

    /**
     * @return the dateEffectiveFrom
     */
    public XMLGregorianCalendar getDateEffectiveFrom() {
        return dateEffectiveFrom;
    }

    /**
     * @param dateEffectiveFrom the dateEffectiveFrom to set
     */
    public void setDateEffectiveFrom(String dateEffectiveFrom) throws CalendarException, DatatypeConfigurationException {
        this.dateEffectiveFrom = XMLGregorianCalendarUtil.getXMLGregorianCalendar(dateEffectiveFrom);
    }

    /**
     * @return the dateEffectiveTo
     */
    public XMLGregorianCalendar getDateEffectiveTo() {
        return dateEffectiveTo;
    }

    /**
     * @param dateEffectiveTo the dateEffectiveTo to set
     */
    public void setDateEffectiveTo(String dateEffectiveTo) throws CalendarException, DatatypeConfigurationException {
        this.dateEffectiveTo = XMLGregorianCalendarUtil.getXMLGregorianCalendar(dateEffectiveTo);
    }

}