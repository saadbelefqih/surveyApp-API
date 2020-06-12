package ma.surveyapp.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
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
import ma.surveyapp.dto.ThemeDTO;
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.service.ThemeService;

@RestController
@RequestMapping("/api/themes")
@RequiredArgsConstructor
public class ThemeController {
	private final ThemeService themeService;
	
	@GetMapping
	public Page<ThemeDTO> getAll(@RequestParam(name="libelle",defaultValue="") String libelle,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="15") int size) throws ApiNoContentException, ApiInternalServerErrorExeption{
		return themeService.getAll(libelle, page, size);
	}
	
	@GetMapping("/{id}")
	public ThemeDTO getone(@PathVariable("id")Long id) throws ApiNoContentException, ApiInternalServerErrorExeption{
		return themeService.getByID(id);
	}
	
	@PostMapping
	public ThemeDTO save(@RequestBody @Valid ThemeDTO themeDTO,BindingResult bindingResult) throws ApiBadRequestException, ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return themeService.save(themeDTO);
	}
	
	@PutMapping
	public ThemeDTO update(@RequestBody @Valid ThemeDTO themeDTO,BindingResult bindingResult) throws ApiNotFoundException, ApiNotModifiedException, ApiInternalServerErrorExeption, ApiBadRequestException {
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return themeService.update(themeDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")Long id) throws ApiNotFoundException, ApiConflictException, ApiInternalServerErrorExeption {
		 themeService.delete(id);
	}

}
