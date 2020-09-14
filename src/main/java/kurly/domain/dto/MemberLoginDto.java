package kurly.domain.dto;

import kurly.domain.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberLoginDto {
	
	private String id;
	private String pw;
	private String name;
	private String email;
	
	public MemberLoginDto(Member member) {
		this.id = member.getId();
		this.pw = member.getPw();
		this.name = member.getName();
		this.email = member.getEmail();
	}
	
	

}
