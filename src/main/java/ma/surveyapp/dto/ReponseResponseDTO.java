package ma.surveyapp.dto;

import java.util.Set;

import lombok.Data;
@Data
public class ReponseResponseDTO {
	private Long idReponse;
	private LigneQuestionnaireResponseDTO ligneQuestionnaire;
	private DemandeResponseDTO demande;
	private Boolean hasRespense;
	private Set<ReponseDetailsDTO> reponsesDetails;
}
