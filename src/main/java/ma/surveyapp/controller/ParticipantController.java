package ma.surveyapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Participant;
import ma.surveyapp.service.ParticipantService;

@RestController
@RequestMapping(value="/api/participants",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ParticipantController {
	private final ParticipantService participantService;
	
	@GetMapping
	public List<Participant> getAll() throws ApiInternalServerErrorExeption, ApiNoContentException{
		return participantService.getAll();
	}
	
	@GetMapping("/{id}")
	public Participant getOne(@PathVariable("id") Long id) throws ApiNoContentException, ApiInternalServerErrorExeption{
		return participantService.getById(id);
	}
	
	@PostMapping()
	public Participant save(@RequestBody @Valid Participant participant,BindingResult bindingResult) throws ApiBadRequestException, ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return participantService.save(participant);
	}
	
	@PutMapping()
	public Participant update(@RequestBody @Valid Participant participant,BindingResult bindingResult) throws ApiBadRequestException, ApiNotFoundException, ApiNotModifiedException, ApiInternalServerErrorExeption{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return participantService.update(participant);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) throws ApiInternalServerErrorExeption{
		participantService.delete(id);
	}
	
	

}
