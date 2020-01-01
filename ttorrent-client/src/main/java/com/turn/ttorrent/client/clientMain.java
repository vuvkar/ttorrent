package com.turn.ttorrent.client;

import org.apache.log4j.BasicConfigurator;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class clientMain {
    public static void main (String[] args) throws UnknownHostException {
        BasicConfigurator.configure();

        SimpleClient simpleClient = new SimpleClient();
        InetAddress address = InetAddress.getLocalHost();


        try {
            simpleClient.downloadTorrent("tests/src/test/resources/parentFiles/file1.jar.torrent",
                    "tests/src/test/resources/parentFiles/",
                    address);

            //download finished
        } catch (Exception e) {
            //download failed, see exception for details
            e.printStackTrace();
        }
    }
}
