package my.projects.webflux.controller;

import my.projects.webflux.model.Place;
import my.projects.webflux.service.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class PlaceHandler {
  private final PlaceServiceImpl placeServiceImpl;

  @Autowired
  PlaceHandler(PlaceServiceImpl placeServiceImpl) {
    this.placeServiceImpl = placeServiceImpl;
  }

  public Mono<ServerResponse> getAllPlaces(ServerRequest serverRequest) {
    return ServerResponse.ok().body(placeServiceImpl.findAllPlaces(), Place.class);
  }

  public Mono<ServerResponse> savePlace(ServerRequest serverRequest) {
    Mono<Place> placeMono = serverRequest.bodyToMono(Place.class);
    return placeMono.flatMap(place ->
        ServerResponse.status(HttpStatus.CREATED).body(placeServiceImpl.savePlace(place), Place.class));
  }

}
