package ma.surveyapp.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Long>{
	Annonce findByLibelleAnnonceAndDateAjout(String libelle,Date date);

}
