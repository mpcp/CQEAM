package com.sino.ams.match.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.URLDefineList;
import com.sino.ams.match.dao.AmsMactPropertyDAO;
import com.sino.ams.match.dto.AmsMactPropertyDTO;
import com.sino.ams.match.model.AmsMactPropertyModel;
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

/**
 * Created by IntelliJ IDEA.
 * User:jiangtao
 * Date: 2007-11-21
 * Time: 11:55:59
 * To change this template use File | Settings | File Templates.
 */
public class AmsMactPropertyServlet extends BaseServlet {

	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		boolean needForward = true;
		boolean autoCommit = false ;
		try {
			SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsMactPropertyDTO.class.getName());
			AmsMactPropertyDTO dto = (AmsMactPropertyDTO) req2DTO.getDTO(req);
			conn = getDBConnection(req);
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit( true );
			AmsMactPropertyDAO AmsMactPropertyDAO = new AmsMactPropertyDAO(user, dto, conn);
			String act = dto.getAct();
			if (act.equals("")) {
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = URLDefineList.CHANGED_MATCHR_MACTPROPERTY;
			} else if (act.equals(WebActionConstant.QUERY_ACTION)) { //��ѯ
				BaseSQLProducer sqlProducer = new AmsMactPropertyModel(user, dto);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = URLDefineList.CHANGED_MATCHR_MACTPROPERTY;
			} else if (act.equals(WebActionConstant.EXPORT_ACTION)) { //����
				File file = AmsMactPropertyDAO.exportFile();
				AmsMactPropertyDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
				needForward = false;
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
		} catch (WebFileDownException e) {
			e.printStackTrace();
		} catch (DataTransException e) {
			e.printStackTrace(); // �ļ�����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit( autoCommit );
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message); 
			if(needForward){
				ServletForwarder forwarder = new ServletForwarder(req, res);
				forwarder.forwardView(forwardURL);
			}
		}
	}
}