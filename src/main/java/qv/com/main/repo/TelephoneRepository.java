package qv.com.main.repo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import qv.com.main.entities.Telephone;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, String>{

	void deleteAll();

	<S extends Telephone> List<S> findAll(Example<S> example, Sort sort);

	<S extends Telephone> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Telephone> entities);

	void deleteAllById(Iterable<? extends String> ids);

	Telephone getReferenceById(String id);

	void delete(Telephone entity);

	Telephone getById(String id);

	void deleteById(String id);

	long count();

	<S extends Telephone, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Telephone getOne(String id);

	void deleteAllInBatch();

	<S extends Telephone> boolean exists(Example<S> example);

	<S extends Telephone> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<String> ids);

	boolean existsById(String id);

	void deleteAllInBatch(Iterable<Telephone> entities);

	Optional<Telephone> findById(String id);

	<S extends Telephone> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Telephone> entities);

	List<Telephone> findAllById(Iterable<String> ids);

	List<Telephone> findAll();

	<S extends Telephone> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Telephone> S saveAndFlush(S entity);

	Page<Telephone> findAll(Pageable pageable);

	void flush();

	List<Telephone> findAll(Sort sort);

	<S extends Telephone> Optional<S> findOne(Example<S> example);

	<S extends Telephone> List<S> saveAll(Iterable<S> entities);

	<S extends Telephone> S save(S entity);
	
	List<Telephone> findBySeries(String series);
	
	@Query("Select t from Telephone t where t.name LIKE  %?1%")
	List<Telephone> findByNameLike(String name);
	
//	@Query("select t from telephones t")
//	List<Telephone> findByBestSell();
}
