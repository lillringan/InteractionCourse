package se.ringbert.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import se.ringbert.model.Message;

@Path("messages")
public class MessageResource {

	private static final Map<Long, Message> messages = new HashMap<>();
	private static final AtomicLong messageIds = new AtomicLong(1000);

	// Create
	// 192.168.158.103/messages
	@POST
	public Response addMessage(String content) {

		Long id = messageIds.incrementAndGet();
		Message message = new Message(id, content);
		messages.put(id, message);

		return Response.status(Status.CREATED).header("Location", "message/" + id).build();
	}

	// Read
	// 192.168.158.103/messages/1001
	@Path("{id}")
	@GET
	public Response getMessage(@PathParam("id") Long id) {
		if (messages.containsKey(id)) {
			return Response.ok(messages.get(id).getContent()).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	// Update
	// 192.168.158.103/messages/1001
	@PUT
	@Path("{id}")
	public Response updateMessage(@PathParam("id") Long id, String newContent) {
		if (messages.containsKey(id)) {
			messages.put(id, new Message(id, newContent));
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	// Delete
	// 192.168.158.103/messages/1001
	@DELETE
	@Path("{id}")
	public Response deleteMessage(@PathParam("id") Long id) {
		if (messages.containsKey(id)) {
			messages.remove(id);
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}