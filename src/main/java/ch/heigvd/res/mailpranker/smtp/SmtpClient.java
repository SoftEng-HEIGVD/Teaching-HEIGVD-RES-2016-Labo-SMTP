package ch.heigvd.res.mailpranker.smtp;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

public class SmtpClient implements ISmtpClient {

    private class SmtpChannel {

        BufferedWriter output;
        BufferedReader input;

        public SmtpChannel(Socket socket) {

            // Open streams
            try {
                output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }
            catch (IOException e) {
                throw new RuntimeException("Unable to open SMTP streams.");
            }

            // Hello server
            controlReplyCode(220);
            send("EHLO localhost");
            controlReplyCode(250);
        }

        public void close() {

            // Quit server
            send("QUIT");
            controlReplyCode(221);

            // Close streams
            try {
                output.close();
                input.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Unable to close SMTP streams.");
            }
        }

        public void send(String data) {
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

        public void controlReplyCode(int code) {
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

    private String address;
    private int port;
    private String cc;

    public SmtpClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void setCC(String cc) {
        this.cc = cc;
    }

    public void send(ISmtpMessage ... messages) {

        // Open socket connection and channel
        Socket socket;
        try {
            socket = new Socket(address, port);
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to open SMTP server connection.");
        }
        SmtpChannel channel = new SmtpChannel(socket);

        // Send messages
        for (ISmtpMessage message : messages) {

            channel.send("MAIL From: <" + message.getSender() + ">");
            channel.controlReplyCode(250);

            for (String to : message.getRecipients()) {
                channel.send("RCPT TO: <" + to + ">");
                channel.controlReplyCode(250);
            }

            channel.send("DATA");
            channel.controlReplyCode(354);

            channel.send("Content-Type: text/plain;charset=utf-8");
            channel.send("From: " + message.getSender());
            channel.send("To: " + String.join(", ", message.getRecipients()));
            // todo : if CC add CC
            channel.send("Subject: " + message.getSubject());
            channel.send("");
            channel.send(message.getBody());
            channel.send(".");
            channel.controlReplyCode(250);
        }

        // Close transport and socket connection
        channel.close();
        try {
            socket.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to close SMTP server connection.");
        }

    }
}
