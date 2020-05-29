package ma.surveyapp.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;
@Data
public class QuestionnaireRequestDTO {
	private Long idQuestionnaire;
	private String intituleQuestionnaire;
	private String descQuestionnaire;
	private Date DateAjoutQuestionnaire;
	private Long idtheme;
	private Set<QuestionDTO>questions;
	 
}
