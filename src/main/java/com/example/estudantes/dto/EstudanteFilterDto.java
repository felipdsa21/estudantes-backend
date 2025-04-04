package com.example.estudantes.dto;

import java.util.Set;

import com.example.estudantes.domain.Curso;

public record EstudanteFilterDto(Set<Curso> cursos, Set<Integer> turmas) {
}
