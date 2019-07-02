package main.java.Tourist;

import main.java.Note.*;
import main.java.FlightTourist.*;
import main.java.Flight.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tourist")
public class TouristController {

    @Autowired

    private TouristRepository touristRepository;
    private NoteRepository noteRepository;
    private FlightTouristRepository flightTouristRepository;
    private FlightRepository flightRepository;

    public TouristController(TouristRepository touristRepository,
                             NoteRepository noteRepository,
                             FlightTouristRepository flightTouristRepository,
                             FlightRepository flightRepository) {
        this.touristRepository = touristRepository;
        this.noteRepository = noteRepository;
        this.flightTouristRepository = flightTouristRepository;
        this.flightRepository = flightRepository;
    }

    /**
     * POST  /tourist/add : Creates a new tourist.
     * <p>
     *
     * @param name tourist name.
     * @param surname tourist surname.
     * @param gender tourist gender.
     * @param country tourist country of citizenship.
     * @param birthDate tourist birth date.
     * @return the body with the new tourist.
     */
    @RequestMapping(value = "/add")
    public Tourist createTourist(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String gender,
                              @RequestParam String country,
                              @RequestParam String birthDate){
        System.out.println("Creating tourist");
        Tourist t = new Tourist(name, surname, gender, country, birthDate);
        touristRepository.save(t);
        return t;
    }

    /**
     * GET  /tourist/get : Get all tourists.
     * <p>
     *
     * @return the body with all found tourists.
     */
    @RequestMapping(value = "/get", method=RequestMethod.GET)
    public List<Tourist> getAllTourists(){
        return touristRepository.findAll();
    }

    /**
     * DELETE  /tourist/delete/id : Delete a tourist with a given ID.
     * <p>
     *
     * @param id tourist id to be deleted.
     * @return ResponseEntity with ok status.
     */
    @RequestMapping(value = "/delete/id", method=RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<Void> deleteTouristByID(@RequestParam Integer id){
        touristRepository.deleteOneById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE  /tourist/delete : Delete a tourist with a given name, surname and birthdate.
     * <p>
     * Deletes a tourist with matching all these variables: name, surname and birthdate
     *
     * @param name tourist name to be deleted.
     * @param surname tourist surname to be deleted.
     * @param birthdate tourist birthdate to be deleted.
     * @return ResponseEntity with ok status.
     */
    @RequestMapping(value = "/delete", method=RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<Void> deleteTourist(@RequestParam String name,
                                              @RequestParam String surname,
                                              @RequestParam String birthdate){
        touristRepository.deleteOneByNameAndSurnameAndBirthDate(name, surname, birthdate);
        return ResponseEntity.ok().build();
    }

    /**
     * POST  /tourist/add/flight : Assign a tourist for a flight.
     * <p>
     * Assign a tourist for a flight if there is still a seat for a flight.
     *
     * @param flightID flight id to be assigned for.
     * @param touristID tourist id which is about to be assigned for a flight.
     * @return the body with the new reservation or null if there are no sets available
     */
    @RequestMapping(value = "/add/flight", method=RequestMethod.POST)
    public FlightTourist addFlight(@RequestParam Integer flightID,
                                   @RequestParam Integer touristID){
        // Find flight by ID
        Flight f = flightRepository.findOneById(flightID);
        // Check if there are still free seats for a flight
        if(f.getCapacity() > 0) {
            f.decreaseCapaictyByOne();
            // Find tourist by ID
            Tourist t = touristRepository.findOneById(touristID);
            FlightTourist ft = new FlightTourist(f, t);
            flightTouristRepository.save(ft);
            // Update a flight capacity
            flightRepository.save(f);
            return ft;
        } else {
            return null;
        }
    }

    /**
     * DELETE  /tourist/delete/flight : Delete a tourist from a flight.
     * <p>
     *
     * @param id tourist reservation id for a flight
     * @return ResponseEntity with ok status.
     */
    @RequestMapping(value = "/delete/flight", method=RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<Void> deleteFlight(@RequestParam Integer id){
        FlightTourist ft = flightTouristRepository.findOneById(id);
        // Increase a capacity for a flight
        Flight f = flightRepository.findOneById(ft.getFlight().getId());
        f.increaseCapaictyByOne();
        flightRepository.save(f);
        flightTouristRepository.deleteOneById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * POST  /tourist/add/note : Add a note for a tourist.
     * <p>
     *
     * @param touristID tourist id
     * @param text content of a note
     * @return the body with a new note.
     */
    @RequestMapping(value = "/add/note", method=RequestMethod.POST)
    public Note addNote(@RequestParam Integer touristID,
                           @RequestParam String text){
        Note n = new Note(text);
        // Find tourist by ID
        Tourist t = touristRepository.findOneById(touristID);
        n.setTourist(t);
        noteRepository.save(n);
        return n;
    }

    //@RequestMapping(value = "/get/note", method=RequestMethod.GET)
    //public List<Note> getAllNotesByTouristId(@RequestParam Integer touristID){
    //    return noteRepository.findOneById(touristID);
    //}
}
