package main.java.Flight;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

    List<Flight> findAll();

    Flight findOneById(Integer id);

    void deleteOneById(Integer id);
}
