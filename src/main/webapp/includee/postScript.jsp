<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${pageContext.request.contextPath }/resources/admin2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath }/resources/admin2/js/sb-admin-2.min.js"></script>


<script src="${cPath }/resources/js/stompjs/stomp.umd.min.js"></script>

<script>
// 	let pushWs = new WebSocket("ws://localhost${cPath}/ws/push");
// 	pushWs.onmessage=function(event){
// 		alert(event.data);
// 	}
	window.addEventListener("DOMContentLoaded", event=>{
		const client = new StompJs.Client({
			brokerURL:"ws://localhost${cPath}/ws/push",
			debug:function(str){
				console.log(str);
			},
			onConnect:function(frame){
				const subscription1 = this.subscribe("/topic/push", function(msgFrame){
					let messageVO = JSON.parse(msgFrame.body);
					Swal.fire({
						  icon: messageVO.messageType.toLowerCase(),
						  title: '전체 푸시 메시지',
						  text: messageVO.message
						})
				});
				const subscription2 = this.subscribe("/user/queue/private", function(msgFrame){
					let messageVO = JSON.parse(msgFrame.body);
					alert(`PRIVATE 메시지 [\${messageVO.message}]`);
				});
			}
		});
		client.activate();
	});
</script>