package se.ringbert.resource;

import static se.ringbert.model.CustomParser.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.ringbert.model.Customer;
import se.ringbert.service.CustomerService;

@Component
@Path("customers")
@Produces(MediaType.APPLICATION_XML)
public final class CustomerResource {

	@Autowired
	private CustomerService service;

	@Context
	private UriInfo uriInfo;
	
	private @HeaderParam("api-key") String apiKey;

	@POST
	public Response addCustmer(String value) {

		Customer customer = fromString(value);
		customer = service.createCustomer(customer);

		return Response.status(Status.CREATED).header("Location", "customers/" + customer.getId()).build();
	}

	@GET
	@Path("{id}")
	public Response getCustomerAsXml(@PathParam("id") Long id) {
		Customer customer = service.getCustomer(id);

		if (customer == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(asXml(customer)).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCustomerAsPlainText(@PathParam("id") Long id) {

		Customer customer = service.getCustomer(id);

		if (customer == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(asString(customer)).build();
	}

	@GET
	public Response getAllCustomers(@QueryParam("size") @DefaultValue("5") int size,
			@QueryParam("sort") @DefaultValue("asc") String sort,
			@QueryParam("asuri") @DefaultValue("false") boolean asUri) {
		if("api-key".equals(apiKey)){
		List<Customer> customers = service.getAllCustomers();
		customers = customers.subList(0, Math.min(customers.size(), size));
		customers.sort((c1, c2) -> sort.equalsIgnoreCase("desc") ? Long.compare(c1.getId(), c2.getId())
				: Long.compare(c2.getId(), c1.getId()));

		List<URI> uris = new ArrayList<>();
		if (asUri) {
			for (Customer c : customers) {
				uris.add(uriInfo.getAbsolutePathBuilder().path(c.getId().toString()).build());
			}
			return Response.ok(uris.toString()).build();
		}

		return Response.ok(arrayAsXml(customers)).build();
		}
		throw new WebApplicationException("Header missing", Response.status(Status.BAD_REQUEST).build());
	}

	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Long id, String content) {

		Customer customer = fromString(content);

		customer = service.updateCustomer(customer);

		return Response.status(Status.NO_CONTENT).build();

	}

	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Long id) {
		service.deleteCustomer(id);
		return Response.status(Status.NO_CONTENT).build();
	}

}
