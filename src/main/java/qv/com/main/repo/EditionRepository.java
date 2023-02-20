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

import qv.com.main.entities.Edition;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Integer>{

	void deleteAll();

	<S extends Edition> List<S> findAll(Example<S> example, Sort sort);

	<S extends Edition> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Edition> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Edition getReferenceById(Integer id);

	void delete(Edition entity);

	Edition getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Edition, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Edition getOne(Integer id);

	void deleteAllInBatch();

	<S extends Edition> boolean exists(Example<S> example);

	<S extends Edition> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Edition> entities);

	Optional<Edition> findById(Integer id);

	<S extends Edition> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Edition> entities);

	List<Edition> findAllById(Iterable<Integer> ids);

	List<Edition> findAll();

	<S extends Edition> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Edition> S saveAndFlush(S entity);

	Page<Edition> findAll(Pageable pageable);

	void flush();

	List<Edition> findAll(Sort sort);

	<S extends Edition> Optional<S> findOne(Example<S> example);

	<S extends Edition> List<S> saveAll(Iterable<S> entities);

	<S extends Edition> S save(S entity);

}
