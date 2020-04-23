package com.company.clinic.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@PublishEntityChangedEvents
@NamePattern("%s %s %s|pet,date,description")
@Table(name = "CLINIC_VISIT")
@Entity(name = "clinic_Visit")
@Listeners("clinic_VisitEntityListener")
public class Visit extends StandardEntity {
    private static final long serialVersionUID = -8254124810105648524L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PET_ID")
    protected Pet pet;

    @Column(name = "NUMBER_")
    protected Long number;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VETERINARIAN_ID")
    protected Veterinarian veterinarian;

    @NotNull
    @Column(name = "DATE_", nullable = false)
    protected LocalDateTime date;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @PositiveOrZero
    @NotNull
    @Column(name = "HOURS_SPENT", nullable = false)
    protected Integer hoursSpent;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    protected BigDecimal amount;

    @JoinTable(name = "CLINIC_VISIT_CONSUMABLE_LINK",
            joinColumns = @JoinColumn(name = "VISIT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONSUMABLE_ID"))
    @ManyToMany
    protected List<Consumable> consumables;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public void setConsumables(List<Consumable> consumables) {
        this.consumables = consumables;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(Integer hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @MetaProperty(related = {"date", "hoursSpent"})
    public LocalDateTime getEndDate() {
        if (hoursSpent != null && date != null) {
            return date.plusHours(hoursSpent);
        }
        return null;
    }

    @PostConstruct
    public void postConstruct() {
        amount = BigDecimal.ZERO;
    }
}