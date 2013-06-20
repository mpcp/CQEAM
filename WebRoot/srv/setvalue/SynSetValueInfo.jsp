<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.soa.mis.srv.valueinfo.dto.SBSYSYInquiryVSetValueInfoDTO" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>MIS系统值集</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>


</head>

<body  onkeydown="autoExeFunction('do_Search()');" onload="initPage();">
<%
	SBSYSYInquiryVSetValueInfoDTO dto = (SBSYSYInquiryVSetValueInfoDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String pageTitle = "MIS系统值集";
%>
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form name="mainFrm" method="post"  action="/servlet/com.sino.soa.mis.srv.valueinfo.servlet.SBSYSYInquiryVSetValueInfoServlet">
    <script type="text/javascript">
    	printTitleBar("<%=pageTitle%>");
    </script>
    <table width="100%" topmargin="0" border="0" bgcolor="#EFEFEF" style="width:100%">
        <input name="act" type="hidden">
        <tr>
			<td align="right" width="6%">值集名称：</td>
			<td width="10%">
                <select name="flexValueName" id="flexValueName" class="noEmptyInput" style="width:80%"><%=dto.getFlexValetSetOption()%> </select>
			</td>
			<td align="right" width="6%">最后更新开始日期：</td>
            <td width="10%">
                <input type="text" name="startLastUpdateDate" value="<%=dto.getStartLastUpdateDate()%>" id="startLastUpdateDate" style="width:60%" title="点击选择日期" readonly><img
                    src="/images/calendar.gif" alt="点击选择日期" onclick="gfPop.fStartPop(startLastUpdateDate, endLastUpdateDate);">
            </td>
            <td align="right" width="6%">最后更新结束日期：</td>
            <td width="10%">
                <input type="text" name="endLastUpdateDate" value="<%=dto.getEndLastUpdateDate()%>" id="endLastUpdateDate" style="width:60%" title="点击选择日期" readonly><img
                    src="/images/calendar.gif" alt="点击选择日期" onclick="gfPop.fEndPop(startLastUpdateDate, endLastUpdateDate);">
            </td>
			<td width="10%" align="right"><img src="/images/eam_images/synchronize.jpg" style="cursor:'hand'" onclick="do_syschronize();" alt="同步"></td>
        </tr>
    </table>
</form>
<div style="position:absolute;top:92%;left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm"
        scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">

    function do_Search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.mainFrm.submit();
    }
    
   /**
	* 功能：同步数据
	*/
	function do_syschronize() {
		var flexValueName = document.getElementById("flexValueName").value;
		if(flexValueName ==""){
            alert("请选择值集名称！");
            return false;
        }
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
		mainFrm.action = "/servlet/com.sino.soa.mis.srv.valueinfo.servlet.SBSYSYInquiryVSetValueInfoServlet";
		mainFrm.act.value = "<%=WebActionConstant.SYSCHRONIZE_ACTION%>";
		mainFrm.submit();
	}
	
    function initPage() {
        do_SetPageWidth();
    }
</script>
</html>

