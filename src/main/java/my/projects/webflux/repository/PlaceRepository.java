package my.projects.webflux.repository;

import java.util.UUID;
import my.projects.webflux.model.Place;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends ReactiveCassandraRepository<Place,UUID> {

}
