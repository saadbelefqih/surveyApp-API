package ma.surveyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.UserApp;

public interface UserAppRepository extends JpaRepository<UserApp, Long>{
	UserApp findByUsername(String username);

}
