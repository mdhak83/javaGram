package org.javagram;

import java.util.concurrent.ExecutorService;
import org.javagram.client.handlers.AbstractChatsHandler;
import org.javagram.client.handlers.AbstractMessagesHandler;
import org.javagram.client.handlers.AbstractUpdatesHandler;
import org.javagram.client.handlers.AbstractUsersHandler;
import org.javagram.client.handlers.DefaultDifferencesHandler;
import org.javagram.client.handlers.DefaultKernelHandler;
import org.javagram.client.kernel.KernelAuthenticator;
import org.javagram.client.kernel.KernelCommunicationService;
import org.javagram.client.kernel.AbstractDatabaseManager;
import org.javagram.client.kernel.DifferenceParametersService;
import org.javagram.client.kernel.TelegramApi;
import org.javagram.client.structure.MemoryApiState;

public class MyTLAppConfiguration {
    
    public static class Builder {
        
        protected boolean registered = false;
        protected String firstName = null;
        protected String lastName = null;
        protected int apiId = 0;
        protected String apiHash = null;
        protected String phoneNumber = null;
        protected String deviceModel = null;
        protected String systemVersion = null;
        protected String appVersion = null;
        protected String systemLangCode = null;
        protected String langPack = null;
        protected String langCode = null;
        protected String authFilename = null;
        protected boolean bot = false;
        protected String botToken = null;
        protected AbstractDatabaseManager databaseManager = null;
        protected AbstractUsersHandler usersHandler = null;
        protected AbstractChatsHandler chatsHandler = null;
        protected AbstractMessagesHandler messagesHandler = null;
        protected AbstractUpdatesHandler updatesHandler = null;
        protected DefaultDifferencesHandler differencesHandler = null;
        protected DefaultKernelHandler kernelHandler = null;
        protected MemoryApiState apiState = null;
        protected KernelCommunicationService kernelCommunicationService = null;
        protected DifferenceParametersService differenceParametersService = null;
        protected KernelAuthenticator kernelAuthenticator = null;
        protected TelegramApi api = null;
        protected ExecutorService executor = null;
        
        public Builder() { }
        
        public MyTLAppConfiguration build() {
            this._checkInputs();
            MyTLAppConfiguration config = new MyTLAppConfiguration(
                    this.registered,
                    this.firstName,
                    this.lastName,
                    this.apiId,
                    this.apiHash,
                    this.phoneNumber,
                    this.deviceModel,
                    this.systemVersion,
                    this.appVersion,
                    this.systemLangCode,
                    this.langPack,
                    this.langCode,
                    this.authFilename,
                    this.bot,
                    this.botToken,
                    this.databaseManager,
                    this.usersHandler,
                    this.chatsHandler,
                    this.messagesHandler,
                    this.updatesHandler,
                    this.differencesHandler,
                    this.kernelHandler,
                    this.apiState,
                    this.kernelCommunicationService,
                    this.kernelAuthenticator,
                    this.api,
                    this.executor
            );
            return config;
        }
        
        private void _checkInputs() {
            if (!this.registered) {
                if (this.firstName == null) {
                    this.firstName = "John";
                }
                if (this.lastName == null) {
                    this.lastName = "Doe";
                }
            }
            if (this.apiId == 0) {
                throw new IllegalArgumentException("API ID must be provided.");
            }
            if (this.apiHash == null) {
                throw new IllegalArgumentException("The API Hash must be provided.");
            }
            if (this.apiHash.trim().isEmpty()) {
                throw new IllegalArgumentException("A valid API Hash must be provided.");
            }
            if (this.phoneNumber == null) {
                throw new IllegalArgumentException("A phone number must be provided.");
            }
            if (this.phoneNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("A valid phone number must be provided.");
            }
            if (this.deviceModel == null || this.deviceModel.trim().isEmpty()) {
                this.deviceModel = "Unknown";
            }
            if (this.systemVersion == null) {
                this.systemVersion = System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch");
            }
            if (this.appVersion == null) {
                this.appVersion = "Unknown";
            }
            if (this.systemLangCode == null || this.systemLangCode.trim().isEmpty()) {
                this.systemLangCode = "en";
            }
            if (this.langPack == null || this.langPack.trim().isEmpty()) {
                this.langPack = "";
            }
            if (this.langCode == null || this.langCode.trim().isEmpty()) {
                this.langCode = "en";
            }
            if (this.authFilename == null || this.authFilename.trim().isEmpty()) {
                this.authFilename = "tl-" + this.phoneNumber + ".auth";
            }
            if (this.bot && this.botToken == null) {
                throw new IllegalArgumentException("A bot must provide a botToken.");
            }
            if (this.bot && this.botToken.trim().isEmpty()) {
                throw new IllegalArgumentException("A bot must provide a valid botToken.");
            }
            if (this.databaseManager == null) {
                throw new IllegalArgumentException("A DatabaseManager implementation must be provided.");
            }
            if (this.usersHandler == null) {
                throw new IllegalArgumentException("A UsersHandler implementation must be provided.");
            }
            if (this.chatsHandler == null) {
                throw new IllegalArgumentException("A ChatsHandler implementation must be provided.");
            }
            if (this.messagesHandler == null) {
                throw new IllegalArgumentException("A MessagesHandler implementation must be provided.");
            }
            if (this.updatesHandler == null) {
                throw new IllegalArgumentException("A UpdatesHandler implementation must be provided.");
            }
        }
        
        public Builder setRegistered(boolean registered) {
            this.registered = registered;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setApiId(int apiId) {
            this.apiId = apiId;
            return this;
        }

        public Builder setApiHash(String apiHash) {
            this.apiHash = apiHash;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
            return this;
        }

        public Builder setSystemVersion(String systemVersion) {
            this.systemVersion = systemVersion;
            return this;
        }

        public Builder setAppVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        public Builder setSystemLangCode(String systemLangCode) {
            this.systemLangCode = systemLangCode;
            return this;
        }

        public Builder setLangPack(String langPack) {
            this.langPack = langPack;
            return this;
        }

        public Builder setLangCode(String langCode) {
            this.langCode = langCode;
            return this;
        }

        public Builder setAuthFilename(String authFilename) {
            this.authFilename = authFilename;
            return this;
        }

        public Builder setBot(boolean bot) {
            this.bot = bot;
            return this;
        }

        public Builder setBotToken(String botToken) {
            this.botToken = botToken;
            return this;
        }

        public Builder setDatabaseManager(AbstractDatabaseManager databaseManager) {
            this.databaseManager = databaseManager;
            return this;
        }

        public Builder setUsersHandler(AbstractUsersHandler usersHandler) {
            this.usersHandler = usersHandler;
            return this;
        }

        public Builder setChatsHandler(AbstractChatsHandler chatsHandler) {
            this.chatsHandler = chatsHandler;
            return this;
        }

        public Builder setMessagesHandler(AbstractMessagesHandler messagesHandler) {
            this.messagesHandler = messagesHandler;
            return this;
        }

        public Builder setUpdatesHandler(AbstractUpdatesHandler updatesHandler) {
            this.updatesHandler = updatesHandler;
            return this;
        }

        public Builder setDifferencesHandler(DefaultDifferencesHandler differencesHandler) {
            this.differencesHandler = differencesHandler;
            return this;
        }

        public Builder setKernelHandler(DefaultKernelHandler kernelHandler) {
            this.kernelHandler = kernelHandler;
            return this;
        }

        public Builder setApiState(MemoryApiState apiState) {
            this.apiState = apiState;
            return this;
        }

        public Builder setKernelCommunicationService(KernelCommunicationService kernelCommunicationService) {
            this.kernelCommunicationService = kernelCommunicationService;
            return this;
        }

        public Builder setDifferenceParametersService(DifferenceParametersService differenceParametersService) {
            this.differenceParametersService = differenceParametersService;
            return this;
        }

        public Builder setKernelAuthenticator(KernelAuthenticator kernelAuthenticator) {
            this.kernelAuthenticator = kernelAuthenticator;
            return this;
        }

        public Builder setApi(TelegramApi api) {
            this.api = api;
            return this;
        }

        public Builder setExecutor(ExecutorService executor) {
            this.executor = executor;
            return this;
        }

    }

    /**
     * 
     */
    private boolean registered;
    
    /**
     * 
     */
    private final String firstName;
    
    /**
     * 
     */
    private final String lastName;
        
    /**
     * The Api id.
     */
    private final int apiId;

    /**
     * The Api hash.
     */
    private final String apiHash;

    /**
     * The phone number.
     */
    private final String phoneNumber;

    /**
     * The Device model.
     */
    private final String deviceModel;

    /**
     * The System version.
     */
    private final String systemVersion;

    /**
     * The App version.
     */
    private final String appVersion;

    /**
     * The Lang code.
     */
    private final String systemLangCode;

    /**
     * The Lang code.
     */
    private final String langPack;

    /**
     * The Lang code.
     */
    private final String langCode;
    
    /**
     * 
     */
    private final String authFilename;
    
    /**
     * 
     */
    private final boolean bot;

    /**
     * 
     */
    private final String botToken;
    
    /**
     * 
     */
    private final AbstractDatabaseManager databaseManager;
    
    /**
     * 
     */
    private final AbstractUsersHandler usersHandler;
    
    /**
     * 
     */
    private final AbstractChatsHandler chatsHandler;
    
    /**
     * 
     */
    private final AbstractMessagesHandler messagesHandler;
    
    /**
     * 
     */
    private final AbstractUpdatesHandler updatesHandler;
    
    /**
     * 
     */
    private String phoneCodeHash;

    /**
     * 
     */
    private DefaultDifferencesHandler differencesHandler;
    
    /**
     * 
     */
    private DefaultKernelHandler kernelHandler;
    
    /**
     * 
     */
    private MemoryApiState apiState;

    /**
     * 
     */
    private KernelCommunicationService kernelCommunicationService;

    /**
     * 
     */
    private DifferenceParametersService differenceParametersService;

    /**
     * 
     */
    private KernelAuthenticator kernelAuthenticator;

    /**
     * 
     */
    private TelegramApi api;

    /**
     * 
     */
    private ExecutorService executor;
        
    /**
     * Instantiates a new App info.
     *
     */
    private MyTLAppConfiguration(
            boolean registered,
            String firstName,
            String lastName,
            int apiId,
            String apiHash,
            String phoneNumber,
            String deviceModel,
            String systemVersion,
            String appVersion,
            String systemLangCode,
            String langPack,
            String langCode,
            String authFilename,
            boolean bot,
            String botToken,
            AbstractDatabaseManager databaseManager,
            AbstractUsersHandler usersHandler,
            AbstractChatsHandler chatsHandler,
            AbstractMessagesHandler messagesHandler,
            AbstractUpdatesHandler updatesHandler,
            DefaultDifferencesHandler differencesHandler,
            DefaultKernelHandler kernelHandler,
            MemoryApiState apiState,
            KernelCommunicationService kernelCommunicationService,
            KernelAuthenticator kernelAuthenticator,
            TelegramApi api,
            ExecutorService executor
    ) {
        this.registered = registered;
        this.firstName = firstName;
        this.lastName = lastName;
        this.apiId = apiId;
        this.apiHash = apiHash;
        this.phoneNumber = phoneNumber;
        this.deviceModel = deviceModel;
        this.systemVersion = systemVersion;
        this.appVersion = appVersion;
        this.systemLangCode = systemLangCode;
        this.langPack = langPack;
        this.langCode = langCode;
        this.authFilename = authFilename;
        this.bot = bot;
        this.botToken = botToken;
        this.databaseManager = databaseManager;
        this.usersHandler = usersHandler;
        this.chatsHandler = chatsHandler;
        this.messagesHandler = messagesHandler;
        this.updatesHandler = updatesHandler;
        this.differencesHandler = differencesHandler;
        this.kernelHandler = kernelHandler;
        this.apiState = apiState;
        this.kernelCommunicationService = kernelCommunicationService;
        this.kernelAuthenticator = kernelAuthenticator;
        this.api = api;
        this.executor = executor;
    }

    public boolean getRegistered() {
        return registered;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getApiId() {
        return apiId;
    }

    public String getApiHash() {
        return apiHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getSystemLangCode() {
        return systemLangCode;
    }

    public String getLangPack() {
        return langPack;
    }

    public String getLangCode() {
        return langCode;
    }

    public String getAuthFilename() {
        return authFilename;
    }

    public boolean isBot() {
        return bot;
    }

    public String getBotToken() {
        return botToken;
    }

    public AbstractDatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public AbstractUsersHandler getUsersHandler() {
        return usersHandler;
    }

    public AbstractChatsHandler getChatsHandler() {
        return chatsHandler;
    }

    public AbstractMessagesHandler getMessagesHandler() {
        return messagesHandler;
    }

    public AbstractUpdatesHandler getUpdatesHandler() {
        return updatesHandler;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getPhoneCodeHash() {
        return phoneCodeHash;
    }

    public void setPhoneCodeHash(String phoneCodeHash) {
        this.phoneCodeHash = phoneCodeHash;
    }

    public DefaultDifferencesHandler getDifferencesHandler() {
        return differencesHandler;
    }

    public void setDifferencesHandler(DefaultDifferencesHandler differencesHandler) {
        this.differencesHandler = differencesHandler;
    }

    public DefaultKernelHandler getKernelHandler() {
        return kernelHandler;
    }

    public void setKernelHandler(DefaultKernelHandler kernelHandler) {
        this.kernelHandler = kernelHandler;
    }

    public MemoryApiState getApiState() {
        return apiState;
    }

    public void setApiState(MemoryApiState apiState) {
        this.apiState = apiState;
    }

    public KernelCommunicationService getKernelCommunicationService() {
        return kernelCommunicationService;
    }

    public void setKernelCommunicationService(KernelCommunicationService kernelCommunicationService) {
        this.kernelCommunicationService = kernelCommunicationService;
    }

    public DifferenceParametersService getDifferenceParametersService() {
        return differenceParametersService;
    }

    public void setDifferenceParametersService(DifferenceParametersService differenceParametersService) {
        this.differenceParametersService = differenceParametersService;
    }

    public KernelAuthenticator getKernelAuthenticator() {
        return kernelAuthenticator;
    }

    public void setKernelAuthenticator(KernelAuthenticator kernelAuthenticator) {
        this.kernelAuthenticator = kernelAuthenticator;
    }

    public TelegramApi getApi() {
        return api;
    }

    public void setApi(TelegramApi api) {
        this.api = api;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

}