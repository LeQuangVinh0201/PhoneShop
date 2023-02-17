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

import qv.com.main.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	void deleteAll();

	<S extends User> List<S> findAll(Example<S> example, Sort sort);

	<S extends User> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends User> entities);

	void deleteAllById(Iterable<? extends String> ids);

	User getReferenceById(String id);

	void delete(User entity);

	User getById(String id);

	void deleteById(String id);

	long count();

	<S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	User getOne(String id);

	void deleteAllInBatch();

	<S extends User> boolean exists(Example<S> example);

	<S extends User> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<String> ids);

	boolean existsById(String id);

	void deleteAllInBatch(Iterable<User> entities);

	<S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<User> entities);

	List<User> findAllById(Iterable<String> ids);

	List<User> findAll();

	<S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends User> S saveAndFlush(S entity);

	Page<User> findAll(Pageable pageable);

	void flush();

	List<User> findAll(Sort sort);

	<S extends User> Optional<S> findOne(Example<S> example);

	<S extends User> List<S> saveAll(Iterable<S> entities);

	<S extends User> S save(S entity);

	Optional<User> findById(String userName);
}
