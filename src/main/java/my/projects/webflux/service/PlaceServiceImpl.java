package my.projects.webflux.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import my.projects.webflux.model.Place;
import my.projects.webflux.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaceServiceImpl implements PlaceService {
  private PlaceRepository placeRepository;

  @Autowired
  PlaceServiceImpl(PlaceRepository placeRepository) {
    this.placeRepository = placeRepository;
  }

  @Override
  public Flux<Place> findAllPlaces() {
    return this.placeRepository.findAll();
//    return Flux.interval(Duration.ofSeconds(1))
//        .onBackpressureDrop()
//        .map(this::generatePlace)
//        .flatMapIterable(x -> x);
  }

  @Override
  public Mono<Place> savePlace(Place place) {
    place.setId(UUID.randomUUID());
    return this.placeRepository.save(place);
  }

  @Override
  public Mono<Void> deletePlace(UUID uuid) {
    return null;
  }

  private List<Place> generatePlace(long interval) {
    Place obj = new Place(
        UUID.randomUUID(),
        "Name",
        "Description");
    return Arrays.asList(obj);
  }
}
