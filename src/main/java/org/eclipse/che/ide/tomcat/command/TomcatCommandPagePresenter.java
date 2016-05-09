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

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import org.eclipse.che.ide.extension.machine.client.command.CommandConfigurationPage;

import javax.validation.constraints.NotNull;

/**
 * Page for editing Tomcat command.
 *
 * @author Artem Zatsarynnyi
 */
public class TomcatCommandPagePresenter implements TomcatCommandPageView.ActionDelegate,
                                                   CommandConfigurationPage<TomcatCommandConfiguration> {

    private final TomcatCommandPageView view;

    private TomcatCommandConfiguration editedConfiguration;
    /** Deployment artifact value before any editing. */
    private String                     originDeploymentArtifactPath;
    private DirtyStateListener         listener;

    @Inject
    public TomcatCommandPagePresenter(TomcatCommandPageView view) {
        this.view = view;
        view.setDelegate(this);
    }

    @Override
    public void resetFrom(TomcatCommandConfiguration configuration) {
        editedConfiguration = configuration;
        originDeploymentArtifactPath = configuration.getDeploymentArtifactPath();
    }

    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);

        view.setDeploymentArtifactPath(editedConfiguration.getDeploymentArtifactPath());
    }

    @Override
    public boolean isDirty() {
        return !(originDeploymentArtifactPath.equals(editedConfiguration.getDeploymentArtifactPath()));
    }

    @Override
    public void setDirtyStateListener(@NotNull DirtyStateListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDeploymentArtifactChanged() {
        editedConfiguration.setDeploymentArtifactPath(view.getDeploymentArtifactPath());
        listener.onDirtyStateChanged();
    }
}
