package com.dekra.Inicio.shared.valueObject.EmailValue.application;


import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import com.dekra.Inicio.shared.valueObject.EmailValue.infraestructure.repository.EmailValueRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailValueService {

    private EmailValueRepositoryImp emailValueRepositoryImp;


    public EmailValueService(EmailValueRepositoryImp emailValueRepositoryImp) {

        this.emailValueRepositoryImp = emailValueRepositoryImp;

    }

    public List<EmailValue> emailValueList(){

        return emailValueRepositoryImp.emailValueList();
    }

    public EmailValue saveEmailValue(EmailValue emailValue) {

        return emailValueRepositoryImp.saveEmailValue(emailValue);
    }

}
