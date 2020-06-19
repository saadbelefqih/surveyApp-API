package ma.surveyapp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.surveyapp.model.Participant;
import ma.surveyapp.model.Responsable;
import ma.surveyapp.repository.ParticipantRepository;
import ma.surveyapp.repository.ResponsableRepository;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	private final ParticipantRepository participantRepository;
	private final ResponsableRepository responsableRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AtomicBoolean isParticipant= new AtomicBoolean(false);
		AtomicBoolean isResponsable=new AtomicBoolean(false);
		Participant participant = new Participant();
		Responsable responsable = new Responsable();
		participantRepository.findByUsername(username).ifPresent(par->{
			participant.setUsername(par.getUsername());
			participant.setPassword(par.getPassword());
			participant.setRoles(par.getRoles());
			isParticipant.set(true);
		});
		if(!isParticipant.get()){
			responsableRepository.findByUsername(username).ifPresent(res->{
				responsable.setUsername(res.getUsername());
				responsable.setPassword(res.getPassword());
				responsable.setRoles(res.getRoles());
				isResponsable.set(true);
			});
		}
		if(!isParticipant.get() && !isResponsable.get())
		{
			throw new  UsernameNotFoundException("user not exists");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if(isParticipant.get()){
			participant.getRoles().forEach(r->{
				authorities.add(new SimpleGrantedAuthority(r.getName()));
				});

			return new User(participant.getUsername(),participant.getPassword(),authorities);
		}
		else{
			responsable.getRoles().forEach(r->{
				authorities.add(new SimpleGrantedAuthority(r.getName()));
				});
			return new User(responsable.getUsername(),responsable.getPassword(),authorities);
		}
	}

}
