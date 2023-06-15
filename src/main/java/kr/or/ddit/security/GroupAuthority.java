package kr.or.ddit.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class GroupAuthority implements GrantedAuthority{
	public static enum EqualLevel{
		NEQ, EQ_AUTHORITY, EQ_ROLE, EQ_GROUP, EQ_GROUP_ROLE
	}
	
	public static class GroupAuthorityComparator{
		public static EqualLevel compareAuthority(GrantedAuthority oneSide, GrantedAuthority theOther) {
			EqualLevel level = EqualLevel.NEQ;
			if(oneSide.getClass().equals(theOther.getClass())) {
				if(oneSide instanceof SimpleGrantedAuthority && oneSide.equals(theOther)) {
					level = EqualLevel.EQ_AUTHORITY;
				}else if(oneSide instanceof GroupAuthority) {
					if(oneSide.equals(theOther)) {
						level = EqualLevel.EQ_GROUP_ROLE;
					}else if(((GroupAuthority) oneSide).groupId.equals(((GroupAuthority)theOther).groupId)) {
						level = EqualLevel.EQ_GROUP;
					}else if(((GroupAuthority) oneSide).groupRole.equals(((GroupAuthority)theOther).groupRole)) {
						level = EqualLevel.EQ_ROLE;
					}
				}
					 
			}else if(oneSide.getAuthority().equals(theOther.getAuthority())) {
				level = EqualLevel.EQ_AUTHORITY;
			}
			return level;
		}
	}
	

	private String groupId;
	private String groupRole;
	
	public GroupAuthority(String groupId, String groupRole) {
		super();
		this.groupId = groupId;
		this.groupRole = groupRole;
	}


	@Override
	public String getAuthority() {
		return String.format("%s_%s", groupId, groupRole);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((groupRole == null) ? 0 : groupRole.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupAuthority other = (GroupAuthority) obj;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (groupRole == null) {
			if (other.groupRole != null)
				return false;
		} else if (!groupRole.equals(other.groupRole))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getAuthority();
	}

}
