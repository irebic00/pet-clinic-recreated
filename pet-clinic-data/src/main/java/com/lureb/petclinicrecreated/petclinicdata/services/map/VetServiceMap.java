package com.lureb.petclinicrecreated.petclinicdata.services.map;

import com.lureb.petclinicrecreated.petclinicdata.model.Vetenarian;
import com.lureb.petclinicrecreated.petclinicdata.services.CrudService;

import java.util.Set;

public class VetServiceMap extends AbstractMapService<Vetenarian, Long> implements CrudService<Vetenarian, Long> {
    @Override
    public Vetenarian findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vetenarian> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vetenarian vetenarian) {
        super.delete(vetenarian);
    }

    @Override
    public Vetenarian save(Vetenarian vetenarian) {
        return super.save(vetenarian.getId(), vetenarian);
    }
}
