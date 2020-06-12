package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.surveyapp.model.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
	@Query(value="SELECT t FROM Theme t WHERE  UPPER(t.intituleTheme) LIKE CONCAT('%',?1,'%')")
	List<Theme> findByInIntitule(@Param("intitule") String intitule,Pageable pageable);
	Page<Theme> findByIntituleThemeIgnoreCaseContaining(String libelle,Pageable pageable);
	Theme findByIntituleThemeIgnoreCase(String libelle);

}
