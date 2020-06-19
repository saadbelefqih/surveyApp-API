package ma.surveyapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginViewModel {
	@NotBlank
    @Size(max = 40,min=4)
	private String username;
	@NotBlank
    @Size(max = 40,min=4)
    private String password;

}
