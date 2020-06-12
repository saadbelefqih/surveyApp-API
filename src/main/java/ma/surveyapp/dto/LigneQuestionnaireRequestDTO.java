package ma.surveyapp.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class LigneQuestionnaireRequestDTO {
	private Long idLQ;
	@NotNull
	private Long idQuestionnaire;
	@NotNull
	private Long idGroupe;
	@NotNull
	private Date dateDebutReponse;
	@NotNull
	private Date dateFinReponse;
}
