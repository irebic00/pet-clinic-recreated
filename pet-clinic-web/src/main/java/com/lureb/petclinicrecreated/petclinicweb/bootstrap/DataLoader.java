package com.lureb.petclinicrecreated.petclinicweb.bootstrap;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import com.lureb.petclinicrecreated.petclinicdata.model.Pet;
import com.lureb.petclinicrecreated.petclinicdata.model.PetType;
import com.lureb.petclinicrecreated.petclinicdata.model.Vet;
import com.lureb.petclinicrecreated.petclinicdata.services.OwnerService;
import com.lureb.petclinicrecreated.petclinicdata.services.PetTypeService;
import com.lureb.petclinicrecreated.petclinicdata.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType dogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType catPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Jela");
        owner1.setLastName("Trkuljina");
        owner1.setAddress("Vinjani Donji 100");
        owner1.setCity("Imotski");
        owner1.setTelephone("0919117567");

        Pet jelasDog = new Pet();
        jelasDog.setPetType(dogPetType);
        jelasDog.setName("Pan Pan");
        jelasDog.setBirthday(LocalDate.now());
        jelasDog.setOwner(owner1);
        owner1.getPets().add(jelasDog);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Manda");
        owner2.setLastName("Sukina");
        owner2.setAddress("Put Gaja 13");
        owner2.setCity("Imotski");
        owner2.setTelephone("+385955347058");

        Pet mandasCat = new Pet();
        mandasCat.setPetType(catPetType);
        mandasCat.setName("Mjau Mjau");
        mandasCat.setBirthday(LocalDate.now());
        mandasCat.setOwner(owner2);
        owner2.getPets().add(mandasCat);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Adam");
        vet1.setLastName("Sandler");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jim");
        vet2.setLastName("Carrey");
        vetService.save(vet2);
    }
}
