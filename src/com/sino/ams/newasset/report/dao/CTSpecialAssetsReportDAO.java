package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.report.dto.SpecialAssetsReportDTO;
import com.sino.ams.newasset.report.model.CTSpecialAssetsReportModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-3-2
 * Time: 21:42:16
 * To change this template use File | Settings | File Templates.
 */

public class CTSpecialAssetsReportDAO extends AMSBaseDAO {

	public CTSpecialAssetsReportDAO(SfUserDTO userAccount, SpecialAssetsReportDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * 功能：SQL生成器baseSQLProducer的初始化。具体的DAO继承时初始化具体的SQL生成器。
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		SpecialAssetsReportDTO dto = (SpecialAssetsReportDTO) dtoParameter;
		sqlProducer = new CTSpecialAssetsReportModel(user, dto);
		this.dto = dto;
	}

	private SpecialAssetsReportDTO dto;

	/**
	 * 功能：获取部门盘点设备明细Excel文件
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "专业构成分布(村通资产)";
			String fileName = reportTitle + ".xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = new HashMap();
			fieldMap.put("ASSETS_SPECIES", "类");
			fieldMap.put("ASSETS_NAPE", "项");

			fieldMap.put("SUM_COST", "原值");
			fieldMap.put("SUM_COUNT", "数量");
			fieldMap.put("ASSETS_RATE_COUNT", "占当期资产总数比重");
			fieldMap.put("ASSETS_RATE", "占当期资产总额比重");
			fieldMap.put("LAST_YEAR_RATE_COUNT", "较去年同期增长率");
			fieldMap.put("LAST_MONTH_RATE_COUNT", "比上月增长率");
			
			fieldMap.put("THREE_YEER_THREE_RATE_COUNT", "近3年增长率("+ (Integer.parseInt(dto.getYear())-3) +")");
            fieldMap.put("THREE_YEER_TWO_RATE_COUNT", "近3年增长率("+ (Integer.parseInt(dto.getYear())-2) +")");
            fieldMap.put("THREE_YEER_ONE_RATE_COUNT", "近3年增长率("+ (Integer.parseInt(dto.getYear())-1) +")");
			rule.setFieldMap(fieldMap);
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			rule.setCalPattern(LINE_PATTERN);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}
}