package ma.surveyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.RoleApp;


public interface RoleAppRepository extends JpaRepository<RoleApp, Long>{
	
	//@Query("SELECT r FROM RoleApp r WHERE UPPER(r.roleName) LIKE UPPER(:roleName)")
	public RoleApp findByName(String role);

}
