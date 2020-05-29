package ma.surveyapp.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;
@Data
public class QuestionnaireResponseDTO {
	private Long idQuestionnaire;
	private String intituleQuestionnaire;
	private String descQuestionnaire;
	private Date DateAjoutQuestionnaire;
	private ThemeDTO theme;
	private Set<QuestionDTO> questions;
}
