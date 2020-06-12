package ma.surveyapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ma.surveyapp.dto.QuestionDTO;
import ma.surveyapp.dto.QuestionnaireRequestDTO;
import ma.surveyapp.dto.QuestionnaireResponseDTO;
import ma.surveyapp.service.QuestionnaireService;

@RestController
@RequestMapping(value="/api/questionnaires",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class QuestionnaireController {
	private final QuestionnaireService questionnaireService;
	
	@GetMapping
	public Page<QuestionnaireResponseDTO> getAll(@RequestParam(name="intitule",defaultValue="")String intitule,@RequestParam(name="page",defaultValue="0")int page,
										@RequestParam(name="size",defaultValue="15")int size){	
		return questionnaireService.getAll(intitule, page, size);
	}
	
	@GetMapping("/{id}")
	public QuestionnaireResponseDTO getOne(@PathVariable("id") Long id) {
		return questionnaireService.getbyID(id);
	}
	
	@GetMapping("/themes/{id}")
	public Page<QuestionnaireResponseDTO> getbyTheme(@PathVariable("id") Long id,@RequestParam(name="page",defaultValue="0")int page,
										@RequestParam(name="size",defaultValue="15")int size) {
		return questionnaireService.findbyTheme(id, page, size);
	}
	
	@PostMapping
	public QuestionnaireResponseDTO save(@RequestBody @Valid QuestionnaireRequestDTO questionnaireRequestDTO){
		return questionnaireService.save(questionnaireRequestDTO);
	}
	
	@PutMapping
	public QuestionnaireResponseDTO update(@RequestBody @Valid QuestionnaireRequestDTO questionnaireRequestDTO) {
		return questionnaireService.update(questionnaireRequestDTO);
	}
	
	@PostMapping("/{id}/addQuestions")
	public QuestionnaireResponseDTO addQuestions(@PathVariable("id") Long id,@RequestBody @Valid List<QuestionDTO> questionsDTO) {
		return questionnaireService.addQuestionsToQuestionnaire(id, questionsDTO);
	}
	
	@PostMapping("/{id}/removeQuestions")
	public QuestionnaireResponseDTO removeQuestions(@PathVariable(name="id",required=true) Long id,@RequestBody @Valid List<QuestionDTO> questionsDTO){
		return questionnaireService.removeQuestionsToQuestionnaire(id, questionsDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteQuestion(@PathVariable("id") Long id){
		questionnaireService.delete(id);
	}
	
	

}
