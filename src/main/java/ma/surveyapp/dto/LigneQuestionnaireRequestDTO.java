package ma.surveyapp.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class LigneQuestionnaireRequestDTO {
	private Long idLQ;
	@NotBlank
	private Long idQuestionnaire;
	@NotBlank
	private Long idGroupe;
	private Date dateDebutReponse;
	private Date dateFinReponse;
}
