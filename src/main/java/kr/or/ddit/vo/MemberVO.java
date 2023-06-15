package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class MemberVO {
	@EqualsAndHashCode.Include
	private String memId;
	@ToString.Exclude
	private String memPass;
	private String memName;
	
	private String memRole;
}
