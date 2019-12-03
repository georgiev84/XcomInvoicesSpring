package com.petar.workspring.service;


import com.petar.workspring.model.service.PartnerServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface PartnerService {
    PartnerServiceModel loginUser(PartnerServiceModel partnerServiceModel);
}
