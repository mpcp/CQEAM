package com.sino.ams.newasset.report.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.dao.LocResReportDAO;
import com.sino.ams.system.trust.dao.AmsMaintainCompanyDAO;
import com.sino.ams.system.trust.dto.AmsMaintainCompanyDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;

public class LocResReportServlet extends BaseServlet {

	/**
	 * 所有的Servlet都必须实现的方法。
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO) getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsAssetsCheckHeaderDTO.class.getName());
			AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) req2DTO.getDTO(req);
			String action = dto.getAct();
			conn = getDBConnection(req);
			AmsMaintainCompanyDTO mainCompany = new AmsMaintainCompanyDTO();
			mainCompany.setCompanyId( dto.getMaintainCompany() );
			AmsMaintainCompanyDAO maintainDAO = new AmsMaintainCompanyDAO(user, mainCompany, conn);
			maintainDAO.setDTOClassName(AmsMaintainCompanyDTO.class.getName());
			mainCompany = (AmsMaintainCompanyDTO)maintainDAO.getDataByPrimaryKey();
			if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				req.setAttribute(WebAttrConstant.MAINTAIN_CORP_ATTR, mainCompany);
				req.setAttribute(WebAttrConstant.WORKORDER_DTO, dto);
				forwardURL = AssetsURLList.SCAN_LOC_DTL;
			} else if (action.equals(WebActionConstant.EXPORT_ACTION)) {
				LocResReportDAO locDAO = new LocResReportDAO(user, dto, conn);
				File file = locDAO.getExportFile(mainCompany);
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
			} else {
				message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (PoolPassivateException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.POOL_PASSIVATE_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.DTO_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.QUERY_ERROR);
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
			closeDBConnection(conn);
			setHandleMessage(req, message);
			if(!StrUtil.isEmpty(forwardURL)){
				ServletForwarder forwarder = new ServletForwarder(req, res);
				forwarder.forwardView(forwardURL);
			}
		}
	}
}
