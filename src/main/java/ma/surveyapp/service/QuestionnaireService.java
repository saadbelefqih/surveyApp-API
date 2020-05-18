package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.model.Questionnaire;

public interface QuestionnaireService {
	
	List<Questionnaire> getAll() throws ApiInternalServerErrorExeption;
	List<Questionnaire> findbyTheme(Long idTheme)throws ApiInternalServerErrorExeption;
	Questionnaire getbyID(Long idQuestionnaire)throws ApiInternalServerErrorExeption;
	Questionnaire save(Questionnaire questionnaire)throws ApiInternalServerErrorExeption;
	Questionnaire update(Questionnaire questionnaire)throws ApiInternalServerErrorExeption;
	void delete(Long idQuestionnaire)throws ApiInternalServerErrorExeption;

}
