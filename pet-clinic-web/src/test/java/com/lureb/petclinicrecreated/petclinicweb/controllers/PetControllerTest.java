package com.lureb.petclinicrecreated.petclinicweb.controllers;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import com.lureb.petclinicrecreated.petclinicdata.model.Pet;
import com.lureb.petclinicrecreated.petclinicdata.model.PetType;
import com.lureb.petclinicrecreated.petclinicdata.services.OwnerService;
import com.lureb.petclinicrecreated.petclinicdata.services.PetService;
import com.lureb.petclinicrecreated.petclinicdata.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1l).build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Disabled
    @Test
    void initCreationForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"));
    }

    @Disabled
    @Test
    void processCreationForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));

        Mockito.verify(petService).save(Mockito.any());
    }

    @Disabled
    @Test
    void initUpdateForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);
        Mockito.when(petService.findById(Mockito.anyLong())).thenReturn(Pet.builder().id(2L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/2/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"));
    }

    @Disabled
    @Test
    void processUpdateForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/2/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));

        Mockito.verify(petService).save(Mockito.any());
    }

    @Test
    void populatePetTypes() {
        //todo impl
    }

    @Test
    void findOwner() {
        //todo impl
    }

    @Test
    void initOwnerBinder() {
        //todo impl
    }
}