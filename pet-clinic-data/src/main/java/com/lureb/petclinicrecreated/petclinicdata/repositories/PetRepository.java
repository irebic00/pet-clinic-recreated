package com.lureb.petclinicrecreated.petclinicdata.repositories;

import com.lureb.petclinicrecreated.petclinicdata.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
