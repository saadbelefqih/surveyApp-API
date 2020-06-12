package ma.surveyapp.util.modelmapper;


import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;


import ma.surveyapp.dto.QuestionnaireRequestDTO;
import ma.surveyapp.dto.QuestionnaireResponseDTO;
import ma.surveyapp.model.Questionnaire;

public class QuestionnaireMapper {
	
	public static QuestionnaireResponseDTO QuestionnaireToQuestionnaireDTO(Questionnaire questionnaire){
		QuestionnaireResponseDTO responseDTO = new QuestionnaireResponseDTO();
		responseDTO.setIdQuestionnaire(questionnaire.getIdQuestionnaire());
		responseDTO.setDescQuestionnaire(questionnaire.getDescQuestionnaire());
		responseDTO.setDateAjoutQuestionnaire(questionnaire.getDateAjoutQuestionnaire());
		responseDTO.setIntituleQuestionnaire(questionnaire.getIntituleQuestionnaire());
		responseDTO.setTheme(ThemeMapper.ThemeToThemeDTO(questionnaire.getTheme()));
		responseDTO.setQuestions(new HashSet<>());
		if(!questionnaire.getQuestions().isEmpty()){
			responseDTO.setQuestions(questionnaire.getQuestions().stream().map(qst->{
				/*List<Integer> resultat = Arrays.asList(0, 0, 0,0);
				resultat=totalReponseByDemande.get(qst.getIdQuestion());
				resultat.get(1)
				*/
				return QuestionMapper.QuestionToQuestionDTO(qst);
			}).collect(Collectors.toSet()));
		}
		return responseDTO;
	}
	
	public static Questionnaire QuestionnaireToQuestionnaireDTO(QuestionnaireRequestDTO questionnaireRequestDTO){
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setDateAjoutQuestionnaire(new Date());
		questionnaire.setIdQuestionnaire(questionnaireRequestDTO.getIdQuestionnaire());
		questionnaire.setIntituleQuestionnaire(questionnaireRequestDTO.getIntituleQuestionnaire());
		questionnaire.setDescQuestionnaire(questionnaireRequestDTO.getDescQuestionnaire());
		
		
		return questionnaire;
	}

}
