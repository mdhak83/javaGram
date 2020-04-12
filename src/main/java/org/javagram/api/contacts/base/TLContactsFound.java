package org.javagram.api.contacts.base;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Users found by name substring and auxiliary data.
 */
public class TLContactsFound extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb3134d9d;

    /**
     * Personalized results 
     */
    private TLVector<TLAbsPeer> myResults;
    
    /**
     * List of found user identifiers
     */
    private TLVector<TLAbsPeer> results;
    
    /**
     * List of found user identifiers
     */
    private TLVector<TLAbsChat> chats;
    
    /**
     * List of users
     */
    private TLVector<TLAbsUser> users;

    public TLContactsFound() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPeer> getMyResults() {
        return myResults;
    }

    public void setMyResults(TLVector<TLAbsPeer> myResults) {
        this.myResults = myResults;
    }

    public TLVector<TLAbsPeer> getResults() {
        return results;
    }

    public void setResults(TLVector<TLAbsPeer> results) {
        this.results = results;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public void setChats(TLVector<TLAbsChat> chats) {
        this.chats = chats;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param value the value
     */
    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.myResults, stream);
        StreamingUtils.writeTLVector(this.results, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.myResults = StreamingUtils.readTLVector(stream, context, TLAbsPeer.class);
        this.results = StreamingUtils.readTLVector(stream, context, TLAbsPeer.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "contacts.found#b3134d9d";
    }

}