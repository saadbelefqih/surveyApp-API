package ma.surveyapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Question;
import ma.surveyapp.repository.QuestionRepository;
import ma.surveyapp.service.QuestionService;
@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService{
	
	private final QuestionRepository questionRepository;

	@Override
	public List<Question> getAll() throws ApiInternalServerErrorExeption {
		try {
			if(!CollectionUtils.isEmpty(questionRepository.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return questionRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Question getbyID(Long idQuestion) throws ApiInternalServerErrorExeption {
		try{
			if(questionRepository.existsById(idQuestion)){
				return questionRepository.getOne(idQuestion);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Question save(Question question) throws ApiInternalServerErrorExeption {
		try {
			if(questionRepository.findByTextQuestionIgnoreCase(question.getTextQuestion())!=null){
				throw new ApiConflictException("Question already exist");
			}
			Question questionSaved = questionRepository.save(question);
			if(questionSaved!=null){
				return questionSaved;
			}
			throw new ApiNotModifiedException("Question is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public Question update(Question question) throws ApiInternalServerErrorExeption {
		try {
			if(!questionRepository.existsById(question.getIdQuestion())){
				throw new ApiNotFoundException("Question does not exist");
			}
			Question questionSaved = questionRepository.save(question);
			if(questionSaved!=null){
				return questionSaved;
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idQuestion) throws ApiInternalServerErrorExeption {
		try {
			questionRepository.deleteById(idQuestion);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}
	
	

}
