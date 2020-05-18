package ma.surveyapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class ReponseDetails {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRd;
	private Boolean isOption1=false;
	private Boolean isOption2=false;
	private Boolean isOption3=false;
	private Boolean isOption4=false;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(nullable=false)
	private Date dateReponse;
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(nullable = false)
	private Question question;
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(nullable = false)
	private Reponse reponse;

}
