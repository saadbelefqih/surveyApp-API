package ma.surveyapp.service;



import java.util.List;

import org.springframework.data.domain.Page;

import ma.surveyapp.dto.QuestionDTO;
import ma.surveyapp.dto.QuestionnaireRequestDTO;
import ma.surveyapp.dto.QuestionnaireResponseDTO;

public interface QuestionnaireService {
	
	Page<QuestionnaireResponseDTO> getAll(String intitule,int page,int size) ;
	Page<QuestionnaireResponseDTO> findbyTheme(Long idTheme,int page,int size);
	QuestionnaireResponseDTO getbyID(Long idQuestionnaire);
	QuestionnaireResponseDTO save(QuestionnaireRequestDTO questionnaireRequestDTO);
	void delete(Long idQuestionnaire);
	QuestionnaireResponseDTO update(QuestionnaireRequestDTO questionnaireRequestDTO);
	QuestionnaireResponseDTO addQuestionsToQuestionnaire(Long questionnnaireID,List<QuestionDTO> questions);
	QuestionnaireResponseDTO removeQuestionsToQuestionnaire(Long questionnnaireID,List<QuestionDTO> questions);
	

}
