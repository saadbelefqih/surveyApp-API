package ma.surveyapp.util.modelmapper;

import java.util.HashSet;

import ma.surveyapp.dto.ThemeDTO;
import ma.surveyapp.model.Theme;

public class ThemeMapper {
	public static ThemeDTO ThemeToThemeDTO(Theme theme){
		ThemeDTO themeDTO = new ThemeDTO();
		themeDTO.setIdTheme(theme.getIdTheme());
		themeDTO.setDescTheme(theme.getDescTheme());
		themeDTO.setIntituleTheme(theme.getIntituleTheme());
		themeDTO.setNbQuestionnaire(theme.getQuestionnaires().size());
		return themeDTO;
	}
	
	public static Theme ThemeDtoToTheme(ThemeDTO themeDTO){
		Theme theme = new Theme();
		theme.setIdTheme(themeDTO.getIdTheme());
		theme.setDescTheme(themeDTO.getDescTheme());
		theme.setIntituleTheme(themeDTO.getIntituleTheme());
		theme.setQuestionnaires(new HashSet<>());
		return theme;
	}

}
