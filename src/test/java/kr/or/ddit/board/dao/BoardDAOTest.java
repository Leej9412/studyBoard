package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.AbstractModelLayerTest;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.Pagination;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardDAOTest extends AbstractModelLayerTest{

	@Inject
	private BoardDAO dao;
	private Pagination<BoardVO> pagination;
	
	@Before
	public void setUp() {
		pagination = new Pagination<BoardVO>();
		pagination.setCurrentPage(2);
	}
	
	@Test
	public void testSelectBoardList() {
		assertNotNull(dao.selectBoardList(pagination));			
	}

	@Test
	public void testSelectTotalRecord() {
		assertNotEquals(0, dao.selectTotalRecord(pagination));
	}
	
	@Test
	public void testSelectBoard() {
		BoardVO board = dao.selectBoard(620);
		assertNotNull(board);
		Optional.ofNullable(board.getAtchFileGroup())
			.ifPresent((afgl)->{
				log.info("atch id : {}", afgl.getAtchId());
				afgl.getAtchFileList().stream()
				.forEach((af)->{
					log.info("{}", af);
				});
				
			});
	}

}
