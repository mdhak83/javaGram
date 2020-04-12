package org.telegram.client.structure;

public class DataCenterInformation {
    
    protected final int id;
    protected final String productionIpAddress;
    protected final int productionIpPort;
    protected final String testIpAddress;
    protected final int testIpPort;
    
    public DataCenterInformation(String row) {
        //row value = dcId;prodSrvIpAddr;prodSrvIpPort;testSrvIpAddr;testsrvIpPort --> example : "2;192.168.0.1;443;192.168.0.2;443"
        String[] _vars = row.split(";");
        this.id = Integer.parseInt(_vars[0]);
        String[] _prod = _vars[1].split(":");
        this.productionIpAddress = _prod[0];
        this.productionIpPort = Integer.parseInt(_prod[1]);
        String[] _test = _vars[2].split(":");
        this.testIpAddress = _test[0];
        this.testIpPort = Integer.parseInt(_test[1]);
    }
    
    public DataCenterInformation(int id, String productionIpAddress, int productionIpPort, String testIpAddress, int testIpPort) {
        this.id = id;
        this.productionIpAddress = productionIpAddress;
        this.productionIpPort = productionIpPort;
        this.testIpAddress = testIpAddress;
        this.testIpPort = testIpPort;
    }

    public int getId() {
        return this.id;
    }

    public String getProductionIpAddress() {
        return this.productionIpAddress;
    }

    public int getProductionIpPort() {
        return this.productionIpPort;
    }
    
    public String getTestIpAddress() {
        return this.productionIpAddress;
    }

    public int getTestIpPort() {
        return this.productionIpPort;
    }
    
}