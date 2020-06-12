package ma.surveyapp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.DemandeResponseDTO;
import ma.surveyapp.dto.GroupeDTO;
import ma.surveyapp.dto.LigneQuestionnaireRequestDTO;
import ma.surveyapp.dto.LigneQuestionnaireResponseDTO;
import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.exception.ApiConflictException;
//import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Annonce;
import ma.surveyapp.model.Groupe;
import ma.surveyapp.model.LigneQuestionnaire;
import ma.surveyapp.model.PublicCible;
import ma.surveyapp.model.Questionnaire;
import ma.surveyapp.model.Reponse;
import ma.surveyapp.repository.AnnonceRepository;
import ma.surveyapp.repository.GroupeRepository;
import ma.surveyapp.repository.LigneQuestionnaireRepository;
import ma.surveyapp.repository.PublicCibleReposirory;
import ma.surveyapp.repository.QuestionnaireRepository;
import ma.surveyapp.repository.ReponseDetailsRepository;
import ma.surveyapp.service.AnnonceService;
import ma.surveyapp.util.modelmapper.AnnonceMapper;
import ma.surveyapp.util.modelmapper.DemandeMapper;
import ma.surveyapp.util.modelmapper.GroupeMapper;
import ma.surveyapp.util.modelmapper.LigneQuestionnaireMapper;
import ma.surveyapp.util.modelmapper.PublicCibleMapper;

@Service
@RequiredArgsConstructor
public class AnnonceServiceImpl implements AnnonceService{
	private final AnnonceRepository annonceRepository;
	private final PublicCibleReposirory publicCibleReposirory;
	private final GroupeRepository groupeRepository;
	private final QuestionnaireRepository questionnaireRepository;
	private final LigneQuestionnaireRepository ligneQuestionnaireRepository;
	private final ReponseDetailsRepository reponseDetailsRepository;
	@Override
	public Page<AnnonceDTO> getAll(int page, int size){
		
			List<AnnonceDTO> annonces=new ArrayList<AnnonceDTO>();
			annonces= annonceRepository.findAll(PageRequest.of(page, size)).stream().
					map(annonce->{return AnnonceMapper.annonceToAnnonceDTO(annonce);}).collect(Collectors.toList());
			if(!annonces.isEmpty()){
				return  new PageImpl<>(annonces, PageRequest.of(page, size), annonces.size());
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public AnnonceDTO getByID(Long idAnnonce) {
		
			if(annonceRepository.existsById(idAnnonce)){
				return AnnonceMapper.annonceToAnnonceDTO(annonceRepository.getOne(idAnnonce));
			}
			throw new ApiNotFoundException("No result founded");
	}
	


	@Override
	public AnnonceDTO save(AnnonceDTO annonceDTO){
		
			if(annonceRepository.findByLibelleAnnonceIgnoreCaseAndDateDebutPublication(annonceDTO.getLibelle(),annonceDTO.getDateDebutTravail())!=null){
				throw new ApiConflictException("Annonce already exist");
			}

			Set<PublicCible> publicsCible =annonceDTO.getPublicsCible().stream().map(pubCibleDTO->{
				return PublicCibleMapper.publicCibleDtoToPublicCible(pubCibleDTO);
			}).collect(Collectors.toSet());
			
			Annonce annonceToSave = AnnonceMapper.annonceDtoToAnnonce(annonceDTO);
			Annonce annonceSaved=annonceRepository.save(addPublicCible(annonceToSave,publicsCible));
			if(annonceSaved!=null){
				return AnnonceMapper.annonceToAnnonceDTO(annonceSaved);
			}
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");
		
	}
	@Override
	public AnnonceDTO addPublicCibleToAnnonce(Long idAnnonce,Set<PublicCibleDTO> publicsCible){
			
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNotFoundException("Annonce not existe");}
			Set<PublicCible> cibles = new HashSet<>();
			cibles= publicsCible.stream().map(p->{
				return PublicCibleMapper.publicCibleDtoToPublicCible(p);
			}).collect(Collectors.toSet());
			
			return AnnonceMapper.annonceToAnnonceDTO(annonceRepository.save(addPublicCible(annonceRepository.findById(idAnnonce).get(),cibles)));
	}

	
	private Annonce addPublicCible(Annonce annonce,Set<PublicCible> publicsCible){
			
			publicsCible.forEach(p->{
				// check if Libelle propertie figure in db in DB
					//if not exist then create it
				if(publicCibleReposirory.findByLibellePublicIgnoreCase(p.getLibellePublic())==null){
				p.setAnnonces(new HashSet<>());
				p.setIdPublic(null);
				PublicCible pC=publicCibleReposirory.save(p);
				p=pC;}
				else{
					p=publicCibleReposirory.findByLibellePublicIgnoreCase(p.getLibellePublic());
				} 
				p.getAnnonces().add(annonce);
				
				annonce.getPublics().add(p);
			});
			return annonce;
		
	}

	@Override
	public AnnonceDTO deletePublicPublicCibleFromAnnonce(Long idAnnonce,Set<PublicCibleDTO> publicsCible) {
			//check if entity exist
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiConflictException("Annonce already exist");}
			//convert collection of PublicCibleDTO TO collection of PublicCible
			final Set<PublicCible> cibles = publicsCible.stream().map(p->{
				return PublicCibleMapper.publicCibleDtoToPublicCible(p);
			}).collect(Collectors.toSet());
			
			// instance list of object to delete
			Set<PublicCible> ciblesToDelete= new HashSet<>();
			
			// for checking if element is detected and deleted from publicCibleReposirory
			AtomicBoolean ifDeleted=new AtomicBoolean(false);
			
			Annonce annonceFinded = annonceRepository.findById(idAnnonce).get();
			
			//search if PublicCible of annonce containe  PublicCible to delete 
				// add to list if exist
			annonceFinded.getPublics().forEach(a->{
				cibles.forEach(c->{
					if(c.getIdPublic()==a.getIdPublic() && c.getIdPublic()==a.getIdPublic() && c.getIdPublic()!=null && a.getIdPublic()!=null){
						ciblesToDelete.add(a);
						a.getAnnonces().remove(annonceFinded);
						if(publicCibleReposirory.save(a)!=null)
						ifDeleted.set(true);
						
					}
				});
			});
			//remove PublicCible founded  from Annonce
			if(ifDeleted.get()==true){
				annonceFinded.getPublics().removeAll(ciblesToDelete);
					return AnnonceMapper.annonceToAnnonceDTO(annonceFinded);
				
			}
			
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");
		
	}

	@Override
	public AnnonceDTO update(AnnonceDTO annonceDTO){
			if(!annonceRepository.existsById(annonceDTO.getId())){
				throw new ApiNotFoundException("Annonce does not exist");
			}
			Annonce annonce=annonceRepository.findById(annonceDTO.getId()).get();
			annonce.setDateDebutPublication(annonceDTO.getDateDebutPublication());
			annonce.setDateDebutTravail(annonceDTO.getDateDebutTravail());
			annonce.setDateFinPublication(annonceDTO.getDateFinPublication());
			annonce.setDateFinTravail(annonceDTO.getDateFinTravail());
			annonce.setDetailAnnonce(annonceDTO.getDetail());
			annonce.setLibelleAnnonce(annonceDTO.getLibelle());
			annonce.setNbParticipantDemander(annonceDTO.getNbParticipantTheorique());
			annonce.setDemandes(annonce.getDemandes());
			annonce.setGroupes(annonce.getGroupes());
			annonce.setPublics(annonce.getPublics());
			Annonce annonceSaved = annonceRepository.save(annonce);
			if(annonceSaved!=null){
				return AnnonceMapper.annonceToAnnonceDTO(annonceSaved);
			}
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");
	}

	@Override
	public void delete(Long idAnnonce) {
			annonceRepository.deleteById(idAnnonce);
	}

	@Override
	public Page<GroupeDTO> getGroupesByAnnonce(Long idAnnonce,int page, int size){
			if(annonceRepository.existsById(idAnnonce)){
				List<GroupeDTO> groupesDTO= annonceRepository.findById(idAnnonce).get().
						getGroupes().stream().map(grp->{
							return GroupeMapper.groupeToGroupeDTO(grp);
							}).collect(Collectors.toList());
				return  new PageImpl<>(groupesDTO, PageRequest.of(page, size), groupesDTO.size());
			}
			throw new ApiNoContentException("No result founded");
	}

	@Override
	public Page<DemandeResponseDTO> getDemandesByAnnonce(Long idAnnonce,int page, int size){
			if(annonceRepository.existsById(idAnnonce)){
				List<DemandeResponseDTO> demandes= annonceRepository.findById(idAnnonce).get().
						getDemandes().stream().map(dmd->{
							return DemandeMapper.demandeToDemandeDTO(dmd);
							}).collect(Collectors.toList());
				
				return  new PageImpl<>(demandes, PageRequest.of(page, size), demandes.size());
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public Page<DemandeResponseDTO> getValideDemandesByAnnonce(Long idAnnonce, int page, int size){
			if(annonceRepository.existsById(idAnnonce)){
				List<DemandeResponseDTO> demandes= new ArrayList<>();
				annonceRepository.findById(idAnnonce).get().
						getDemandes().forEach(dmd->{
							if(dmd.getIsValide() && !dmd.getIsPending() && !dmd.getIsRefuse()){
								demandes.add(DemandeMapper.demandeToDemandeDTO(dmd));}
						});
				
				return  new PageImpl<>(demandes, PageRequest.of(page, size), demandes.size());
			}
			
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public Page<DemandeResponseDTO> getRefuseDemandesByAnnonce(Long idAnnonce, int page, int size){
			if(annonceRepository.existsById(idAnnonce)){
				List<DemandeResponseDTO> demandes= new ArrayList<>();
				annonceRepository.findById(idAnnonce).get().
						getDemandes().forEach(dmd->{
							if(!dmd.getIsValide() && !dmd.getIsPending() && dmd.getIsRefuse()){
								demandes.add(DemandeMapper.demandeToDemandeDTO(dmd));}
						});
				
				return  new PageImpl<>(demandes, PageRequest.of(page, size), demandes.size());
			}
			
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public Page<DemandeResponseDTO> getPendingDemandesByAnnonce(Long idAnnonce, int page, int size){
			if(annonceRepository.existsById(idAnnonce)){
				List<DemandeResponseDTO> demandes= new ArrayList<>();
				annonceRepository.findById(idAnnonce).get().
						getDemandes().forEach(dmd->{
							if(!dmd.getIsValide() && dmd.getIsPending() && !dmd.getIsRefuse()){
								demandes.add(DemandeMapper.demandeToDemandeDTO(dmd));}
						});
				return  new PageImpl<>(demandes, PageRequest.of(page, size), demandes.size());
			}
			
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public GroupeDTO addGroupeToAnnonce(Long idAnnonce, GroupeDTO groupeDTO){
			
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNotFoundException("Annonce does not exist");
			}
			Annonce annonce = annonceRepository.findById(idAnnonce).get();
			Groupe p = GroupeMapper.groupeDtoToGroupe(groupeDTO);
			p.setIdGroupe(null);
			p.setAnnonce(annonce);
			p.setLigneQuestionnaires(new HashSet<>());
			p.setDemandes(new HashSet<>());
			p=groupeRepository.save(p);
			annonce.getGroupes().add(p);
			if(p.getIdGroupe()!=null){
				return GroupeMapper.groupeToGroupeDTO(p);
			}
			throw new ApiNotModifiedException("Groupe is unsuccessfully inserted");
	}

	@Override
	public GroupeDTO getGroupeByAnnonce(Long idGroupe, Long idAnnonce){
			if(!groupeRepository.existsById(idGroupe)){
				throw new ApiNotFoundException("Groupe does not exist");
			}
			Groupe g = new Groupe();
			g=groupeRepository.findById(idGroupe).get();
			if(g.getAnnonce().getIdAnnonce()!=idAnnonce){
				throw new ApiNotFoundException("Groupe not has annonce");
			}
			return GroupeMapper.groupeToGroupeDTO(g);
	}

	@Override
	public void deleteGroupeFromAnnonce(Long idAnnonce, Long groupeid){
		
			if(!groupeRepository.existsById(groupeid)){
				throw new ApiNotFoundException("Groupe does not exist");}
			Groupe p = groupeRepository.findById(groupeid).get();
			if(p.getAnnonce().getIdAnnonce()!=idAnnonce){
				throw new ApiNotFoundException("Groupe not has annonce");}
			if(!p.getDemandes().isEmpty()){
				throw new ApiNotFoundException("cant delete Groupe if has Demande");
			}
			groupeRepository.deleteById(groupeid);
	}

	@Override
	public Page<LigneQuestionnaireResponseDTO> getLignesQuestionnaireToGroupeByGroupe(Long idGroupe, Long idAnnonce,int page,int size){
			if(!groupeRepository.existsById(idGroupe)){
				throw new ApiNotFoundException("Groupe does not exist");
			}
			if(groupeRepository.findById(idGroupe).get().getAnnonce().getIdAnnonce()!=idAnnonce){
				throw new ApiNotFoundException("Groupe not has annonce");
			}
			List<LigneQuestionnaireResponseDTO> listes=new ArrayList<>(); 
			listes=groupeRepository.findById(idGroupe).get().getLigneQuestionnaires().stream().map(lQ->{
				return LigneQuestionnaireMapper.ligneQuestionnaireToLigneQuestionnaireDto(lQ);
			}).collect(Collectors.toList());
			listes.stream().forEach(LigneQuestDTO->{
				LigneQuestDTO.getQuestionnaire().getQuestions().stream().forEach(question->{
					question.setTotalReponseOption1(reponseDetailsRepository.countOption1ByLigneQuestionnaireIdAndidQuestion(LigneQuestDTO.getIdLQ(), question.getIdQuestion()));
					question.setTotalReponseOption2(reponseDetailsRepository.countOption2ByLigneQuestionnaireIdAndidQuestion(LigneQuestDTO.getIdLQ(), question.getIdQuestion()));
					question.setTotalReponseOption3(reponseDetailsRepository.countOption3ByLigneQuestionnaireIdAndidQuestion(LigneQuestDTO.getIdLQ(), question.getIdQuestion()));
					question.setTotalReponseOption4(reponseDetailsRepository.countOption4ByLigneQuestionnaireIdAndidQuestion(LigneQuestDTO.getIdLQ(), question.getIdQuestion()));
				});
			});
			return new PageImpl<>(listes, PageRequest.of(page, size), listes.size());
			
		
	}
	
	private Optional<LigneQuestionnaireResponseDTO> saveLigneQuestionnairToGroupeeByGroupe(LigneQuestionnaireRequestDTO ligneDTO,Long idAnnonce){
		Boolean isChecked=false;
		LigneQuestionnaire lqGet= new LigneQuestionnaire();
		lqGet=ligneQuestionnaireRepository.findLigneQuestionnaireByQuestionnaireIdAndGroupeId(ligneDTO.getIdQuestionnaire(), ligneDTO.getIdGroupe());
		//Annonce  a= new Annonce();
		//a = annonceRepository.findById(idAnnonce).get();
		
		if(groupeRepository.existsById(ligneDTO.getIdGroupe()) &&
				questionnaireRepository.existsById(ligneDTO.getIdQuestionnaire()) &&
				lqGet==null ){
			isChecked=true;
		}
		if(isChecked){
			LigneQuestionnaire lq = new LigneQuestionnaire();
			Groupe groupe = groupeRepository.findById(ligneDTO.getIdGroupe()).get();
			Questionnaire questionnaire = questionnaireRepository.findById(ligneDTO.getIdQuestionnaire()).get();
			lq.setIdLQ(null);
			lq.setGroupe(groupe);
			lq.setQuestionnaire(questionnaire);
			lq.setDateDebutReponse(ligneDTO.getDateDebutReponse());
			lq.setDateFinReponse(ligneDTO.getDateFinReponse());
			Set<Reponse> listReponses= new HashSet<>();
			listReponses= groupe.getDemandes().stream().map(dmd->{
				Reponse rep = new Reponse();
				rep.setIdReponse(null);
				rep.setHasRespense(false);
				rep.setDemande(dmd);
				rep.setLigneQuestionnaire(lq);
				//reponseRepository.save(rep);
				return rep;
			}).collect(Collectors.toSet());
			lq.setReponses(listReponses);
			LigneQuestionnaire lqSaved = new LigneQuestionnaire();
			lqSaved = ligneQuestionnaireRepository.save(lq);
			return Optional.ofNullable(LigneQuestionnaireMapper.ligneQuestionnaireToLigneQuestionnaireDto(lqSaved));
			
		}
		return Optional.empty();
	}

	@Override
	public List<LigneQuestionnaireResponseDTO> saveLignesQuestionnairToGroupeeByGroupe(Long idGroupe, Long idAnnonce,
			List<LigneQuestionnaireRequestDTO> lignesQuestionnaireDTO){
			List<LigneQuestionnaireResponseDTO> lists=new ArrayList<>();
			lignesQuestionnaireDTO.forEach(ligneDTO->{	
					this.saveLigneQuestionnairToGroupeeByGroupe(ligneDTO, idAnnonce)
					.ifPresent(ligne->lists.add(ligne));
			});
			if(lists.isEmpty()){
				throw new ApiNotModifiedException("0 rows insert");
			}
			return lists;
	}

	@Override
	public void deleteLignesQuestionnairToGroupeeByGroupe(Long idGroupe, Long idAnnonce,
			List<LigneQuestionnaireRequestDTO> lignesQuestionnaireDTO){
			lignesQuestionnaireDTO.forEach(ligneDTO->{
				if(groupeRepository.existsById(ligneDTO.getIdGroupe()) &&
						questionnaireRepository.existsById(ligneDTO.getIdQuestionnaire()) &&
							ligneQuestionnaireRepository.findLigneQuestionnaireByQuestionnaireIdAndGroupeId(ligneDTO.getIdQuestionnaire(), ligneDTO.getIdGroupe())!=null){
					ligneQuestionnaireRepository.deleteLigneQuestionnaireByQuestionnaireIdAndGroupeId(ligneDTO.getIdQuestionnaire(), ligneDTO.getIdGroupe());
				}
				
			});
	}


	
	

}
