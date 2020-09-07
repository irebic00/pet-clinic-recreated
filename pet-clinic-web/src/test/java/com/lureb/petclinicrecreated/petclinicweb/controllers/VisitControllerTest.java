package com.lureb.petclinicrecreated.petclinicweb.controllers;

import com.lureb.petclinicrecreated.petclinicdata.model.Pet;
import com.lureb.petclinicrecreated.petclinicdata.services.PetService;
import com.lureb.petclinicrecreated.petclinicdata.services.VisitService;
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

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;
    Set<Pet> pets = new HashSet<>();

    @BeforeEach
    void setUp() {
        // visit.getPet().getOwner()
        pets.add(Pet.builder().id(1L).build());
        pets.add(Pet.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initCreationForm() throws Exception {
        Mockito.when(petService.findById(Mockito.anyLong())).thenReturn(Pet.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/1/visits/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        Mockito.when(petService.findById(Mockito.anyLong())).thenReturn(Pet.builder().id(2L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/2/visits/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));

        Mockito.verify(visitService).save(Mockito.any());
    }

    @Disabled
    @Test
    void initUpdateForm() throws Exception {
        Mockito.when(petService.findById(Mockito.anyLong())).thenReturn(Pet.builder().id(2L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/2/visits/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visits"))
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdateVisitForm"));
    }

    @Disabled
    @Test
    void processUpdateForm() throws Exception {
        Mockito.when(petService.findById(Mockito.anyLong())).thenReturn(Pet.builder().id(2L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/2/visits/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));

        Mockito.verify(petService).save(Mockito.any());
    }
}