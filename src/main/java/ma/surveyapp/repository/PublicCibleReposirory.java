package ma.surveyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.PublicCible;

public interface PublicCibleReposirory extends JpaRepository<PublicCible, Long>{
	PublicCible findByLibellePublicIgnoreCase(String libellePublic);

}
