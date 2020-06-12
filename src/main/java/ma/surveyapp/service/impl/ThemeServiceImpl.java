package ma.surveyapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.ThemeDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Theme;
import ma.surveyapp.repository.ThemeRepository;
import ma.surveyapp.service.ThemeService;
import ma.surveyapp.util.modelmapper.ThemeMapper;
@Service
@RequiredArgsConstructor
@Slf4j
public class ThemeServiceImpl implements ThemeService{
	private final ThemeRepository themeRepository;

	@Override
	public Page<ThemeDTO> getAll(String intitule,int page,int size) {
		
			List<ThemeDTO> lists= new ArrayList<>();
			lists=themeRepository.findByInIntitule(intitule.toUpperCase(),PageRequest.of(page, size)).stream().map(theme->{
				return ThemeMapper.ThemeToThemeDTO(theme);
			}).collect(Collectors.toList());
			if(lists.isEmpty()){
				return new PageImpl<ThemeDTO>(lists, PageRequest.of(page, size), lists.size());}
			throw new ApiNoContentException("No result founded");
	}

	@Override
	public ThemeDTO getByID(Long idTheme) throws ApiNoContentException,ApiInternalServerErrorExeption {
		try{
			if(themeRepository.existsById(idTheme)){
				return ThemeMapper.ThemeToThemeDTO(themeRepository.getOne(idTheme));
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public ThemeDTO save(ThemeDTO themeDTO) throws ApiConflictException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try {
			if(themeRepository.findByIntituleThemeIgnoreCase(themeDTO.getIntituleTheme())!=null){
				throw new ApiConflictException("Theme already exist");
			}
			Theme themeToSave = new Theme();
			themeToSave=ThemeMapper.ThemeDtoToTheme(themeDTO);
			themeToSave.setIdTheme(null);
			Theme themeSaved = themeRepository.save(themeToSave);
			if(themeSaved!=null){
				return ThemeMapper.ThemeToThemeDTO(themeSaved);
			}
			throw new ApiNotModifiedException("Theme is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public ThemeDTO update(ThemeDTO themeDTO) throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try {
			if(themeDTO.getIdTheme()==null){
				throw new ApiNotFoundException("theme id must be not null");
			}
			if(!themeRepository.existsById(themeDTO.getIdTheme())){
				throw new ApiNotFoundException("Theme does not exist");
			}
			Theme theme = new Theme();
			theme=themeRepository.findById(themeDTO.getIdTheme()).get();
			theme.setDescTheme(themeDTO.getDescTheme());
			theme.setIntituleTheme(themeDTO.getIntituleTheme());	
			Theme themeSaved = themeRepository.save(theme);
			if(themeSaved!=null){
				return ThemeMapper.ThemeToThemeDTO(themeSaved);
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idTheme) throws ApiNotFoundException,ApiConflictException,ApiInternalServerErrorExeption {
		try {
			if(!themeRepository.existsById(idTheme)){
				throw new ApiNotFoundException("Theme does not exist");
			}
			if(themeRepository.getOne(idTheme).getQuestionnaires().size()>0){
				throw new ApiConflictException("Theme cannot be delete because it has alrady Questionnaire");
			}
			themeRepository.deleteById(idTheme);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
		
	}

	
}
