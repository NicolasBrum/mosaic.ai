package com.mosaicai.api.services;

import com.mosaicai.api.Exceptions.StringFormatException;
import com.mosaicai.api.entities.LeadEntity;
import com.mosaicai.api.helpers.StringValidator;
import com.mosaicai.api.models.LeadModel;
import com.mosaicai.api.repositories.LeadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LeadService {
    private final LeadRepository leadRepository;

    public void save(LeadModel lead) throws StringFormatException {
        StringValidator.validateString(lead.name());
        StringValidator.validateString(lead.surname());
        StringValidator.validateString(lead.email());
        StringValidator.validateString(lead.position());
        StringValidator.validateString(lead.enterprise());
        StringValidator.validateString(lead.email());

        var leadEntity = new LeadEntity(lead);
        leadRepository.save(leadEntity);
    }

}
