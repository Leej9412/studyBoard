package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVOWrapper;
import kr.or.ddit.websocket.vo.MessageVO;
import kr.or.ddit.websocket.vo.MessageVO.MessageType;

@Controller
@RequestMapping("/board/boardInsert.do")
public class BoardInsertController {
	@Inject
	private BoardService service;
	@Inject
	private SimpMessagingTemplate messagingTmpl;
	
	@ModelAttribute("freeboard")
	public BoardVO board(Authentication  authentication) {
		MemberVOWrapper wrapper = (MemberVOWrapper) authentication.getPrincipal();
		BoardVO board = new BoardVO();
		board.setBoWriter(authentication.getName());
		return board;
	}
	
	@GetMapping
	public String insertForm() {
		return "board/boardForm";
	}
	
	@Resource(name="wsSessionList")
	private List<WebSocketSession> sessionList;
	
	@PostMapping
	public String insert(
		@Validated(InsertGroup.class) @ModelAttribute("freeboard") BoardVO board
		, BindingResult errors
	) {
		if(!errors.hasErrors()) {
			service.createBoard(board);
			// 알림 메시지 전송
			 
			String payload = String.format("%d 번 글이 새로 등록됨.", board.getBoNo());
			MessageVO messageVO = MessageVO.builder().messageType(MessageType.INFO).message(payload).build();
			messagingTmpl.convertAndSend("/topic/push", messageVO);
			messagingTmpl.convertAndSendToUser("a001", "/queue/private", messageVO);
//			sessionList.forEach((ws)->{
//				try {
//					ws.sendMessage(new TextMessage(payload));
//				} catch (IOException e) {
//					throw new RuntimeException(e);
//				}
//			});
			return "redirect:/board/boardList.do";
		}else {
			return "board/boardForm";
		}
	}
}












