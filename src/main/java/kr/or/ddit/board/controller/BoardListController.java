package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.Pagination;
import kr.or.ddit.vo.SimpleCondition;

@Controller
@RequestMapping("/board/boardList.do")
public class BoardListController {
	
	@Inject
	private BoardService service;
	
	@RequestMapping
	public String getUI() {
		return "board/boardList";
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Pagination<BoardVO> getJson(
		@RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, SimpleCondition simpleCondition
	) {
		Pagination<BoardVO> pagination = new Pagination<>();
		pagination.setCurrentPage(currentPage);
		pagination.setSimpleCondition(simpleCondition);
		service.retrieveBoardList(pagination);
		return pagination;
	}
}
