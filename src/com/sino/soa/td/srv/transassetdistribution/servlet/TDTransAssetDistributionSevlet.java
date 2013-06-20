package com.sino.soa.td.srv.transassetdistribution.servlet;


import com.sino.ams.bean.OrgOptionProducer;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.TimeException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvType;
import com.sino.soa.common.SrvURLDefineList;
import com.sino.soa.common.SrvWebActionConstant;
import com.sino.soa.mis.srv.transassetdistribution.dto.SrvAssetDistributionDTO;
//import com.sino.soa.mis.srv.transassetdistribution.srv.TransAssetDistributionSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;
import com.sino.soa.td.srv.transassetdistribution.srv.TDTransAssetDistributionSrv;   //TD

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.sql.Connection;

/**
 * <p>Title: TransAssetDistributionSevlet</p>
 * <p>Description:�ʲ������ж�ȡ�����ࡰTransAssetDistributionSevlet��</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzp
 * @version 1.0
 * function:��ѯ�ʲ���������Ϣ_TD(ODI)
 */

public class TDTransAssetDistributionSevlet extends BaseServlet {

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        boolean autoCommit = true;
        long resumeTime = 0;
        try {
            conn = getDBConnection(req);
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(SrvAssetDistributionDTO.class.getName());
            SrvAssetDistributionDTO dtoParameter = (SrvAssetDistributionDTO) req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            String assetsType = StrUtil.nullToString(req.getParameter("assetsType"));
            OrgOptionProducer optionProducer = new OrgOptionProducer(user, conn);
            String range = assetsType.equals("TD") ? "Y" : "N";
            dtoParameter.setAssetsType(assetsType);
            String opt = optionProducer.getBookTypeCodeOption(dtoParameter.getBookTypeCode(),range);
            dtoParameter.setOrgOption(opt);
            req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
            if (action.equals("")) {
                forwardURL = SrvURLDefineList.TD_TRANS_ASSET_DISTRIBUTION_PAGE;
            } else if (action.equals(SrvWebActionConstant.INFORSYN) && AssetsWebAttributes.TD_ASSETS_TYPE.equals(assetsType)) { //TD
                long start = System.currentTimeMillis();
                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                DTOSet ds = new DTOSet();
                ds.addDTO(dtoParameter);

                String envCode = SynUpdateDateUtils.getEnvCode("TDTransAssetDistributionSrv", conn);

                TDTransAssetDistributionSrv service = new TDTransAssetDistributionSrv();
                service.setEnvCode(envCode);
                service.setBookTypeCode(dtoParameter.getBookTypeCode());
                if (StrUtil.isNotEmpty(dtoParameter.getEndLastUpdateDate())) {
                    service.setStratLastUpdateDate(dtoParameter.getStratLastUpdateDate());
                }
                if (StrUtil.isNotEmpty(dtoParameter.getEndLastUpdateDate())) {
                    service.setEndLastUpdateDate(dtoParameter.getEndLastUpdateDate());
                }
                service.excute();
                SrvReturnMessage srvMessage = service.getReturnMessage();
                resumeTime = System.currentTimeMillis() - start;
                message = new Message();
                if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_FA_DISTRIBUTION, conn);
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_TD_FA_DISTRIBUTION);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��TD�ʲ���������Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "���룬�ʲ��˲���" + dtoParameter.getBookTypeCode());
                    logUtil.synLog(logDTO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_FA_DISTRIBUTION, conn);
                    message.setMessageValue("ͬ��TD�ʲ���������Ϣ(ODI)�ɹ���" + "��ʱ" + resumeTime + "����");
                } else {
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_TD_FA_DISTRIBUTION);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��TD�ʲ���������Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���룬�ʲ��˲���" + dtoParameter.getBookTypeCode()+"��������Ϣ:"+srvMessage.getErrorMessage());
                    logUtil.synLog(logDTO, conn);
                    message.setMessageValue("ͬ��TD�ʲ���������Ϣ(ODI)ʧ�ܣ�" + "��ʱ" + resumeTime + "���룬�ʲ��˲���" + dtoParameter.getBookTypeCode()+"��������Ϣ:"+srvMessage.getErrorMessage());
                }
                forwardURL = SrvURLDefineList.TD_TRANS_ASSET_DISTRIBUTION_PAGE;
            }
        } catch (PoolPassivateException ex) {
            ex.printLog();
            message.setMessageValue("ͬ��ʧ��");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
        } catch (DTOException ex) {
            ex.printLog();
            message.setMessageValue("ͬ��ʧ��");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
        } catch (DatatypeConfigurationException ex1) {
            message.setMessageValue("ͬ��ʧ��");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
            ex1.printStackTrace();
        } catch (TimeException e) {
            message.setMessageValue("ͬ��ʧ��");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
            e.printStackTrace();
        } catch (Exception e) {
            message.setMessageValue("ͬ��ʧ��");
            forwardURL = SrvURLDefineList.MESSAGE_PRINT_PUB;
            e.printStackTrace();
        } finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
        }
    }

}