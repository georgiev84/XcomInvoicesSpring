package com.petar.workspring.service;

import com.petar.workspring.domain.entities.PartnersLoginEntity;
import com.petar.workspring.repository.PartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnersService {
    private PartnersRepository partnersRepository;

    @Autowired
    public PartnersService() {
    }

    public List<PartnersLoginEntity> list(){
        return partnersRepository.findAll();
    }
}
