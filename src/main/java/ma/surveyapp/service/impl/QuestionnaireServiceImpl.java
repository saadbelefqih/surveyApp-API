package ma.surveyapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.QuestionDTO;
import ma.surveyapp.dto.QuestionnaireRequestDTO;
import ma.surveyapp.dto.QuestionnaireResponseDTO;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Question;
import ma.surveyapp.model.Questionnaire;
import ma.surveyapp.repository.QuestionRepository;
import ma.surveyapp.repository.QuestionnaireRepository;
import ma.surveyapp.repository.ThemeRepository;
import ma.surveyapp.service.QuestionnaireService;
import ma.surveyapp.util.modelmapper.QuestionMapper;
import ma.surveyapp.util.modelmapper.QuestionnaireMapper;
@Service
@RequiredArgsConstructor
//@Slf4j
public class QuestionnaireServiceImpl implements QuestionnaireService{
	private final QuestionnaireRepository questionnaireRepository;
	private final QuestionRepository questionRepository;
	private final ThemeRepository themeRepository;

	
	@Override
	public Page<QuestionnaireResponseDTO> getAll(String intitule,int page,int size) {
			List<QuestionnaireResponseDTO> lists = new ArrayList<QuestionnaireResponseDTO>();
			lists=questionnaireRepository.findByInIntitule(intitule,PageRequest.of(page, size)).stream().map(questionnaire->{
				return QuestionnaireMapper.QuestionnaireToQuestionnaireDTO(questionnaire);}).collect(Collectors.toList());
			if(!lists.isEmpty()){
				return  new PageImpl<>(lists, PageRequest.of(page, size), lists.size());
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public QuestionnaireResponseDTO getbyID(Long idQuestionnaire){
		if(questionnaireRepository.existsById(idQuestionnaire)){
				return QuestionnaireMapper.QuestionnaireToQuestionnaireDTO(questionnaireRepository.getOne(idQuestionnaire));
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public QuestionnaireResponseDTO save(QuestionnaireRequestDTO questionnaireRequestDTO) {
			if(questionnaireRequestDTO.getIdtheme()==null){
				throw new ApiNotFoundException("Entity Theme cannot be null");
			}
			if(questionnaireRequestDTO.getQuestions().isEmpty()){
				throw new ApiNotFoundException("List questions must be not empty");
			}
			
			if(questionnaireRepository.findByIntituleQuestionnaireIgnoreCase(questionnaireRequestDTO.getIntituleQuestionnaire())!=null){
				throw new ApiNotFoundException("Entity Questionnaire already exist");
			}
			if(!themeRepository.existsById(questionnaireRequestDTO.getIdtheme())){
				throw new ApiNotFoundException("Entity Theme not exist");
			}
			Questionnaire questionnaireSaved = questionnaireRepository.save(convertQuestionnaireRequestDtoToQuestionnaire(questionnaireRequestDTO));
			if(questionnaireSaved!=null){
				return QuestionnaireMapper.QuestionnaireToQuestionnaireDTO(questionnaireSaved);
			}
			
			throw new ApiNotModifiedException("Entity is unsuccessfully inserted");
	}
	
	private Questionnaire convertQuestionnaireRequestDtoToQuestionnaire(QuestionnaireRequestDTO questionnaireRequestDTO){
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setDateAjoutQuestionnaire(new Date());
		questionnaire.setIdQuestionnaire(questionnaireRequestDTO.getIdQuestionnaire());
		questionnaire.setIntituleQuestionnaire(questionnaireRequestDTO.getIntituleQuestionnaire());
		questionnaire.setDescQuestionnaire(questionnaireRequestDTO.getDescQuestionnaire());
		questionnaire.setTheme(themeRepository.getOne(questionnaireRequestDTO.getIdtheme()));
		questionnaire.setLignesQuestionnaire(new HashSet<>());
		Set<Question> questions = new HashSet<>();
		questions= questionnaireRequestDTO.getQuestions().stream().map(questionDTO->{
			Question question = new Question();
			question = QuestionMapper.QuestionDtoToQuestion(questionDTO);
			question.setQuestionnaire(questionnaire);
			question.setReponses(new HashSet<>());
			return question;
		}).collect(Collectors.toSet());
		questionnaire.setQuestions(questions);
		return questionnaire;
	}

	@Override
	public QuestionnaireResponseDTO update(QuestionnaireRequestDTO questionnaireRequestDTO){
			if(!questionnaireRepository.existsById(questionnaireRequestDTO.getIdQuestionnaire())){
				throw new ApiNotFoundException("Entity does not exist");
			}
			Questionnaire questionnaire = questionnaireRepository.getOne(questionnaireRequestDTO.getIdQuestionnaire());
			questionnaire.setDescQuestionnaire(questionnaireRequestDTO.getDescQuestionnaire());
			questionnaire.setIntituleQuestionnaire(questionnaireRequestDTO.getIntituleQuestionnaire());
			Questionnaire questionnaireSaved = questionnaireRepository.save(questionnaire);
			if(questionnaireSaved!=null){
				return QuestionnaireMapper.QuestionnaireToQuestionnaireDTO(questionnaireSaved);
			}
			throw new ApiNotModifiedException("Entity is unsuccessfully updated");
	}

	@Override
	public void delete(Long idQuestionnaire) {
			if(!questionnaireRepository.existsById(idQuestionnaire)){
				throw new ApiNotFoundException("Entity does not exist");
			}
			if(!questionnaireRepository.findById(idQuestionnaire).get().getQuestions().isEmpty()){
				throw new ApiNotFoundException("list of Question must be empty");
			}
			questionnaireRepository.deleteById(idQuestionnaire);
	}

	@Override
	public Page<QuestionnaireResponseDTO> findbyTheme(Long idTheme,int page,int size){
			
			List<QuestionnaireResponseDTO> lists = new ArrayList<QuestionnaireResponseDTO>();
			lists= questionnaireRepository.findByThemeId(idTheme).stream().map(questionnaire->{
				return QuestionnaireMapper.QuestionnaireToQuestionnaireDTO(questionnaire);}).collect(Collectors.toList());
			return new PageImpl<QuestionnaireResponseDTO>(lists, PageRequest.of(page, size), lists.size());
	}

	@Override
	public QuestionnaireResponseDTO addQuestionsToQuestionnaire(Long questionnnaireID,List<QuestionDTO> questions) {
			if(questions.isEmpty()){
				throw new ApiNotFoundException("list of Question is empty");
			}
			if(!questionnaireRepository.existsById(questionnnaireID)){
				throw new ApiNotFoundException("Entity does not exist");
			}
			Questionnaire questionnaire = questionnaireRepository.findById(questionnnaireID).get();
			List<Question> lists=questions.stream().map(questionDTO->{
				Question question =QuestionMapper.QuestionDtoToQuestion(questionDTO);
				question.setQuestionnaire(questionnaire);
				return question;
			}).collect(Collectors.toList());
			questionRepository.saveAll(lists);
			//Questionnaire questionnaireSaved = questionnaireRepository.save()
			
			return QuestionnaireMapper.QuestionnaireToQuestionnaireDTO(questionnaireRepository.findById(questionnnaireID).get());
	}

	@Override
	public QuestionnaireResponseDTO removeQuestionsToQuestionnaire(Long questionnnaireID, List<QuestionDTO> questions) {
			if(!questionnaireRepository.existsById(questionnnaireID)){
				throw new ApiNotFoundException("Entity does not exist");
			}
			if(!questionnaireRepository.existsById(questionnnaireID)){
				throw new ApiNotFoundException("Entity does not exist");
			}
			questions.forEach(qDTO->{
				if(qDTO.getIdQuestion()!=null){
				questionRepository.deleteById(qDTO.getIdQuestion());
				}
			});
				return QuestionnaireMapper.QuestionnaireToQuestionnaireDTO(questionnaireRepository.findById(questionnnaireID).get());

	}

}
