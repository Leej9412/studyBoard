package kr.or.ddit.security.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MemberVO;

@Mapper
public interface MemberDAO{
	public MemberVO selectMember(String memId);
}
