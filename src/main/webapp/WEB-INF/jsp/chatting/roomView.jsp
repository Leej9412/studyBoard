<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${cPath }/resources/js/stompjs/stomp.umd.min.js"></script>
</head>
<body>
<security:authentication property="principal.realUser" var="authUser"/>
<h4>${room.roomTitle }</h4>
<input type="text" id="msgIpt" />
<button id="sendBtn">send</button>
<div id="msgArea"></div>
<script>
window.addEventListener("DOMContentLoaded", event=>{
	const client = new StompJs.Client({
		brokerURL:"ws://localhost${cPath}/ws/chatting",
		debug:function(str){
			console.log(str);
		},
		onConnect:function(frame){
			const subscription1 = this.subscribe("${room.destination}", function(msgFrame){
				let messageVO = JSON.parse(msgFrame.body);
				msgArea.innerHTML += `<p>[\${messageVO.sender}]\${messageVO.message}</p>`;
			});
			const subscription2 = this.subscribe("/user${room.destination}", function(msgFrame){
				let messageVO = JSON.parse(msgFrame.body);
				msgArea.innerHTML += `<p class='private'>PRIVATE 메시지, [\${messageVO.sender}]\${messageVO.message}<p>`;
			});
		}
	});
	client.activate();
	sendBtn.addEventListener("click", event=>{
		let sender = "${authUser.memId}";
		let message = msgIpt.value;
		msgIpt.value="";
		let payload = {sender:sender, message:message}
		client.publish({
			destination:"${room.destination}", 
			body:JSON.stringify(payload),
			headers:{
				"content-type":"application/json"
			}
		});
	});
});
</script>
</body>
</html>