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

import qv.com.main.entities.Revenue;
import qv.com.main.repo.RevenueRopository;

@Service
public class RevenueService implements RevenueRopository{
	@Autowired
	RevenueRopository revenueRopository;

	@Override
	public <S extends Revenue> S save(S entity) {
		return revenueRopository.save(entity);
	}

	@Override
	public <S extends Revenue> List<S> saveAll(Iterable<S> entities) {
		return revenueRopository.saveAll(entities);
	}

	@Override
	public <S extends Revenue> Optional<S> findOne(Example<S> example) {
		return revenueRopository.findOne(example);
	}

	@Override
	public List<Revenue> findAll(Sort sort) {
		return revenueRopository.findAll(sort);
	}

	@Override
	public void flush() {
		revenueRopository.flush();
	}

	@Override
	public Page<Revenue> findAll(Pageable pageable) {
		return revenueRopository.findAll(pageable);
	}

	@Override
	public <S extends Revenue> S saveAndFlush(S entity) {
		return revenueRopository.saveAndFlush(entity);
	}

	@Override
	public <S extends Revenue> List<S> saveAllAndFlush(Iterable<S> entities) {
		return revenueRopository.saveAllAndFlush(entities);
	}

	@Override
	public List<Revenue> findAll() {
		return revenueRopository.findAll();
	}

	@Override
	public List<Revenue> findAllById(Iterable<Integer> ids) {
		return revenueRopository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Revenue> entities) {
		revenueRopository.deleteInBatch(entities);
	}

	@Override
	public <S extends Revenue> Page<S> findAll(Example<S> example, Pageable pageable) {
		return revenueRopository.findAll(example, pageable);
	}

	@Override
	public Optional<Revenue> findById(Integer id) {
		return revenueRopository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Revenue> entities) {
		revenueRopository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return revenueRopository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		revenueRopository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Revenue> long count(Example<S> example) {
		return revenueRopository.count(example);
	}

	@Override
	public <S extends Revenue> boolean exists(Example<S> example) {
		return revenueRopository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		revenueRopository.deleteAllInBatch();
	}

	@Override
	public Revenue getOne(Integer id) {
		return revenueRopository.getOne(id);
	}

	@Override
	public <S extends Revenue, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return revenueRopository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return revenueRopository.count();
	}

	@Override
	public void deleteById(Integer id) {
		revenueRopository.deleteById(id);
	}

	@Override
	public Revenue getById(Integer id) {
		return revenueRopository.getById(id);
	}

	@Override
	public void delete(Revenue entity) {
		revenueRopository.delete(entity);
	}

	@Override
	public Revenue getReferenceById(Integer id) {
		return revenueRopository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		revenueRopository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Revenue> entities) {
		revenueRopository.deleteAll(entities);
	}

	@Override
	public <S extends Revenue> List<S> findAll(Example<S> example) {
		return revenueRopository.findAll(example);
	}

	@Override
	public <S extends Revenue> List<S> findAll(Example<S> example, Sort sort) {
		return revenueRopository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		revenueRopository.deleteAll();
	}
	
	
}
