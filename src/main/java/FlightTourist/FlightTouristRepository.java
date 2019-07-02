package main.java.FlightTourist;

import org.springframework.data.repository.CrudRepository;

public interface FlightTouristRepository extends CrudRepository<FlightTourist, Integer> {

    FlightTourist findOneById(Integer id);

    void deleteOneById(Integer id);
}
