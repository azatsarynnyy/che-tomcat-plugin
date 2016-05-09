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

import org.eclipse.che.api.machine.shared.dto.CommandDto;
import org.eclipse.che.ide.extension.machine.client.command.CommandConfigurationFactory;
import org.eclipse.che.ide.extension.machine.client.command.CommandType;

/**
 * {@link CommandConfigurationFactory} for Tomcat commands.
 *
 * @author Artem Zatsarynnyi
 */
public class TomcatCommandConfigurationFactory extends CommandConfigurationFactory<TomcatCommandConfiguration> {

    protected TomcatCommandConfigurationFactory(CommandType commandType) {
        super(commandType);
    }

    @Override
    public TomcatCommandConfiguration createFromDto(CommandDto descriptor) {
        TomcatCommandConfiguration command = new TomcatCommandConfiguration(getCommandType(),
                                                                            descriptor.getName(),
                                                                            descriptor.getAttributes());

        String firstLine = descriptor.getCommandLine().split("\n")[0];
        command.setDeploymentArtifactPath(firstLine.split("=")[1]);

        return command;
    }
}
