package ma.surveyapp.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Reponse;
import ma.surveyapp.repository.DemandeRepository;
import ma.surveyapp.repository.LigneQuestionnaireRepository;
import ma.surveyapp.repository.ReponseRepository;
import ma.surveyapp.repository.ParticipantRepository;
import ma.surveyapp.service.ReponseService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReponseServiceImpl implements ReponseService{
	private final ReponseRepository reponseRepository;
	private final DemandeRepository demandeRepository;
	private final LigneQuestionnaireRepository lQRepository;
	private final ParticipantRepository participantRepository;
	@Override
	public List<Reponse> getAllByLigneQuestionnaireAndVolontaire(Long idLigneQuestionnaire, Long idVolontaire)
			throws ApiNotFoundException, ApiNoContentException, ApiInternalServerErrorExeption {
		try {
			if(!lQRepository.existsById(idLigneQuestionnaire)){
				throw new ApiNotFoundException("Entity does not exist");
			}
			if(!participantRepository.existsById(idVolontaire)){
				throw new ApiNotFoundException("Participant does not exist");
			}
			 AtomicBoolean isExistVolontaire = new AtomicBoolean(false);
			 AtomicLong idDemande= new AtomicLong();
			lQRepository.findById(idLigneQuestionnaire).get().getGroupe().getDemandes().forEach(dmd->{
				if(dmd.getParticipant().equals(participantRepository.getOne(idVolontaire))){
					isExistVolontaire.set(true);
					idDemande.set(dmd.getIdDemande());
				}
			});
			if(isExistVolontaire.get()==false){
				throw new ApiNotFoundException("Participant does not belong to this Groupe");
			}
			
			if(CollectionUtils.isEmpty(reponseRepository.findByLigneQuestionnaireAndDemande(lQRepository.findById(idLigneQuestionnaire).get(),demandeRepository.findById(idDemande.get()).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findByLigneQuestionnaireAndDemande(lQRepository.findById(idLigneQuestionnaire).get(),demandeRepository.findById(idDemande.get()).get());
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}
	@Override
	public List<Reponse> getAllByLigneQuestionnaire(Long idLigneQuestionnaire)
			throws ApiNotFoundException, ApiNoContentException, ApiInternalServerErrorExeption {
		try {
			if(!lQRepository.existsById(idLigneQuestionnaire)){
				throw new ApiNotFoundException("Groupe does not exist");
			}
			if(CollectionUtils.isEmpty(reponseRepository.findByLigneQuestionnaire(lQRepository.findById(idLigneQuestionnaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findByLigneQuestionnaire(lQRepository.findById(idLigneQuestionnaire).get());
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}
	@Override
	public List<Reponse> getAllByToResponseByLigneQuestionnaire(Long idLigneQuestionnaire)
			throws ApiNotFoundException, ApiNoContentException, ApiInternalServerErrorExeption {
		try {
			if(!lQRepository.existsById(idLigneQuestionnaire)){
				throw new ApiNotFoundException("Entity does not exist");
			}
			
			if(CollectionUtils.isEmpty(reponseRepository.findByLigneQuestionnaireAndHasRespenseFalse(lQRepository.findById(idLigneQuestionnaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findByLigneQuestionnaireAndHasRespenseFalse(lQRepository.findById(idLigneQuestionnaire).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}
	@Override
	public List<Reponse> getAllByRespondedByLigneQuestionnaire(Long idLigneQuestionnaire)
			throws ApiNotFoundException, ApiNoContentException, ApiInternalServerErrorExeption {
		try {
			if(!lQRepository.existsById(idLigneQuestionnaire)){
				throw new ApiNotFoundException("Entity does not exist");
			}
			
			if(CollectionUtils.isEmpty(reponseRepository.findByLigneQuestionnaireAndHasRespenseTrue(lQRepository.findById(idLigneQuestionnaire).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findByLigneQuestionnaireAndHasRespenseTrue(lQRepository.findById(idLigneQuestionnaire).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}
	
	@Override
	public List<Reponse> getAllByDemande(Long idDemande)
			throws ApiNotFoundException, ApiNoContentException, ApiInternalServerErrorExeption {
		try {
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNotFoundException("Demande does not exist");
			}
			
			if(CollectionUtils.isEmpty(reponseRepository.findByDemande(demandeRepository.findById(idDemande).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findByDemande(demandeRepository.findById(idDemande).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}
	@Override
	public List<Reponse> getAllByToResponseByDemande(Long idDemande)
			throws ApiNotFoundException, ApiNoContentException, ApiInternalServerErrorExeption {
		try {
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNotFoundException("Demande does not exist");
			}
			
			if(CollectionUtils.isEmpty(reponseRepository.findByDemandeAndHasRespenseFalse(demandeRepository.findById(idDemande).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findByDemandeAndHasRespenseFalse(demandeRepository.findById(idDemande).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}
	@Override
	public List<Reponse> getAllByRespondedByDemande(Long idDemande)
			throws ApiNotFoundException, ApiNoContentException, ApiInternalServerErrorExeption {
		try {
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNotFoundException("Demande does not exist");
			}
			
			if(CollectionUtils.isEmpty(reponseRepository.findByDemandeAndHasRespenseTrue(demandeRepository.findById(idDemande).get()))){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findByDemandeAndHasRespenseTrue(demandeRepository.findById(idDemande).get());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}
	@Override
	public Reponse getById(Long id) throws ApiNoContentException, ApiInternalServerErrorExeption {
		try {
			if(reponseRepository.existsById(id)){
				return reponseRepository.getOne(id);
			}

			throw new ApiNotFoundException("Reponse does not exist");
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}
	@Override
	public Reponse save(Reponse reponse)
			throws ApiNotFoundException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(!reponseRepository.existsById(reponse.getIdReponse())){
				throw new ApiNotFoundException("Demande does not exist");
			}
			if(reponse.getReponsesDetails().size()<=0){
				throw new ApiNotFoundException("Reponses does not exist");
			}
			Reponse rep = reponseRepository.findById(reponse.getIdReponse()).get();
			rep.setHasRespense(true);
			rep.setReponsesDetails(reponse.getReponsesDetails());
			Reponse reponseAdded=reponseRepository.save(rep);
			if(reponseAdded!=null){
				return reponseAdded;
			}
			throw new ApiNotModifiedException("Reponse is unsuccessfully inserted");
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}
	@Override
	public void delete(Long id) throws ApiNotModifiedException, ApiInternalServerErrorExeption {
		// TODO Auto-generated method stub
		
	}
	

	

}
