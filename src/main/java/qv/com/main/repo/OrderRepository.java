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

import qv.com.main.entities.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

	void deleteAll();

	<S extends Orders> List<S> findAll(Example<S> example, Sort sort);

	<S extends Orders> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Orders> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Orders getReferenceById(Integer id);

	void delete(Orders entity);

	Orders getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Orders, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Orders getOne(Integer id);

	void deleteAllInBatch();

	<S extends Orders> boolean exists(Example<S> example);

	<S extends Orders> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Orders> entities);

	Optional<Orders> findById(Integer id);

	<S extends Orders> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Orders> entities);

	List<Orders> findAllById(Iterable<Integer> ids);

	List<Orders> findAll();

	<S extends Orders> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Orders> S saveAndFlush(S entity);

	Page<Orders> findAll(Pageable pageable);

	void flush();

	List<Orders> findAll(Sort sort);

	<S extends Orders> Optional<S> findOne(Example<S> example);

	<S extends Orders> List<S> saveAll(Iterable<S> entities);

	<S extends Orders> S save(S entity);

}
