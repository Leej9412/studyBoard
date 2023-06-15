<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!-- 1. 최근에 작성된 글이 먼저 조회될것. -->
<!-- 2. UI 와 데이터는 분리 요청 처리할 것(페이징과 검색은 비동기로). -->
<!-- 3. 검색조건 : 작성자/내용/전체 -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.6/index.global.min.js'></script>
<div id="calendar" data-source="${cPath }/board/boardList_FC.json"></div>
 <script >
    let calendar = null;

    $(function () {
        const Calendar = FullCalendar.Calendar; // 캘린더 api 생성하기

        const calendarEl = document.getElementById('calendar'); // body에 캘린더 넣을 부분 태그선택

        calendar = new Calendar(calendarEl, {
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
            },
            events: calendarEl.dataset.source,
            locale: 'ko', // 한국어
            eventClick: function (info) {
               location.href="${cPath}/board/boardView.do?what="+info.event.id;
            }

        });
        calendar.render();
    });
</script>










