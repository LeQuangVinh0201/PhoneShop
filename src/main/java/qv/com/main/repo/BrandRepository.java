package qv.com.main.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import qv.com.main.entities.Brand;
import qv.com.main.entities.Revenue;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
	@Query("Select b from Brand b where b.brand = ?1")
	Brand findByBrand(String brand);
	
	void deleteAll();

	<S extends Brand> List<S> findAll(Example<S> example, Sort sort);

	<S extends Brand> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Brand> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Brand getReferenceById(Integer id);

	void delete(Brand entity);

	Brand getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Brand, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Brand getOne(Integer id);

	void deleteAllInBatch();

	<S extends Brand> boolean exists(Example<S> example);

	<S extends Brand> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Brand> entities);

	Optional<Brand> findById(Integer id);

	<S extends Brand> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Brand> entities);

	List<Brand> findAllById(Iterable<Integer> ids);

	List<Brand> findAll();

	<S extends Brand> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Brand> S saveAndFlush(S entity);

	Page<Brand> findAll(Pageable pageable);

	void flush();

	List<Brand> findAll(Sort sort);

	<S extends Brand> Optional<S> findOne(Example<S> example);

	<S extends Brand> List<S> saveAll(Iterable<S> entities);

	<S extends Brand> S save(S entity);

}
