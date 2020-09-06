package com.lureb.petclinicrecreated.petclinicweb.formatters;

import com.lureb.petclinicrecreated.petclinicdata.model.PetType;
import com.lureb.petclinicrecreated.petclinicdata.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        return petTypeService.findAll()
                .stream()
                .filter(petType -> petType.getName().equals(text))
                .findFirst()
                .orElseThrow(() -> new ParseException("type not found: " + text, 0));
    }
}
