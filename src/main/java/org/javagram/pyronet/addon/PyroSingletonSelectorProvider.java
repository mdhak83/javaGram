/*
 * Created on 26 apr 2010
 */
package org.javagram.pyronet.addon;

import java.nio.channels.SocketChannel;
import org.javagram.pyronet.PyroSelector;

public class PyroSingletonSelectorProvider implements PyroSelectorProvider {

    private final PyroSelector selector;

    public PyroSingletonSelectorProvider(PyroSelector selector) {
        this.selector = selector;
    }

    @Override
    public PyroSelector provideFor(SocketChannel channel) {
        return this.selector;
    }

}