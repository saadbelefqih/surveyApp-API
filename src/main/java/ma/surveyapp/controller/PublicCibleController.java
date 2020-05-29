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
import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.service.PublicCibleService;

@RestController
@RequestMapping(value="/api/publicCible",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PublicCibleController {
	private final PublicCibleService publicCibleService;
	
	@GetMapping
	public List<PublicCibleDTO> getAll() throws ApiInternalServerErrorExeption{
		return publicCibleService.getAll();
	}
	
	@GetMapping("/{id}")
	public PublicCibleDTO getOne(@PathVariable("id") Long id) throws ApiInternalServerErrorExeption{
		return publicCibleService.getByID(id);
	}
	
	@GetMapping("/{id}/annonces")
	public List<AnnonceDTO> getAnnoncesByPublicCible(@PathVariable("id") Long id) throws ApiInternalServerErrorExeption{
		return publicCibleService.getAnnoncesByPublicCible(id);
	}
	
	@PostMapping
	public PublicCibleDTO save(@RequestBody @Valid PublicCibleDTO publicCibleDTO , BindingResult bindingResult) throws ApiInternalServerErrorExeption, ApiBadRequestException{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return publicCibleService.save(publicCibleDTO);
	}
	
	@PutMapping
	public PublicCibleDTO update(@RequestBody @Valid PublicCibleDTO publicCibleDTO , BindingResult bindingResult) throws ApiInternalServerErrorExeption, ApiBadRequestException{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return publicCibleService.update(publicCibleDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) throws ApiInternalServerErrorExeption{
		publicCibleService.delete(id);
	}

}
