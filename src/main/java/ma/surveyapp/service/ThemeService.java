package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Theme;

public interface ThemeService {
	List<Theme> getAll()throws ApiNoContentException,ApiInternalServerErrorExeption;
	Theme getByID(Long idTheme)throws ApiNoContentException,ApiInternalServerErrorExeption;
	Theme save(Theme Theme)throws ApiConflictException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	Theme update(Theme Theme)throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	void delete(Long idTheme)throws ApiInternalServerErrorExeption;

}
