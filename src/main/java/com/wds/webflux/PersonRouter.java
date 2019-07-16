package com.wds.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class PersonRouter {
	
//	fun routesFun() = router {
//	    GET("/", serveStatic(ClassPathResource("/graphiql.html")))
//	    (POST("/graphql") or GET("/graphql")).invoke { req: ServerRequest ->
//	      getGraphQLParameters(req)
//	        .flatMap { executeGraphQLQuery(it) }
//	        .flatMap { ok().syncBody(it) }
//	        .switchIfEmpty(badRequest().build())
//	    }


	@Bean
	public RouterFunction<ServerResponse> route(){
		PersonRepository repository= new PersonRepositoryImpl();
		PersonHandler personHandler = new  PersonHandler(repository);
		System.out.println("acontinuacion lista personas");
		return RouterFunctions
				.route(GET("/person/{id}").and(accept(APPLICATION_JSON)), personHandler::getPerson)
				.andRoute(GET("/person").and(accept(APPLICATION_JSON)),personHandler::listPerson)
				.andRoute(POST("/person/create").and(contentType(APPLICATION_JSON)),personHandler::createPerson);
	}

}
