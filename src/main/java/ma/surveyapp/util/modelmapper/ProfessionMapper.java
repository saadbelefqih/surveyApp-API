package ma.surveyapp.util.modelmapper;

import ma.surveyapp.dto.ProfessionDTO;
import ma.surveyapp.model.Profession;

public class ProfessionMapper {
	
	public static ProfessionDTO ProfessionToProfessionDTO(Profession profession){
		ProfessionDTO professionDTO = new ProfessionDTO();
		professionDTO.setIdProfession(profession.getIdProfession());
		professionDTO.setLibelleProfession(profession.getLibelleProfession());
		professionDTO.setDescProfession(profession.getDescProfession());
		return professionDTO;
	}
	
	public static Profession ProfessionDtoToProfession(ProfessionDTO professionDTO){
		Profession profession = new Profession();
		profession.setIdProfession(professionDTO.getIdProfession());
		profession.setLibelleProfession(professionDTO.getLibelleProfession());
		profession.setDescProfession(professionDTO.getDescProfession());
		return profession;
	}

}
