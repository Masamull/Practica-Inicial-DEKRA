package com.dekra.Inicio.shared.valueObject.EmailValue.infraestructure.repository;

import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.repository.EmailRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class EmailValueRepositoryImp implements EmailRepository {

    private Set<EmailValue> emails = new HashSet<>();

    @Override
    public List<EmailValue> emailValueList() {
        return emails.stream().toList();
    }

    @Override
    public EmailValue saveEmailValue(EmailValue emailValue) {

        emails.add(emailValue);
        return emailValue;
    }

    @Override
    public EmailValue deleteEmailValue(EmailValue emailValue) {

        emails.remove(emailValue);
        return emailValue;
    }
}
