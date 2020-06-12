package ma.surveyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long>{
	Groupe findByIntituleGroupeIgnoreCase(String intitule);

}
