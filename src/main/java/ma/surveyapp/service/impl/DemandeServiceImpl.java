package ma.surveyapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Demande;
import ma.surveyapp.repository.AnnonceRepository;
import ma.surveyapp.repository.DemandeRepository;
import ma.surveyapp.repository.GroupeRepository;
import ma.surveyapp.repository.ParticipantRepository;
import ma.surveyapp.service.DemandeService;
@Service
@RequiredArgsConstructor
@Slf4j
public class DemandeServiceImpl implements DemandeService{
	private final DemandeRepository demandeRepository;
	private final AnnonceRepository annonceRepository;
	private final GroupeRepository groupeRepository;
	private final ParticipantRepository participantRepository;

	@Override
	public List<Demande> getAllByAnnonce(Long idAnnonce) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNoContentException("Annonce not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByAnnonce(annonceRepository.findById(idAnnonce).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByAnnonce(annonceRepository.findById(idAnnonce).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<Demande> getValidateByAnnonce(Long idAnnonce) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNoContentException("Annonce not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByAnnonceAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(annonceRepository.findById(idAnnonce).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByAnnonceAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(annonceRepository.findById(idAnnonce).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<Demande> getPendingByAnnonce(Long idAnnonce) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNoContentException("Annonce not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByAnnonceAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(annonceRepository.findById(idAnnonce).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByAnnonceAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(annonceRepository.findById(idAnnonce).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<Demande> getRefuseByAnnonce(Long idAnnonce) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNoContentException("Annonce not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByAnnonceAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(annonceRepository.findById(idAnnonce).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByAnnonceAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(annonceRepository.findById(idAnnonce).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}	
		
	}

	@Override
	public Demande getByID(Long idDemande) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try{
			if(demandeRepository.existsById(idDemande)){
				return demandeRepository.getOne(idDemande);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Demande save(Demande demande) throws ApiConflictException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try{
			if(demandeRepository.findByAnnonceAndParticipant(demande.getAnnonce(), demande.getParticipant())!=null){
				throw new ApiConflictException("Demande already exist");
			}
			demande.setIsPending(true);
			demande.setIsValide(false);
			demande.setIsRefuse(false);
			Demande demandeSaved=demandeRepository.save(demande);
			if(demandeSaved!=null){
				return demandeSaved;
			}
			throw new ApiNotModifiedException("Groupe is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Demande setValidate(Long idDemande) throws ApiNoContentException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try{
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNoContentException("Demande not exist");
			}
			Demande demande = demandeRepository.findById(idDemande).get();
			demande.setIsValide(true);
			demande.setIsPending(false);
			demande.setIsRefuse(false);
			Demande demandeSaved=demandeRepository.save(demande);
			if(demandeSaved!=null){
				return demandeSaved;
			}
			throw new ApiNotModifiedException("Demande is unsuccessfully updated");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Demande setPending(Long idDemande) throws ApiNoContentException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try{
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNoContentException("Demande not exist");
			}
			Demande demande = demandeRepository.findById(idDemande).get();
			demande.setIsPending(true);
			demande.setIsValide(false);
			demande.setIsRefuse(false);
			Demande demandeSaved=demandeRepository.save(demande);
			if(demandeSaved!=null){
				return demandeSaved;
			}
			throw new ApiNotModifiedException("Demande is unsuccessfully updated");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Demande setRefuse(Long idDemande) throws ApiNoContentException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try{
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNoContentException("Demande not exist");
			}
			Demande demande = demandeRepository.findById(idDemande).get();
			demande.setIsRefuse(true);
			demande.setIsValide(false);
			demande.setIsPending(false);
			Demande demandeSaved=demandeRepository.save(demande);
			if(demandeSaved!=null){
				return demandeSaved;
			}
			throw new ApiNotModifiedException("Demande is unsuccessfully updated");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}


	@Override
	public Demande delete(Long idDemande) throws ApiInternalServerErrorExeption {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDemandeToGroupe(Long idDemande, Long idGroupe) throws ApiNoContentException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try{
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNoContentException("Demande not exist");
			}
			if(!groupeRepository.existsById(idGroupe)){
				throw new ApiNoContentException("Groupe not exist");
			}
			Demande demande = demandeRepository.findById(idDemande).get();
			if(demande.getIsRefuse() || demande.getIsPending() || !demande.getIsValide()){
				throw new ApiNotModifiedException("Demande invalid state");
			}
			Demande demandeSaved=demandeRepository.save(demande);
			if(demandeSaved==null){
				throw new ApiNotModifiedException("Demande is unsuccessfully updated");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<Demande> getAllByVolontaire(Long idVolontaire) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNoContentException("Entity not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByParticipant(participantRepository.findById(idVolontaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByParticipant(participantRepository.findById(idVolontaire).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<Demande> getRefuseByVolontaire(Long idVolontaire) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNoContentException("Entity not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByParticipantAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(participantRepository.findById(idVolontaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByParticipantAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(participantRepository.findById(idVolontaire).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<Demande> getValidateByVolontaire(Long idVolontaire) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNoContentException("Entity not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByParticipantAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(participantRepository.findById(idVolontaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByParticipantAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(participantRepository.findById(idVolontaire).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<Demande> getPendingByVolontaire(Long idVolontaire) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNoContentException("Entity not founded");
			}
			if(!CollectionUtils.isEmpty(demandeRepository.findByParticipantAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(participantRepository.findById(idVolontaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return demandeRepository.findByParticipantAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(participantRepository.findById(idVolontaire).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}
	

}
