package qv.com.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import qv.com.main.entities.Edition;
import qv.com.main.repo.EditionRepository;

@Service
public class EditionService implements EditionRepository{
	@Autowired
	EditionRepository editionRepository;

	@Override
	public <S extends Edition> S save(S entity) {
		return editionRepository.save(entity);
	}

	@Override
	public <S extends Edition> List<S> saveAll(Iterable<S> entities) {
		return editionRepository.saveAll(entities);
	}

	@Override
	public <S extends Edition> Optional<S> findOne(Example<S> example) {
		return editionRepository.findOne(example);
	}

	@Override
	public List<Edition> findAll(Sort sort) {
		return editionRepository.findAll(sort);
	}

	@Override
	public void flush() {
		editionRepository.flush();
	}

	@Override
	public Page<Edition> findAll(Pageable pageable) {
		return editionRepository.findAll(pageable);
	}

	@Override
	public <S extends Edition> S saveAndFlush(S entity) {
		return editionRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Edition> List<S> saveAllAndFlush(Iterable<S> entities) {
		return editionRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Edition> findAll() {
		return editionRepository.findAll();
	}

	@Override
	public List<Edition> findAllById(Iterable<Integer> ids) {
		return editionRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Edition> entities) {
		editionRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Edition> Page<S> findAll(Example<S> example, Pageable pageable) {
		return editionRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Edition> findById(Integer id) {
		return editionRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Edition> entities) {
		editionRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return editionRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		editionRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Edition> long count(Example<S> example) {
		return editionRepository.count(example);
	}

	@Override
	public <S extends Edition> boolean exists(Example<S> example) {
		return editionRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		editionRepository.deleteAllInBatch();
	}

	@Override
	public Edition getOne(Integer id) {
		return editionRepository.getOne(id);
	}

	@Override
	public <S extends Edition, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return editionRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return editionRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		editionRepository.deleteById(id);
	}

	@Override
	public Edition getById(Integer id) {
		return editionRepository.getById(id);
	}

	@Override
	public void delete(Edition entity) {
		editionRepository.delete(entity);
	}

	@Override
	public Edition getReferenceById(Integer id) {
		return editionRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		editionRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Edition> entities) {
		editionRepository.deleteAll(entities);
	}

	@Override
	public <S extends Edition> List<S> findAll(Example<S> example) {
		return editionRepository.findAll(example);
	}

	@Override
	public <S extends Edition> List<S> findAll(Example<S> example, Sort sort) {
		return editionRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		editionRepository.deleteAll();
	}
	
	
}
