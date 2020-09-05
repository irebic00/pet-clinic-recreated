package com.lureb.petclinicrecreated.petclinicweb.controllers;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import com.lureb.petclinicrecreated.petclinicdata.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).firstName("Milivoj").build());
        owners.add(Owner.builder().id(2L).firstName("Zdravko").build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        Mockito.when(ownerService.findAllByLastNameLike(Mockito.anyString()))
                .thenReturn(new ArrayList<>(owners));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attribute("selections", Matchers.hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        Mockito.when(ownerService
                .findAllByLastNameLike(Mockito.anyString()))
                .thenReturn(Collections.singletonList(Owner.builder().id(1L).build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));
    }

    @Disabled
    @Test
    void processFindFormEmptyReturnMany() throws Exception {
        Mockito.when(ownerService.findAllByLastNameLike(Mockito.anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build(),
                        Owner.builder().id(2L).build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners")
                .param("lastName",""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attribute("selections", Matchers.hasSize(2)));
    }

    @Test
    void displayOwner() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownerDetails"))
                .andExpect(MockMvcResultMatchers.model().attribute("owner", Matchers.hasProperty("id", Matchers.is(1L))));
    }

    @Disabled
    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyNoInteractions(ownerService);
    }

    @Disabled
    @Test
    void processCreationForm() throws Exception {
        Mockito.when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verify(ownerService).save(ArgumentMatchers.any());
    }

    @Disabled
    @Test
    void initUpdateOwnerForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyNoInteractions(ownerService);
    }

    @Disabled
    @Test
    void processUpdateOwnerForm() throws Exception {
        Mockito.when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verify(ownerService).save(ArgumentMatchers.any());
    }
}