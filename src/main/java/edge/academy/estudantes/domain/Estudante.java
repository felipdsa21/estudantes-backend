package edge.academy.estudantes.domain;

import java.time.YearMonth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Estudante implements Identifiable {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String nome;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private Curso curso;

    @Column(nullable = false)
    @Getter
    @Setter
    private Integer turma;

    @Column(nullable = false)
    @Getter
    @Setter
    private Integer periodoAtual;

    @Column(nullable = false)
    @Getter
    @Setter
    private YearMonth dataDeIngresso;

    protected Estudante() {
    }

    public Estudante(
        String nome,
        String email,
        Curso curso,
        Integer turma,
        Integer periodoAtual,
        YearMonth dataDeIngresso
    ) {
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.turma = turma;
        this.periodoAtual = periodoAtual;
        this.dataDeIngresso = dataDeIngresso;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
