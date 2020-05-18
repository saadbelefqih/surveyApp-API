package ma.surveyapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{
	Participant findByNomParticipantIgnoreCaseAndPrenomParticipantIgnoreCase(String nom,String prenom);

	
}
