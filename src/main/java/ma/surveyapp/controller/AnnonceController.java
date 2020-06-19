package ma.surveyapp.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.DemandeRequestDTO;
import ma.surveyapp.dto.DemandeResponseDTO;
import ma.surveyapp.dto.GroupeDTO;
import ma.surveyapp.dto.LigneQuestionnaireRequestDTO;
import ma.surveyapp.dto.LigneQuestionnaireResponseDTO;
import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.service.AnnonceService;
import ma.surveyapp.service.DemandeService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value="/api/annonces",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AnnonceController {
	
	private final AnnonceService annonceService;
	private final DemandeService demandeService;
	
	/*
	 * 
	 * CRUD ANNONCE CONTROLLER
	 * 
	 */
	
	@GetMapping
	public Page<AnnonceDTO> getAll(
			@RequestParam(name="words",defaultValue="",required=false)String words,
			@RequestParam(name="datedebut",required=false)@DateTimeFormat(pattern = "yyyy-MM-dd") String datedebut,
			@RequestParam(name="datefin",required=false)@DateTimeFormat(pattern = "yyyy-MM-dd") String datefin,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="6")int size) throws ParseException{
		
		return annonceService.getAll(words,datedebut,datefin,page,size);
	}
	
	@GetMapping("/getOne/{id}")
	public AnnonceDTO getOne(@PathVariable("id")Long id){
		return annonceService.getByID(id);
	}
	
	@PostMapping
	public AnnonceDTO save(@RequestBody @Valid AnnonceDTO annonceDTO,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.save(annonceDTO);
	}
	
	@PutMapping
	public AnnonceDTO update(@RequestBody @Valid AnnonceDTO annonceDTO,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.update(annonceDTO);
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		annonceService.delete(id);
	}
	/*
	 * 
	 *  PUBLIC CIBLE
	 * 
	 */
	@PostMapping("/{id}/addPublicCible")
	public AnnonceDTO addPublicCibleToAnnonce(@PathVariable("id") Long id ,@RequestBody @Valid Set<PublicCibleDTO> publicsCible,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.addPublicCibleToAnnonce(id, publicsCible);
	}
	
	@PostMapping("/{id}/removePublicCible")
	public AnnonceDTO removePublicCibleFromAnnonce(@PathVariable("id") Long id ,@RequestBody @Valid Set<PublicCibleDTO> publicsCible,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.deletePublicPublicCibleFromAnnonce(id, publicsCible);
	}
	
	
	/*
	 * 
	 *  GROUPES
	 * 
	 */
	
	@GetMapping("/{idAnnonce}/groupes/{idgroupe}")
	public GroupeDTO getGroupeByAnnonce(@PathVariable(name="idAnnonce",required=true)Long idAnnonce,@PathVariable(name="idgroupe",required=true)Long idgroupe,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size){
		return annonceService.getGroupeByAnnonce(idgroupe, idAnnonce);
	}
	
	@GetMapping("/{id}/groupes")
	public Page<GroupeDTO> getGroupeByAnnonce(@PathVariable(name="id",required=true)Long id,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size){
		return annonceService.getGroupesByAnnonce(id, page,size);
	}
	
	@PostMapping("/{id}/groupes")
	public GroupeDTO createGroupeForAnnonce(@PathVariable(name="id",required=true)Long id,@RequestBody @Valid GroupeDTO groupeDTO,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return annonceService.addGroupeToAnnonce(id,groupeDTO);
	}
	
	@PostMapping("/{idAnnonce}/groupes/{idgroupe}")
	public void deleteGroupeForAnnonce(@PathVariable(name="idAnnonce",required=true)Long idAnnonce,@PathVariable(name="idgroupe",required=true)Long idgroupe){
		
		// will be deleted if Groupe does not contain participant
		 annonceService.deleteGroupeFromAnnonce(idAnnonce,idgroupe);
	}
	
	@PostMapping("/{idAnnonce}/groupes/{idgroupe}/addParticipantToGroupe")
	public GroupeDTO addDemandesToGroupe(@PathVariable(name="idAnnonce",required=true)Long idAnnonce,@PathVariable(name="idgroupe",required=true)Long idgroupe,@RequestBody List<DemandeRequestDTO> demandesRequestDTO){
		 return demandeService.addDemandesToGroupe(idAnnonce,idgroupe,demandesRequestDTO);
	}
	
	@PostMapping("/removeParticipantFromGroupe")
	public DemandeResponseDTO removeParticipantFromGroupe(@RequestBody DemandeRequestDTO demandeRequestDTO){
		 return demandeService.removeDemandeFromGroupe(demandeRequestDTO);
	}
	

	/*
	 * 
	 * Questionnnaire
	 * 
	 */
	
	@GetMapping("/{idAnnonce}/groupes/{idgroupe}/questionnaires")
	public Page<LigneQuestionnaireResponseDTO> getLigneQuestionnaireByGroupe(@PathVariable(name="idAnnonce",required=true)Long idAnnonce,
			@PathVariable(name="idgroupe",required=true)Long idgroupe,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="15")int size){
		return annonceService.getLignesQuestionnaireToGroupeByGroupe(idgroupe,idAnnonce,page,size);
	}
	
	
	@PostMapping("/{idAnnonce}/groupes/{idgroupe}/questionnaires")
	public List<LigneQuestionnaireResponseDTO> saveLigneQuestionnaireByGroupe(@PathVariable(name="idAnnonce",required=true)Long idAnnonce,
			@PathVariable(name="idgroupe",required=true)Long idgroupe,
			@RequestBody @Valid List<LigneQuestionnaireRequestDTO> lignesQuestionnaireRequestDTO,
			BindingResult bindingResult){
		return annonceService.saveLignesQuestionnairToGroupeeByGroupe(idgroupe, idAnnonce, lignesQuestionnaireRequestDTO); 
	}
	
	@PostMapping("/{idAnnonce}/groupes/{idgroupe}/removeQuestionnaires")
	public void removeLigneQuestionnaireByGroupe(@PathVariable(name="idAnnonce",required=true)Long idAnnonce,
			@PathVariable(name="idgroupe",required=true)Long idgroupe,
			@RequestBody @Valid List<LigneQuestionnaireRequestDTO> lignesQuestionnaireRequestDTO,
			BindingResult bindingResult){
		 annonceService.deleteLignesQuestionnairToGroupeeByGroupe(idgroupe, idAnnonce, lignesQuestionnaireRequestDTO);
	}
	
	
	
	
	/*
	 * 
	 * DEMANDES 
	 * 
	 */
	
	@GetMapping("/{id}/demandes")
	public Page<DemandeResponseDTO> getDemandesByAnnonce(@PathVariable(name="id",required=true)Long id,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size){
		return annonceService.getDemandesByAnnonce(id, page,size);
	}
	
	@GetMapping("/{idAnnonce}/demandes/{idDemande}")
	public DemandeResponseDTO getOneDemande(@PathVariable(name="idAnnonce",required=true) Long idAnnonce,@PathVariable(name="idDemande",required=true) Long idDemande){
		return demandeService.getByDemandeIdAndAnnonceId(idDemande, idAnnonce);
	}
	
	
	@GetMapping("/{id}/demandes/valides")
	public Page<DemandeResponseDTO> getValideDemandesByAnnonce(@PathVariable(name="id",required=true)Long id,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size){
		return annonceService.getValideDemandesByAnnonce(id, page,size);
	}
	
	@GetMapping("/{id}/demandes/refuses")
	public Page<DemandeResponseDTO> getRefuseDemandesByAnnonce(@PathVariable(name="id",required=true)Long id,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size){
		return annonceService.getRefuseDemandesByAnnonce(id, page,size);
	}
	
	@GetMapping("/{id}/demandes/pendings")
	public Page<DemandeResponseDTO> getPendingDemandesByAnnonce(@PathVariable("id")Long id,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="15")int size){
		return annonceService.getPendingDemandesByAnnonce(id, page,size);
	}
	
	@PostMapping("/{id}/demandes/valides")
	public void setValidateDemande(@RequestBody List<DemandeRequestDTO> demandes){
		demandeService.setValidate(demandes);
	}
	
	@PostMapping("/{id}/demandes/refuses")
	public void setRefuseDemande(@RequestBody List<DemandeRequestDTO> demandes){
		demandeService.setRefuse(demandes);
	}
	
	@PostMapping("/{id}/demandes/pendings")
	public void setPendingDemande(@RequestBody List<DemandeRequestDTO> demandes){
		demandeService.setPending(demandes);
	}
}
