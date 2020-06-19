package ma.surveyapp.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{
	Participant findByNomIgnoreCaseAndPrenomIgnoreCase(String nom,String prenom);
	Optional<Participant> findByUsername(String userName);

	
}
