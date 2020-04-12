package org.telegram.api.messages.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.messages.base.input.filter.TLAbsMessagesFilter;

/**
 * Indicates how many results would be found by a @see <a href="https://core.telegram.org/method/messages.search">messages.search</a> call with the same parameters
 * messages.searchCounter#e844ebff flags:# inexact:flags.1?true filter:MessagesFilter count:int = messages.SearchCounter;
 */
public class TLMessagesSearchCounter extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe844ebff;

    private static final int FLAG_INEXACT   = 0x00000002; // 1 : If set, the results may be inexact

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Provided message filter
     */
    private TLAbsMessagesFilter filter;
    
    /**
     * Number of results that were found server-side
     */
    private int count;

    public TLMessagesSearchCounter() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public boolean isInexact() {
        return this.isFlagSet(FLAG_INEXACT);
    }

    public void setInexact(boolean value) {
        this.setFlag(FLAG_INEXACT, value);
    }

    public TLAbsMessagesFilter getFilter() {
        return filter;
    }

    public void setFilter(TLAbsMessagesFilter filter) {
        this.filter = filter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.searchCounter#e844ebff";
    }

}