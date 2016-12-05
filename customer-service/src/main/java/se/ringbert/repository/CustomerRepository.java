package se.ringbert.repository;

import java.util.List;

import se.ringbert.model.Customer;

public interface CustomerRepository {

	void add(Customer customer);

	Customer get(Long id);

	void update(Customer customer);

	boolean delete(Long id);
	
	List<Customer> getAll();
	
	List<Customer> getAllBySize(int size);
	
	List<Customer> getAllBySizeAndSorted(int size, String sort);

}
