package com.lureb.petclinicrecreated.petclinicdata.services.map;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerServiceMap;
    private Long firstOwnerId = 1L;
    private String firstOwnerName = "Jozo";
    private String firstOwnerLastName = "De Stefano";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner
                .builder()
                .firstName(firstOwnerName)
                .lastName(firstOwnerLastName)
                .build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(firstOwnerId);

        assertEquals(firstOwnerName, owner.getFirstName());
        assertEquals(firstOwnerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().firstName("Milivoj").build();
        Owner savedOwner2 = ownerServiceMap.save(owner2);

        assertEquals(id, savedOwner2.getId());

        assertEquals(owner2, ownerServiceMap.save(owner2));
        assertEquals("Milivoj", ownerServiceMap.findById(id).getFirstName());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(firstOwnerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(firstOwnerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        assertEquals(firstOwnerLastName, ownerServiceMap.findByLastName(firstOwnerLastName).getLastName());
    }

    @Test
    void findByLastNameNonExistent() {
        assertNull(ownerServiceMap.findByLastName("NotAPerson"));
    }
}