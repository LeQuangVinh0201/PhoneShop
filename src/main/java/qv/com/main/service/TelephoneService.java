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

import qv.com.main.entities.Telephone;
import qv.com.main.repo.TelephoneRepository;

@Service
public class TelephoneService implements TelephoneRepository{
	
	@Autowired
	TelephoneRepository telephoneRepository;
	
	
	
	public List<Telephone> findByNameLike(String name) {
		return telephoneRepository.findByNameLike(name);
	}

	@Override
	public <S extends Telephone> S save(S entity) {
		return telephoneRepository.save(entity);
	}

	@Override
	public <S extends Telephone> List<S> saveAll(Iterable<S> entities) {
		return telephoneRepository.saveAll(entities);
	}

	@Override
	public <S extends Telephone> Optional<S> findOne(Example<S> example) {
		return telephoneRepository.findOne(example);
	}

	@Override
	public List<Telephone> findAll(Sort sort) {
		return telephoneRepository.findAll(sort);
	}

	@Override
	public void flush() {
		telephoneRepository.flush();
	}

	@Override
	public Page<Telephone> findAll(Pageable pageable) {
		return telephoneRepository.findAll(pageable);
	}

	@Override
	public <S extends Telephone> S saveAndFlush(S entity) {
		return telephoneRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Telephone> List<S> saveAllAndFlush(Iterable<S> entities) {
		return telephoneRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Telephone> findAll() {
		return telephoneRepository.findAll();
	}

	@Override
	public List<Telephone> findAllById(Iterable<String> ids) {
		return telephoneRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Telephone> entities) {
		telephoneRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Telephone> Page<S> findAll(Example<S> example, Pageable pageable) {
		return telephoneRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Telephone> findById(String id) {
		return telephoneRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Telephone> entities) {
		telephoneRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return telephoneRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		telephoneRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Telephone> long count(Example<S> example) {
		return telephoneRepository.count(example);
	}

	@Override
	public <S extends Telephone> boolean exists(Example<S> example) {
		return telephoneRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		telephoneRepository.deleteAllInBatch();
	}

	@Override
	public Telephone getOne(String id) {
		return telephoneRepository.getOne(id);
	}

	@Override
	public <S extends Telephone, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return telephoneRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return telephoneRepository.count();
	}

	@Override
	public void deleteById(String id) {
		telephoneRepository.deleteById(id);
	}

	@Override
	public Telephone getById(String id) {
		return telephoneRepository.getById(id);
	}

	@Override
	public void delete(Telephone entity) {
		telephoneRepository.delete(entity);
	}

	@Override
	public Telephone getReferenceById(String id) {
		return telephoneRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		telephoneRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Telephone> entities) {
		telephoneRepository.deleteAll(entities);
	}

	@Override
	public <S extends Telephone> List<S> findAll(Example<S> example) {
		return telephoneRepository.findAll(example);
	}

	@Override
	public <S extends Telephone> List<S> findAll(Example<S> example, Sort sort) {
		return telephoneRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		telephoneRepository.deleteAll();
	}

	@Override
	public List<Telephone> findBySeries(String series) {
		return telephoneRepository.findBySeries(series);
	}

//	@Override
//	public List<Telephone> findByBestSell() {
//		return null;
//	}
	
	
}
