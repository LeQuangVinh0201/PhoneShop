package qv.com.main.repo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import qv.com.main.entities.Revenue;

@Repository
public interface RevenueRopository extends JpaRepository<Revenue, Integer>{

	void deleteAll();

	<S extends Revenue> List<S> findAll(Example<S> example, Sort sort);

	<S extends Revenue> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Revenue> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Revenue getReferenceById(Integer id);

	void delete(Revenue entity);

	Revenue getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Revenue, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Revenue getOne(Integer id);

	void deleteAllInBatch();

	<S extends Revenue> boolean exists(Example<S> example);

	<S extends Revenue> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Revenue> entities);

	Optional<Revenue> findById(Integer id);

	<S extends Revenue> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Revenue> entities);

	List<Revenue> findAllById(Iterable<Integer> ids);

	List<Revenue> findAll();

	<S extends Revenue> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Revenue> S saveAndFlush(S entity);

	Page<Revenue> findAll(Pageable pageable);

	void flush();

	List<Revenue> findAll(Sort sort);

	<S extends Revenue> Optional<S> findOne(Example<S> example);

	<S extends Revenue> List<S> saveAll(Iterable<S> entities);

	<S extends Revenue> S save(S entity);

}
