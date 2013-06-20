package com.sino.ams.system.rent.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.rent.model.AmsRentAssetModel;
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
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsPriviServlet</p>
 * <p>Description:程序自动生成服务程序“AmsAssetsPriviServlet”，请根据需要自行修改</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: 北京思诺博信息技术有限公司</p>
 * @author 唐明胜
 * @version 1.0
 */

public class AmsRentAssetDAO extends AMSBaseDAO {

	/**
	 * 功能：固定资产当前信息(EAM) ETS_FA_ASSETS 数据访问层构造函数
	 * @param userAccount SfUserDTO 代表本系统的最终操作用户对象
	 * @param dtoParameter AmsAssetsAddressVDTO 本次操作的数据
	 * @param conn Connection 数据库连接，由调用者传入。
	 */
	public AmsRentAssetDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * 功能：SQL生成器BaseSQLProducer的初始化。
	 * @param userAccount BaseUserDTO 本系统最终操作用户类
	 * @param dtoParameter DTO 本次操作的数据
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsAddressVDTO dtoPara = (AmsAssetsAddressVDTO)dtoParameter;
		super.sqlProducer = new AmsRentAssetModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * 功能：根据选择的资产导出数据
	 * @param barcodes String[]
	 * @return String 返回导出Excel文件
	 * @throws com.sino.base.exception.DataTransException
     */
	public File exportCheckedAssets(String[] barcodes) throws DataTransException {
		File file = null;
		AmsRentAssetModel assetsModel = (AmsRentAssetModel) sqlProducer;
		SQLModel sqlModel = assetsModel.getExpCheckedAssetsModel(barcodes);
		file = getExportFile(sqlModel);
		return file;
	}

	/**
	 * 功能：导出查询SQL资产数据
	 * @return String 返回导出Excel文件
	 * @throws DataTransException
	 */
	public File exportQueryAssets() throws DataTransException {
		AmsRentAssetModel assetsModel = (AmsRentAssetModel) sqlProducer;
		SQLModel sqlModel = assetsModel.getPageQueryModel();
		return getExportFile(sqlModel);
	}

	/**
	 * 功能：根据SQL导出文件
	 * @param sqlModel SQLModel
	 * @return File
	 * @throws DataTransException
	 */
	private File getExportFile(SQLModel sqlModel) throws DataTransException {
		File file = null;
		String reportTitle = getFormatTitle();
		String fileName = reportTitle + ".xls";
		TransRule rule = new TransRule();
		rule.setDataSource(sqlModel);
		rule.setSourceConn(conn);
		String filePath = WorldConstant.USER_HOME;
		filePath += WorldConstant.FILE_SEPARATOR;
		filePath += fileName;
		rule.setTarFile(filePath);
		DataRange range = new DataRange();
		rule.setDataRange(range);
		Map fieldMap = getFieldMap();
		rule.setFieldMap(fieldMap);
		CustomTransData custData = new CustomTransData();
		custData.setReportTitle(reportTitle);
		custData.setReportPerson(userAccount.getUsername());
		custData.setNeedReportDate(true);
		rule.setCustData(custData);
//		rule.setSheetSize(2000);
		rule.setCalPattern(LINE_PATTERN);
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		file = (File) transfer.getTransResult();
		return file;
	}

	private Map getFieldMap(){
		Map fieldMap = null;
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)dtoParameter;
		String treeCategory = dto.getTreeCategory();
		if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_PERSON)){//个人资产
			fieldMap = getAssetsMap();
		} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_DEPART)){//部门资产
			fieldMap = getAssetsMap();
		} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_COMPAN)){//公司资产
			fieldMap = getAssetsMap();
		} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_PROVIN)){//全省资产
			fieldMap = getAssetsMap();
//		} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)){//待确认资产
//			fieldMap = getConfirmAssetsMap();
//		} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER)){//调出资产
//			fieldMap = getTransferAssetsMap();
		}
		return fieldMap;
	}

	/**
	 * 功能：获取个人资产的导出表头
	 * @return Map
	 */
	private static Map getAssetsMap() {
		Map fieldMap = new HashMap();
//		fieldMap.put("BARCODE", "资产标签");
//		fieldMap.put("ASSET_NUMBER", "资产编号");
//		fieldMap.put("FA_CATEGORY1", "资产类别一");
//		fieldMap.put("FA_CATEGORY2", "资产类别二");
//		fieldMap.put("ASSETS_DESCRIPTION", "资产名称");
//		fieldMap.put("MODEL_NUMBER", "资产型号");
//		fieldMap.put("UNIT_OF_MEASURE", "计量单位");
//		fieldMap.put("CURRENT_UNITS", "数量");
//		fieldMap.put("COST", "资产原值");
//		fieldMap.put("DATE_PLACED_IN_SERVICE", "启用日期");
//		fieldMap.put("LIFE_IN_YEARS", "服务年限");
//		fieldMap.put("DEPRECIATION", "累计折旧");
//		fieldMap.put("DEPRN_COST", "资产净值");
//		fieldMap.put("SCRAP_VALUE", "资产残值");
//		fieldMap.put("DEPRECIATION_ACCOUNT", "折旧账户");
//		fieldMap.put("BOOK_TYPE_CODE", "资产账簿");
//		fieldMap.put("WORKORDER_OBJECT_NAME", "所在地点");
//		fieldMap.put("PROJECT_NAME", "项目名称");
//		fieldMap.put("RESPONSIBILITY_USER_NAME", "责任人");
//		fieldMap.put("DEPT_NAME", "责任部门");
//		fieldMap.put("MAINTAIN_USER_NAME", "保管人");
//		fieldMap.put("ITEM_STATUS_NAME", "资产状态");
            fieldMap.put("ROWNUM", "序号");
            fieldMap.put("BARCODE", "资产编号");
            fieldMap.put("ITEM_NAME", "资产名称");
            fieldMap.put("ITEM_SPEC", "规格型号");
            fieldMap.put("FINANCE_PROP", "资产类别");
            fieldMap.put("RENT_PERSON", "对方签约单位");
            fieldMap.put("OBJECT_NAME", "资产地点");
            fieldMap.put("MAINTAIN_DEPT", "使用部门");
            fieldMap.put("TENANCY", "租期（年）");
            fieldMap.put("YEAR_RENTAL", "年租金(元)");
            fieldMap.put("MONTH_REANTAL", "月租金");
            fieldMap.put("RENT_DATE", "起始日期");
            fieldMap.put("END_DATE", "终止日期");
            fieldMap.put("USER_NAME", "责任人");
            fieldMap.put("GROUP_NAME", "责任部门");
            fieldMap.put("REMARK", "备注");
        return fieldMap;
	}

	/**
	 * 功能：获取待确认资产的导出表头
	 * @return Map
	 */
	private static Map getConfirmAssetsMap() {
		return getTransferAssetsMap();
	}

	/**
	 * 功能：获取调拨资产的导出表头
	 * @return Map
	 */
	private static Map getTransferAssetsMap() {
		Map fieldMap = new HashMap();
		fieldMap.put("TRANS_NO", "调拨单号");
		fieldMap.put("BARCODE", "资产标签");
		fieldMap.put("ASSET_NUMBER", "资产编号");
		fieldMap.put("ASSETS_DESCRIPTION", "资产名称");
		fieldMap.put("MODEL_NUMBER", "资产型号");
		fieldMap.put("COST", "资产原值");
		fieldMap.put("DEPRN_COST", "累计折旧");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "启用日期");
		fieldMap.put("CURRENT_UNITS", "数量");
		fieldMap.put("OLD_LOCATION_NAME", "原地点");
		fieldMap.put("OLD_RESPONSIBILITY_USER_NAME", "原责任人");
		fieldMap.put("OLD_RESPONSIBILITY_DEPT_NAME", "原责任部门");
		fieldMap.put("ASSIGNED_TO_LOCATION_NAME", "调入地点");
		fieldMap.put("RESPONSIBILITY_USER_NAME", "新责任人");
		fieldMap.put("RESPONSIBILITY_DEPT_NAME", "调入部门");
		fieldMap.put("REMARK", "摘要");
		return fieldMap;
	}

	/**
	 * 功能：构造导出Excel的标题
	 * @return String
	 */
	private String getFormatTitle() {
		StringBuffer reportTitle = new StringBuffer();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)dtoParameter;
		String treeCategory = dto.getTreeCategory();
		String companyName = dto.getCompanyName();
		String deptName = dto.getDeptName();
		String userName = userAccount.getUsername();
		if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_PERSON)){//个人资产导出
			reportTitle.append(userAccount.getCompany());
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append(userName);
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append("租赁资产");
		} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_DEPART)){//部门租赁资产导出
			reportTitle.append(companyName);
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append(deptName);
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append("租赁资产");
		} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_COMPAN)){//公司租赁资产导出
			reportTitle.append(companyName);
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append(deptName);
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append("租赁资产");
		} else if(treeCategory.equals(AssetsWebAttributes.RENT_ASSETS_TREE_PROVIN)){//全省租赁资产导出
			reportTitle.append(servletConfig.getProCompanyName());
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append(AssetsWebAttributes.ASSETS_PROVIN);
			reportTitle.append(companyName);
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append(deptName);
			reportTitle.append(WorldConstant.EMPTY_SPACE);
			reportTitle.append("租赁资产");
//		} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)){//待确认资产导出
//			reportTitle.append(companyName);
//			reportTitle.append(WorldConstant.EMPTY_SPACE);
//			reportTitle.append(userAccount.getUsername());
//			reportTitle.append(WorldConstant.EMPTY_SPACE);
//			reportTitle.append("待确认资产");
//		} else if(treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER)){//调出资产导出
//			reportTitle.append(companyName);
//			reportTitle.append(WorldConstant.EMPTY_SPACE);
//			reportTitle.append(deptName);
//			reportTitle.append(WorldConstant.EMPTY_SPACE);
//			reportTitle.append("调出资产");
		}
		return reportTitle.toString();
	}
}
