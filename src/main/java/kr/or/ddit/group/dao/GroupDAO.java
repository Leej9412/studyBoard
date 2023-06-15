package kr.or.ddit.group.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.group.GroupVO;
@Mapper
public interface GroupDAO {

	GroupVO selectGroup(String groupId);

}
