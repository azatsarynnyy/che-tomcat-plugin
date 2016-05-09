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
package org.eclipse.che.ide.tomcat;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.multibindings.GinMultibinder;

import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.ide.tomcat.command.TomcatCommandType;
import org.eclipse.che.ide.extension.machine.client.command.CommandType;

/**
 * Gin module for Tomcat extension.
 *
 * @author Artem Zatsarynnyi
 */
@ExtensionGinModule
public class TomcatGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        GinMultibinder.newSetBinder(binder(), CommandType.class).addBinding().to(TomcatCommandType.class);
    }
}