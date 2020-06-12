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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import ma.surveyapp.dto.ProfessionDTO;
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.service.ProfessionService;

@RestController
@RequestMapping(value="/api/professions",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor

public class ProfessionController {
	
	private final ProfessionService professionService;
	
	@GetMapping()
	public List<ProfessionDTO> getByLibelle(@RequestParam(name="name",defaultValue="")String libelle,@RequestParam(name="page",defaultValue="0")int page ){
		return professionService.getAll(libelle, page, 10);
	}
	
	@GetMapping("/{id}")
	public ProfessionDTO getById(@PathVariable("id")Long id){
		return professionService.getByID(id);
	}
	
	@PostMapping
	public ProfessionDTO save(@RequestBody @Valid ProfessionDTO profession,BindingResult bindingResult ){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return professionService.save(profession);
	}
	
	@PutMapping
	public ProfessionDTO update(@RequestBody @Valid ProfessionDTO profession,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return professionService.update(profession);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")Long id) {
		professionService.delete(id);
	}

}
