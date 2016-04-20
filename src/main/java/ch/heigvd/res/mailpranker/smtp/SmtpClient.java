package ch.heigvd.res.mailpranker.smtp;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

/**
 * An SMTP client sending emails, with a control of
 * the reply codes.
 *
 * Establishes a connection with the server for each send,
 * but the send() method use java ellipses to take one or
 * more messages to send. So, it's possible to send more
 * than one message with an unique socket connection.
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class SmtpClient implements ISmtpClient {

    /**
     * Information for the connection to server
     */
    private String address;
    private int port;

    /**
     * Constructor of the object saving server parameters
     *
     * @param address the IP address
     * @param port the port
     */
    public SmtpClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Send one or more email
     *
     * @param messages the messages to send
     */
    public void send(ISmtpMessage ... messages) {
        Socket socket;
        BufferedWriter output;
        BufferedReader input;

        // Open socket connection and streams
        try {
            socket = new Socket(address, port);
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to open SMTP server connection.");
        }

        // Hello server
        controlReplyCode(input, 220);
        send(output, "EHLO localhost");
        controlReplyCode(input, 250);

        // Send messages
        for (ISmtpMessage message : messages) {

            // From
            send(output, "MAIL From: <" + message.getFrom() + ">");
            controlReplyCode(input, 250);

            // To
            for (String to : message.getTo()) {
                send(output, "RCPT TO: <" + to + ">");
                controlReplyCode(input, 250);
            }

            // Cc
            for (String to : message.getCC()) {
                send(output, "RCPT TO: <" + to + ">");
                controlReplyCode(input, 250);
            }

            // Bcc
            for (String to : message.getBCC()) {
                send(output, "RCPT TO: <" + to + ">");
                controlReplyCode(input, 250);
            }

            // Data
            send(output, "DATA");
            controlReplyCode(input, 354);

            // Headers
            send(output, "Content-Type: text/plain;charset=utf-8");
            send(output, "From: " + message.getFrom());
            send(output, "To: " + String.join(", ", message.getTo()));
            if (message.getCC().size() > 0) {
                send(output, "Cc: " + String.join(", ", message.getCC()));
            }
            send(output, "Subject: " + message.getSubject());

            // Body
            send(output, "");
            send(output, message.getBody());
            send(output, ".");
            controlReplyCode(input, 250);
        }

        // Quit server
        send(output, "QUIT");
        controlReplyCode(input, 221);

        // Close streams and socket connection
        try {
            output.close();
            input.close();
            socket.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to close SMTP server connection.");
        }
    }

    /**
     * Write and flush a line in the socket input stream
     *
     * @param output the writer
     * @param data the data to write
     */
    private static void send(BufferedWriter output, String data) {
        try {
            System.out.println("C : " + data);
            output.write(data);
            output.write("\r\n");
            output.flush();
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to speak with the SMTP server.");
        }
    }

    /**
     * Verify the reply code.
     *
     * If the server message contains multiple lines ("code-"),
     * read all lines until the final one.
     *
     * @param input the reader
     * @param code the expected code
     */
    private static void controlReplyCode(BufferedReader input, int code) {
        try {
            String line;
            while (true) {
                line = input.readLine();
                System.out.println("S : " + line);

                // Read until the last line (with a space after code)
                if (line.charAt(3) == ' ') {
                    break;
                }
            }
            if (!line.startsWith(Integer.toString(code))) {
                throw new RuntimeException("SMTP return code error. Expected " + code + ".");
            }
        }
        catch (IOException e) {
            throw new RuntimeException("SMTP return code error.");
        }
    }
}
