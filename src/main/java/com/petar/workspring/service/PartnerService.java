package com.petar.workspring.service;


import com.petar.workspring.domain.entities.Partner;
import com.petar.workspring.domain.models.service.PartnerServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface PartnerService {
    PartnerServiceModel loginUser(PartnerServiceModel partnerServiceModel);

    Partner partnerInfo(int userId);

}
