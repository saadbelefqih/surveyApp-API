package ma.surveyapp.service;

import org.springframework.data.domain.Page;

import ma.surveyapp.dto.ThemeDTO;

public interface ThemeService {
	
	Page<ThemeDTO> getAll(String intitule,int page,int size);
	ThemeDTO getByID(Long idTheme);
	ThemeDTO save(ThemeDTO themeDTO);
	ThemeDTO update(ThemeDTO themeDTO);
	void delete(Long idTheme);

}
