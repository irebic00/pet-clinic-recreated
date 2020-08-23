package com.lureb.petclinicrecreated.petclinicdata.services;

import com.lureb.petclinicrecreated.petclinicdata.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
