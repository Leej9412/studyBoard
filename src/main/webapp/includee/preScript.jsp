<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Custom fonts for this template-->
<link href="<c:url value='/resources/admin2/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css">
<link
    href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
    rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<c:url value='/resources/admin2/css/sb-admin-2.min.css' />" rel="stylesheet">


<script type="text/javascript" src="${cPath}/resources/js/sweetalert2/sweetalert2.all.min.js"></script>
<link rel="stylesheet" href="${cPath}/resources/js/sweetalert2/sweetalert2.min.css" >
<script type="text/javascript" src="${cPath}/resources/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="${cPath}/resources/js/jquery.serializeObject.min.js"></script>
<script>

	let headerName = $("meta[name='_csrf_header']").attr("content");
	let headerValue = $("meta[name='_csrf']").attr("content");
	let headers = {}
	headers[headerName] = headerValue;
	$.CPATH = "${cPath}";
	$.ajaxSetup({
		headers:headers
	})
	$(document).ajaxError(function(event, jqXHR, settings, error){
		console.log(`비동기 요청[\${settings.url}, \${settings.type}] 실패, 상태코드 : \${jqXHR.status}, 에러메시지 : \${error}`);
	});
</script>