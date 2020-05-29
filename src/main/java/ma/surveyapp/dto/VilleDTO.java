package ma.surveyapp.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class VilleDTO {
	private Long idVille;
	@NotBlank
    @Size(max = 40)
	private String libelleVille;
	private Float latitudeVille;
	private Float longitudeVille;

}
