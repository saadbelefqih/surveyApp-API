package ma.surveyapp.util.modelmapper;

import ma.surveyapp.dto.GroupeDTO;
import ma.surveyapp.model.Groupe;

public class GroupeMapper {
	
	public static GroupeDTO groupeToGroupeDTO(Groupe groupe){
		GroupeDTO groupeDTO = new GroupeDTO();
		groupeDTO.setId(groupe.getIdGroupe());
		groupeDTO.setIntitule(groupe.getIntituleGroupe());
		groupeDTO.setDesc(groupe.getDescGroupe());
		groupeDTO.setNbParticipantDemander(groupe.getNbParticipant());
		groupeDTO.setDateDebuteTravail(groupe.getDateDebuteTravailGroupe());
		groupeDTO.setDateFinTravail(groupe.getDateFinTravailGroupe());
		groupeDTO.setNbParticipantExist(groupe.getDemandes().size());
		return groupeDTO;
	}
	
	public static Groupe groupeDtoToGroupe(GroupeDTO groupedto){
		Groupe groupe = new Groupe();
		groupe.setIdGroupe(groupedto.getId());
		groupe.setIntituleGroupe(groupedto.getIntitule());
		groupe.setDescGroupe(groupedto.getDesc());
		groupe.setNbParticipant(groupedto.getNbParticipantDemander());
		groupe.setDateDebuteTravailGroupe(groupedto.getDateDebuteTravail());
		groupe.setDateFinTravailGroupe(groupedto.getDateFinTravail());
		return groupe;
	}
}
