package ma.surveyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.surveyapp.model.Profession;
@Repository
public interface ProfessionRepository extends JpaRepository<Profession,Long>{
	@Query(value="SELECT P FROM Profession P WHERE  UPPER(P.libelleProfession) LIKE CONCAT('%',?1,'%') OR UPPER(P.descProfession) LIKE CONCAT('%',?1,'%')")
	Page<Profession> findByFullTextSearch(@Param("name")String name,Pageable pageable);
	Profession findByLibelleProfessionIgnoreCase(String libelle);

}
