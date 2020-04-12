package org.telegram.client.handlers.interfaces;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.api.updates.base.TLUpdatesState;

public interface IDifferencesHandler {
    
    /**
     * Load differences from server
     */
    void getDifferences();

    /**
     * Modify updates state
     * @param state New updates state
     * @param isGettingDifferent true if differences are being loaded, false otherwise
     */
    void updateStateModification(@NotNull TLUpdatesState state, boolean isGettingDifferent);

    /**
     * Load differences from server
     * @param chatId
     * @param accessHash
     */
    void getChannelDifferences(int chatId, long accessHash);

    /**
     * Modify updates state
     * @param chatId
     * @param accessHash
     * @param pts
     * @param isGettingDifferent true if differences are being loaded, false otherwise
     */
    void updateChannelStateModification(int chatId, @Nullable Long accessHash, int pts, boolean isGettingDifferent);

}