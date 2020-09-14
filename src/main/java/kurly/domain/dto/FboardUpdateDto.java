package kurly.domain.dto;

import kurly.domain.entity.BoardEntity;
import kurly.domain.entity.Notice;
import lombok.Data;

@Data
public class FboardUpdateDto {
	
	private String title;
	private String content;
	
	public BoardEntity toEntity() {
		return BoardEntity.builder()
				.title(title)
				.content(content).build();
	}

}
