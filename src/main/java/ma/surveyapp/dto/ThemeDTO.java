package ma.surveyapp.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class ThemeDTO {
	private Long idTheme;
	@NotBlank
    @Size(min = 4,max=100)
	private String intituleTheme;
	private String descTheme;
	private int nbQuestionnaire;

}
