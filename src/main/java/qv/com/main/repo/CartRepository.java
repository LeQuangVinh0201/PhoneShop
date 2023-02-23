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
import qv.com.main.entities.ProductCart;

@Repository
public interface CartRepository extends JpaRepository<ProductCart, Integer>{

	void deleteAll();

	<S extends ProductCart> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductCart> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends ProductCart> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	ProductCart getReferenceById(Integer id);

	void delete(ProductCart entity);

	ProductCart getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends ProductCart, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	ProductCart getOne(Integer id);

	void deleteAllInBatch();

	<S extends ProductCart> boolean exists(Example<S> example);

	<S extends ProductCart> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<ProductCart> entities);

	Optional<ProductCart> findById(Integer id);

	<S extends ProductCart> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<ProductCart> entities);

	List<ProductCart> findAllById(Iterable<Integer> ids);

	List<ProductCart> findAll();

	<S extends ProductCart> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ProductCart> S saveAndFlush(S entity);

	Page<ProductCart> findAll(Pageable pageable);

	void flush();

	List<ProductCart> findAll(Sort sort);

	<S extends ProductCart> Optional<S> findOne(Example<S> example);

	<S extends ProductCart> List<S> saveAll(Iterable<S> entities);

	<S extends ProductCart> S save(S entity);

	

}
