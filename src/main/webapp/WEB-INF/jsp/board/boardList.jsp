<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!-- 1. 최근에 작성된 글이 먼저 조회될것. -->
<!-- 2. UI 와 데이터는 분리 요청 처리할 것(페이징과 검색은 비동기로). -->
<!-- 3. 검색조건 : 작성자/내용/전체 -->

<table class="table table-bordered">
	<thead class="table-primary">
		<tr>
			<th><spring:message code="rnum" /></th>
			<th><spring:message code="freeboard.boTitle" /></th>
			<th><spring:message code="freeboard.boWriter" /></th>
			<th><spring:message code="freeboard.boDate" /></th>
			<th><spring:message code="freeboard.boHit" /></th>
		</tr>
	</thead>
	<tbody id="listBody" data-view-url="${cPath}/board/boardView.do">
	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
				<div class="pagingArea d-flex justify-content-center">
				
				</div>
				<div id="searchUI" class="row d-flex justify-content-center">
					<div class="col-auto">	
						<select name="searchType" class="form-control">
							<option value><spring:message code="all" /></option>
							<option value="writer"><spring:message code="freeboard.boWriter" /></option>
							<option value="content"><spring:message code="freeboard.boContent" /></option>
						</select>
					</div>
					<div class="col-auto">	
						<input type="text" name="searchWord"  class="form-control col-auto" />
					</div>
					
					<security:authorize url="/board/boardInsert.do">
						<div class="col-auto">	
							<input type="button" value="<spring:message code="search" />" id="searchBtn" class="btn btn-primary"/>
							<a href="${cPath }/board/boardInsert.do" class="btn btn-secondary"><spring:message code="regist" /></a>
						</div>
					</security:authorize>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form name="searchForm" method="post">
	<security:csrfInput/>
	<input type="hidden" name="page" placeholder="page"/>
	<input type="hidden" name="searchType" placeholder="searchType"/>
	<input type="hidden" name="searchWord" placeholder="searchWord"/>
</form>
<script src="${cPath }/resources/js/board/boardList.js"></script>











