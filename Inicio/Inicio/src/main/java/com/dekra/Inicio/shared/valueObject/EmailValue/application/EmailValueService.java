package com.dekra.Inicio.shared.valueObject.EmailValue.application;


import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.repository.EmailRepository;
import com.dekra.Inicio.shared.valueObject.EmailValue.infraestructure.repository.EmailValueRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailValueService {

    private final EmailRepository emailValueRepository;


    public EmailValueService(EmailRepository emailValueRepositoryImp) {

        this.emailValueRepository = emailValueRepositoryImp;

    }

    public List<EmailValue> emailValueList(){

        return emailValueRepository.emailValueList();
    }

    public EmailValue saveEmailValue(EmailValue emailValue) {

        return emailValueRepository.saveEmailValue(emailValue);
    }

}
