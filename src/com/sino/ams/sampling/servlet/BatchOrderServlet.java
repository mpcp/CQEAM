package com.sino.ams.sampling.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.sampling.bean.SamplingOptProducer;
import com.sino.ams.sampling.constant.SamplingActions;
import com.sino.ams.sampling.constant.SamplingURLs;
import com.sino.ams.sampling.dao.BatchOrderDAO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.model.BatchOrderModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.CheckBoxProp;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * <p>Title: AmsAssetsSamplingHeaderServlet</p>
 * <p>Description:程序自动生成服务程序“AmsAssetsSamplingHeaderServlet”，请根据需要自行修改</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 北京思诺博信息技术有限公司</p>
 * @author mshtang
 * @version 1.0
 */

public class BatchOrderServlet extends BaseServlet {

	/**
	 * 所有的Servlet都必须实现的方法。
	 *
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
			SfUserDTO user = (SfUserDTO)SessionUtil.getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsAssetsSamplingHeaderDTO.class.getName());
			AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)req2DTO.getDTO(req);
			conn = getDBConnection(req);
			String action = dto.getAct();
			BatchOrderDAO orderDAO = new BatchOrderDAO(user, dto, conn);
			orderDAO.appendOuInfo();
			dto = (AmsAssetsSamplingHeaderDTO)orderDAO.getDTOParameter();
			if(action.equals("")){
				appendOption(user, dto, conn);
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = SamplingURLs.ORDER_FRM_RIGHT;
			} else if(action.equals(SamplingActions.QUERY_ACTION)){
				BaseSQLProducer sqlProducer = new BatchOrderModel(user, dto);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.setCalPattern(LINE_PATTERN);
				pageDAO.setDTOClassName(AmsAssetsSamplingHeaderDTO.class.getName());
				CheckBoxProp checkProp = new CheckBoxProp("subCheck");
				checkProp.addDbField("HEADER_ID");
				pageDAO.setWebCheckProp(checkProp);
				pageDAO.setPageSize(20);
				pageDAO.produceWebData();

				appendOption(user, dto, conn);
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = SamplingURLs.ORDER_FRM_RIGHT;
			} else if(action.equals(SamplingActions.EXPORT_ACTION)){
				File file = orderDAO.getExportFile();
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
		} catch (Exception ex) {
			Logger.logError(ex);
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

	/**
	 * 功能：补充抽查任务DTO的各种下拉框数据(用于界面展示时)
	 * @param user SfUserDTO
	 * @param dto AmsAssetsSamplingHeaderDTO
	 * @param conn Connection
	 * @throws QueryException
	 */
	private void appendOption(SfUserDTO user, AmsAssetsSamplingHeaderDTO dto, Connection conn) throws QueryException {
		String act = dto.getAct();
		SamplingOptProducer optProducer = new SamplingOptProducer(user, conn);
		if(act.equals("") || act.equals(SamplingActions.QUERY_ACTION)){
			if(user.isProvinceUser()){
				String sampledOuOpt = optProducer.getAllOrganization(dto.getSampledOu());
				dto.setSampledOuOpt(sampledOuOpt);
			} else {
				String sampledOuOpt = optProducer.getOrganizationOption(dto.getSampledOu());
				dto.setSampledOuOpt(sampledOuOpt);
			}
		}
	}
}
