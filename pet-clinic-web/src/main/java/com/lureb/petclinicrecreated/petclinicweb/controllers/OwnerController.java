package com.lureb.petclinicrecreated.petclinicweb.controllers;

import com.lureb.petclinicrecreated.petclinicdata.model.Owner;
import com.lureb.petclinicrecreated.petclinicdata.model.Pet;
import com.lureb.petclinicrecreated.petclinicdata.services.OwnerService;
import com.lureb.petclinicrecreated.petclinicdata.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class OwnerController {

    private final OwnerService ownerService;

    private final VisitService visitService;

    public OwnerController(OwnerService ownerService, VisitService visitService) {
        this.ownerService = ownerService;
        this.visitService = visitService;
    }

    /**
     * Set allow fields for posting forms. {@link WebDataBinder} has been in Spring since 1.2.
     * It enables RESTful services to control what can be posted to web server.
     * In our example we disallow posting id from POST forms because it might pose security issu.
     * For example, malicious action could alter ids in database if allowed to post such requests from frontend.
     *
     * @param dataBinder data binder
     */
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

//    @GetMapping("/owners/new")
//    public String initCreationForm(Map<String, Object> model) {
//        Owner owner = new Owner();
//        model.put("owner", owner);
//        return Views.OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
//    }
//
//    @PostMapping("/owners/new")
//    public String processCreationForm(@Valid Owner owner, BindingResult result) {
//        if (result.hasErrors()) {
//            return Views.OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
//        }
//        else {
//            ownerService.save(owner);
//            return Views.REDIRECT_OWNERS + owner.getId();
//        }
//    }
//
//    @GetMapping("/owners/find")
//    public String initFindForm(Map<String, Object> model) {
//        model.put("owner", new Owner());
//        return Views.OWNERS_FIND_OWNERS;
//    }
//
//    @GetMapping("/owners/{ownerId}/edit")
//    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
//        Owner owner = this.owners.findById(ownerId);
//        model.addAttribute(owner);
//        return Views.OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
//    }
//
//    @PostMapping("/owners/{ownerId}/edit")
//    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
//                                         @PathVariable("ownerId") int ownerId) {
//        if (result.hasErrors()) {
//            return Views.OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
//        }
//        else {
//            owner.setId(ownerId);
//            this.owners.save(owner);
//            return "redirect:/owners/{ownerId}";
//        }
//    }

    /**
     * Custom handler for displaying an owner.
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") long ownerId) {
        ModelAndView mav = new ModelAndView(Views.OWNERS_OWNER_DETAILS);
        Owner owner = ownerService.findById(ownerId);
        mav.addObject(owner);
        return mav;
    }

    @GetMapping("/owners/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return Views.OWNERS_FIND_OWNERS;
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return Views.OWNERS_FIND_OWNERS;
        }
        else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return Views.REDIRECT_OWNERS + owner.getId();
        }
        else {
            // multiple owners found
            model.addAttribute("selections", results);
            return Views.OWNERS_OWNERS_LIST;
        }
    }
}
