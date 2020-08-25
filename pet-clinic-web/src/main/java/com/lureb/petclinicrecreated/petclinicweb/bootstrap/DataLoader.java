package com.lureb.petclinicrecreated.petclinicweb.bootstrap;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import com.lureb.petclinicrecreated.petclinicdata.model.PetType;
import com.lureb.petclinicrecreated.petclinicdata.model.Vet;
import com.lureb.petclinicrecreated.petclinicdata.services.OwnerService;
import com.lureb.petclinicrecreated.petclinicdata.services.PetTypeService;
import com.lureb.petclinicrecreated.petclinicdata.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Manda");
        owner2.setLastName("Sukina");
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
