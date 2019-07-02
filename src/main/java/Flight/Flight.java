package main.java.Flight;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime departure;

    private LocalDateTime arrival;

    private Integer capacity;

    private Integer ticketPrice;

    public Flight(){}

    public Flight(LocalDateTime departure, LocalDateTime arrival, Integer capacity, Integer ticketPrice) {
        this.departure = departure;
        this.arrival = arrival;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
    }

    public void increaseCapaictyByOne(){
        this.capacity +=1;
    }

    public void decreaseCapaictyByOne(){
        this.capacity -=1;
    }
}
