package com.sino.td.synAssets.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.message.Message;
import com.sino.base.util.ArrUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.td.commom.TdURLDefineList;
import com.sino.td.synAssets.dao.TdNewLocationsSynDAO;
import com.sino.td.synAssets.model.TdNewLocationsSynModel;

/**
 * Created by IntelliJ IDEA.
 * Author:      李軼
 * Date:        2009-9-8
 * Time:        17:25:55
 * Function：    TD新增地點同步
 */
public class TdNewLocationsSynServlet extends BaseServlet {
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        String msg = "";
        Message message = SessionUtil.getMessage(req);
        String action = req.getParameter("act");
        action = StrUtil.nullToString(action);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(EamSyschronizeDTO.class.getName());
            EamSyschronizeDTO dto = (EamSyschronizeDTO) req2DTO.getDTO(req);
            conn = getDBConnection(req);
            TdNewLocationsSynDAO dao = new TdNewLocationsSynDAO(user, dto, conn);
            if (action.equals("")) {
                req.setAttribute(WebAttrConstant.SYSCHRONIZE_DTO, dto);
                forwardURL = TdURLDefineList.TD_NEW_LOCATIONS;
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) { //查询操作
                BaseSQLProducer sqlProducer = new TdNewLocationsSynModel(user, dto);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.produceWebData();
                req.setAttribute(WebAttrConstant.SYSCHRONIZE_DTO, dto);
                forwardURL = TdURLDefineList.TD_NEW_LOCATIONS;
            } else if (action.equals(WebActionConstant.EXPORT_ACTION)) { //查询操作
                File file = dao.getExportFile();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else if (action.equals(WebActionConstant.SYSCHRONIZE_ACTION)) { //同步操作
                String[] systemIds = req.getParameterValues("systemids"); //查询参数
                String systemId = ArrUtil.arrToSqlStr(systemIds);
                dao.syschronizeLocus(systemId);
                message = dao.getMessage();
				message = getMessage(MsgKeyConstant.SYNCHRONIZATION_DATA_SUCCESS);
				message.addParameterValue("TD变动地点");
                req.setAttribute("syn_msg",message.getMessageValue());
                forwardURL = "/servlet/com.sino.td.synAssets.servlet.TdNewLocationsSynServlet?act=QUERY_ACTION";

            } else {
                message = getMessage(MsgKeyConstant.INVALID_REQ);
                message.setIsError(true);
                forwardURL = MessageConstant.MSG_PRC_SERVLET;
            }
        } catch (PoolPassivateException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.POOL_PASSIVATE_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DTOException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.DTO_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (QueryException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DataTransException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (WebFileDownException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            if (!StrUtil.isEmpty(forwardURL)) {
                ServletForwarder forwarder = new ServletForwarder(req, res);
                if (msg != "" && msg != null) {
                    forwarder.forwardOpenerView(forwardURL, msg);
                } else {
                    forwarder.forwardView(forwardURL);
                }
            }
        }
    }
}