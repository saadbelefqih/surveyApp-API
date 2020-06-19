package ma.surveyapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.ReponseRequestDTO;
import ma.surveyapp.dto.ReponseResponseDTO;
//import ma.surveyapp.exception.ApiInternalServerErrorExeption;
//import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
//import ma.surveyapp.model.Demande;
import ma.surveyapp.model.Question;
import ma.surveyapp.model.Reponse;
import ma.surveyapp.model.ReponseDetails;
import ma.surveyapp.repository.DemandeRepository;
//import ma.surveyapp.repository.LigneQuestionnaireRepository;
import ma.surveyapp.repository.ReponseRepository;
//import ma.surveyapp.repository.ParticipantRepository;
import ma.surveyapp.repository.QuestionRepository;
import ma.surveyapp.repository.ReponseDetailsRepository;
import ma.surveyapp.service.ReponseService;
import ma.surveyapp.util.modelmapper.ReponseMapper;

@Service
@RequiredArgsConstructor
public class ReponseServiceImpl implements ReponseService{
	private final ReponseRepository reponseRepository;
	private final DemandeRepository demandeRepository;
	//private final LigneQuestionnaireRepository lQRepository;
	//private final ParticipantRepository participantRepository;
	private final ReponseDetailsRepository reponseDetailsRepository;
	private final QuestionRepository questionRepository;
/*	
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
	public List<Reponse> getAllByRespondedByDemande(Long idDemande){
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNotFoundException("Demande does not exist");
			}
			Demande demande= new Demande();
			demande=demandeRepository.findById(idDemande).get();
			if(reponseRepository.findByDemandeAndHasRespenseTrue(demande).isEmpty()){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findByDemandeAndHasRespenseTrue(demande);
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
	*/
	@Override
	public Page<ReponseResponseDTO> getToAnswersByDemande(Long idDemande,Long idParticipant,int page,int size){
			if(!demandeRepository.existsById(idDemande)){
				throw new ApiNotFoundException("Demande does not exist");
			}
			if(demandeRepository.getOne(idDemande).getParticipant().getIdpers()!=idParticipant){
				throw new ApiNotFoundException("Error Participant !!!");
			}
			List<ReponseResponseDTO> lists=new ArrayList<>();
			lists=reponseRepository.findResponseByDemandeIdAndDate(idDemande,new Date()).stream().map(reponse->{
				 return ReponseMapper.ReponseToReponseResponseDTO(reponse);
			 }).collect(Collectors.toList());
			if(!lists.isEmpty()){
			return new PageImpl<ReponseResponseDTO>(lists, PageRequest.of(page, size), lists.size());
			}
			throw new ApiNotFoundException("No result founded");
	}
	
	@Override
	public Boolean saveAnswer(ReponseRequestDTO reponseRequestDTO,Long idDemande){
			if(!reponseRepository.existsById(reponseRequestDTO.getIdReponse())){
				throw new ApiNotFoundException("Demande does not exist");
			}
			if(reponseRequestDTO.getReponsesDetails().isEmpty()){
				throw new ApiNotFoundException("Reponses does not exist");
			}
			
			Reponse rep = reponseRepository.findById(reponseRequestDTO.getIdReponse()).get();
			if(rep.getDemande().getIdDemande()!=idDemande){
				throw new ApiNotFoundException("Participant Error !!!");
			}
			Set<ReponseDetails> reponsesDetails=new HashSet<>();
			reponseRequestDTO.getReponsesDetails().forEach(reponseDTO->{
				if(questionRepository.existsById(reponseDTO.getQuestion())) 
						{
					Question question = questionRepository.getOne(reponseDTO.getQuestion());
						if(reponseDetailsRepository.findByReponseAndQuestion(rep,question)==null){
					ReponseDetails reponseDetails = new ReponseDetails();
					reponseDetails.setIdRd(null);
					reponseDetails.setDateReponse(new Date());
					reponseDetails.setIsOption1(reponseDTO.getIsOption1());
					reponseDetails.setIsOption2(reponseDTO.getIsOption2());
					reponseDetails.setIsOption3(reponseDTO.getIsOption3());
					reponseDetails.setIsOption4(reponseDTO.getIsOption4());
					reponseDetails.setReponse(rep);
					reponseDetails.setQuestion(question);
					reponsesDetails.add(reponseDetails);
				}
						}
			});
			rep.setReponsesDetails(reponsesDetails);
			rep.setHasRespense(true);
			Reponse repSaved=new Reponse();
			repSaved=reponseRepository.save(rep);
			if(!repSaved.getReponsesDetails().isEmpty()){
				return true;
			}
			
			throw new ApiNotModifiedException("Reponse is unsuccessfully inserted");
		
	}
	
	

	

}
