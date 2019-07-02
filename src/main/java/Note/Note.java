package main.java.Note;

import lombok.Getter;
import lombok.Setter;

import main.java.Tourist.Tourist;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String text;

    @ManyToOne
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name="TOURIST_ID")
    private Tourist tourist;

   private LocalDateTime date;

    public Note(String text) {
        this.text = text;
        this.date = LocalDateTime.now();
    }
}
