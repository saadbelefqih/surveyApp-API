package ma.surveyapp.service;

//import java.util.List;

import org.springframework.data.domain.Page;

import ma.surveyapp.dto.ReponseRequestDTO;
import ma.surveyapp.dto.ReponseResponseDTO;
//import ma.surveyapp.model.Reponse;

public interface ReponseService {
	
	
	/*List<Reponse> getAllByLigneQuestionnaireAndVolontaire(Long idLigneQuestionnaire,Long idDemande);
	
	List<Reponse> getAllByLigneQuestionnaire(Long idLigneQuestionnaire) ;
	List<Reponse>getAllByToResponseByLigneQuestionnaire(Long idLigneQuestionnaire);
	List<Reponse>getAllByRespondedByLigneQuestionnaire(Long idLigneQuestionnaire);
	
	List<Reponse> getAllByDemande(Long idDemande);
	List<Reponse>getAllByRespondedByDemande(Long idDemande);
	
	Reponse getById(Long id);*/

	Page<ReponseResponseDTO>getToAnswersByDemande(Long idDemande,Long idParticipant,int page,int size);
	Boolean saveAnswer(ReponseRequestDTO reponseRequestDTO,Long idDemande);
	
	

}
