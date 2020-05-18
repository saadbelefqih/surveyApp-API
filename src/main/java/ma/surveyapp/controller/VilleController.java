package ma.surveyapp.controller;


import java.util.List;

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
import ma.surveyapp.model.Ville;
import ma.surveyapp.service.VilleService;
@RestController
@RequestMapping(value="/api/villes",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VilleController {
	private final VilleService villeService;
	
	@GetMapping()
	public Page<Ville> getByLibelle(@RequestParam(name="name",defaultValue="")String libelle,@RequestParam(name="page",defaultValue="0")int page ) throws ApiInternalServerErrorExeption{
		return villeService.getByLibelle(libelle, page, 10);
	}
	
	
	@GetMapping("/{id}")
	public Ville getById(@PathVariable("id")Long id) throws ApiNoContentException, ApiInternalServerErrorExeption{
		return villeService.getByID(id);
	}
	
	@PostMapping()
	public Ville save(@RequestBody @Valid Ville ville,BindingResult bindingResult ) throws ApiBadRequestException, ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return villeService.save(ville);
	}
	
	@PutMapping()
	public Ville update(@RequestBody @Valid Ville ville,BindingResult bindingResult) throws ApiNotFoundException, ApiNotModifiedException, ApiInternalServerErrorExeption, ApiBadRequestException{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return villeService.update(ville);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")Long id) throws ApiInternalServerErrorExeption{
		 villeService.delete(id);
	}
	
	
	

}
