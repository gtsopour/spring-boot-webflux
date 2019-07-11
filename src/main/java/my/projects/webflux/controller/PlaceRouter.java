package my.projects.webflux.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PlaceRouter {
  @Bean
  public RouterFunction<ServerResponse> routes(PlaceHandler placeHandler) {
    return nest(path("/places"),
        nest(accept(MediaType.APPLICATION_JSON),
            route(GET("/"), placeHandler::getAllPlaces)
                .andRoute(POST("/"), placeHandler::savePlace)

        ));
  }
}
