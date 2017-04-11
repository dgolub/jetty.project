//
//  ========================================================================
//  Copyright (c) 1995-2017 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.websocket.tests;

import java.net.URI;

import org.eclipse.jetty.websocket.api.WebSocketConnectionListener;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.common.LogicalConnection;
import org.eclipse.jetty.websocket.common.SessionFactory;
import org.eclipse.jetty.websocket.common.WebSocketSession;
import org.eclipse.jetty.websocket.common.scopes.WebSocketContainerScope;

public class UntrustedWSSessionFactory implements SessionFactory
{
    private final WebSocketContainerScope containerScope;
    
    public UntrustedWSSessionFactory(WebSocketContainerScope containerScope)
    {
        this.containerScope = containerScope;
    }
    
    @Override
    public boolean supports(Object websocket)
    {
        return (websocket instanceof WebSocketConnectionListener) || (websocket.getClass().getAnnotation(WebSocket.class) != null);
    }
    
    @Override
    public WebSocketSession createSession(URI requestURI, Object websocket, LogicalConnection connection)
    {
        return new UntrustedWSSession(containerScope, requestURI, websocket, connection);
    }
}
