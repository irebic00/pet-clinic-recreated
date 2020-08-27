package com.lureb.petclinicrecreated.petclinicweb.bootstrap;

import com.lureb.petclinicrecreated.petclinicdata.model.*;
import com.lureb.petclinicrecreated.petclinicdata.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType dogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType catPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

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

        Visit mandasCatVisit = new Visit();
        mandasCatVisit.setDate(LocalDate.now());
        mandasCatVisit.setDescription("Cat flu was cured");
        mandasCatVisit.setPet(mandasCat);
        visitService.save(mandasCatVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Adam");
        vet1.setLastName("Sandler");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jim");
        vet2.setLastName("Carrey");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);
    }
}
