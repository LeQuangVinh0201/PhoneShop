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

import qv.com.main.entities.Orders;
import qv.com.main.repo.OrderRepository;

@Service
public class OrdersService{
	@Autowired
	OrderRepository orderRepository;

	public void deleteById(Integer id) {
		orderRepository.deleteById(id);
	}
	
	public Orders findById(Integer id) {
		return orderRepository.getById(id);
	}
	
	public void delete(Orders order) {
		orderRepository.delete(order);
	}
	
	public void save(Orders order) {
		orderRepository.save(order);
	}
	
	
}
