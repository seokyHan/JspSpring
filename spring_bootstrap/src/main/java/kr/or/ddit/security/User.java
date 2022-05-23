package kr.or.ddit.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.dto.MemberVO;

public class User implements UserDetails {
	
	//bean등록 X 외부에서 받아쓰는 형태
	private MemberVO member;
	public User(MemberVO member) {
		this.member = member;
	}
	

	
	// 권한이 한개나 여러가지 일 수 있다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(member.getAuthority()));
		
		return roles;
	}

	@Override
	public String getPassword() {
		
		return member.getPwd();
	}

	@Override
	public String getUsername() {
		
		return member.getId();
	}

	@Override
	public boolean isAccountNonExpired() { //기간제 계정의 경우 기간만료 여부 : enabled=4
		
		return member.getEnabled() != 4;
	}

	@Override
	public boolean isAccountNonLocked() { //사용정지 혹은 휴먼계정 : enabled=3
		
		return member.getEnabled() != 3;
	}

	@Override
	public boolean isCredentialsNonExpired() { //인증정보 만료 여부 : enabled=2
		
		return member.getEnabled() != 2;
	}

	@Override
	public boolean isEnabled() { // 탈퇴 혹은 삭제 : enabled = 0
		
		return member.getEnabled() != 0;
	}
	
	public MemberVO getMemberVO() {
		return this.member;
	}

}
