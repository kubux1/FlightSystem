package main.java.Tourist;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TouristRepository extends CrudRepository<Tourist, Integer> {

    Tourist findOneById(Integer id);

    List<Tourist> findAll();

    void deleteOneById(Integer id);

    void deleteOneByNameAndSurnameAndBirthDate(String name, String surname, String birthdate);
}