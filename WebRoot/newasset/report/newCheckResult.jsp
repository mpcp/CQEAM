<%@ page import="com.sino.ams.newasset.constant.AssetsActionConstant" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�����ʲ��̵��</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="initPage();">
<%
	AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.NewAssetsCheckServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("�ʲ���������-->>�����ʲ��̵��أ�����˾��")
</script>
	<table width="100%" border="0" class="queryHeadBg">
		<tr>
			<td width="12%" align="right">��˾���ƣ�</td>
			<td width="20%"><select size="1" name="organizationId" style="width:100%"><%=dto.getOrgOpt()%></select></td>
			<td width="12%" align="right">�ʲ��������ڣ�</td>
			<td width="15%"><input type="text" name="startDate" style="cursor:hand;width:100%" title="���ѡ��ʼ����" readonly value="<%=dto.getStartDate()%>" onclick="gfPop.fStartPop(startDate, endDate);" size="20"></td>
			<td width="6%" align="center">����</td>
			<td width="15%"><input type="text" name="endDate" style="cursor:hand;width:100%" title="���ѡ���������" readonly value="<%=dto.getEndDate()%>" onclick="gfPop.fEndPop(startDate, endDate);"></td>
			<td width="20%" align="right">
			<img border="0" src="/images/eam_images/search.jpg"  onclick="do_Search();">
			<img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">
			</td>
		</tr>
	</table>
	<input name="act" type="hidden">
	<input name="companyName" type="hidden">
</form>


<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:840px">
	<table class="headerTable" border="1" width="100%">
		<tr height="22">
			<td width="22%" align="center">��˾����</td>
			<td width="13%" align="center">MIS��PDAɨ������</td>
			<td width="13%" align="center">����MIS����</td>
			<td width="13%" align="center">δ��MIS����</td>
			<td width="13%" align="center">��ʵ�����</td>
			<td width="13%" align="center">��ʵ�����</td>
            <td width="13%" align="center">�̵�ٷֱ�</td>
        </tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:370px;width:857px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

        		<%--<tr height="22" title="����鿴��˾����ͬ���̵����" style="cursor:hand" onClick="do_ShowDetail('83', '��ͬ')">--%>
			<%--<td width="22%">0</td>--%>
			<%--<td width="13%" align="right">0</td>--%>
			<%--<td width="13%" align="right">0</td>--%>
			<%--<td width="13%" align="right">0</td>--%>
			<%--<td width="13%" align="right">0</td>--%>
			<%--<td width="13%" align="right">0</td>--%>
            <%--<td width="13%" align="right">0</td>--%>
        <%--</tr>--%>
<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
		<tr height="22" title="����鿴��˾��<%=row.getValue("COMPANY")%>���̵����" style="cursor:hand" onClick="do_ShowDetail('<%=row.getValue("ORGANIZATION_ID")%>', '<%=row.getValue("COMPANY")%>')">
			<td width="22%"><%=row.getValue("COMPANY")%></td>
			<td width="13%" align="right"><%=row.getValue("TOTAL_COUNT")%></td>
			<td width="13%" align="right"><%=row.getValue("SCANED_COUNT")%></td>
			<td width="13%" align="right"><%=row.getValue("NOT_SCANED_COUNT")%></td>
			<td width="13%" align="right"><%=row.getValue("IDENTICAL_COUNT")%></td>
			<td width="13%" align="right"><%=row.getValue("IDENTICAL_RATE")%></td>
            <td width="13%" align="right"><%=row.getValue("SCANED_RATE")%></td>
        </tr>
<%
		}
	}
%>
	</table>
</div>
<%
	if(hasData){
%>
<div style="position:absolute;top:460px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function initPage(){
	do_SetPageWidth();
}

function do_Search(){
	document.mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	document.mainFrm.target = "_self";
	document.mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.NewAssetsCheckServlet";
	document.mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Export() {
    document.mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	document.mainFrm.target = "_self";
	document.mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.NewAssetsCheckServlet";
    document.mainFrm.submit();
}

function do_ShowDetail(organizationId, companyName){
	document.mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.NewAssetsFrmServlet";
	document.mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	var selObj = document.mainFrm.organizationId;
	selectSpecialOptionByItem(selObj, organizationId);
	document.mainFrm.companyName.value = companyName;
    var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
    window.open("/public/waiting2.htm", "reportWin", style);
    document.mainFrm.target = "reportWin";
    document.mainFrm.submit();
}
</script>