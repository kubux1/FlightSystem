package main.java.Note;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Integer>{

    Note findOneById(Integer Id);

    List<Note> findAllByTourist_id(Integer Tourist_id);

}
