
package se.ringbert.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.ringbert.resources.MessageResource;


@Component
public final class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
		register(MessageResource.class);
	}
	
	

}
