package kr.or.ddit.vo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MemberVOWrapper extends User{
	private MemberVO realUser;
	
	public MemberVOWrapper(MemberVO realUser) {
		super(realUser.getMemId(), realUser.getMemPass(), AuthorityUtils.createAuthorityList(realUser.getMemRole()));
		this.realUser = realUser;
	}
	
	public MemberVOWrapper(MemberVO realUser, Collection<GrantedAuthority> authorities) {
		super(realUser.getMemId(), realUser.getMemPass()
				, Stream.concat(AuthorityUtils.createAuthorityList(realUser.getMemRole()).stream(), authorities.stream())
					.collect(Collectors.toList()));
		this.realUser = realUser;
	}
	
	public MemberVO getRealUser() {
		return realUser;
	}
}
