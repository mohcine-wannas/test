package ma.salamgaz.tawassol.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.salamgaz.tawassol.service.GenericService;

/**
 * Created by chamakh on 06/01/2017.
 */
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    @Autowired
    private JpaRepository<T, ID> jpaRepository;

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return jpaRepository.findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<ID> iterable) {
        return jpaRepository.findAll(iterable);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> iterable) {
        return jpaRepository.save(iterable);
    }

    @Override
    public void flush() {
        jpaRepository.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return jpaRepository.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        jpaRepository.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        jpaRepository.deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return jpaRepository.getOne(id);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return jpaRepository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return jpaRepository.findAll(example,sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public <S extends T> S save(S s) {
        return jpaRepository.save(s);
    }

    @Override
    public T findOne(ID id) {
        return jpaRepository.findOne(id);
    }

    @Override
    public boolean exists(ID id) {
        return jpaRepository.exists(id);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public void delete(ID id) {
        jpaRepository.delete(id);

    }

    @Override
    public void delete(T t) {
        jpaRepository.delete(t);
    }

    @Override
    public void delete(Iterable<? extends T> iterable) {
        jpaRepository.delete(iterable);
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    @Override
    public <S extends T> S findOne(Example<S> example) {
        return jpaRepository.findOne(example);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return jpaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return jpaRepository.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return jpaRepository.exists(example);
    }
}
