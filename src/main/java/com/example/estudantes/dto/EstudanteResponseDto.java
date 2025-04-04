package com.example.estudantes.dto;

import java.time.YearMonth;

import com.example.estudantes.domain.Curso;

public record EstudanteResponseDto(
    int id, String nome, String email, Curso curso, int turma, int periodoAtual, YearMonth dataDeIngresso
) {
}
