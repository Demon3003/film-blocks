package com.zhurawell.base.api.rest.mapper;

import com.zhurawell.base.api.rest.dto.movie.MovieDto;
import com.zhurawell.base.api.rest.dto.user.UserDto;
import com.zhurawell.base.data.model.movie.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    public MovieDto entityToDto(Movie user);

    public Movie dtoToEntity(MovieDto dto);

    List<MovieDto> entityListToDtoList(List<Movie> entity);

    List<MovieDto> dtoListToEntityList(List<UserDto> api);
}
