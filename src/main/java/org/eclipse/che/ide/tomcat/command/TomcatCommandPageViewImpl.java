/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.tomcat.command;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * The implementation of {@link TomcatCommandPageView}.
 *
 * @author Artem Zatsarynnyi
 */
public class TomcatCommandPageViewImpl implements TomcatCommandPageView {

    private static final TomcatCommandPageViewImplUiBinder UI_BINDER = GWT.create(TomcatCommandPageViewImplUiBinder.class);

    private final FlowPanel rootElement;

    @UiField
    TextBox deploymentArtifact;

    private ActionDelegate delegate;

    @Inject
    public TomcatCommandPageViewImpl() {
        rootElement = UI_BINDER.createAndBindUi(this);
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Widget asWidget() {
        return rootElement;
    }

    @Override
    public String getDeploymentArtifactPath() {
        return deploymentArtifact.getValue();
    }

    @Override
    public void setDeploymentArtifactPath(String deploymentArtifactPath) {
        deploymentArtifact.setValue(deploymentArtifactPath);
    }

    @UiHandler({"deploymentArtifact"})
    void onDeploymentArtifactChanged(KeyUpEvent event) {
        // commandLine value may not be updated immediately after keyUp
        // therefore use the timer with delay=0
        new Timer() {
            @Override
            public void run() {
                delegate.onDeploymentArtifactChanged();
            }
        }.schedule(0);
    }

    interface TomcatCommandPageViewImplUiBinder extends UiBinder<FlowPanel, TomcatCommandPageViewImpl> {
    }
}
