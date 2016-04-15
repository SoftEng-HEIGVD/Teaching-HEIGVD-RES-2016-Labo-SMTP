package ch.heigvd.res.mailpranker.model;

public class Prank {

    private Message message;
    private Group group;

    public Prank(Group group, Message message) {
        this.group = group;
        this.message = message;
    }
}
