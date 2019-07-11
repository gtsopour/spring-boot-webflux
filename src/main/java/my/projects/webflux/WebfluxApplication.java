package my.projects.webflux;

import my.projects.webflux.model.Place;
import my.projects.webflux.repository.PlaceRepository;
import my.projects.webflux.service.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class WebfluxApplication {

	@Autowired private PlaceRepository placeRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

}

//@RestController
//class Test {
//
//	@Autowired
//	PlaceServiceImpl placeService;
//
//	@GetMapping(value = "/getPlaces", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	Flux<Place> getSystemRunTime() {
//		return  this.placeService.findAllPlaces();
//	}
//
//}
