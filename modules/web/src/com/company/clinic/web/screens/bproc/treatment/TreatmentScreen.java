package com.company.clinic.web.screens.bproc.treatment;

import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.addon.bproc.web.processform.*;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.util.Map;

@UiController("clinic_TreatmentScreen")
@UiDescriptor("treatment-screen.xml")
@ProcessForm (
        outcomes = {
                @Outcome( id = "complete"),
                @Outcome(id = "needDetails", outputVariables = {
                        @OutputVariable(name = "comment", type = String.class)
                })
        }
)
public class TreatmentScreen extends Screen {

    @Inject
    @ProcessVariable (name = "comment")
    private TextArea<String> comment;
    @Inject
    @ProcessVariable(name = "description")
    private TextArea<String> description;
    @Inject
    @ProcessVariable(name = "pet")
    private LookupField pet;

    @Inject
    private ProcessFormContext processFormContext;
    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private UserSession userSession;

    @Subscribe("completeBtn")
    public void onCompleteBtnClick(Button.ClickEvent event) {
        processFormContext.taskCompletion().withOutcome("complete").complete();
        closeWithDefaultAction();
    }

    @Subscribe("needInfo")
    public void onNeedInfoClick(Button.ClickEvent event) {
        processFormContext.taskCompletion().withOutcome("needInfo")
                .addProcessVariable("comment", comment.getValue())
                .complete();
        closeWithDefaultAction();
    }



}