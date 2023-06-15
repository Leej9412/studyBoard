<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath }">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="${cPath }">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Addons
            </div>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/chatting/roomList'/>">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>채팅</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/board/boardList.do'/>">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>게시글 목록</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/board/boardList_FC.do'/>">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>게시글 목록(FullCalendar)</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <security:authorize url="/group/GROUP1/groupView">
	            <li class="nav-item">
	                <a class="nav-link" href="${cPath }/group/GROUP1/groupView">
	                    <i class="fas fa-fw fa-table"></i>
	                    <span>GROUP1_TASK_view</span></a>
	            </li>
            </security:authorize>
            <security:authorize url="/group/GROUP1/groupEdit">
	            <li class="nav-item">
	                <a class="nav-link" href="${cPath }/group/GROUP1/groupEdit">
	                    <i class="fas fa-fw fa-table"></i>
	                    <span>GROUP1_TASK_edit</span></a>
	            </li>
            </security:authorize>
            <security:authorize url="/group/GROUP2/groupView">
	            <li class="nav-item">
	                <a class="nav-link" href="${cPath }/group/GROUP2/groupView">
	                    <i class="fas fa-fw fa-table"></i>
	                    <span>GROUP2_TASK_view</span></a>
	            </li>
            </security:authorize>
            <security:authorize url="/group/GROUP2/groupEdit">
	            <li class="nav-item">
	                <a class="nav-link" href="${cPath }/group/GROUP2/groupEdit">
	                    <i class="fas fa-fw fa-table"></i>
	                    <span>GROUP2_TASK_edit</span></a>
	            </li>
			</security:authorize>
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            <!-- Sidebar Message -->
            <div class="sidebar-card d-none d-lg-flex">
                <p class="text-center mb-2"><strong>SB Admin Pro</strong> is packed with premium features, components, and more!</p>
                <a class="btn btn-success btn-sm" href="https://startbootstrap.com/theme/sb-admin-pro">Upgrade to Pro!</a>
            </div>