package edge.academy.estudantes.controller;

import java.util.Collections;
import java.util.Map;

import edge.academy.estudantes.domain.Estudante;
import edge.academy.estudantes.dto.EstudanteDto;
import edge.academy.estudantes.dto.EstudanteFilterDto;
import edge.academy.estudantes.dto.EstudanteResponseDto;
import edge.academy.estudantes.service.EstudanteService;
import edge.academy.estudantes.utils.EstudanteMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudantes")
@Tag(name = "API simples de cadastro de estudantes")
public class EstudanteController {
    private final EstudanteService estudanteService;
    private final EstudanteMapper estudanteMapper;

    public EstudanteController(EstudanteService estudanteService, EstudanteMapper estudanteMapper) {
        this.estudanteService = estudanteService;
        this.estudanteMapper = estudanteMapper;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna as informações do estudante com o id passado")
    public EstudanteResponseDto findById(@PathVariable int id) {
        var entity = this.estudanteService.findById(id);
        return this.estudanteMapper.entityToResponseDto(entity);
    }

    @GetMapping
    @Operation(summary = "Retorna as informações de todos os estudantes de forma paginada")
    public PagedModel<EstudanteResponseDto> findAll(Pageable pageable) {
        var estudantes = this.estudanteService.findAll(pageable);
        return new PagedModel<>(estudantes.map(this.estudanteMapper::entityToResponseDto));
    }

    @PostMapping("/filter")
    @Operation(summary = "Retorna as informações dos estudantes de uma ou mais turmas")
    public PagedModel<EstudanteResponseDto> findAll(Pageable pageable, @RequestBody EstudanteFilterDto dto) {
        Specification<Estudante> spec = Specification.where(null), tmp;
        if (dto.turmas() != null && !dto.turmas().isEmpty()) {
            for (var turma : dto.turmas()) {
                tmp = (root, query, builder) -> builder.equal(root.get("turma"), turma);
                spec = tmp.or(spec);
            }
        }

        var estudantes = this.estudanteService.findBySpecification(spec, pageable);
        return new PagedModel<>(estudantes.map(this.estudanteMapper::entityToResponseDto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salva um novo estudante no sistema")
    public EstudanteResponseDto save(@RequestBody EstudanteDto dto) {
        var entity = this.estudanteMapper.dtoToEntity(dto);
        var savedEntity = this.estudanteService.save(entity);
        return this.estudanteMapper.entityToResponseDto(savedEntity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza as informações do estudante com o id passado")
    public Map<?, ?> update(@PathVariable int id, @RequestBody EstudanteDto dto) {
        this.estudanteService.update(id, entity -> this.estudanteMapper.copyUpdateDtoToEntity(dto, entity));
        return Collections.emptyMap();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Apaga um estudante do sistema")
    public void deleteById(@PathVariable int id) {
        this.estudanteService.deleteById(id);
    }
}
