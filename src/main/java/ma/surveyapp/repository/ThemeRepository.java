package ma.surveyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
	
	Theme findByIntituleThemeIgnoreCase(String libelle);

}
