package kr.or.ddit.group.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.group.GroupVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/group/{groupId}/")
public class GroupController {

	@ModelAttribute("group")
	public GroupVO group(GroupVO group) {
		return group;
	}
	
	@RequestMapping("groupView")
	public String handler1(@PathVariable String groupId) {
		log.info("조회하려는 그룹 번호 : {}", groupId);
		return "GROUPVIEW";
	}
	@RequestMapping("groupEdit")
	@ResponseBody
	public String handler2(@PathVariable String groupId) {
		log.info("수정하려는 그룹 번호 : {}", groupId);
		return "GROUPEDIT";
	}
}
