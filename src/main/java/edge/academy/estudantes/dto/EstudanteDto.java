package edge.academy.estudantes.dto;

import java.time.YearMonth;

import edge.academy.estudantes.domain.Curso;

public record EstudanteDto(
    String nome, String email, Curso curso, int turma, int periodoAtual, YearMonth dataDeIngresso
) {
}
