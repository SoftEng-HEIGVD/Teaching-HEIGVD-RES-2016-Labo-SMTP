package ch.heigvd.res.mailpranker.smtp;

public interface ISmtpClient {

    void send(ISmtpMessage ... messages);
}
