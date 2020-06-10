package org.javagram;

import org.javagram.client.handlers.DefaultKernelHandler;
import org.javagram.client.structure.MemoryApiState;
import org.javagram.utils.BotLogger;
import org.javagram.client.structure.LoginStatus;
import org.javagram.utils.LogInterface;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;
import org.javagram.client.kernel.DifferenceParametersService;
import org.javagram.client.kernel.KernelAuthenticator;
import org.javagram.client.kernel.KernelCommunicationService;
import org.javagram.client.kernel.TelegramApi;
import org.javagram.utils.DefaultApiCallback;
import org.javagram.utils.LoggerInterface;

public class TelegramClient {
    
    private static final AtomicInteger CURRENT_INSTANCES_NUMBER = new AtomicInteger(2000);
    private final String logtag;
    private final int instanceNumber;

    private final MyTLAppConfiguration config;

    public TelegramClient(MyTLAppConfiguration.Builder configBuilder) {
        this.instanceNumber = CURRENT_INSTANCES_NUMBER.incrementAndGet();
        this.logtag = "[TelegramClient#" + this.instanceNumber + "]";
        this.initializeLogging();
        BotLogger.info(this.logtag, "Creating");
        if (configBuilder == null) {
            throw new NullPointerException("The complete configuration must be provided.");
        }
        this.config = configBuilder.build();
        this.config.getDatabaseManager().build(this.config);
        if (this.config.getDifferenceParametersService() == null) {
            this.config.setDifferenceParametersService(new DifferenceParametersService());
        }
        this.config.getDifferenceParametersService().build(this.config);
        BotLogger.info(this.logtag, "Created");
    }
    
    private void initializeLogging() {
        
        org.javagram.mtproto.log.Logger.registerInterface(new LogInterface() {
            @Override
            public void w(String tag, String message) {
                BotLogger.warning("[MTProto]", message);
            }

            @Override
            public void d(String tag, String message) {
                BotLogger.debug("[MTProto]", message);
            }

            @Override
            public void e(String tag, String message) {
                BotLogger.error("[MTProto]", message);
            }

            @Override
            public void e(String tag, Throwable t) {
                BotLogger.error("[MTProto]", t);
            }
        });
        org.javagram.utils.Logger.registerInterface(new LoggerInterface() {
            @Override
            public void w(String tag, String message) {
                BotLogger.warning(TelegramClient.this.logtag, message);
            }

            @Override
            public void d(String tag, String message) {
                BotLogger.debug(TelegramClient.this.logtag, message);
            }

            @Override
            public void e(String tag, Throwable t) {
                BotLogger.error(TelegramClient.this.logtag, t);
            }
        });
    }

    public LoginStatus init() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BotLogger.info(this.logtag, "Initializing");

        // Initialize handlers
        this._initializeHandlers();
        
        // Retrieve or setup the API state
        this._initializeApiState();
        
        // Set up threads and assign api state
        this._createKernelComm();
        
        // Create TelegramApi and run the updates handler threads
        this._createApi();
        
        // Assign API state to kernel auth
        this._createKernelAuth();
        
        // Perform login if necessary
        final LoginStatus loginResult = this._startKernelAuth();
        
        // Create rest of handlers
        this._createKernelHandler();
        BotLogger.info(this.logtag, "Initialized");
        
        return loginResult;
    }
    
    public void start() {
        BotLogger.debug(this.logtag, "Starting");

        //Retrieves current client's user
        this.config.getDatabaseManager().retrieveSelf();

        this.config.getKernelHandler().start();
        BotLogger.debug(this.logtag, "Started");
    }

    public void stop() {
        BotLogger.debug(this.logtag, "Stopping");
        this.config.getKernelHandler().stop();
        BotLogger.debug(this.logtag, "Stopped");
    }
    
    private void _initializeHandlers() {
        this.config.getUsersHandler().build(this.config);
        this.config.getChatsHandler().build(this.config);
        this.config.getUpdatesHandler().build(this.config);
        this.config.getMessagesHandler().build(this.config);
    }
    
    private void _initializeApiState() {
        BotLogger.debug(this.logtag, "Creating API State");
        if (this.config.getApiState() == null) {
            this.config.setApiState(new MemoryApiState());
        }
        this.config.getApiState().build(this.config);
        BotLogger.debug(this.logtag, "API State created");
    }

    private void _createKernelComm() {
        BotLogger.debug(this.logtag, "Creating KernelCommunicationService");
        if (this.config.getKernelCommunicationService() == null) {
            this.config.setKernelCommunicationService(new KernelCommunicationService());
        }
        this.config.getKernelCommunicationService().build(this.config);
        BotLogger.debug(this.logtag, "KernelCommunicationService created");
    }

    private void _createApi() {
        BotLogger.trace(this.logtag, "Creating TelegramApi");
        TelegramApi api = this.config.getApi();
        if (api == null) {
            DefaultApiCallback cb = new DefaultApiCallback();
            cb.build(this.config);
            api = new TelegramApi(this.config, cb);
        }
        this.config.setApi(api);
        BotLogger.debug(this.logtag, "TelegramApi created");
    }

    private void _createKernelAuth() {
        BotLogger.debug(this.logtag, "Creating KernelAuthenticator");
        if (this.config.getKernelAuthenticator() == null) {
            this.config.setKernelAuthenticator(new KernelAuthenticator());
        }
        this.config.getKernelAuthenticator().build(this.config);
        BotLogger.debug(this.logtag, "KernelAuthenticator created");
    }

    private LoginStatus _startKernelAuth() {
        BotLogger.debug(this.logtag, "starting KernelAuthenticator");
        final LoginStatus status = this.config.getKernelAuthenticator().start();
        BotLogger.debug(this.logtag, "KernelAuthenticator started");
        return status;
    }

    private void _createKernelHandler() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BotLogger.debug(this.logtag, "Creating KernelHandler");
        if (this.config.getKernelHandler() == null) {
            this.config.setKernelHandler(new DefaultKernelHandler());
        }
        this.config.getKernelHandler().build(this.config);
        BotLogger.debug(this.logtag, "KernelHandler created");
    }

    public MyTLAppConfiguration getConfig() {
        return this.config;
    }

    public boolean isAuthenticated() {
        return this.config.getApiState().isAuthenticated();
    }
}