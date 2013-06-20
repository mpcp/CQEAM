<%@ page import="com.sino.soa.common.SrvURLDefineList" %>
<%@ page import="com.sino.soa.common.SrvWebActionConstant" %>
<%@ page import="com.sino.soa.mis.srv.transassetdistribution.dto.SrvAssetDistributionDTO" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>

</head>
<body leftmargin="0" topmargin="0"  onload="initPage();" onkeydown="autoExeFunction('query();');">
<%
    SrvAssetDistributionDTO dto = (SrvAssetDistributionDTO) request.getAttribute(QueryConstant.QUERY_DTO);
	String assetsType = StrUtil.nullToString(dto.getAssetsType());
	String pageTitle = "ͬ���ʲ���������Ϣ(ODI)";
	
	if((AssetsWebAttributes.TD_ASSETS_TYPE).equals(assetsType)){
		pageTitle = "ͬ��TD�ʲ���������Ϣ(ODI)";
	}
%>
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form action="<%=SrvURLDefineList.TD_TRANS_ASSET_DISTRIBUTION_SERVLET1 %>" method="post" name="mainFrm">
<script type="text/javascript">
     printTitleBar("<%=pageTitle%>");
</script>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="assetsType" value="<%=assetsType%>">
    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td align="right" width="10%" align="right">�˲����룺</td>
            <td align="left" width="15%"><select class="noEmptyInput" name="bookTypeCode" id="bookTypeCode" style="width:100%" size="1"><%=dto.getOrgOption()%></select><td>
            <td align="right" width="6%">��ʼʱ�䣺</td>
             <td align="left" style="width:20%">
                <input type="text" name="stratLastUpdateDate" value="<%=dto.getStratLastUpdateDate()%>" style="width:60%" title="���ѡ������" readonly
                       class="readonlyInput" onclick="gfPop.fStartPop(stratLastUpdateDate, endLastUpdateDate);"><img
                    src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(stratLastUpdateDate, endLastUpdateDate);">
            </td>
            <td align="right" width="6%">����ʱ�䣺</td>
            <td align="left" style="width:20%">
                <input type="text" name="endLastUpdateDate" value="<%=dto.getEndLastUpdateDate()%>" style="width:60%" title="���ѡ������" readonly
                       class="readonlyInput" onclick="gfPop.fEndPop(stratLastUpdateDate, endLastUpdateDate);"><img
                    src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(stratLastUpdateDate, endLastUpdateDate);">
            </td>
            <td width="15%" align="left">
                <img src="/images/eam_images/synchronize.jpg" alt="���ͬ��" onclick="do_SubmitSyn();">
            </td>
        </tr>
    </table>
</form>


</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">

	function do_SearchOrder() {
		if(mainFrm.transferType){
			mainFrm.transferType.disabled = false;
		}
	    mainFrm.act.value = "<%=SrvWebActionConstant.QUERY_ACTION%>";
	    mainFrm.submit();
	}
	
	function do_SubmitSyn() {
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	    mainFrm.act.value = "<%=SrvWebActionConstant.INFORSYN%>";
	    mainFrm.submit();
	}
	
	function initPage() {
        do_SetPageWidth();
    }
</script>
</html>