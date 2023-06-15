<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<input type="text" placeholder="새로 만들 방의 제목" id="roomTitle"/>
<button id="newRoomBtn">방 만들기</button>
<ul id="roomUL">
	<c:if test="${not empty roomList }">
		<c:forEach items="${roomList }" var="room">
			<li class="room" data-room-id="${room.roomId }">${room.roomTitle }[${room.owner }]</li>
		</c:forEach>
	</c:if>
</ul>
<script>
window.addEventListener("DOMContentLoaded", ()=>{
	roomUL.addEventListener("click", event=>{
		if(event.target.classList.contains("room")){
			let roomTag = event.target;
			let roomId = roomTag.dataset.roomId;
			const windowFeatures = "left=100,top=100,width=320,height=320"
			window.open(`${cPath}/chatting/enter/\${roomId}`, "mozillaWindow", windowFeatures);
		}
	});
	
	const updateRooms = function(room){
		let newLi = document.createElement("li");
		newLi.classList.add("room");
		newLi.dataset.roomId=room.roomId;
		newLi.innerHTML = `\${room.roomTitle}[\${room.owner}]`;
		roomUL.appendChild(newLi);
	}
	
	newRoomBtn.addEventListener("click", event=>{
		if(roomTitle.value){
			let makeUrl = "${cPath}/chatting/makeRoom";
			let data = new FormData();
			data.append("roomTitle", roomTitle.value);
			fetch(makeUrl, {
				method:"post",
				body:data,
				headers:headers
			}).then(resp=>resp.json())
			.then(()=>{
				console.log("등록 성공")
			});
		}
	});
	
	const client = new StompJs.Client({
		brokerURL:"ws://localhost${cPath}/ws/rooms",
		debug:function(str){
			console.log(str);
		},
		onConnect:function(frame){
			const subscription1 = this.subscribe("/chatting/roomList", function(msgFrame){
				let room = JSON.parse(msgFrame.body);
				updateRooms(room);
			});
		}
	});
	client.activate();
});
</script>
