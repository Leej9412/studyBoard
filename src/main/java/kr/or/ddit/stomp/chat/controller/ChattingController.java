package kr.or.ddit.stomp.chat.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.stomp.chat.vo.ChatRoom;

@Controller
@RequestMapping("/chatting")
public class ChattingController {
	
	@Resource(name="chatRooms")
	private Map<String, ChatRoom> chatRooms;
	@Inject
	private SimpMessagingTemplate msgTmpl;
	
	@RequestMapping("roomList")
	public String roomList(Model model) {
		model.addAttribute("roomList", chatRooms.values());
		return "chatting/roomList";
	}
	
	@PostMapping(value="makeRoom", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ChatRoom makeRoom(Authentication authentication, String roomTitle) {
		ChatRoom newRoom = new ChatRoom(msgTmpl, authentication.getName(), roomTitle);
		chatRooms.put(newRoom.getRoomId(), newRoom);
		msgTmpl.convertAndSend("/chatting/roomList", newRoom);
		return newRoom;				
	}
	
	@GetMapping("enter/{roomId}")
	public String enterRoom(@PathVariable String roomId, Model model) {
		ChatRoom findedRoom = chatRooms.get(roomId);
		model.addAttribute("room", findedRoom);
		return "chatting/roomView";
	}
	
}





