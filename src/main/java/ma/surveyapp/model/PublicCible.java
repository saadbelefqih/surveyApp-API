package ma.surveyapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PublicCible {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPublic;
	@Column(nullable=false,unique=true)
	private String libellePublic;
	private String descPublic;
	@ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
    private Set<Annonce> annonces;

}
