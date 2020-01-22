package com.petar.workspring.service;

import com.petar.workspring.domain.data.Owner;
import com.petar.workspring.domain.entities.Partner;
import com.petar.workspring.domain.models.service.PartnerServiceModel;
import com.petar.workspring.repository.PartnersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.when;

public class PartnerServiceImplTests {

    @Test
    public void getPartnerInfo_whenUserExist_expectUser(){
        // Arrange (setup)
        Partner partner = new Partner();
        partner.setId(100);
        partner.setUsername("ivan");
        partner.setCompany("Company Name");

        PartnersRepository repository = Mockito.mock(PartnersRepository.class);

        when(repository.findById(partner.getId()))
                .thenReturn(new Partner("ivan", "Company Name"));

        ModelMapper mapper = new ModelMapper();

        PartnerServiceImpl service = new PartnerServiceImpl(repository, mapper);

        // Act (do the behavior)
        Partner testPartner = service.partnerInfo(partner.getId());

        // Assert (check if correct)
        assert (partner.getUsername().equals(testPartner.getUsername()));

    }
}
