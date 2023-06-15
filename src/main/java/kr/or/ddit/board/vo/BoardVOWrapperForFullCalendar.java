package kr.or.ddit.board.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import kr.or.ddit.board.vo.fullcalendar.FullCalendarVO;

public class BoardVOWrapperForFullCalendar implements FullCalendarVO{
	private BoardVO board;

	public BoardVOWrapperForFullCalendar(BoardVO board) {
		super();
		this.board = board;
	}

	@Override
	public String getId() {
		return board.getBoNo().toString();
	}
	
	@Override
	public String getTitle() {
		return board.getBoTitle();
	}

	@JsonFormat(shape = Shape.STRING)
	@Override
	public LocalDateTime getStart() {
		return board.getBoDate();
	}

	@JsonFormat(shape = Shape.STRING)
	@Override
	public LocalDateTime getEnd() {
		return board.getBoDate();
	}

	@Override
	public boolean isAllDay() {
		return false;
	}
	
	

}
