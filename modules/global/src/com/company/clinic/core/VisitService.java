package com.company.clinic.core;

import com.company.clinic.entity.Pet;
import com.company.clinic.entity.Veterinarian;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.security.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface VisitService {
    String NAME = "clinic_VisitService";

    BigDecimal calculateAmount(Visit visit);

    Visit scheduleVisit(Pet pet, LocalDateTime visitDate, User user);

    Veterinarian findVetByUser(User user);
}
