<%--
  Created by HERRY.
  Date: 2007-10-22
  Time: 15:53:42
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsActionConstant" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsDictConstant" %>
<%@ page import="com.sino.flow.constant.FlowConstant" %>
<%@ include file="/spare/headerInclude.htm"%>
<html>
<head><title>备件申领查看</title>
</head>
<body leftmargin="1" topmargin="1" onload="do_SetPageWidth()">
<%
    AmsItemTransHDTO dto = (AmsItemTransHDTO) request.getAttribute("BJSL_HEADER");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
	String status = dto.getTransStatus();
	String createdBy = dto.getCreatedBy();
	String userId = user.getUserId();
%>
<form name="mainForm" action="/servlet/com.sino.ams.spare.servlet.BjslServlet" method="post">

<jsp:include page="/flow/include.jsp" flush="true"/>
<input type="hidden" name="act" value="">
<input type="hidden" name="transId" value="<%=dto.getTransId()%>">
<input type="hidden" name="transType" value="<%=dto.getTransType()%>">
<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
<input type="hidden" name="toObjectNo" value="<%=dto.getToObjectNo()%>">
<input type="hidden" name="transStatus" value="<%=dto.getTransStatus()%>">
<input type="hidden" name="toOrganizationId" value="<%=dto.getToOrganizationId()%>">
<input type="hidden" name="fromOrganizationId" value="<%=dto.getFromOrganizationId()%>">
<input type="hidden" name="fromDept" value="<%=dto.getFromDept()%>">
<input type="hidden" name="groupId" value="<%=user.getCurrGroupId()%>">
<table border="1" bordercolor="#9FD6FF" class="detailHeader" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1">
                <tr height="22">
                    <td width="8%" align="right">单据号：</td>
                    <td width="17%"><input type="text" name="transNo" value="<%=dto.getTransNo()%>" readonly style="width:100%" class="blueborderGray"></td>
                    <td width="8%" align="right">申请公司：</td>
                    <td width="17%"><input type="text" name="toOrganizationName" value="<%=dto.getToOrganizationName()%>" style="width:100%" class="blueborderGray"></td>
                    <td align="right" width="8%">创建人：</td>
                    <td width="17%"><input type="text" name="createdUser" value="<%=dto.getCreatedUser()%>" readonly style="width:100%" class="blueborderGray"></td>
                    <td align="right" width="8%">创建日期：</td>
                    <td width="17%"><input type="text" name="creationDate" readonly style="width:100%" value="<%=dto.getCreationDate()%>" class="blueborderGray"></td>
                </tr>
                <tr height="22">
                    <td align="right" width="8%">单据状态：</td>
                    <td width="17%"><input type="text" name="transStatusName" style="width:100%"  readonly value="<%=dto.getTransStatusName()%>" class="blueborderGray"></td>
                    <td width="8%" align="right">厂商：</td>
                    <td width="17%"><input type="text" name="vendorName" style="width:100%"  readonly value="<%=dto.getVendorName()%>" class="blueborderGray"></td>
                    <td align="right" width="8%">备注：</td>
                    <td width="42%" colspan="3"><textarea name="remark" style="width:100%;height:100%" class="blueBorder"><%=dto.getRemark()%></textarea></td>
                </tr>
			</table>
        </td>
    </tr>
</table>
<fieldset style="border:1px solid #397DF3; position:absolute;top:63px;width:100%;height:90%">
    <legend>
<%
    String taskProp=StrUtil.nullToString(request.getParameter("taskProp"));
    boolean isFirstNode=taskProp.equals("")||taskProp.equals(FlowConstant.TASK_PROP_START);

        if(dto.getTransStatus().equals(DictConstant.REJECTED)){
        %>
          <img src="/images/button/viewOpinion.gif" alt="查阅意见" onClick="viewOpinion();">
        <%}%>
         <%if (!StrUtil.isEmpty(dto.getTransNo())) {
             if (!dto.getTransNo().equals(WebAttrConstant.ORDER_NO_AUTO_PRODUCE)) {
         %>
            <img src="/images/button/print.gif" alt="打印页面" onclick="do_print();">
       <%} }%>
        <img src="/images/button/close.gif" alt="关闭" onClick="do_Close();">
<%
	if((status.equals("") || status.equals(DictConstant.SAVE_TEMP) || status.equals(DictConstant.REJECTED)) && userId.equals(createdBy)){
%>

        统一设置：<label for="allTransCount">申领数量</label><input type="checkbox" name="allTransCount" id="allTransCount">  
<%
	}			
%>		
    </legend>

	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="100%">
			<tr height="22" onClick="executeClick(this)" style="cursor:'hand'" title="点击全选或取消全选">
				<td width="4%" align="center"><input type="checkbox" class="headCheckbox" name="mainCheck" onPropertyChange="checkAll('mainCheck', 'subCheck')"></td>
				<td width="10%" align="center">部件号</td>
				<td width="20%" align="center">设备名称</td>
				<td width="20%" align="center">规格型号</td>
				<td width="15%" align="center">设备类别</td>
				<td width="15%" align="center">设备厂商</td>
				<td width="8%" align="center">申领数量</td>
                <td width="8%" align="center">实分数量</td>
                <td style="display:none">隐藏域字段</td>
			</tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:540px;width:1007px;position:absolute;top:48px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	RowSet rows = (RowSet) request.getAttribute("BJSL_LINES");
	if (rows == null || rows.isEmpty()) {
%>
            <tr id="mainTr0" style="display:none">
				<td width="4%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" style="height:20px;margin:0;padding:0"></td>
				<td width="10%"><input type="text" name="barcode" id="barcode0" value="" class="finput" readonly></td>
				<td width="20%"><input type="text" name="itemName" id="itemName0" value="" class="finput" readonly></td>
				<td width="20%"><input type="text" name="itemSpec" id="itemSpec0" value="" class="finput" readonly></td>
	            <td width="15%"><input type="text" name="spareUsage" id="spareUsage0" value="" class="finput" readonly></td>
	            <td width="15%"><input type="text" name="vendorName" id="vendorName0" value="" class="finput" readonly></td>
				<td width="8%"><input type="text" name="quantity" id="quantity0" value="" class="finput" readonly></td>
                <td width="8%"><input type="text" name="actualQty" id="actualQty0" value="" class="finput" readonly></td>
                <td style="display:none">
					<input type="hidden" name="lineId" id="lineId0" value="">
				</td>
            </tr>
<%
	} else {
		Row row = null;
		for (int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
%>
            <tr id="mainTr<%=i%>">
				<td width="4%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>" style="height:20px;margin:0;padding:0"></td>
	            <td width="10%"><input type="text" name="barcode" id="barcode<%=i%>" readonly value="<%=row.getValue("BARCODE")%>" class="finput" style="width:100%"></td>
				<td width="20%"><input type="text" name="itemName" id="itemName<%=i%>" value="<%=row.getValue("ITEM_NAME")%>" class="finput" readonly></td>
				<td width="20%"><input type="text" name="itemSpec" id="itemSpec<%=i%>" value="<%=row.getValue("ITEM_SPEC")%>" class="finput" readonly></td>
	            <td width="15%"><input type="text" name="spareUsage" id="spareUsage<%=i%>" value="<%=row.getValue("SPARE_USAGE")%>" class="finput" readonly></td>
	            <td width="15%"><input type="text" name="vendorName" id="vendorName<%=i%>" value="<%=row.getValue("VENDOR_NAME")%>" class="finput" readonly></td>
				<td width="8%"><input type="text" name="quantity" id="quantity<%=i%>" value="<%=row.getValue("QUANTITY")%>" class="finput" readonly></td>
                <td width="8%"><input type="text" name="actualQty" id="actualQty<%=i%>" value="<%=row.getValue("ACTUAL_QTY")%>" class="finput" readonly></td>
                <td style="display:none">
					<input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=row.getValue("LINE_ID")%>">
				</td>
            </tr>
<%
		}
	}
%>
        </table>
    </div>
</fieldset>
</form>
    <jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
</body>
<script type="text/javascript">
function initPage() {
	do_SetPageWidth();
	var length = "<%=user.getUserGroups().getSize()%>";
	var groupId = mainForm.groupId;
	if (length == 1) {
		groupId.value = "<%=user.getCurrGroupId()%>";
	} else {
		var style = "dialogleft:200px;dialogtop:200px;dialogwidth:350px;dialogheight:250px;toolbar:no;directories:no;status:no;menubar:no;scrollbars:no;revisable:no";
		var url = "/public/chooseGroup.jsp";
		var ret = window.showModalDialog(url, null, style);
		if (ret) {
			var arr = ret.split(",");
			groupId.value = arr[0];
		}
	}
}


function do_SelectObject() {
	var projects = getLookUpValues("<%=LookUpConstant.BJ_SPARE_CATEGORY%>", 48, 30);
	if (projects) {
		//            dto2Frm(projects[0], "form1");
		document.mainForm.toObjectName.value = projects[0].workorderObjectName;
		document.mainForm.toObjectNo.value = projects[0].workorderObjectNo;
		document.mainForm.toObjectLocation.value = projects[0].workorderObjectLocation;
	}
}


function do_SelectItem() {
	var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.BJ_SPARE_CATEGORY%>";
	var popscript = "dialogWidth:65;dialogHeight:30;center:yes;status:no;scrollbars:no";
	/*   window.open(url);*/
	var assets = window.showModalDialog(url, null, popscript);
	if (assets) {
		var data = null;
		var tab = document.getElementById("dataTable");
		for (var i = 0; i < assets.length; i++) {
			data = assets[i];
			if (!isItemExist(data)) {
				appendDTORow(tab, data);
			}
		}
	}
}


function isItemExist(itemObj) {
	var retVal = false;
	var tab = document.getElementById("dataTable");
	if (tab.rows) {
		var trObjs = tab.rows;
		var trCount = trObjs.length;
		var trObj = null;
		var itemValue = itemObj.barcode;
		var rowValue = null;
		for (var i = 0; i < trCount; i++) {
			trObj = trObjs[i];
			rowValue = trObj.cells[1].childNodes[0].value;
			if (itemValue == rowValue) {
				retVal = true;
			}
		}
	}
	return retVal;
}

function do_SavePo(flag) {
	if(getvalues()){
		if(validateData()){
			if (flag == 1) {
				document.mainForm.act.value = "<%=WebActionConstant.SAVE_ACTION%>";
				document.mainForm.submit();
			} else {
				var orgId = "<%=dto.getFromOrganizationId()%>";
				var userId = "<%=dto.getCreatedBy()%>";
				var groupId = document.mainForm.fromDept.value;
				var procdureName = "备件申领流程";
				var flowCode = "";
				var paramObj = new Object();
				paramObj.orgId = orgId;
				paramObj.useId = userId;
				paramObj.groupId = groupId;
				paramObj.procdureName = procdureName;
				paramObj.flowCode = flowCode;
				paramObj.submitH = "submitH()";
				assign(paramObj);
			}
		}
	}
}


function submitH() {
	document.mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
	document.mainForm.transStatus.value = "<%=DictConstant.IN_PROCESS%>";
	document.mainForm.submit();
}

function validateData(){
	var validate = false;
	var fieldNames = "quantity";
	var fieldLabels = "数量";
	var validateType = EMPTY_VALIDATE;
	validate = formValidate(fieldNames, fieldLabels, validateType);
	if(validate){
		validateType = POSITIVE_INT_VALIDATE;
		validate = formValidate(fieldNames, fieldLabels, validateType);
	}
	return validate;
}


function getvalues() {
	var tab = document.getElementById("dataTable");
	if (tab.rows.length == 0 || (tab.rows[0].style.display == 'none' && tab.rows.length == 1)) {
		alert("没有选择行数据，请选择行数据！");
		return false;
	}
	return true;
}


function do_print() {
	var headerId=document.mainForm.transId.value;
	var url="/servlet/com.sino.ams.spare.servlet.SpareAttemperServlet?act=print&transId="+headerId;
	var  style = 'width=1024,height=670,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=yes';
	window.open(url, "", style);
}


function checkQty(obj){
	var id = obj.id.substring(8,obj.id.length);
	var qtyObj = document.getElementById("quantity"+id);
	if (isNaN(obj.value)) {
		alert("入库数量必须是数字");
		obj.focus();
		return;
	}
	if (!(Number(obj.value)>0)) {
		alert("入库数量必须大于0！");
		obj.focus();
		return;
	}
	do_SetLineCount(obj);
}


function do_SetLineCount(lineBox){
	if(!mainForm.allTransCount){
		return;
	}
	if(!mainForm.allTransCount.checked){
		return
	}
	var id = lineBox.id;
	var lineValue = lineBox.value;
	var fields = document.getElementsByName("quantity");
	var dataTable = document.getElementById("dataTable");
	var rows = dataTable.rows;
	var row = null;
	var checkObj = null;
	var checkedCount = getCheckedBoxCount("subCheck");
	for(var i = 0; i < fields.length; i++){
		if(checkedCount > 0){
			row = rows[i];
			checkObj = row.childNodes[0].childNodes[0];
			if(!checkObj.checked){
				continue;
			}
		}
		if(fields[i].id == id){
			continue;
		}
		fields[i].value = lineValue;
	}
}

function do_Close(){
	self.close();
}	

function do_SaveOrder() {
    mainForm.act.value = "<%=AssetsActionConstant.SAVE_ACTION%>";
    mainForm.submit();
	document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}

function do_CancelOrder() {
	if(confirm("你正准备撤销本单据，确定吗？继续请点击“确定”按钮，否则请点击“取消”按钮!")){
		mainForm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
		mainForm.submit();
	}
}
</script>
</html>