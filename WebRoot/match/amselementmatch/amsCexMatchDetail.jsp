<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <title>建立资产目录与投资分类对应关系</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">  
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>
<body >
 <jsp:include page="/message/MessageProcess"/>
<%
	AmsElementMatchDTO aemDTO = (AmsElementMatchDTO) request.getAttribute(WebAttrConstant.AMS_ELEMENT_MATCH_DTO);
%>

<form name="mainFrm" method="POST">
    <script language="javascript">
        printTitleBar("投资分类与资产目录对应关系维护");
    </script>
    <table border="0" width="70%" id="table1" align = "center">
    	<caption>&nbsp;</caption>
    	<caption align = "left" style = "font-size:12px">请选择对应--资产目录：</caption>
        <tr>
            <td width="25%" align="right" height="22">资产目录名称：&nbsp;</td>
             <td width="50%" align="left" height="22">
            	<input type="text" name = "contentName" size = "40" onclick="do_SelectContent(); return false;" 
            	readonly class="input_style1" title="点击选择资产目录" style="width:80%;cursor:hand">&nbsp;<font style="color: red">*</font>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">资产目录代码：&nbsp;</td>
            <td width="50%" align="left" height="22" colspan="3">
            <input type="text" name="contentCode" class="input_style1" readonly size="40" style="width:80%"></td>
        </tr>
     </table>
     <p></p>
     <table border="0" width="70%" id="table1" align = "center">
     	<caption align = "left" style = "font-size:12px">请选择对应--投资分类： </caption>
     	<input type = "hidden" name = "amsCexId">
        <tr>
            <td width="25%" align="right" height="22">投资分类名称：&nbsp;</td>
            <td width="50%" align="left" height="22">
                <input type="text" name="investCatName" style="width:80%" style="width:100%;cursor:hand" readonly 
                class="input_style1" title="点击选择投资分类" onClick="do_SelectCex();">&nbsp;<font style="color: red">*</font>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">投资分类代码：&nbsp;</td>
            <td width="50%" align="left" height="22" >
             <input type="text" name="investCatCode" class="input_style1" readonly size="40"  style="width:80%">
            </td>
        </tr>
        <tr>
        	<tr>
            <td width="25%" align="right" height="22">投资大类：&nbsp;</td>
            <td width="50%" align="left" height="22" >
              <input type="text" name="investCategory1" readonly size="40" class="input_style1" style="width:80%">
            </td>
        </tr>
        <tr>
        	<tr>
            <td width="25%" align="right" height="22">投资种类：&nbsp;</td>
            <td width="50%" align="left" height="22">
            <input type="text" name="investCategory2" class="input_style1" readonly size="40" style="width:80%"></td>
        </tr>
    </table>
    <p align = "center">
    	<img src="/images/eam_images/save.jpg" alt="保存" onClick="do_Save(); return false;">&nbsp;&nbsp;
        <img src="/images/eam_images/back.jpg" alt="取消" onclick="history.go(-1);"></td>
    </p>
	<input type="hidden" name="act" value="">
</form>
</body>
</html>
<script>
function do_Save() {
		if(mainFrm.contentCode.value != ""){
			if(mainFrm.investCatCode.value != ""){
				var action = "<%=WebActionConstant.CREATE_ACTION%>";
				mainFrm.act.value = action;
				mainFrm.action = "com.sino.ams.match.amselementmatch.servlet.AmsElementMatchServlet?accessType=cex";
				mainFrm.submit();
			}else{
				alert("请选择对应投资分类!");
			}			
		}else{
			alert("请选择对应资产目录!");
		}
}

function do_SelectCex(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_CEX %>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
	} else {
        mainFrm.investCatCode.value = "";
        mainFrm.investCatName.value = "";
        mainFrm.investCategory1.value = "";
        mainFrm.investCategory2.value = "";
    }
}

function do_SelectContent() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_CONTENT_NOMATCH_CEX %>";
        var dialogWidth = 48;
        var dialogHeight = 30;
        var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                user = users[i];
                dto2Frm(user, "mainFrm");
            }
        } else {
            mainFrm.contentName.value = "";
            mainFrm.contentCode.value = "";
        }
}

</script>

