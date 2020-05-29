package ma.surveyapp.util.modelmapper;

import java.util.HashSet;

import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.model.PublicCible;

public class PublicCibleMapper {
	public static PublicCibleDTO publicCibleToPublicCibleDTO(PublicCible publicCible){
		PublicCibleDTO publicCibleDTO=new PublicCibleDTO();
		publicCibleDTO.setId(publicCible.getIdPublic());
		publicCibleDTO.setLibelle(publicCible.getLibellePublic());
		publicCibleDTO.setDesc(publicCible.getDescPublic());
		publicCibleDTO.setNbAnnonce(publicCible.getAnnonces().size());
		return publicCibleDTO;
	}
	
	public static PublicCible publicCibleDtoToPublicCible(PublicCibleDTO publicCibleDTO){
		PublicCible publicCible=new PublicCible();
		publicCible.setIdPublic(publicCibleDTO.getId());
		publicCible.setLibellePublic(publicCibleDTO.getLibelle());
		publicCible.setDescPublic(publicCibleDTO.getDesc());
		publicCible.setAnnonces(new HashSet<>());
		return publicCible;
	}

}
