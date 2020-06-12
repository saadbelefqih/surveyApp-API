package ma.surveyapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import ma.surveyapp.dto.DemandeRequestDTO;
import ma.surveyapp.dto.DemandeResponseDTO;
import ma.surveyapp.dto.GroupeDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Demande;
import ma.surveyapp.model.Groupe;
import ma.surveyapp.repository.AnnonceRepository;
import ma.surveyapp.repository.DemandeRepository;
import ma.surveyapp.repository.GroupeRepository;
import ma.surveyapp.repository.ParticipantRepository;
import ma.surveyapp.service.DemandeService;
import ma.surveyapp.util.modelmapper.DemandeMapper;
import ma.surveyapp.util.modelmapper.GroupeMapper;
@Service
@Transactional
@RequiredArgsConstructor
public class DemandeServiceImpl implements DemandeService{
	private final DemandeRepository demandeRepository;
	private final AnnonceRepository annonceRepository;
	private final GroupeRepository groupeRepository;
	private final ParticipantRepository participantRepository;
	/*
	@Override
	public List<Demande> getAllByAnnonce(Long idAnnonce){
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNotFoundException("Annonce not founded");
			}
			if(demandeRepository.findByAnnonce(annonceRepository.findById(idAnnonce).get()).isEmpty()){
				throw new ApiNotFoundException("No result founded");
			}
			return demandeRepository.findByAnnonce(annonceRepository.findById(idAnnonce).get());
	}

	@Override
	public List<Demande> getValidateByAnnonce(Long idAnnonce){
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNoContentException("Annonce not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByAnnonceAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(annonceRepository.findById(idAnnonce).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByAnnonceAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(annonceRepository.findById(idAnnonce).get());
		
	}

	@Override
	public List<Demande> getPendingByAnnonce(Long idAnnonce){
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNoContentException("Annonce not founded");
			}
			if(demandeRepository.findByAnnonceAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(annonceRepository.findById(idAnnonce).get()).isEmpty()){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByAnnonceAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(annonceRepository.findById(idAnnonce).get());
		
	}

	@Override
	public List<Demande> getRefuseByAnnonce(Long idAnnonce){
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNoContentException("Annonce not founded");
			}
			if(demandeRepository.findByAnnonceAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(annonceRepository.findById(idAnnonce).get()).isEmpty()){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByAnnonceAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(annonceRepository.findById(idAnnonce).get());
	}
	
	
	@Override
	public List<Demande> getAllByVolontaire(Long idVolontaire){
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNoContentException("Entity not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByParticipant(participantRepository.findById(idVolontaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByParticipant(participantRepository.findById(idVolontaire).get());
	}

	@Override
	public List<Demande> getRefuseByVolontaire(Long idVolontaire){
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNoContentException("Entity not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByParticipantAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(participantRepository.findById(idVolontaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByParticipantAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(participantRepository.findById(idVolontaire).get());

	}

	@Override
	public List<Demande> getValidateByVolontaire(Long idVolontaire){
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNoContentException("Entity not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByParticipantAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(participantRepository.findById(idVolontaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByParticipantAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(participantRepository.findById(idVolontaire).get());
		
	}

	@Override
	public List<Demande> getPendingByVolontaire(Long idVolontaire){
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNoContentException("Entity not founded");
			}
			if(demandeRepository.findByParticipantAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(participantRepository.findById(idVolontaire).get()).isEmpty()){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByParticipantAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(participantRepository.findById(idVolontaire).get());
	}
	*
	*/

	@Override
	public DemandeResponseDTO getByID(Long idDemande) {
			if(demandeRepository.existsById(idDemande)){
				return DemandeMapper.demandeToDemandeDTO(demandeRepository.findById(idDemande).get());
			}
			throw new ApiNoContentException("No result founded");
	}

	@Override
	public DemandeResponseDTO save(DemandeRequestDTO demandeRequestDTO){
			if(!participantRepository.existsById(demandeRequestDTO.getIdParticipant())){
				throw new ApiNoContentException("Participant not founded");
			}
			if(!annonceRepository.existsById(demandeRequestDTO.getIdAnnonce())){
				throw new ApiNoContentException("Annonce not founded");
			}
			if(demandeRepository.getAnnonceAndParticipant(demandeRequestDTO.getIdAnnonce(),demandeRequestDTO.getIdParticipant())!=null){
				throw new ApiConflictException("Demande already exist");
			}
			Demande demande = new Demande();
			demande.setAnnonce(annonceRepository.getOne(demandeRequestDTO.getIdAnnonce()));
			demande.setParticipant(participantRepository.getOne(demandeRequestDTO.getIdParticipant()));
			demande.setGroupe(null);
			demande.setReponses(new HashSet<>());
			demande.setDateDemande(new Date());
			demande.setIsPending(true);
			demande.setIsValide(false);
			demande.setIsRefuse(false);
			Demande demandeSaved=demandeRepository.save(demande);
			if(demandeSaved!=null){
				return DemandeMapper.demandeToDemandeDTO(demandeSaved);
			}
			throw new ApiNotModifiedException("Groupe is unsuccessfully inserted");
	}

	
	public void setValidate(Long idDemande) {
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNoContentException("Demande not exist");
			}
			demandeRepository.validerDemande(idDemande);
	}

	public void setPending(Long idDemande){
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNoContentException("Demande not exist");}
			demandeRepository.pendingDemande(idDemande);
	}

	public void setRefuse(Long idDemande) {
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNoContentException("Demande not exist");
			}
			demandeRepository.refuserDemande(idDemande);
	}


	@Override
	public Boolean supprimerDemande(Long idDemande) {
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNoContentException("Demande not exist");
			}
			demandeRepository.deleteInPendingDemande(idDemande);
			if(!demandeRepository.existsById(idDemande)){
				return true;
			}
			throw new ApiNoContentException("Demande is not in status Pending");
	}

	
	public void addDemandeToGroupe(Long idAnnonce,DemandeRequestDTO demandeRequestDTO) {
			if(!demandeRepository.existsById(demandeRequestDTO.getIdDemande())){
				throw new ApiNoContentException("Demande not exist");
			}
			
			if(!groupeRepository.existsById(demandeRequestDTO.getIdGroupe())){
				throw new ApiNoContentException("Groupe not exist");
			}
			Demande demande = demandeRepository.findById(demandeRequestDTO.getIdDemande()).get();
			if(demande.getAnnonce().getIdAnnonce()!=idAnnonce){
				throw new ApiNoContentException("Demande not for Annonce");
			}
			
			if(demande.getGroupe()!=null){
				throw new ApiNoContentException("Demande contient un Groupe");
			}
			
			if(!demande.getIsValide()){
				throw new ApiNotModifiedException("Demande invalid state");
			}
			Groupe groupe= groupeRepository.findById(demandeRequestDTO.getIdGroupe()).get();
			if(!groupe.getLigneQuestionnaires().isEmpty()){
				throw new ApiNoContentException("cannot add new participant to this Groupe ! Groupe is closed ");
			}
			groupe.getDemandes().add(demande);
			demande.setGroupe(groupe);
			
			Demande demandeSaved=demandeRepository.save(demande);
			if(demandeSaved==null){

				throw new ApiNotModifiedException("Demande is unsuccessfully updated");
			}
		
	}


	@Override
	public GroupeDTO addDemandesToGroupe(Long idAnnonce,Long idGroupe,final List<DemandeRequestDTO> demandesRequestDTO){
		demandesRequestDTO.forEach((dr)->{
		if(dr.getIdGroupe()==idGroupe && dr.getIdAnnonce()==idAnnonce){
			try {
				
				this.addDemandeToGroupe(idAnnonce,dr);
			} catch (Exception e) {
				
			}
		}
			});
		return GroupeMapper.groupeToGroupeDTO(groupeRepository.findById(idGroupe).get());
	}

	@Override
	public DemandeResponseDTO removeDemandeFromGroupe(DemandeRequestDTO demandeRequestDTO){
			if(!demandeRepository.existsById(demandeRequestDTO.getIdDemande())){
				throw new ApiNoContentException("Demande not exist");
			}
			if(!groupeRepository.existsById(demandeRequestDTO.getIdGroupe())){
				throw new ApiNoContentException("Groupe not exist");
			}
			Demande demande = demandeRepository.findById(demandeRequestDTO.getIdDemande()).get();
			if(!demande.getGroupe().getLigneQuestionnaires().isEmpty()){
				throw new ApiNoContentException("cannot delete participant from this Groupe ! Groupe is closed ");
			}
			if(demande.getGroupe()==null){
				throw new ApiNoContentException("Demande ne contient pas Groupe");
			}
			if(!demande.getIsValide()){
				throw new ApiNotModifiedException("Demande invalid state");
			}
			
			demande.setGroupe(null);
			Demande demandeSaved=demandeRepository.save(demande);
			if(demandeSaved!=null){
				return DemandeMapper.demandeToDemandeDTO(demandeSaved);
			}
			throw new ApiNotModifiedException("Demande is unsuccessfully updated");
		
	}

	@Override
	public void setValidate(List<DemandeRequestDTO> demandes){
		
		demandes.forEach(demande->{
				this.setValidate(demande.getIdDemande());
		});
		
	}

	@Override
	public void setPending(List<DemandeRequestDTO> demandes){
		
		demandes.forEach(demande->{
				this.setPending(demande.getIdDemande());
		});
		
	}

	@Override
	public void setRefuse(List<DemandeRequestDTO> demandes){
		demandes.forEach(demande->{
				this.setRefuse(demande.getIdDemande());
		});
	}

	@Override
	public DemandeResponseDTO getByDemandeIdAndAnnonceId(Long idDemande, Long idAnnonce){
			if(demandeRepository.getByIdAnnonceAndIdDemande(idAnnonce,idDemande)!=null){
				return DemandeMapper.demandeToDemandeDTO(demandeRepository.getByIdAnnonceAndIdDemande(idAnnonce,idDemande));
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public DemandeResponseDTO getByDemandeIdAndParticipantId(Long idDemande, Long idparticipant){
			Demande d = new Demande();			
			d=demandeRepository.findById(idDemande).get();
			if(d.getParticipant().getIdParticipant()!=idparticipant){
				throw new ApiNotFoundException("Participant Error !!!");
			}
			return DemandeMapper.demandeToDemandeDTO(d);
	}

	@Override
	public Page<DemandeResponseDTO> getByParticipantId(Long idparticipant,int page, int size){
				List<DemandeResponseDTO> demandes= new ArrayList<DemandeResponseDTO>();
				demandes=demandeRepository.getByIdParticipant(idparticipant).stream().map(dmd->{
							return DemandeMapper.demandeToDemandeDTO(dmd);
							
							}).collect(Collectors.toList());
				
				if(!demandes.isEmpty()){
					return  new PageImpl<>(demandes, PageRequest.of(page, size), demandes.size());
				}
				throw new ApiNotFoundException("No result founded");
	}

	@Override
	public Page<DemandeResponseDTO> getByValideDemandeByParticipantId(Long idparticipant, int page, int size){
			List<DemandeResponseDTO> demandes= new ArrayList<DemandeResponseDTO>();
			demandes=demandeRepository.getByValideDemandesByIdParticipant(idparticipant).stream().map(dmd->{
						return DemandeMapper.demandeToDemandeDTO(dmd);
						}).collect(Collectors.toList());
			
			if(!demandes.isEmpty()){
				return  new PageImpl<>(demandes, PageRequest.of(page, size), demandes.size());
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public Page<DemandeResponseDTO> getByRefuseDemandeByParticipantId(Long idparticipant, int page, int size){
			List<DemandeResponseDTO> demandes= new ArrayList<DemandeResponseDTO>();
			demandes=demandeRepository.getByRefuseDemandesByIdParticipant(idparticipant).stream().map(dmd->{
						return DemandeMapper.demandeToDemandeDTO(dmd);
						}).collect(Collectors.toList());
			if(!demandes.isEmpty()){
				return  new PageImpl<>(demandes, PageRequest.of(page, size), demandes.size());
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public Page<DemandeResponseDTO> getByPendingDemandeByParticipantId(Long idparticipant, int page, int size){
			List<DemandeResponseDTO> demandes= new ArrayList<DemandeResponseDTO>();
			demandes=demandeRepository.getByPendingDemandesByIdParticipant(idparticipant).stream().map(dmd->{
						return DemandeMapper.demandeToDemandeDTO(dmd);
						}).collect(Collectors.toList());
			
			if(!demandes.isEmpty()){
				return  new PageImpl<>(demandes, PageRequest.of(page, size), demandes.size());
			}
			throw new ApiNotFoundException("No result founded");
	}
	

}
