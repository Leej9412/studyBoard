package kr.or.ddit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.BoardInvalidPasswordException;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.UpdateGroup;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/boardUpdate.do")
@RequiredArgsConstructor
public class BoardUpdateController {

	private final BoardService service;
	
	@ModelAttribute("freeboard")
	public BoardVO board(@RequestParam("what") int boNo) {
		return service.retrieveBoard(boNo);
	}
	
	@GetMapping
	public String updateForm() {
		return "board/boardEdit";
	}
	
	@PostMapping
	public String updateBoard(
		@Validated(UpdateGroup.class) @ModelAttribute("freeboard") BoardVO board
		, Errors errors
		, Model model
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			try {
				service.modifyBoard(board);
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
			}catch (BoardInvalidPasswordException e) {
				model.addAttribute("message", "비밀번호 오류");
			}
		}else {
			viewName = "board/boardEdit";
		}
		return viewName;
	}
}












