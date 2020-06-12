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
import ma.surveyapp.dto.VilleDTO;
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.service.VilleService;

@RestController
@RequestMapping(value="/api/villes",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VilleController {
	private final VilleService villeService;
	
	@GetMapping()
	public Page<VilleDTO> getByLibelle(@RequestParam(name="name",defaultValue="")String libelle,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size ) {
		return villeService.getByLibelle(libelle, page, size);
	}
	
	
	@GetMapping("/{id}")
	public VilleDTO getById(@PathVariable(name="id",required=true)Long id) {
		return villeService.getByID(id);
	}
	
	@PostMapping
	public VilleDTO save(@RequestBody @Valid VilleDTO ville,BindingResult bindingResult ) {
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return villeService.save(ville);
	}
	
	@PutMapping("/{id}")
	public VilleDTO update(@PathVariable(name="id")Long id,@RequestBody @Valid VilleDTO ville,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return villeService.update(id,ville);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")Long id){
		 villeService.delete(id);
	}
	
	
	

}
