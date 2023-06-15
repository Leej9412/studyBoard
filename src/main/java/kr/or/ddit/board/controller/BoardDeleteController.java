package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.BoardInvalidPasswordException;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.DeleteGroup;

@Controller
@RequestMapping("/board/boardDelete.do")
public class BoardDeleteController {
	@Inject
	private BoardService service;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deleteBoardAjax(
			@Validated(DeleteGroup.class) BoardVO condition
			, BindingResult errors
			, Model model
	) {
		
		String viewName = null;
		boolean success = false;
		if(!errors.hasErrors()) {
			try {
				service.removeBoard(condition);
				success = true;
				model.addAttribute("location", "/board/boardList.do");
			}catch (BoardInvalidPasswordException e) {
				model.addAttribute("message", "비번 오류");
			}
		}else {
			model.addAttribute("message", "비번 누락");
		}
		model.addAttribute("success", success);
		
		return "jsonView";
	}
	@PostMapping
	public String deleteBoard(
			@Validated(DeleteGroup.class) BoardVO condition
			, BindingResult errors
			, RedirectAttributes redirectAttributes
			) {
		String viewName = null;
		if(!errors.hasErrors()) {
			try {
				service.removeBoard(condition);
				viewName = "redirect:/board/boardList.do";
			}catch (BoardInvalidPasswordException e) {
				redirectAttributes.addFlashAttribute("message", "비번 오류");
				viewName = "redirect:/board/boardView.do?what="+condition.getBoNo();
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "비번 누락");
			viewName = "redirect:/board/boardView.do?what="+condition.getBoNo();
		}
		return viewName;
	}
}


















