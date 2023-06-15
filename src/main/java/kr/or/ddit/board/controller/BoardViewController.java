package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * @Pre/PostAuthorize 기반의 접근 제어.
 * 1. 컨트롤러 레이어를 대상으로 어노테이션 기반의 접근 제어를 하기 위한 AOP 위빙 설정이 필요함 
 *    -> servlet-security.xml -> global-method-security
 * 2. 접근 허용 여부를 결정할 유틸리티 작성 : kr.or.ddit.security.AuthorizeUtils 
 * 3. 접근 제어가 필요한 핸들러 메소드에 @Pre/PostAuthorize 를 적용함.
 * 4. 해당 어노테이션에서 접근 허용 여부를 결정하는 표현식으로 spEL 을 사용함.
 *    
 *
 */
@Controller
public class BoardViewController {
	@Inject
	private BoardService service;
	
	@PreAuthorize("T(kr.or.ddit.security.AuthorizeUtils).hasAuthorityOnPre(authentication, #boNo)")
	@PostAuthorize("T(kr.or.ddit.security.AuthorizeUtils).hasAuthorityOnPost(authentication, #model.getAttribute('freeboard'), returnObject)")
	@RequestMapping("/board/boardView.do")
	public String boardView(@RequestParam("what") int boNo, Model model) {
		BoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("freeboard", board);
		return "board/boardView";
	}
}
