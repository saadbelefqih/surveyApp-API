package ma.surveyapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class ProfessionDTO {
	private Long idProfession;
	@NotBlank
    @Size(max = 40)
	private String libelleProfession;
	private String descProfession;

}
