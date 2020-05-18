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
import ma.surveyapp.model.Questionnaire;
import ma.surveyapp.repository.QuestionnaireRepository;
import ma.surveyapp.repository.ThemeRepository;
import ma.surveyapp.service.QuestionnaireService;
@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionnaireServiceImpl implements QuestionnaireService{
	private final QuestionnaireRepository questionnaireRepository;
	private final ThemeRepository themeRepository;

	@Override
	public List<Questionnaire> getAll() throws ApiInternalServerErrorExeption {
		try {
			if(!CollectionUtils.isEmpty(questionnaireRepository.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return questionnaireRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Questionnaire getbyID(Long idQuestionnaire) throws ApiInternalServerErrorExeption {
		try{
			if(questionnaireRepository.existsById(idQuestionnaire)){
				return questionnaireRepository.getOne(idQuestionnaire);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Questionnaire save(Questionnaire questionnaire) throws ApiInternalServerErrorExeption {
		try {
			if(questionnaireRepository.findByIntituleQuestionnaireIgnoreCase(questionnaire.getIntituleQuestionnaire())!=null){
				throw new ApiConflictException("Entity already exist");
			}
			Questionnaire questionnaireSaved = questionnaireRepository.save(questionnaire);
			if(questionnaireSaved!=null){
				return questionnaireSaved;
			}
			throw new ApiNotModifiedException("Entity is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
		
		
	}

	@Override
	public Questionnaire update(Questionnaire questionnaire) throws ApiInternalServerErrorExeption {
		try {
			if(!questionnaireRepository.existsById(questionnaire.getIdQuestionnaire())){
				throw new ApiNotFoundException("Entity does not exist");
			}
			Questionnaire questionnaireSaved = questionnaireRepository.save(questionnaire);
			if(questionnaireSaved!=null){
				return questionnaireSaved;
			}
			throw new ApiNotModifiedException("Entity is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idQuestionnaire) throws ApiInternalServerErrorExeption {
		try {
			questionnaireRepository.deleteById(idQuestionnaire);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<Questionnaire> findbyTheme(Long idTheme) throws ApiInternalServerErrorExeption {
		try {
			if(!themeRepository.existsById(idTheme)){
				throw new ApiNotFoundException("Entity does not exist");
			}
			if(!CollectionUtils.isEmpty(questionnaireRepository.findByTheme(themeRepository.getOne(idTheme)))){
				throw new ApiNoContentException("No result founded");
			}
			return questionnaireRepository.findByTheme(themeRepository.getOne(idTheme));
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
		
		
	}

}
