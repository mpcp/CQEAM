package com.sino.ams.newasset.bean;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.sino.ams.log.dao.UserLoginDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.exception.XMLParseException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.security.dto.FilterConfigDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: 中国移动资产实物管理系统</p>
 * <p>Copyright: 北京思诺博信息技术有限公司版权所有Copyright (c) 2007</p>
 * <p>Company: 北京思诺博信息技术有限公司</p>
 * @author 唐明胜
 * @version 1.0
 */

public class OrderPDACreateParser {
	private static Map orderMap = OrderXMLAssistant.getOrderCreateMap();
	private AmsAssetsCheckHeaderDTO checkOrder = null;
	private SfUserDTO userAccount = null;
	private Connection conn = null;

	public OrderPDACreateParser() {
		checkOrder = new AmsAssetsCheckHeaderDTO();
	}

	/**
	 * 功能：解析PDA创建的盘点工单，获取工单头对象。
	 * @param filterConfig FilterConfigDTO
	 * @param conn Connection
	 * @param filePath String
	 * @throws XMLParseException
	 */
	public void parseXML(FilterConfigDTO filterConfig, Connection conn, String filePath) throws XMLParseException {
		try {
			this.conn = conn;
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(filePath);
			Element root = doc.getRootElement();
			List attributes = root.getAttributes();
			Attribute attribute = null;
			String fieldName = "";
			String fieldValue = "";
			Map orderMaps = OrderXMLAssistant.getOrderMaps();
			if (attributes != null) {
				for (int i = 0; i < attributes.size(); i++) {
					attribute = (Attribute) attributes.get(i);
					fieldName = attribute.getName();
					fieldName = (String) orderMap.get(fieldName);
					if (StrUtil.isEmpty(fieldName)) {
						continue;
					}
					fieldValue = attribute.getValue();
					if (fieldName.equals("orderType")) { //将汉字描述转换为代码
						fieldValue = String.valueOf(orderMaps.get(fieldValue));
					}
					ReflectionUtil.setProperty(checkOrder, fieldName, fieldValue);
				}
			}
			List children = root.getChildren();
			Element child = null;
			if (children != null) {
				for (int i = 0; i < children.size(); i++) {
					child = (Element) children.get(i);
					attributes = child.getAttributes();
					if (attributes != null) {
						for (int j = 0; j < attributes.size(); j++) {
							attribute = (Attribute) attributes.get(j);
							fieldName = attribute.getName();
							fieldName = (String) orderMap.get(fieldName);
							if (StrUtil.isEmpty(fieldName)) {
								continue;
							}
							fieldValue = attribute.getValue();
							ReflectionUtil.setProperty(checkOrder, fieldName, fieldValue);
							if (fieldName.equals(filterConfig.getLoginName())) {
								initUserData(fieldValue);
							}
						}
					}
				}
			}
		} catch (ReflectException ex) {
			ex.printLog();
			throw new XMLParseException(ex);
		} catch (IOException ex) {
			Logger.logError(ex);
			throw new XMLParseException(ex);
		} catch (JDOMException ex) {
			Logger.logError(ex);
			throw new XMLParseException(ex);
		} catch (QueryException ex) {
			ex.printLog();
			throw new XMLParseException(ex);
		}

	}

	/**
	 * 功能：产生工单创建人信息
	 * @param loginName String
	 * @throws QueryException
	 */
	private void initUserData(String loginName) throws QueryException {
		userAccount = new SfUserDTO();
		userAccount.setLoginName(loginName);
		UserLoginDAO loginDAO = new UserLoginDAO(userAccount, conn);
		loginDAO.setFromPDA(true);
		if (loginDAO.isValidUser()) {
			userAccount = (SfUserDTO) loginDAO.getUserAccount();
		}
	}

	/**
	 * 功能：获取创建用户
	 * @return SfUserDTO
	 */
	public SfUserDTO getCreatedUser() {
		return userAccount;
	}

	/**
	 * 功能：获取从xml文件中解析出的工单头对象
	 * @return AmsAssetsCheckHeaderDTO
	 */
	public AmsAssetsCheckHeaderDTO getCheckOrder() {
		return checkOrder;
	}
}
