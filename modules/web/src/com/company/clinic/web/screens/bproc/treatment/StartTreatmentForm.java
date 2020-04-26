package com.company.clinic.web.screens.bproc.treatment;

import com.company.clinic.entity.Pet;
import com.haulmont.addon.bproc.web.processform.OutputVariable;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.addon.bproc.web.processform.ProcessVariable;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;

@UiController("clinic_StartTreatmentForm")
@UiDescriptor("start-treatment-form.xml")
@LoadDataBeforeShow
@ProcessForm(
        outputVariables = {
                @OutputVariable(name = "pet", type = Pet.class),
                @OutputVariable(name = "description", type = String.class),
                @OutputVariable(name = "nurse", type = User.class),
                @OutputVariable(name = "doctor", type = User.class)
        }
)
public class StartTreatmentForm extends Screen {

    @Inject
    @ProcessVariable(name = "description")
    private TextArea<String> descriptionField;

    @Inject
    @ProcessVariable(name = "nurse")
    private LookupField<User> executorField;

    @Inject
    @ProcessVariable(name = "pet")
    private LookupField<Pet> petField;

    @Inject
    @ProcessVariable(name = "doctor")
    private LookupField<User> doctorField;

    @Inject
    private ProcessFormContext processFormContext;
    @Inject
    private UserSession userSession;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        doctorField.setValue(userSession.getUser());
    }



    @Subscribe("startProcessBtn")
    public void onStartProcessBtnClick(Button.ClickEvent event) {
        processFormContext.processStarting()
                .saveInjectedProcessVariables()
                .start();
        closeWithDefaultAction();
    }



}