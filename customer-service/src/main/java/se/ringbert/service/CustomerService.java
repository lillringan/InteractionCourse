package se.ringbert.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.ringbert.model.Customer;
import se.ringbert.repository.CustomerRepository;
import se.ringbert.resource.InvalidCustomerException;

@Component
public final class CustomerService {

	private final CustomerRepository customerRepository;
	private final AtomicLong customerIds = new AtomicLong(1000);

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer createCustomer(Customer customer) {

		if(validNumber(customer.getCustomerNumber())){
			Long id = customerIds.incrementAndGet();
			Customer c = new Customer(id, customer.getFirstName(), customer.getLastName(), customer.getCustomerNumber());
			customerRepository.add(c);
			return c;
		}else{
			throw new InvalidCustomerException("Customernumber can not contain digits");
		}
	
	}

	public Customer getCustomer(Long id) {
		return customerRepository.get(id);
	}

	public Customer updateCustomer(Customer customer) {
		customerRepository.update(customer);
		return customer;
	}

	public boolean deleteCustomer(Long id) {
		return customerRepository.delete(id);
	}
	
	public List<Customer> getAllCustomers(){
		return customerRepository.getAll();
	}
	
	private boolean validNumber(String number){
		if(number.matches(".*\\d+.*")){
			return false;
		}
		return true;
	}

}
