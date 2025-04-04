package edge.academy.estudantes.utils;

import java.util.List;

import edge.academy.estudantes.domain.Estudante;
import edge.academy.estudantes.dto.EstudanteDto;
import edge.academy.estudantes.dto.EstudanteResponseDto;
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
