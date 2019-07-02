package main.java.Tourist;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Tourist {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String gender;

    private String country;

    private String birthDate;

    public Tourist(){}

    public Tourist(String name, String surname, String gender, String country, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.birthDate = birthDate;
    }
}
