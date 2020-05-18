package ma.surveyapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Annonce;
import ma.surveyapp.model.LigneQuestionnaire;
import ma.surveyapp.model.Reponse;
import ma.surveyapp.repository.GroupeRepository;
import ma.surveyapp.repository.LigneQuestionnaireRepository;
import ma.surveyapp.repository.QuestionnaireRepository;
import ma.surveyapp.repository.ReponseRepository;
import ma.surveyapp.service.LigneQuestionnaireService;
@Service
@RequiredArgsConstructor
@Slf4j
public class LigneQuestionnaireServiceImpl implements LigneQuestionnaireService{
	private final LigneQuestionnaireRepository ligneQuestionnaireRepository;
	private final GroupeRepository groupeRepository;
	private final QuestionnaireRepository questionnaireRepository;
	private final ReponseRepository reponseRepository;

	@Override
	public List<LigneQuestionnaire> getAll() throws ApiInternalServerErrorExeption,ApiNoContentException {
		try {
			if(!CollectionUtils.isEmpty(ligneQuestionnaireRepository.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return ligneQuestionnaireRepository.findAll();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public LigneQuestionnaire getbyID(Long idLigneQuestionnaire) throws ApiNoContentException, ApiInternalServerErrorExeption {
		try{
			if(ligneQuestionnaireRepository.existsById(idLigneQuestionnaire)){
				return ligneQuestionnaireRepository.getOne(idLigneQuestionnaire);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public LigneQuestionnaire save(LigneQuestionnaire lq) throws ApiInternalServerErrorExeption {
		try {
			if(!groupeRepository.existsById(lq.getGroupe().getIdEquipe())){
				throw new ApiNotFoundException("Groupe does not exist");
			}
			if(!questionnaireRepository.existsById(lq.getQuestionnaire().getIdQuestionnaire())){
				throw new ApiNotFoundException("Qusestionnaire does not exist");
			}
			if(ligneQuestionnaireRepository.findByQuestionnaireAndGroupe(lq.getQuestionnaire(), lq.getGroupe())!=null){
				throw new ApiConflictException("Ligne already exist");
			}
			Annonce  a = groupeRepository.findById(lq.getGroupe().getIdEquipe()).get().getAnnonce();
			if(!lq.getDateDebutReponse().after(a.getDateDebuteTravail())
					|| !lq.getDateDebutReponse().before(a.getDateFinTravail())
					|| !lq.getDateFinReponse().after(a.getDateDebuteTravail())
					|| !lq.getDateFinReponse().before(a.getDateFinTravail())
					){
				throw new ApiConflictException("Interval not respected");
			}
			LigneQuestionnaire lqSaved = ligneQuestionnaireRepository.save(lq);
			if(lqSaved!=null){
				lqSaved.getGroupe().getDemandes().forEach(
						dmd->{
							Reponse rep = new Reponse();
							rep.setDemande(dmd);
							rep.setHasRespense(false);
							rep.setLigneQuestionnaire(lqSaved);
							reponseRepository.save(rep);}
						);
				return lqSaved;
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}


	@Override
	public void delete(Long idLigneQuestionnaire) throws ApiNotModifiedException,ApiInternalServerErrorExeption {
		try {
			if(ligneQuestionnaireRepository.existsById(idLigneQuestionnaire) && ligneQuestionnaireRepository.findById(idLigneQuestionnaire).get().getReponses().size()>0){
				throw new ApiNotModifiedException("Entity is unsuccessfully deleted");
			}
			ligneQuestionnaireRepository.deleteById(idLigneQuestionnaire);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
		
	}

	@Override
	public List<LigneQuestionnaire> getByGroupe(Long idGroupe) throws ApiInternalServerErrorExeption,ApiNotFoundException,ApiNoContentException {
		try {
				if(!groupeRepository.existsById(idGroupe)){
					throw new ApiNotFoundException("Groupe does not exist");
				}
				if(!CollectionUtils.isEmpty(ligneQuestionnaireRepository.findByGroupe(groupeRepository.getOne(idGroupe)))){
					throw new ApiNoContentException("No result founded");
				}
				return ligneQuestionnaireRepository.findByGroupe(groupeRepository.getOne(idGroupe));
			} 
		catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<LigneQuestionnaire> getByQuestionnaire(Long idQuestionnaire) throws ApiInternalServerErrorExeption,ApiNotFoundException,ApiNoContentException {
		try {
			if(!questionnaireRepository.existsById(idQuestionnaire)){
				throw new ApiNotFoundException("Questionnaire does not exist");
			}
			if(!CollectionUtils.isEmpty(ligneQuestionnaireRepository.findByQuestionnaire(questionnaireRepository.getOne(idQuestionnaire)))){
				throw new ApiNoContentException("No result founded");
			}
			return ligneQuestionnaireRepository.findByQuestionnaire(questionnaireRepository.getOne(idQuestionnaire));
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}
	

}
