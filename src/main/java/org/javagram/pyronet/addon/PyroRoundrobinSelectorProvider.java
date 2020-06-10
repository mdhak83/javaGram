/*
 * Created on 26 apr 2010
 */
package org.javagram.pyronet.addon;

import java.nio.channels.SocketChannel;
import java.util.Arrays;
import org.javagram.pyronet.PyroSelector;

public class PyroRoundrobinSelectorProvider implements PyroSelectorProvider {

    private final PyroSelector[] selectors;
    private int index;

    public PyroRoundrobinSelectorProvider(PyroSelector[] selectors) {
        this.selectors = Arrays.copyOf(selectors, selectors.length);
    }

    @Override
    public PyroSelector provideFor(SocketChannel channel) {
        // this is called from the PyroServer-selector thread
        return this.selectors[this.index++ % this.selectors.length];
    }

}