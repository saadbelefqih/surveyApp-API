package ma.surveyapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Responsable;

public interface ResponsableRepository extends JpaRepository<Responsable, Long>{
	Optional<Responsable> findByUsername(String userName);

}
