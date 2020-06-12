package ma.surveyapp.util.modelmapper;


import ma.surveyapp.dto.LigneQuestionnaireResponseDTO;
import ma.surveyapp.model.LigneQuestionnaire;

public class LigneQuestionnaireMapper {
	public static LigneQuestionnaireResponseDTO ligneQuestionnaireToLigneQuestionnaireDto(LigneQuestionnaire ligneQuestionnaire){
		LigneQuestionnaireResponseDTO ligneQuestionnaireResponseDTO = new LigneQuestionnaireResponseDTO();
		ligneQuestionnaireResponseDTO.setDateDebutReponse(ligneQuestionnaire.getDateDebutReponse());
		ligneQuestionnaireResponseDTO.setDateFinReponse(ligneQuestionnaire.getDateFinReponse());
		ligneQuestionnaireResponseDTO.setIdLQ(ligneQuestionnaire.getIdLQ());
		ligneQuestionnaireResponseDTO.setGroupe(GroupeMapper.groupeToGroupeDTO(ligneQuestionnaire.getGroupe()));
		ligneQuestionnaireResponseDTO.setQuestionnaire(QuestionnaireMapper.QuestionnaireToQuestionnaireDTO(ligneQuestionnaire.getQuestionnaire()));
		return ligneQuestionnaireResponseDTO;
	}

}
