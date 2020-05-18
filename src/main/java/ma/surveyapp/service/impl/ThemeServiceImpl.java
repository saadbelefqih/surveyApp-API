package ma.surveyapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Theme;
import ma.surveyapp.repository.ThemeRepository;
import ma.surveyapp.service.ThemeService;
@Service
@RequiredArgsConstructor
@Slf4j
public class ThemeServiceImpl implements ThemeService{
	private final ThemeRepository themeRepository;

	@Override
	public List<Theme> getAll() throws ApiNoContentException,ApiInternalServerErrorExeption {
		try {
			if(!CollectionUtils.isEmpty(themeRepository.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return themeRepository.findAll();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Theme getByID(Long idTheme) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try{
			if(themeRepository.existsById(idTheme)){
				return themeRepository.getOne(idTheme);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Theme save(Theme theme) throws ApiConflictException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try {
			if(themeRepository.findByIntituleThemeIgnoreCase(theme.getIntituleTheme())!=null){
				throw new ApiConflictException("Theme already exist");
			}
			Theme themeSaved = themeRepository.save(theme);
			if(themeSaved!=null){
				return themeSaved;
			}
			throw new ApiNotModifiedException("Theme is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public Theme update(Theme theme) throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try {
			if(!themeRepository.existsById(theme.getIdTheme())){
				throw new ApiNotFoundException("Theme does not exist");
			}
			Theme themeSaved = themeRepository.save(theme);
			if(themeSaved!=null){
				return themeSaved;
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idTheme) throws ApiInternalServerErrorExeption {
		try {
			themeRepository.deleteById(idTheme);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
		
	}

	
}
