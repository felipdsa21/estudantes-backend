package edge.academy.estudantes.dto;

import java.util.Set;

import edge.academy.estudantes.domain.Curso;

public record EstudanteFilterDto(Set<Curso> cursos, Set<Integer> turmas) {
}
