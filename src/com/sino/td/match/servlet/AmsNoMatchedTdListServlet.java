package com.sino.td.match.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.URLDefineList;
import com.sino.ams.match.dto.AmsNoMactingAssetDTO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.db.QueryConstant;
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
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.td.match.dao.AmsNoMatchedTdListDAO;
import com.sino.td.match.model.AmsNoMatchedTdListModel;

/**
 * Created by IntelliJ IDEA.
 * User:jiangtao
 * Date: 2007-11-21
 * Time: 11:55:59
 * To change this template use File | Settings | File Templates.
 */
public class AmsNoMatchedTdListServlet extends BaseServlet {
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsNoMactingAssetDTO.class.getName());
			AmsNoMactingAssetDTO dto = (AmsNoMactingAssetDTO) req2DTO.getDTO(req);
			String action = dto.getAct();

			conn = getDBConnection(req);
			AmsNoMatchedTdListDAO noMactionDAO = new AmsNoMatchedTdListDAO(user, dto, conn);
			if (action.equals("")) {
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = URLDefineList.CHANGED_MATCHR_NOMACTINGASSET_TD;
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) { //����
				BaseSQLProducer sqlProducer = new AmsNoMatchedTdListModel(user, dto);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.setCalPattern(CalendarConstant.LINE_PATTERN); //
				pageDAO.produceWebData();
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = URLDefineList.CHANGED_MATCHR_NOMACTINGASSET_TD;
			} else if (action.equals(WebActionConstant.EXPORT_ACTION)) { //����
				File file = noMactionDAO.exportFile();
				noMactionDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
			} else {
				message = getMessage(AssetsMessageKeys.INVALID_REQ);
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
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (WebFileDownException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			if(!forwardURL.equals("")){
				ServletForwarder forwarder = new ServletForwarder(req, res);
				forwarder.forwardView(forwardURL);
			}
		}
	}
}
