package ma.surveyapp.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ma.surveyapp.dto.ParticipantRequestDTO;
import ma.surveyapp.exception.ApiBadRequestException;
import ma.surveyapp.service.AccountService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class AccountController {
	private final AccountService accountService;
	
	@PostMapping("/singUp/Participant")
	public Boolean registerParticipant(@Valid @RequestBody ParticipantRequestDTO participantRequestDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()){
			throw new ApiBadRequestException(bindingResult.getAllErrors().toString());
		}
		return accountService.singUpParticipant(participantRequestDTO);
	}
	

}
