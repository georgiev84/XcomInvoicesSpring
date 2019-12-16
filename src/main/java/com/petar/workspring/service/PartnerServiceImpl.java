package com.petar.workspring.service;

import com.petar.workspring.domain.entities.Partner;
import com.petar.workspring.domain.models.service.PartnerServiceModel;
import com.petar.workspring.repository.PartnersRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Type;


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



        Partner partner = this.partnersRepository.findByUsername(partnerServiceModel.getUsername()).orElse(null);

        String pswd = partnerServiceModel.getPassword();

        // for password crypt DigestUtils.shaHex(pswd)

        if(partner == null || !partner.getPassword().equals(pswd)){
            return null;
        }

        return this.modelMapper.map(partner, PartnerServiceModel.class);
    }

    @Override
    public Partner partnerInfo(int userId) {
        Partner partner = partnersRepository.findById(userId);
        return partner;
    }
}
