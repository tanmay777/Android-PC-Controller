package com.example.tanmayjha.androidmousepadandvideocontrolller.Control;

/**
 * Created by tanmay jha on 28-10-2016.
 */

public class NetworkValues {
    public static String serverIP;
    public static Integer serverPort;
    public static boolean isConnected;

    public NetworkValues(String serverIP, Integer serverPort, boolean isConnected) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.isConnected = isConnected;
    }

    public static NetworkValues networkValues=new NetworkValues("192.168.43.120",138,true);
    //what does networkValues color code mean?

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public NetworkValues(boolean isConnected) {

        this.isConnected = isConnected;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public NetworkValues(String serverIP, Integer serverPort) {

        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
}
