package org.javagram.api.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.input.TLInputClientProxy;

/**
 * Initialize connection
 * initConnection#785188b8 {X:Type} flags:# api_id:int device_model:string system_version:string app_version:string system_lang_code:string lang_pack:string lang_code:string proxy:flags.0?InputClientProxy query:!X = X;
 */
public class TLRequestInitConnection extends TLMethod<TLObject> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x785188b8;

    private static final int FLAG_PROXY = 0x00000001; // 0

    /**
     * Application identifier (see @see <a href ="https://core.telegram.org/myapp">App configuration</a>)
     */
    private int apiId;
    
    /**
     * Device model
     */
    private String deviceModel = "";
    
    /**
     * Operation system version
     */
    private String systemVersion = "";
    
    /**
     * Application version
     */
    private String appVersion = "";
    
    /**
     * Code for the language used on the device's OS, ISO 639-1 standard
     */
    private String systemLangCode = "";
    
    /**
     * Language pack to use
     */
    private String langPack = "";
    
    /**
     * Code for the language used on the client, ISO 639-1 standard
     */
    private String langCode = "";
    
    /**
     * Info about an MTProto proxy
     */
    private TLInputClientProxy proxy;
    
    /**
     * The query itself
     */
    private TLMethod query;

    public TLRequestInitConnection() {
        super();
    }

    public TLRequestInitConnection(int apiId, String deviceModel, String systemVersion, String appVersion, String systemLangCode, String langPack, String langCode, TLMethod method) {
        super();
        this.setApiId(apiId);
        this.setDeviceModel(deviceModel);
        this.setSystemVersion(systemVersion);
        this.setAppVersion(appVersion);
        this.setSystemLangCode(systemLangCode);
        this.setLangPack(langPack);
        this.setLangCode(langCode);
        this.setQuery(method);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getApiId() {
        return this.apiId;
    }

    public final void setApiId(int value) {
        this.apiId = value;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public final void setDeviceModel(String value) {
        this.deviceModel = value;
    }

    public String getSystemVersion() {
        return this.systemVersion;
    }

    public final void setSystemVersion(String value) {
        this.systemVersion = value;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public final void setAppVersion(String value) {
        this.appVersion = value;
    }

    public String getSystemLangCode() {
        return systemLangCode;
    }

    public final void setSystemLangCode(String systemLangCode) {
        this.systemLangCode = systemLangCode;
    }

    public String getLangPack() {
        return langPack;
    }

    public final void setLangPack(String langPack) {
        this.langPack = langPack;
    }

    public String getLangCode() {
        return this.langCode;
    }

    public final void setLangCode(String value) {
        this.langCode = value;
    }

    public boolean hasProxy() {
        return this.isFlagSet(FLAG_PROXY);
    }
    
    public TLInputClientProxy getProxy() {
        return proxy;
    }

    public final void setProxy(TLInputClientProxy proxy) {
        this.proxy = proxy;
        this.setFlag(FLAG_PROXY, this.proxy != null);
    }

    public TLMethod getQuery() {
        return this.query;
    }

    public final void setQuery(TLMethod value) {
        this.query = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.deviceModel, stream);
        StreamingUtils.writeTLString(this.systemVersion, stream);
        StreamingUtils.writeTLString(this.appVersion, stream);
        StreamingUtils.writeTLString(this.systemLangCode, stream);
        StreamingUtils.writeTLString(this.langPack, stream);
        StreamingUtils.writeTLString(this.langCode, stream);
        if (this.hasProxy()) {
            StreamingUtils.writeTLObject(this.proxy, stream);
        }
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.apiId = StreamingUtils.readInt(stream);
        this.deviceModel = StreamingUtils.readTLString(stream);
        this.systemVersion = StreamingUtils.readTLString(stream);
        this.appVersion = StreamingUtils.readTLString(stream);
        this.systemLangCode = StreamingUtils.readTLString(stream);
        this.langPack = StreamingUtils.readTLString(stream);
        this.langCode = StreamingUtils.readTLString(stream);
        if (this.hasProxy()) {
            this.proxy = StreamingUtils.readTLObject(stream, context, TLInputClientProxy.class);
        }
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    @Override
    public TLObject deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    @Override
    public String toString() {
        return "initConnection#785188b8";
    }

}