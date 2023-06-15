package kr.or.ddit.group.controller.advice;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.ddit.group.GroupVO;
import kr.or.ddit.group.dao.GroupDAO;

@ControllerAdvice("kr.or.ddit.group.controller")
public class GroupControllerAdvice {
	@Inject
	private GroupDAO groupDAO;
	
	@ModelAttribute("findedGroup")
	public GroupVO group(@PathVariable String groupId) {
		GroupVO groupVO = groupDAO.selectGroup(groupId);
		
		return groupVO;
	}
}
// 감사합니다!!