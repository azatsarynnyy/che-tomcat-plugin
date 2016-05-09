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

import com.google.inject.Inject;

import org.eclipse.che.ide.extension.machine.client.command.CommandConfiguration;
import org.eclipse.che.ide.extension.machine.client.command.CommandConfigurationFactory;
import org.eclipse.che.ide.extension.machine.client.command.CommandConfigurationPage;
import org.eclipse.che.ide.extension.machine.client.command.CommandType;
import org.vectomatic.dom.svg.ui.SVGResource;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Tomcat command type.
 *
 * @author Artem Zatsarynnyi
 */
public class TomcatCommandType implements CommandType {

    public static final String COMMAND_TEMPLATE =
            "DEPLOYMENT_ARTIFACT=<CHE_DEPLOYMENT_ARTIFACT>\n" +
            "cp $DEPLOYMENT_ARTIFACT $TOMCAT_HOME/webapps/ROOT.war\n" +
            "$TOMCAT_HOME/bin/catalina.sh run\n";

    private final TomcatCommandConfigurationFactory configurationFactory;

    private final Collection<CommandConfigurationPage<? extends CommandConfiguration>> pages;

    @Inject
    public TomcatCommandType(TomcatCommandPagePresenter page) {
        configurationFactory = new TomcatCommandConfigurationFactory(this);
        pages = new LinkedList<>();
        pages.add(page);
    }

    @Override
    public String getId() {
        return "tomcat_deploy";
    }

    @Override
    public String getDisplayName() {
        return "Tomcat";
    }

    @Override
    public SVGResource getIcon() {
        return null;
    }

    @Override
    public Collection<CommandConfigurationPage<? extends CommandConfiguration>> getConfigurationPages() {
        return pages;
    }

    @Override
    public CommandConfigurationFactory<? extends CommandConfiguration> getConfigurationFactory() {
        return configurationFactory;
    }

    @Override
    public String getCommandTemplate() {
        return COMMAND_TEMPLATE.replace("<CHE_DEPLOYMENT_ARTIFACT>", "${current.project.path}/target/*.war");
    }

    @Override
    public String getPreviewUrlTemplate() {
        return "";
    }
}
