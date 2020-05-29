package ma.surveyapp.dto;



import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class ThemeDTO {
	private Long idTheme;
	@NotBlank
	private String intituleTheme;
	private String descTheme;

}
