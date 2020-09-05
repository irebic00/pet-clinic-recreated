package com.lureb.petclinicrecreated.petclinicdata.services;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String pattern);
}
