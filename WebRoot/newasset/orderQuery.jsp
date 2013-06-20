<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.*" %>
<%@page import="com.sino.ams.newasset.scrap.constant.ScrapURLListConstant"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
	<head>
		<title>单据打印查询</title>
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
		<script language="javascript" src="/WebLibary/js/Constant.js"></script>
		<script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
		<script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
		<script language="javascript" src="/WebLibary/js/calendar.js"></script>
		<script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
		<script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
		<script language="javascript" src="/WebLibary/js/calendar.js"></script>
		<script language="javascript" src="/WebLibary/js/CheckboxProcess.js">
</script>
		<script language="javascript" src="/WebLibary/js/AppStandard.js">
</script>
	</head>
	<%
		RequestParser parser = new RequestParser();
		parser.transData(request);
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) parser
				.getAttribute(QueryConstant.QUERY_DTO);
		String provinceCode = dto.getServletConfig().getProvinceCode();
		String disapleProp = "";
		//if (provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)) {
		//	disapleProp = "disabled";
		//}
		String transferType = dto.getTransferType();
		RowSet rows = (RowSet) request
				.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
		String transType = dto.getTransType();
		String pageTitle = "";
		String orderNoName = "";
		String deptNameDesc = "";
		if (transType.equals(AssetsDictConstant.ASS_RED)) {
			pageTitle = "资产调拨管理>>调拨单据查询";
			orderNoName = "调拨单号";
			deptNameDesc = "调出部门";
		} else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
			pageTitle = "处置单据查询";
			orderNoName = "处置单号";
			deptNameDesc = "处置部门";
		} else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
			pageTitle = "报废单据查询";
			orderNoName = "报废单号";
			deptNameDesc = "报废部门";
		} else if (transType.equals(AssetsDictConstant.ASS_DIS_OTHER)) {
			pageTitle = "报废单据查询";
			orderNoName = "报废单号";
			deptNameDesc = "报废部门";
		} else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
			pageTitle = "闲置单据查询";
			orderNoName = "闲置单号";
			deptNameDesc = "闲置部门";
		} else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
			pageTitle = "减值单据查询";
			orderNoName = "减值单号";
			deptNameDesc = "减值部门";
		} else if (transType.equals(AssetsDictConstant.ASS_SHARE)) {
			pageTitle = "共享单据查询";
			orderNoName = "共享单号";
			deptNameDesc = "共享部门";
		} else if (transType.equals(AssetsDictConstant.ASS_SELL)) {
			pageTitle = "销售单据查询";
			orderNoName = "销售单号";
			deptNameDesc = "销售部门";
		} else if (transType.equals(AssetsDictConstant.ASS_RENT)) {
			pageTitle = "出租单据查询";
			orderNoName = "出租单号";
			deptNameDesc = "出租部门";
		} else if (transType.equals(AssetsDictConstant.ASS_DONA)) {
			pageTitle = "捐赠单据查询";
			orderNoName = "捐赠单号";
			deptNameDesc = "捐赠部门";
		}
		String srcPage = parser.getParameter("srcPage");
	%>

	<body onkeydown="autoExeFunction('do_SearchOrder()');"
		onload="initPage();">
		<%=WebConstant.WAIT_TIP_MSG%>
		<form action="<%=AssetsURLList.ORDER_QUERY_SERVLET%>" name="mainFrm"
			method="post">
			<script type="text/javascript">
printTitleBar("<%=pageTitle%>")
</script>
			<input type="hidden" name="act" value="">
			<input type="hidden" name="transType" value="<%=transType%>">
			<table border="0"
				style="width: 100%; TABLE-LAYOUT: fixed; word-break: break-all">
				<%
					if (transType.equals(AssetsDictConstant.ASS_RED)) {
				%>
				<tr>
					<td width="8%" align="right">
						单据类型：
					</td>
					<td width="11%">
						<select class="select_style1" name="transferType"
							style="width: 100%" <%=disapleProp%>><%=dto.getTransferTypeOption()%></select>
					</td>
					<td width="8%" align="right"><%=orderNoName%>：
					</td>
					<td width="12%">
						<input class="input_style1" type="text" name="transNo"
							style="width: 100%" value="<%=dto.getTransNo()%>">
					</td>
					<td width="8%" align="right">
						创建日期：
					</td>
					<td width="19%">
						<input class="input_style1" type="text" name="startDate"
							value="<%=dto.getStartDate()%>" style="width: 48%" title="点击选择日期"
							readonly class="readonlyInput"
							onclick="gfPop.fStartPop(startDate, endDate)">
						<input class="input_style1" type="text" name="endDate"
							value="<%=dto.getEndDate()%>" style="width: 48%" title="点击选择日期"
							readonly class="readonlyInput"
							onclick="gfPop.fEndPop(startDate, endDate)">
					</td>
				</tr>
				<tr>
					<td align="right">
						标签号：
					</td>
					<td>
						<input class="input_style1" type="text" name="barcodeSearch"
							style="width: 100%" value="<%=dto.getBarcodeSearch()%>">
					</td>
					<td align="right">
						单据状态：
					</td>
					<td align="center">
						<select class="select_style1" name="transStatus"
							style="width: 100%"><%=dto.getStatusOption()%></select>
					</td>
					<td align="right" colspan="2">
						<img src="/images/eam_images/search.jpg" alt="点击查询"
							onclick="do_SearchOrder();">
						<img src="/images/eam_images/export.jpg" title="点击导出"
							onclick="do_ExportOrder();">
					</td>
				</tr>
				<%
					} else {
				%>
				<tr>
					<td width="10%" align="right"><%=orderNoName%>：
					</td>
					<td width="20%">
						<input class="input_style1" type="text" name="transNo"
							style="width: 100%" value="<%=dto.getTransNo()%>">
					</td>
					<td width="10%" align="right">
						创建日期：
					</td>
					<td width="30%">
						<input class="input_style1" type="text" name="startDate"
							value="<%=dto.getStartDate()%>" style="width: 40%" title="点击选择日期"
							readonly class="readonlyInput"
							onclick="gfPop.fStartPop(startDate, endDate)">
						<img src="/images/calendar.gif" alt="点击查询"
							onclick="gfPop.fStartPop(startDate, endDate);">
						<input class="input_style1" type="text" name="endDate"
							value="<%=dto.getEndDate()%>" style="width: 40%" title="点击选择日期"
							readonly class="readonlyInput"
							onclick="gfPop.fEndPop(startDate, endDate)">
						<img src="/images/calendar.gif" alt="点击查询"
							onclick="gfPop.fEndPop(startDate, endDate);">
					</td>
					<td width="25%" align="right">
						<img src="/images/eam_images/search.jpg" alt="点击查询"
							onclick="do_SearchOrder();">
						<img src="/images/eam_images/export.jpg" title="点击导出"
							onclick="do_ExportOrder();">
					</td>
				</tr>
				<%
					}
				%>

			</table>
			<%--<input type="hidden" name="act" value="<%=action%>">--%>
		</form>
		<%
			if (!transType.equals(AssetsDictConstant.ASS_RED)) {
		%>
		<div id="headDiv"
			style="overflow-y: scroll; overflow-x: hidden; position: absolute; top: 49px; left: 0px; width: 100%">
			<table border="1" width="100%" class="eamHeaderTable" cellpadding="0"
				cellspacing="0">
				<tr height="23px">
					<td align=center width="18%"><%=orderNoName%></td>
					<td align=center width="8%">
						单据状态
					</td>
					<td align=center width="16%"><%=deptNameDesc%></td>
					<td align=center width="8%">
						申请人
					</td>
					<td align=center width="10%">
						创建日期
					</td><td align=center width="16%">报废说明</td>
				</tr>
			</table>
		</div>

		<div id="dataDiv"
			style="overflow: scroll; height: 72%; width: 100%; position: absolute; top: 72px; left: 0px"
			align="left"
			onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
			<table width="100%" border="1" bordercolor="#666666">
				<%
					if (rows != null && !rows.isEmpty()) {
							for (int i = 0; i < rows.getSize(); i++) {
								Row row = rows.getRow(i);
				%>

				<tr class="dataTR"
					onclick="showDetail('<%=row.getValue("TRANS_ID")%>')">
					<td width="18%>">
						<input type="text" class="finput2" readonly
							value="<%=row.getValue("TRANS_NO")%>">
					</td>
					<td width="8%>">
						<input type="text" class="finput2" readonly
							value="<%=row.getValue("TRANS_STATUS_DESC")%>">
					</td>
					<td width="16%>">
						<input type="text" class="finput" readonly
							value="<%=row.getValue("FROM_DEPT_NAME")%>">
					</td>
					<td width="8%>">
						<input type="text" class="finput2" readonly
							value="<%=row.getValue("CREATED")%>">
					</td>
					<td width="10%>">
						<input type="text" class="finput2" readonly
							value="<%=row.getValue("CREATION_DATE")%>">
					</td>
					<td width="16%"><input type="text" class="finput" readonly value="<%=row.getValue("CREATED_REASON")%>"></td>
				</tr>
				<%
					}
						}
				%>
			</table>
		</div>

		<%
			} else {
		%>

		<div id="headDiv"
			style="overflow-y: scroll; overflow-x: hidden; position: absolute; top: 69px; left: 0px; width: 100%">
			<table border="1" width="100%" class="eamHeaderTable" cellpadding="0"
				cellspacing="0">
				<tr height="23px">
					<td align=center width="18%">
						调拨单号
					</td>
					<td align=center width="10%">
						单据类型
					</td>
					<td align=center width="16%">
						调出单位
					</td>
					<td align=center width="12%">
						调拨申请人
					</td>
					<td align=center width="10%">
						调拨申请日期
					</td>
					<td align=center width="10%">
						调拨单状态
					</td>
				</tr>
			</table>
		</div>
		<div id="dataDiv"
			style="overflow: scroll; height: 72%; width: 100%; position: absolute; top: 92px; left: 0px"
			align="left"
			onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
			<table width="100%" border="1" bordercolor="#666666" id="dataTab">
				<%
					String transId = "";
						if (rows != null && !rows.isEmpty()) {
							for (int i = 0; i < rows.getSize(); i++) {
								Row row = rows.getRow(i);
								transferType = row.getStrValue("TRANSFER_TYPE");
								int index = ArrUtil.getArrIndex(
										AssetsDictConstant.TRANS_OPT_VALUES,
										transferType);
								if (index >= 0) {
									transferType = AssetsDictConstant.TRANS_OPT_LABELS[index];
								}
								transId = row.getStrValue("TRANS_ID");
				%>

				<tr class="dataTR" onclick="showDetail('<%=transId%>')">
					<td width="18%">
						<input type="text" class="finput2" readonly
							value="<%=row.getValue("TRANS_NO")%>">
					</td>
					<td width="10%">
						<input type="text" class="finput2" readonly
							value="<%=transferType%>">
					</td>
					<td width="16%">
						<input type="text" class="finput" readonly
							value="<%=row.getValue("FROM_DEPT_NAME")%>">
					</td>
					<td width="12%">
						<input type="text" class="finput" readonly
							value="<%=row.getValue("CREATED")%>">
					</td>
					<td width="10%">
						<input type="text" class="finput2" readonly
							value="<%=row.getValue("CREATION_DATE")%>">
					</td>
					<td width="10%">
						<input type="text" class="finput" readonly
							value="<%=row.getValue("TRANS_STATUS_DESC")%>">
					</td>
				</tr>
				<%
					}
						}
				%>
			</table>
		</div>


		<%
			}
			if (rows != null && !rows.isEmpty()) {
		%>
		<div id="pageNaviDiv" style="position: absolute; top: 87%; left: 0;"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
		<%
			}
		%>
	</body>
	<iframe width=174 height=189 name="gToday:normal:calendar.js"
		id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm"
		scrolling="no" frameborder="0"
		style="visibility: visible; z-index: 999; position: absolute; left: -500px; top: 0;">
	</iframe>
</html>
<script type="text/javascript">
function initPage() {
	do_SetPageWidth();
}

function do_SearchOrder() {
if(mainFrm.transferType){
		mainFrm.transferType.disabled = false;
		
		if (mainFrm.transferType.value == "INN_DEPT_RFU") {
			mainFrm.transType.value = "ASS-RFU";
			<%dto.setTransType("ASS-RFU");%>
		}
		
	}
    mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function showDetail(transId){
    var transType = mainFrm.transType.value;
    var url = "";
    if(transType=="ASS-DIS"){
      url = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsDisTransHeaderServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&transType=" + transType+"&transId="+transId;
    }else if(transType=="ASS-DIS-OTHER"){
      url = "<%=ScrapURLListConstant.SCRAP_SERVELT%>?act=<%=AssetsActionConstant.DETAIL_ACTION%>&transType=" + transType+"&transId="+transId;
    }else if (transType=="ASS-RED"){//调拨
        url = "<%=AssetsURLList.ASSETS_ALLOCATION_SERVLET%>?act=<%=AssetsActionConstant.DETAIL_ACTION%>&transType=" + transType+"&transId="+transId;
    }else if (transType=="ASS-FREE"){//闲置
        url = "/servlet/com.sino.ams.newasset.free.servlet.FreeAssetsHeaderServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&transType=" + transType+"&transId="+transId;
    }else{
        url = "<%=AssetsURLList.ASSETS_TRANS_SERVLET%>?act=<%=AssetsActionConstant.DETAIL_ACTION%>&transType=" + transType+"&transId="+transId;
    }
    var factor = 0.92;
    var width = window.screen.availWidth * factor;
    var height = window.screen.availHeight * factor;
    var left = window.screen.availWidth * (1 - factor)/ 2;
    var top = window.screen.availHeight * (1 - factor)/ 10;
    var style = "width="
            + width
            + "px,height="
            + height
            + "px,top="
            + top
            + "px,left="
            + left
            + "px,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, 'transferWin', style);
}

function do_ExportOrder(){
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}
</script>