package my.projects.webflux.service;

import java.util.UUID;
import my.projects.webflux.model.Place;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaceService {
  Flux<Place> findAllPlaces();
  Mono<Place> savePlace(Place place);
  Mono<Void> deletePlace(UUID uuid);
}
