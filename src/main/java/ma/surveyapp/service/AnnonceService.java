package ma.surveyapp.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.DemandeResponseDTO;
import ma.surveyapp.dto.GroupeDTO;
import ma.surveyapp.dto.LigneQuestionnaireRequestDTO;
import ma.surveyapp.dto.LigneQuestionnaireResponseDTO;
import ma.surveyapp.dto.PublicCibleDTO;

public interface AnnonceService {
	
	Page<AnnonceDTO> getAll(int page, int size);
	AnnonceDTO getByID(Long idAnnonce);
	AnnonceDTO save(AnnonceDTO annonceDTO);
	AnnonceDTO addPublicCibleToAnnonce(Long idAnnonce,Set<PublicCibleDTO> publicsCible);
	AnnonceDTO deletePublicPublicCibleFromAnnonce(Long idAnnonce,Set<PublicCibleDTO> publicsCible);
	AnnonceDTO update(AnnonceDTO annonceDTO);
	void delete(Long idAnnonce);
	
	
	Page<GroupeDTO> getGroupesByAnnonce(Long idAnnonce,int page, int size);
	GroupeDTO getGroupeByAnnonce(Long idGroupe,Long idAnnonce);
	GroupeDTO addGroupeToAnnonce(Long idAnnonce,GroupeDTO groupeDTO);
	void deleteGroupeFromAnnonce(Long idAnnonce,Long groupeid);
	
	Page<LigneQuestionnaireResponseDTO> getLignesQuestionnaireToGroupeByGroupe(Long idGroupe,Long idAnnonce,int page,int size);
	List<LigneQuestionnaireResponseDTO> saveLignesQuestionnairToGroupeeByGroupe(Long idGroupe,Long idAnnonce,List<LigneQuestionnaireRequestDTO> lignesQuestionnaireDTO);
	void deleteLignesQuestionnairToGroupeeByGroupe(Long idGroupe,Long idAnnonce,List<LigneQuestionnaireRequestDTO> lignesQuestionnaireDTO);
	
	Page<DemandeResponseDTO>getDemandesByAnnonce(Long idAnnonce,int page, int size);
	Page<DemandeResponseDTO>getValideDemandesByAnnonce(Long idAnnonce,int page, int size);
	Page<DemandeResponseDTO>getRefuseDemandesByAnnonce(Long idAnnonce,int page, int size);
	Page<DemandeResponseDTO>getPendingDemandesByAnnonce(Long idAnnonce,int page, int size);


}
