package com.epam.kabaldin.chatgpt.developer.security;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SecureStreamExample {

    private static final String HOST = "example.com";
    private static final int PORT = 443;

    public static void main(String[] args) throws IOException {
        // Create SSL context
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
        } catch (Exception e) {
            throw new IOException("Failed to initialize SSL context", e);
        }

        // Create socket factory
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();

        // Create secure socket
        SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(HOST, PORT);

        // Perform hostname verification
        SSLParameters sslParams = new SSLParameters();
        sslParams.setEndpointIdentificationAlgorithm("HTTPS");
        sslSocket.setSSLParameters(sslParams);

        // Enable SSL/TLS protocols (optional)
        sslSocket.setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.3"});

        // Enable cipher suites (optional)
        sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());

        // Perform handshake
        sslSocket.startHandshake();

        // Get input/output streams for secure communication
        InputStream inputStream = sslSocket.getInputStream();
        OutputStream outputStream = sslSocket.getOutputStream();

        // Use the streams for secure communication
        // ...

        // Close the streams and the socket when done
        inputStream.close();
        outputStream.close();
        sslSocket.close();
    }
}

