package com.example.estudantes.utils;

import java.util.List;

import com.example.estudantes.domain.Estudante;
import com.example.estudantes.dto.EstudanteDto;
import com.example.estudantes.dto.EstudanteResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface EstudanteMapper {
    @Mapping(target = "id", ignore = true)
    Estudante dtoToEntity(EstudanteDto dto);

    EstudanteResponseDto entityToResponseDto(Estudante entity);

    List<EstudanteResponseDto> entitiesToResponseDto(List<Estudante> entities);

    @Mapping(target = "id", ignore = true)
    void copyUpdateDtoToEntity(EstudanteDto dto, @MappingTarget Estudante entity);
}
