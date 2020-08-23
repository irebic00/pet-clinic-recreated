package com.lureb.petclinicrecreated.petclinicdata.services;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
