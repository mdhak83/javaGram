/*
 * Created on 26 apr 2010
 */
package org.javagram.pyronet.addon;

import java.nio.channels.SocketChannel;
import org.javagram.pyronet.PyroSelector;

public interface PyroSelectorProvider {

    public PyroSelector provideFor(SocketChannel channel);

}