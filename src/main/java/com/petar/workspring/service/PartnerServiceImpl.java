package com.petar.workspring.service;

import com.petar.workspring.domain.entities.Partner;
import com.petar.workspring.domain.models.service.PartnerServiceModel;
import com.petar.workspring.repository.PartnersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerServiceImpl implements PartnerService {
    private final PartnersRepository partnersRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PartnerServiceImpl(PartnersRepository partnersRepository, ModelMapper modelMapper) {
        this.partnersRepository = partnersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PartnerServiceModel loginUser(PartnerServiceModel partnerServiceModel) {
        Partner partner = this.partnersRepository.findByEmail(partnerServiceModel.getUsername()).orElse(null);

        if(partner == null || !partner.getPassword().equals(partnerServiceModel.getPassword())){
            return null;
        }

        return this.modelMapper.map(partner, PartnerServiceModel.class);
    }
}
