package com.lureb.petclinicrecreated.petclinicdata.services;

import com.lureb.petclinicrecreated.petclinicdata.model.Vetenarian;

import java.util.Set;

public interface VetService {
    Vetenarian findById(Long id);

    Vetenarian save(Vetenarian vetenarian);

    Set<Vetenarian> findAll();
}
