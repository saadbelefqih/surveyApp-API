package ma.surveyapp.util.modelmapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.model.Annonce;

public class AnnonceMapper {
	
	public static AnnonceDTO annonceToAnnonceDTO(Annonce annonce){
		AnnonceDTO annonceDTO = new AnnonceDTO();
		annonceDTO.setId(annonce.getIdAnnonce());
		annonceDTO.setLibelle(annonce.getLibelleAnnonce());
		annonceDTO.setDetail(annonce.getDetailAnnonce());
		annonceDTO.setNbParticipantTheorique(annonce.getNbParticipantDemander());
		annonceDTO.setNbParticipantPresent(annonce.getDemandes().size());
		annonceDTO.setNbGroupe(annonce.getGroupes().size());
		annonceDTO.setDateDebutPublication(annonce.getDateDebutPublication());
		annonceDTO.setDateFinPublication(annonce.getDateFinPublication());
		annonceDTO.setDateDebutTravail(annonce.getDateDebutTravail());
		annonceDTO.setDateFinTravail(annonce.getDateFinTravail());
		Set<PublicCibleDTO>publicsCibleDTO=annonce.getPublics().stream().map(pubCible->{
			return PublicCibleMapper.publicCibleToPublicCibleDTO(pubCible);
		}).collect(Collectors.toSet());
		annonceDTO.setPublicsCible(publicsCibleDTO);
		return annonceDTO;
	}
	
	
	public static Annonce annonceDtoToAnnonce(AnnonceDTO annonceDTO){
		Annonce annonce = new Annonce();
		annonce.setIdAnnonce(annonceDTO.getId());
		annonce.setLibelleAnnonce(annonceDTO.getLibelle());
		annonce.setDetailAnnonce(annonceDTO.getDetail());
		annonce.setNbParticipantDemander(annonceDTO.getNbParticipantTheorique());
		annonce.setDateDebutPublication(annonceDTO.getDateDebutPublication());
		annonce.setDateFinPublication(annonceDTO.getDateFinPublication());
		annonce.setDateDebutTravail(annonceDTO.getDateDebutTravail());
		annonce.setDateFinTravail(annonceDTO.getDateFinTravail());
		annonce.setDemandes(new HashSet<>());
		annonce.setPublics(new HashSet<>());
		annonce.setGroupes(new HashSet<>());
		
		return annonce;
	}

}
