package ma.surveyapp.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Ville;

public interface VilleRepository extends JpaRepository<Ville, Long>{
	Page<Ville> findByLibelleVilleIgnoreCaseContaining(String libelle,Pageable pageable);
	
	Ville findByLibelleVilleIgnoreCase(String libelle);

}
