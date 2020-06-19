package ma.surveyapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@DiscriminatorValue("PRT")
@EqualsAndHashCode(callSuper=true)
@Data @ToString @AllArgsConstructor @NoArgsConstructor
public final class Participant extends UserApp{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ville_id",nullable = false)
	private Ville ville;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "profession_id",nullable = false)
    private Profession profession;
	@OneToMany(mappedBy = "participant",fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Demande> demandes=new HashSet<Demande>();
	
	

	
}
