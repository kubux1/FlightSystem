package main.java.FlightTourist;

import lombok.Getter;
import lombok.Setter;
import main.java.Tourist.Tourist;
import main.java.Flight.Flight;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class FlightTourist {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name="FLIGHT_ID")
    private Flight flight;

    @OneToOne
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name="TOURIST_ID")
    private Tourist tourist;

    public FlightTourist(){}

    public FlightTourist(Flight flight, Tourist tourist){
        this.flight = flight;
        this.tourist = tourist;
    }
}
