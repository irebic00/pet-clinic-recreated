package com.lureb.petclinicrecreated.petclinicweb.bootstrap;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import com.lureb.petclinicrecreated.petclinicdata.model.Vetenarian;
import com.lureb.petclinicrecreated.petclinicdata.services.OwnerService;
import com.lureb.petclinicrecreated.petclinicdata.services.VetService;
import com.lureb.petclinicrecreated.petclinicdata.services.map.OwnerServiceMap;
import com.lureb.petclinicrecreated.petclinicdata.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;

    private VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Jela");
        owner1.setLastName("Trkuljina");
        owner1.setId(1L);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Manda");
        owner2.setLastName("Sukina");
        owner2.setId(2L);
        ownerService.save(owner2);

        Vetenarian vet1 = new Vetenarian();
        vet1.setFirstName("Adam");
        vet1.setLastName("Sandler");
        vet1.setId(1L);
        vetService.save(vet1);

        Vetenarian vet2 = new Vetenarian();
        vet2.setFirstName("Jim");
        vet2.setLastName("Carrey");
        vet2.setId(2L);
        vetService.save(vet2);
    }
}
