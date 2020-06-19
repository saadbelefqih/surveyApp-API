package ma.surveyapp.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import ma.surveyapp.model.Annonce;
@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long>{
	Annonce findByLibelleAnnonceIgnoreCaseAndDateDebutPublication(String libelle,Date date);
	
	// AndDateDebutPublicationGreaterThanEqualAndDateFinPublicationLessThanEqual    
	Page<Annonce> findByLibelleAnnonceContainingIgnoreCaseOrDetailAnnonceContainingIgnoreCaseAndDateDebutPublicationGreaterThanEqualAndDateFinPublicationLessThanEqual(String libelle,String details,@DateTimeFormat(pattern = "yyyy-MM-dd") Date datepub,@DateTimeFormat(pattern = "yyyy-MM-dd") Date datefinPub,Pageable pageable);
	
	
	@Query("SELECT a FROM Annonce a WHERE (UPPER(a.libelleAnnonce) LIKE CONCAT('%',?1,'%') OR UPPER(a.detailAnnonce) LIKE CONCAT('%',?2,'%')) AND (a.dateDebutTravail>= ?3 AND a.dateFinTravail<=?4) AND a.dateFinPublication>=?5")
	Page<Annonce> findFullSearchByDateDebutAndFinTravail(
					@Param("words1") String words1,@Param("words2") String words2,@Param("datedebutTravail") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedebutTravail,@Param("datefinTravail") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefinTravail,@Param("datefinpub") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefinpub,Pageable pageable);
	
	
	@Query("SELECT a FROM Annonce a WHERE (UPPER(a.libelleAnnonce) LIKE CONCAT('%',?1,'%') OR UPPER(a.detailAnnonce) LIKE CONCAT('%',?2,'%')) AND a.dateDebutTravail>= ?3  AND a.dateFinPublication>=?4")
	Page<Annonce> findFullSearchByDateDebutTrvail(
					@Param("words1") String words1,@Param("words2") String words2,@Param("datedebutTravail") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedebutTravail,@Param("datefinpub") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefinpub,Pageable pageable);
	
	
	@Query("SELECT a FROM Annonce a WHERE (UPPER(a.libelleAnnonce) LIKE CONCAT('%',?1,'%') OR UPPER(a.detailAnnonce) LIKE CONCAT('%',?2,'%')) AND a.dateFinTravail<=?3 AND a.dateFinPublication>=?4")
	Page<Annonce> findFullSearchByDateFinTravail(
					@Param("words1") String words1,@Param("words2") String words2,@Param("datefinTravail") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefinTravail,@Param("datefinpub") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefinpub,Pageable pageable);
	
	
	@Query("SELECT a FROM Annonce a WHERE (UPPER(a.libelleAnnonce) LIKE CONCAT('%',?1,'%') OR UPPER(a.detailAnnonce) LIKE CONCAT('%',?2,'%')) AND a.dateFinPublication>=?3")
	Page<Annonce> findFullSearch(
					@Param("words1") String words1,@Param("words2") String words2,@Param("datefinpub") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefinpub,Pageable pageable);
}
