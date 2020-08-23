package com.lureb.petclinicrecreated.petclinicweb.bootstrap;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import com.lureb.petclinicrecreated.petclinicdata.model.Vetenarian;
import com.lureb.petclinicrecreated.petclinicdata.services.OwnerService;
import com.lureb.petclinicrecreated.petclinicdata.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Jela");
        owner1.setLastName("Trkuljina");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Manda");
        owner2.setLastName("Sukina");
        ownerService.save(owner2);

        Vetenarian vet1 = new Vetenarian();
        vet1.setFirstName("Adam");
        vet1.setLastName("Sandler");
        vetService.save(vet1);

        Vetenarian vet2 = new Vetenarian();
        vet2.setFirstName("Jim");
        vet2.setLastName("Carrey");
        vetService.save(vet2);
    }
}
