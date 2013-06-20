<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/td/newasset/headerIncludeTd.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<%
	TdAssetsTransHeaderDTO headerDTO = (TdAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
%>
<head>
	<title><%=headerDTO.getTransTypeValue()%></title>
</head>
<body  leftmargin="0" topmargin="0" onload="initPage();">
<form action="<%=TdURLDefineList.ASSETS_TRANS_SERVLET_TD%>" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<jsp:include page="/td/newasset/headerDetailTd.jsp" flush="true"/>
<fieldset style="border:1px solid #397DF3; position:absolute;top:123px;width:100%;height:80%">
    <legend>
        <img src="/images/eam_images/view_opinion.jpg" alt="查阅审批意见" onClick="do_ViewOpinion(); return false;">
        <img src="/images/eam_images/close.jpg" id="img6" alt="关闭" onClick="do_Close(); return false;">
    </legend>
<jsp:include page="/td/newasset/transLineDetailTd.jsp" flush="true"/>

</fieldset>
</form>
</body>
</html>
<script>
function initPage() {
    window.focus();
    do_SetPageWidth();
}

function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.td.newasset.servlet.EtsFaTdAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=860,height=495,left=100,top=130";
	window.open(url, winName, style);
}

function do_ViewOpinion(){
	var appId = "<%=headerDTO.getTransId()%>";
	var tableName = "TD_ASSETS_TRANS_HEADER";
	viewOpinion(appId, tableName);
}
</script>