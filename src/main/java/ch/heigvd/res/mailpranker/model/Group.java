package ch.heigvd.res.mailpranker.model;

import java.util.List;
import java.util.LinkedList;

public class Group {

    private List<Email> emails = new LinkedList<Email>();

    public void addEmail(Email email) {
        emails.add(email);
    }
}
