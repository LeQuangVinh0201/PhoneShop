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
import qv.com.main.entities.ProductCart;
import qv.com.main.repo.CartRepository;

@Service
public class CartService implements CartRepository{
	@Autowired
	CartRepository caRepository;

	@Override
	public <S extends ProductCart> S save(S entity) {
		return caRepository.save(entity);
	}

	@Override
	public <S extends ProductCart> List<S> saveAll(Iterable<S> entities) {
		return caRepository.saveAll(entities);
	}

	@Override
	public <S extends ProductCart> Optional<S> findOne(Example<S> example) {
		return caRepository.findOne(example);
	}

	@Override
	public List<ProductCart> findAll(Sort sort) {
		return caRepository.findAll(sort);
	}

	@Override
	public void flush() {
		caRepository.flush();
	}

	@Override
	public Page<ProductCart> findAll(Pageable pageable) {
		return caRepository.findAll(pageable);
	}

	@Override
	public <S extends ProductCart> S saveAndFlush(S entity) {
		return caRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends ProductCart> List<S> saveAllAndFlush(Iterable<S> entities) {
		return caRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<ProductCart> findAll() {
		return caRepository.findAll();
	}

	@Override
	public List<ProductCart> findAllById(Iterable<Integer> ids) {
		return caRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<ProductCart> entities) {
		caRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductCart> Page<S> findAll(Example<S> example, Pageable pageable) {
		return caRepository.findAll(example, pageable);
	}

	@Override
	public Optional<ProductCart> findById(Integer id) {
		return caRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductCart> entities) {
		caRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return caRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		caRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends ProductCart> long count(Example<S> example) {
		return caRepository.count(example);
	}

	@Override
	public <S extends ProductCart> boolean exists(Example<S> example) {
		return caRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		caRepository.deleteAllInBatch();
	}

	@Override
	public ProductCart getOne(Integer id) {
		return caRepository.getOne(id);
	}

	@Override
	public <S extends ProductCart, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return caRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return caRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		caRepository.deleteById(id);
	}

	@Override
	public ProductCart getById(Integer id) {
		return caRepository.getById(id);
	}

	@Override
	public void delete(ProductCart entity) {
		caRepository.delete(entity);
	}

	@Override
	public ProductCart getReferenceById(Integer id) {
		return caRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		caRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductCart> entities) {
		caRepository.deleteAll(entities);
	}

	@Override
	public <S extends ProductCart> List<S> findAll(Example<S> example) {
		return caRepository.findAll(example);
	}

	@Override
	public <S extends ProductCart> List<S> findAll(Example<S> example, Sort sort) {
		return caRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		caRepository.deleteAll();
	}

	
	
}
