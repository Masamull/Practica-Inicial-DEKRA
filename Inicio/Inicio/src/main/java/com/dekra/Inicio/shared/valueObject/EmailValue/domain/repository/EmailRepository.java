package com.dekra.Inicio.shared.valueObject.EmailValue.domain.repository;

import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository {

    List<EmailValue> emailValueList();
    EmailValue saveEmailValue(EmailValue emailValue);


}
