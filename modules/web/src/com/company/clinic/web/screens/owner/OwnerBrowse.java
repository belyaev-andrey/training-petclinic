package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;
import com.haulmont.reports.gui.ReportGuiManager;
import com.haulmont.reports.gui.actions.RunReportAction;

import javax.inject.Inject;

@UiController("clinic_Owner.browse")
@UiDescriptor("owner-browse.xml")
@LookupComponent("ownersTable")
@LoadDataBeforeShow
public class OwnerBrowse extends StandardLookup<Owner> {


    @Inject
    private GroupTable<Owner> ownersTable;
    @Inject
    private Notifications notifications;
    @Inject
    private Button ownersTableRunReportBtn;

    @Subscribe
    public void onInit(InitEvent event) {
        ownersTableRunReportBtn.setAction(new RunReportAction());
    }

    @Subscribe("ownersTable.greet")
    public void onOwnersTableGreet(Action.ActionPerformedEvent event) {
        Owner selectedOwner = ownersTable.getSingleSelected();
        if (selectedOwner != null) {
            notifications.create()
                    .withHideDelayMs(100)
                    .withCaption("Hello "+selectedOwner.getName())
                    .show();
        }
    }



}