package ma.surveyapp.service;
import org.springframework.data.domain.Page;

import ma.surveyapp.dto.VilleDTO;

public interface VilleService {
	// List<VilleDTO> getAll(int page,int size);
	Page<VilleDTO> getByLibelle(String libelle,int page,int size);
	VilleDTO getByID(Long idville);
	VilleDTO save(VilleDTO ville) ;
	VilleDTO update(Long idExistVille,VilleDTO ville);
	void delete(Long idville);

}
