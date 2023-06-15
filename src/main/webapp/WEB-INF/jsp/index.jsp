<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<h1>Main page</h1>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal"/>
</security:authorize>
<hr />
${sessionScope }