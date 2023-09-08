package com.zarlok.webshop.restapi.service;

import com.zarlok.webshop.restapi.dao.UnitRepository;
import com.zarlok.webshop.restapi.entity.Unit;
import com.zarlok.webshop.restapi.exception.UnitNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> findAll(){
        return unitRepository.findAll();
    }

    public Unit findById(int id){
        Optional<Unit> optionalUnit = unitRepository.findById(id);
        if(!optionalUnit.isPresent()){
            throw new UnitNotFoundException("Not found unit with id - "+ id);
        }
        return optionalUnit.get();
    }
}
