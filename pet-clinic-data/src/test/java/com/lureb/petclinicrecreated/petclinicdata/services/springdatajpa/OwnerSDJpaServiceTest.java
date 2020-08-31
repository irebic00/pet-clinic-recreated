package com.lureb.petclinicrecreated.petclinicdata.services.springdatajpa;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import com.lureb.petclinicrecreated.petclinicdata.repositories.OwnerRepository;
import com.lureb.petclinicrecreated.petclinicdata.repositories.PetRepository;
import com.lureb.petclinicrecreated.petclinicdata.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    OwnerSDJpaService service;

    private Owner firstOwner;
    private final String FIRST_OWNER_FIRST_NAME = "John";
    private final String FIRST_OWNER_LAST_NAME = "Smith";

    @BeforeEach
    public void setUp() {
        firstOwner = Owner.builder().firstName(FIRST_OWNER_FIRST_NAME).lastName(FIRST_OWNER_LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        Mockito.when(ownerRepository.findByLastName(Mockito.anyString())).thenReturn(firstOwner);
        assertEquals(FIRST_OWNER_LAST_NAME, service.findByLastName(FIRST_OWNER_LAST_NAME).getLastName());
        Mockito.verify(ownerRepository).findByLastName(Mockito.anyString());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().firstName("Milan").build());
        returnOwnersSet.add(Owner.builder().firstName("R2-D2").build());

        Mockito.when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(firstOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }


    @Test
    void save() {
        Owner ownerToSave = Owner.builder().firstName("Milivoj").build();

        Mockito.when(ownerRepository.save(Mockito.any())).thenReturn(firstOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);

        Mockito.verify(ownerRepository).save(Mockito.any());
    }

    @Test
    void delete() {
        service.delete(firstOwner);

        //default is 1 times
        Mockito.verify(ownerRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        Mockito.verify(ownerRepository).deleteById(Mockito.anyLong());
    }
}