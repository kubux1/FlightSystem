package main.java.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired

    private FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * POST  /flight/add : Creates a new flight.
     * <p>
     *
     * @param departure flight departure time.
     * @param arrival flight arrival time.
     * @param capacity plane capacity for flight.
     * @param ticketPrice the price of the ticket for a flight.
     * @return the body with the new flight.
     */
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public Flight createFlight(@RequestParam String departure,
                               @RequestParam String arrival,
                               @RequestParam Integer capacity,
                               @RequestParam Integer ticketPrice){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime departureParsed = LocalDateTime.parse(departure, formatter);
        LocalDateTime arrivalParsed = LocalDateTime.parse(arrival, formatter);

        Flight f = new Flight(departureParsed, arrivalParsed, capacity, ticketPrice);
        flightRepository.save(f);
        return f;
    }

    /**
     * GET  /flight/get : Get all flights.
     * <p>
     *
     * @return the body with all found flights.
     */
    @RequestMapping(value = "/get", method=RequestMethod.GET)
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    /**
     * DELETE  /flight/delete : Delete a flight.
     * <p>
     * Delete a flight with a given id.
     *
     * @return the ResponseEntity with ok status.
     */
    @RequestMapping(value = "/delete", method=RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<Void> deleteFlight(@RequestParam Integer id){
        flightRepository.deleteOneById(id);
        return ResponseEntity.ok().build();
    }
}
