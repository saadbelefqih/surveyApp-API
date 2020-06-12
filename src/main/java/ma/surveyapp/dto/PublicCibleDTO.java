package ma.surveyapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class PublicCibleDTO {
	private Long id;
	@NotBlank
    @Size(max = 40,min=4)
	private String libelle;
	private String desc;
	private int nbAnnonce;
}
