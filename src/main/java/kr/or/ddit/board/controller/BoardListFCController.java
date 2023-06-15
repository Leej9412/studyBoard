package kr.or.ddit.board.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.BoardVOWrapperForFullCalendar;
import kr.or.ddit.vo.Pagination;
import kr.or.ddit.vo.SimpleCondition;

@Controller
public class BoardListFCController {
	
	@Inject
	private BoardService service;
	
	@RequestMapping("/board/boardList_FC.do")
	public String getUI() {
		return "board/boardList_FC";
	}
	
	@RequestMapping(value="/board/boardList_FC.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<BoardVOWrapperForFullCalendar> getJson() {
		Pagination<BoardVO> pagination = new Pagination<BoardVO>(10000, 1);
		pagination.setCurrentPage(1);
		service.retrieveBoardList(pagination);
		return pagination.getDataList().stream()
				.map((vo)->new BoardVOWrapperForFullCalendar(vo))
				.collect(Collectors.toList());
	}
}
