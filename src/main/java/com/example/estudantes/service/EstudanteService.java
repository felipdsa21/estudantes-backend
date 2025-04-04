package com.example.estudantes.service;

import java.util.List;

import com.example.estudantes.domain.Estudante;
import com.example.estudantes.repository.EstudanteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService extends AbstractCrudService<Estudante> {
    private final EstudanteRepository estudanteRepository;

    public EstudanteService(EstudanteRepository estudanteRepository) {
        this.estudanteRepository = estudanteRepository;
    }

    public List<Estudante> findBySpecification(Specification<Estudante> spec) {
        return this.estudanteRepository.findAll(spec);
    }

    public Page<Estudante> findBySpecification(Specification<Estudante> spec, Pageable pageable) {
        return this.estudanteRepository.findAll(spec, pageable);
    }

    @Override
    protected JpaRepository<Estudante, Integer> getRepository() {
        return this.estudanteRepository;
    }
}
