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

import com.google.inject.ImplementedBy;

import org.eclipse.che.ide.api.mvp.View;

/**
 * The view of {@link TomcatCommandPagePresenter}.
 *
 * @author Artem Zatsarynnyi
 */
@ImplementedBy(TomcatCommandPageViewImpl.class)
public interface TomcatCommandPageView extends View<TomcatCommandPageView.ActionDelegate> {

    /** Returns working directory. */
    String getDeploymentArtifactPath();

    /** Sets working directory. */
    void setDeploymentArtifactPath(String workingDirectory);

    /** Action handler for the view actions/controls. */
    interface ActionDelegate {

        /** Called when working directory has been changed. */
        void onDeploymentArtifactChanged();
    }
}
