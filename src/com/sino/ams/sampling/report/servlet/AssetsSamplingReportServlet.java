package com.sino.ams.sampling.report.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.sampling.bean.SamplingOptProducer;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.report.dao.AssetsSamplingReportDAO;
import com.sino.ams.sampling.report.model.AssetsSamplingReportModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
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
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-9-17
 * Time: 15:52:05
 * To change this template use File | Settings | File Templates.
 */
public class AssetsSamplingReportServlet extends BaseServlet {

	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO) getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsAssetsSamplingHeaderDTO.class.getName());
			AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) req2DTO.getDTO(req);
			String action = dto.getAct();
			conn = getDBConnection(req);
			AssetsOptProducer opt = new AssetsOptProducer(user, conn);
	        if (StrUtil.isEmpty(dto.getSampledOu())) {
	            dto.setSampledOu(user.getOrganizationId());
	        }
	        dto.setSampledOuOpt(opt.getOrganization(user.getOrganizationId()));
//            SamplingOptProducer optProducer = new SamplingOptProducer(user, conn);
//            if(user.isProvinceUser()){
//                String sampledOuOpt = optProducer.getAllOrganization(dto.getOrganizationId());
//                dto.setSampledOuOpt(sampledOuOpt);
//            } else {
//                String sampledOuOpt = optProducer.getOrganizationOption(user.getOrganizationId());
//                dto.setSampledOuOpt(sampledOuOpt);
//            }
            if (action.equals("")) {
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = AssetsURLList.SAMPLING_CHK_REPORT;
			} else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new AssetsSamplingReportModel(user, dto);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.produceWebData();
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = AssetsURLList.SAMPLING_CHK_REPORT;
			} else if (action.equals(AssetsActionConstant.EXPORT_ACTION)) {
				AssetsSamplingReportDAO rptDAO = new AssetsSamplingReportDAO(user, dto, conn);
				File file = rptDAO.getExportFile();
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
		} catch (WebFileDownException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DataTransException ex) {
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
