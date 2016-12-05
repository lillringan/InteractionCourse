package se.ringbert.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import se.ringbert.model.Customer;

@Component
public final class InMemoryCustomerRepository implements CustomerRepository {

	private final Map<Long, Customer> customers = new HashMap<>();

	@Override
	public void add(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	@Override
	public Customer get(Long id) {
		return customers.get(id);
	}

	@Override
	public void update(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	@Override
	public boolean delete(Long id) {
		return customers.remove(id) != null;
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> customerList = new ArrayList<>();

		for (Customer c : customers.values()) {
			customerList.add(c);
		}
		return customerList;
	}

	@Override
	public List<Customer> getAllBySize(int size) {
		List<Customer> customerList = new ArrayList<>();
		for (Customer c : customers.values()) {
			customerList.add(c);
		}
		return customerList;
	}

	@Override
	public List<Customer> getAllBySizeAndSorted(int size, String sort) {
		List<Customer> customerList = new ArrayList<>();
		for (Customer c : customers.values()) {
			customerList.add(c);
		}
		return customerList;
	}

}
