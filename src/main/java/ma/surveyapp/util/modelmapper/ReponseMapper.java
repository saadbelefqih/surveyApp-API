package ma.surveyapp.util.modelmapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ma.surveyapp.dto.ReponseDetailsDTO;
import ma.surveyapp.dto.ReponseResponseDTO;
import ma.surveyapp.model.Reponse;

public class ReponseMapper {
	public static ReponseResponseDTO ReponseToReponseResponseDTO(Reponse reponse){
		ReponseResponseDTO reponseResponseDTO = new ReponseResponseDTO();
		reponseResponseDTO.setIdReponse(reponse.getIdReponse());
		reponseResponseDTO.setHasRespense(reponse.getHasRespense());
		reponseResponseDTO.setDemande(DemandeMapper.demandeToDemandeDTO(reponse.getDemande()));
		reponseResponseDTO.setLigneQuestionnaire(LigneQuestionnaireMapper.ligneQuestionnaireToLigneQuestionnaireDto(reponse.getLigneQuestionnaire()));
		Set<ReponseDetailsDTO> details=new HashSet<>();
		if(!reponse.getReponsesDetails().isEmpty()){
			details= reponse.getReponsesDetails().stream().map(detail->{
				return ReponseDetailsMapper.reponseDetailsToReponseDetailsDTO(detail);
			}).collect(Collectors.toSet());
		}
		reponseResponseDTO.setReponsesDetails(details);
		
		return reponseResponseDTO;
	}

}
