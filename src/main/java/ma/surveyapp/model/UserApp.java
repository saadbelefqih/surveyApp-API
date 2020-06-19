package ma.surveyapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name="type_pers",discriminatorType=DiscriminatorType.STRING,length=3)

@Data @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode
public abstract class UserApp {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonValue
	private Long idpers;
	//true pour Homme False pour Femme
	private Boolean genre;
	@NotNull
	@Size(min=4, message="la taille doit être supérieur de 4")
	private String nom;
	@NotNull
	@Size(min=4, message="la taille doit être supérieur de 4")
	private String prenom;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date datenais;
	private String ntel;
	private String adresse;
	private String email;
	private String photo;
	//debut authentitifaction 
	@Column(unique=true)
	@NotNull
    private String username;
	@NotNull
	@Size(min=6, message="la langeur doit être supérieur de 6")
    private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<RoleApp> roles=new ArrayList<>();
	//fin authentification
	@Column(name = "active", columnDefinition = "boolean default true", nullable = false)
	private Boolean active=true;

}
