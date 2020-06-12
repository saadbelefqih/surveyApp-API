package ma.surveyapp.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class QuestionnaireRequestDTO {
	private Long idQuestionnaire;
	@NotBlank
    @Size(max = 100,min=4)
	private String intituleQuestionnaire;
	private String descQuestionnaire;
	private Long idtheme;
	private Set<QuestionDTO>questions;
	 
}
