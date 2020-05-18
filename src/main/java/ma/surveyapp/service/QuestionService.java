package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.model.Question;

public interface QuestionService {
	List<Question> getAll()throws ApiInternalServerErrorExeption;
	Question getbyID(Long idQuestion)throws ApiInternalServerErrorExeption;
	Question save(Question question)throws ApiInternalServerErrorExeption;
	Question update(Question question)throws ApiInternalServerErrorExeption;
	void delete(Long idQuestion)throws ApiInternalServerErrorExeption;

}
