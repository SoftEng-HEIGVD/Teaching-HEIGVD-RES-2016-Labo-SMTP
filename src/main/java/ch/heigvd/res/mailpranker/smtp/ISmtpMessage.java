package ch.heigvd.res.mailpranker.smtp;

import java.util.List;

public interface ISmtpMessage {

    String getSender();

    List<String> getRecipients();

    String getSubject();

    String getBody();
}
