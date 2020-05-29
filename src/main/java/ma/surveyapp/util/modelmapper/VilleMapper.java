package ma.surveyapp.util.modelmapper;

import ma.surveyapp.dto.VilleDTO;
import ma.surveyapp.model.Ville;

public class VilleMapper {
	
	public static VilleDTO villeToVilleDTO(Ville ville){
		VilleDTO villeDTO = new VilleDTO();
		villeDTO.setIdVille(ville.getIdVille());
		villeDTO.setLibelleVille(ville.getLibelleVille());
		villeDTO.setLatitudeVille(ville.getLatitudeVille());
		villeDTO.setLongitudeVille(ville.getLongitudeVille());
		return villeDTO;
	}
	
	public static Ville VilleDtoToVille(VilleDTO villeDTO){
		Ville ville = new Ville();
		ville.setIdVille(villeDTO.getIdVille());
		ville.setLibelleVille(villeDTO.getLibelleVille());
		ville.setLatitudeVille(villeDTO.getLatitudeVille());
		ville.setLongitudeVille(villeDTO.getLongitudeVille());
		return ville;
	}

}
