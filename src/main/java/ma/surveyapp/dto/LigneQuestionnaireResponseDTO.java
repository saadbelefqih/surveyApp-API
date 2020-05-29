package ma.surveyapp.dto;

import java.util.Date;


import lombok.Data;
@Data
public class LigneQuestionnaireResponseDTO {
	private Long idLQ;
	private QuestionnaireResponseDTO Questionnaire;
	private GroupeDTO groupe;
	private Date dateDebutReponse;
	private Date dateFinReponse;
	
}
