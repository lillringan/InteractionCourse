package se.ringbert.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.ringbert.resource.CustomerResource;
import se.ringbert.resource.MessageExceptionMapper;
import se.ringbert.resource.ParameterResource;;

@Component
public final class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
		register(CustomerResource.class);
		register(ParameterResource.class);
		register(MessageExceptionMapper.class);
	}
}
