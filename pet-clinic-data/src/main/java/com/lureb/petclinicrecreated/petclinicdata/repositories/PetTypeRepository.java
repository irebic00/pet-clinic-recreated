package com.lureb.petclinicrecreated.petclinicdata.repositories;

import com.lureb.petclinicrecreated.petclinicdata.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
