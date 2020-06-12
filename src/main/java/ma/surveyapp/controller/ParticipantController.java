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
import ma.surveyapp.dto.DemandeRequestDTO;
import ma.surveyapp.dto.DemandeResponseDTO;
import ma.surveyapp.dto.ParticipantRequestDTO;
import ma.surveyapp.dto.ParticipantResponseDTO;
import ma.surveyapp.dto.ReponseRequestDTO;
import ma.surveyapp.dto.ReponseResponseDTO;
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.service.DemandeService;
import ma.surveyapp.service.ParticipantService;
import ma.surveyapp.service.ReponseService;

@RestController
@RequestMapping(value="/api/participants",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ParticipantController {
	private final ParticipantService participantService;
	private final DemandeService demandeService;
	private final ReponseService reponseService;
	
	@GetMapping
	public List<ParticipantResponseDTO> getAll(@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size) {
		return participantService.getAll(page,size);
	}
	
	@GetMapping("/{id}")
	public ParticipantResponseDTO getOne(@PathVariable(name="id",required=true) Long id){
		return participantService.getById(id);
	}
	
	@PostMapping
	public ParticipantResponseDTO save(@RequestBody @Valid ParticipantRequestDTO participant,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return participantService.save(participant);
	}
	
	@PutMapping
	public ParticipantResponseDTO update(@RequestBody @Valid ParticipantRequestDTO participant,BindingResult bindingResult){ 
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return participantService.update(participant);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name="id",required=true) Long id){
		participantService.delete(id);
	}
	
	/*
	 * 
	 * DEMANDES
	 * 
	 */
	
	@GetMapping("/{idParticipant}/demandes/refuses")
	public Page<DemandeResponseDTO> getRefusDemandesByParticipant(@PathVariable(name="idParticipant",required=true) Long idParticipant,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size){
		return demandeService.getByRefuseDemandeByParticipantId(idParticipant,page,size);
	}
	
	@GetMapping("/{idParticipant}/demandes/pendings")
	public Page<DemandeResponseDTO> getpendingDemandesByParticipant(@PathVariable(name="idParticipant",required=true) Long idParticipant,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size) {
		return demandeService.getByPendingDemandeByParticipantId(idParticipant,page,size);
	}
	
	@PostMapping("/demandeParticipation")
	public DemandeResponseDTO demandeParticipation(@RequestBody DemandeRequestDTO demandeRequestDTO){
		return demandeService.save(demandeRequestDTO);
	}
	
	@PostMapping("/{idPar}/annulerDemandeParticipation/{idDmd}")
	public Boolean annulerDemandeParticipation(@PathVariable("idPar") Long idPar,@PathVariable("idDmd") Long idDmd){
		return demandeService.supprimerDemande(idDmd);
	}
	
	@GetMapping("/{idParticipant}/demandes/{idDemande}")
	public DemandeResponseDTO getOneDemande(@PathVariable(name="idParticipant",required=true) Long idParticipant,@PathVariable(name="idDemande",required=true) Long idDemande){
		return demandeService.getByDemandeIdAndParticipantId(idDemande, idParticipant);
	}
	
	@GetMapping("/{idParticipant}/demandes")
	public Page<DemandeResponseDTO> getDemandesByParticipant(@PathVariable("idParticipant") Long idParticipant,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size){
		return demandeService.getByParticipantId(idParticipant,page,size);
	}
	
	@GetMapping("/{idParticipant}/demandes/valides")
	public Page<DemandeResponseDTO> getvalideDemandesByParticipant(@PathVariable("idParticipant") Long idParticipant,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size) {
		return demandeService.getByValideDemandeByParticipantId(idParticipant,page,size);
	}
	
	/*
	 * 
	 * Questionnaires
	 * 
	 */
	
	@GetMapping("/{idParticipant}/demandes/{idDemande}/valides/questionnairesToAnswer")
	public Page<ReponseResponseDTO> getquestionnairesDemandesByParticipant(@PathVariable(name="idParticipant",required=true) Long idParticipant,@PathVariable(name="idDemande",required=true) Long idDemande,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size) {
		return reponseService.getToAnswersByDemande(idDemande, idParticipant,page,size);
	}
	
	@PostMapping("/{idParticipant}/demandes/{idDemande}/valides/questionnairesToAnswer")
	public Boolean answer(@PathVariable(name="idParticipant",required=true) Long idParticipant,@PathVariable(name="idDemande",required=true) Long idDemande,@RequestBody @Valid ReponseRequestDTO reponseRequestDTO,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return reponseService.saveAnswer(reponseRequestDTO,idDemande);
	}

}
