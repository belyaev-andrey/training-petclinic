package com.company.clinic.security.app.role;

import com.company.clinic.entity.Consumable;
import com.company.clinic.entity.Owner;
import com.company.clinic.entity.Pet;
import com.company.clinic.entity.PetType;
import com.company.clinic.entity.Veterinarian;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = VeterinarianRole.ROLE_NAME)
public class VeterinarianRole extends AnnotatedRoleDefinition {

    public static final String ROLE_NAME = "veterinarian-access";

    @EntityAccess(entityClass = Visit.class, operations = {EntityOp.READ, EntityOp.UPDATE})
    @EntityAccess(entityClass = Consumable.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Owner.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Pet.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = PetType.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Veterinarian.class, operations = {EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Visit.class,
            modify = {"consumables", "hoursSpent", "date", "description", "pet", "veterinarian"})
    @EntityAttributeAccess(entityClass = Consumable.class,
            view = {"price", "title"})
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {
            "application-clinic",
            "clinic_Visit.browse",
            "clinic_Visit.edit",
            "clinic_Consumable.browse"
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }
}
