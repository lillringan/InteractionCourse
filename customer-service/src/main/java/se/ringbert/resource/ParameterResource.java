package se.ringbert.resource;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import se.ringbert.model.PageRequestBean;

@Path("parameters")
public final class ParameterResource {
		
		@GET 
		@Path("test2")
		public String test2(@BeanParam PageRequestBean request){
			return String.format("page:%d, size:%d, sort:%s", request.getPage(), request.getSize(), request.getSort());
		}
}
