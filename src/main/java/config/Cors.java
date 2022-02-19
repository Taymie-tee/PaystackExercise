package config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class Cors implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response
                .getHeaders()
                .putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE, PATCH, HEAD");
        response
                .getHeaders()
                .putSingle(
                        "Access-Control-Allow-Headers",
                        "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        response.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getHeaders().putSingle("Access-Control-Max-Age", "1209600");
    }
}
