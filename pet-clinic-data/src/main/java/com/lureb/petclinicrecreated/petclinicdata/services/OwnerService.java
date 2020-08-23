package com.lureb.petclinicrecreated.petclinicdata.services;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
