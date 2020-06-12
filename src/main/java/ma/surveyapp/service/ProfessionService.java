package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.dto.ProfessionDTO;

public interface ProfessionService {
	List<ProfessionDTO> getAll(String name,int page,int size);
	ProfessionDTO getByID(Long idprofession);
	ProfessionDTO save(ProfessionDTO profession);
	ProfessionDTO update(ProfessionDTO profession);
	void delete(Long idprofession);

}
