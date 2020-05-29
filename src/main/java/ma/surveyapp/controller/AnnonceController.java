package ma.surveyapp.controller;

import java.util.List;
import java.util.Set;

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
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.service.AnnonceService;
@RestController
@RequestMapping(value="/api/annonces",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AnnonceController {
	
	private final AnnonceService annonceService;
	
	@GetMapping
	public List<AnnonceDTO> getAll() throws ApiInternalServerErrorExeption{
		return annonceService.getAll();
	}
	
	@GetMapping("/{id}")
	public AnnonceDTO getOne(@PathVariable("id")Long id) throws ApiNoContentException, ApiInternalServerErrorExeption{
		return annonceService.getByID(id);
	}
	
	@PostMapping
	public AnnonceDTO save(@RequestBody @Valid AnnonceDTO annonceDTO,BindingResult bindingResult) throws ApiBadRequestException, ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.save(annonceDTO);
	}
	
	@PostMapping("/{id}/addPublicCible")
	public AnnonceDTO addPublicCibleToAnnonce(@PathVariable("id") Long id ,@RequestBody @Valid Set<PublicCibleDTO> publicsCible,BindingResult bindingResult) throws ApiBadRequestException, ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.addPublicCibleToAnnonce(id, publicsCible);
	}
	
	@PostMapping("/{id}/removePublicCible")
	public AnnonceDTO removePublicCibleFromAnnonce(@PathVariable("id") Long id ,@RequestBody @Valid Set<PublicCibleDTO> publicsCible,BindingResult bindingResult) throws ApiBadRequestException, ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption, ApiNoContentException{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.deletePublicPublicCibleFromAnnonce(id, publicsCible);
	}
	
	
	
	@PutMapping
	public AnnonceDTO update(@RequestBody @Valid AnnonceDTO annonceDTO,BindingResult bindingResult) throws ApiBadRequestException, ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.update(annonceDTO);
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) throws ApiInternalServerErrorExeption{
		annonceService.delete(id);
	}

}
