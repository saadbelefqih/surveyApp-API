package ma.surveyapp.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
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
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Profession;
import ma.surveyapp.service.ProfessionService;

@RestController
@RequestMapping(value="/api/professions",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor

public class ProfessionController {
	
	private final ProfessionService professionService;
	
	@GetMapping()
	public Page<Profession> getByLibelle(@RequestParam(name="name",defaultValue="")String libelle,@RequestParam(name="page",defaultValue="0")int page ) throws ApiInternalServerErrorExeption{
		return professionService.getAll(libelle, page, 10);
	}
	
	@GetMapping("/{id}")
	public Profession getById(@PathVariable("id")Long id) throws ApiNoContentException, ApiInternalServerErrorExeption{
		return professionService.getByID(id);
	}
	
	@PostMapping()
	public Profession save(@RequestBody @Valid Profession profession,BindingResult bindingResult ) throws ApiBadRequestException, ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return professionService.save(profession);
	}
	
	@PutMapping()
	public Profession update(@RequestBody @Valid Profession profession,BindingResult bindingResult) throws ApiNotFoundException, ApiNotModifiedException, ApiInternalServerErrorExeption, ApiBadRequestException{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return professionService.update(profession);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")Long id) throws ApiInternalServerErrorExeption{
		professionService.delete(id);
	}

}
