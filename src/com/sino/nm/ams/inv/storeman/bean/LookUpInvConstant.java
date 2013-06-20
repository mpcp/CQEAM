package com.sino.nm.ams.inv.storeman.bean;

/**
 * Created by MyEclipse.
 * User: yushibo
 * Date: 2008-12-12
 * Time: 13:31:26
 */
public interface LookUpInvConstant {

	String LOOK_UP_WORKORDER_OBJECT_NO = "LOOK_UP_WORKORDER_OBJECT_NO"; //仓库ID
	String LOOK_UP_USER_ID = "LOOK_UP_USER_ID"; //仓管员ID
	String LOOK_UP_UPDATED_USER = "LOOK_UP_UPDATED_USER"; //选择修改人
	String LOOK_UP_CREATED_USER = "LOOK_UP_CREATED_USER"; //选择创建人
	
	String LOOK_UP_CATALOG_VALUE_ID = "LOOK_UP_CATALOG_VALUE_ID"; //选择目录编号
	String LOOK_UP_ITEM_CATEGORY2 = "LOOK_UP_ITEM_CATEGORY2"; //选择目录编号的同时把相关的信息也选择出来，此选择项查找的是ETS_SYSTEM_ITEM表
	String LOOK_UP_RESPONSIBILITY_DEPT = "LOOK_UP_RESPONSIBILITY_DEPT"; //选择使用部门
	String LOOK_UP_RESPONSIBILITY_USER = "LOOK_UP_RESPONSIBILITY_USER"; //选择领用人
	String LOOK_UP_SYS_ITEM = "LOOK_UP_SYS_ITEM"; //选择规格型号
	String LOOK_UP_SYS_ITEM_DZYH = "LOOK_UP_SYS_ITEM_DZYH"; //选择低值易耗品规格型号
	String LOOK_UP_RESPONSIBILITY_EMPLOYEE = "LOOK_UP_RESPONSIBILITY_EMPLOYEE"; //选择责任人
	String LOOK_UP_BARCODE = "LOOK_UP_BARCODE"; //选择标签编号
	String LOOK_UP_WORKORDER_OBJECT_NO_DZYH_WARE = "LOOK_UP_WORKORDER_OBJECT_NO_DZYH_WARE"; //选择低值易耗品仓库
	String LOOK_UP_WORKORDER_OBJECT_NO_DZYH = "LOOK_UP_WORKORDER_OBJECT_NO_DZYH"; //选择低值易耗品地点
}
