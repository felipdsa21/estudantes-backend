package com.example.estudantes.service;

import java.util.List;
import java.util.function.Consumer;

import com.example.estudantes.domain.Identifiable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractCrudService<T extends Identifiable> {
    public T findById(int id) {
        var entity = this.getRepository().findById(id);
        return entity.orElseThrow();
    }

    public List<T> findAll() {
        return this.getRepository().findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        return this.getRepository().findAll(pageable);
    }

    @Transactional
    public T save(T entity) {
        return this.getRepository().saveAndFlush(entity);
    }

    @Transactional
    public T update(Integer id, Consumer<T> changesApplier) {
        var entity = this.findById(id);
        changesApplier.accept(entity);
        return this.save(entity);
    }

    @Transactional
    public void deleteById(Integer id) {
        this.findById(id);
        this.getRepository().deleteById(id);
    }

    abstract protected JpaRepository<T, Integer> getRepository();
}