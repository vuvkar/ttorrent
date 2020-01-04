package com.turn.ttorrent.client;

import com.turn.ttorrent.common.creation.MetadataBuilder;
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.FileHandler;

public class clientMain {
    public static void main (String[] args) throws IOException {
        BasicConfigurator.configure();
        clientMain clientMain = new clientMain();

        clientMain.createTorrentFileForDummy();
        clientMain.startClient("bin/a/");
//        clientMain.startClient("bin/");
    }

    private void startClient (String downloadPath) throws UnknownHostException {

        SimpleClient simpleClient = new SimpleClient();
        InetAddress address = InetAddress.getLocalHost();


        try {
            simpleClient.downloadTorrent("bin/test.torrent",
                    downloadPath,
                    address);

            //download finished
        } catch (Exception e) {
            //download failed, see exception for details
            e.printStackTrace();
        }
    }

    private void createTorrentFileForDummy () throws IOException {

        File testFile = new File("test.txt");
        MetadataBuilder metadataBuilder = new MetadataBuilder().addFile(testFile)
                .setTracker("http://localhost:6969/announce");
        byte[] bytes = metadataBuilder.buildBinary();

        File file = new File("bin/test.torrent");

        FileOutputStream fos = null;

        try {

            fos = new FileOutputStream(file);

            // Writes bytes from the specified byte array to this file output stream
            fos.write(bytes);

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
        finally {
            // close the streams using close method
            try {
                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }

        }
    }
}
