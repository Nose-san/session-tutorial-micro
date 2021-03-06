<!DOCTYPE html>
<html class="no-js">
<head>
    <%@ include file="../common/include.jsp" %>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey">
    <tiles:insertAttribute name="title" ignore="true" />
</c:set>
<title><spring:message code="${titleKey}" text="session-tutorial-init" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"
    media="screen, projection">
</head>
<body>
    <div class="container">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="body" />
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>