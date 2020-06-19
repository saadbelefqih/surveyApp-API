package ma.surveyapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@DiscriminatorValue("RSP")
@EqualsAndHashCode(callSuper=true)
@Data @ToString @AllArgsConstructor @NoArgsConstructor
public final class Responsable extends UserApp{
	private String grade;
}
