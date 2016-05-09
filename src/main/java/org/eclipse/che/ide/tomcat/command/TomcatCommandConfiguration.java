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

import org.eclipse.che.ide.extension.machine.client.command.CommandConfiguration;
import org.eclipse.che.ide.extension.machine.client.command.CommandType;

import java.util.Map;

import static org.eclipse.che.ide.tomcat.command.TomcatCommandType.COMMAND_TEMPLATE;

/**
 * Tomcat command.
 *
 * @author Artem Zatsarynnyi
 */
public class TomcatCommandConfiguration extends CommandConfiguration {

    private String deploymentArtifactPath;

    protected TomcatCommandConfiguration(CommandType type, String name, Map<String, String> attributes) {
        super(type, name, attributes);
        deploymentArtifactPath = "";
    }

    public String getDeploymentArtifactPath() {
        return deploymentArtifactPath;
    }

    public void setDeploymentArtifactPath(String path) {
        deploymentArtifactPath = path;
    }

    @Override
    public String toCommandLine() {
        return COMMAND_TEMPLATE.replace("<CHE_DEPLOYMENT_ARTIFACT>", deploymentArtifactPath);
    }
}
