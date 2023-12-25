package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    private int inboxCapacity;
    private List<Mail> inbox;
    private List<Mail> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message) {
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if (inbox.size() >= inboxCapacity) {
            moveOldestToTrash();
        }
        inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message) {
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        Mail mailToRemove = inbox.stream()
                .filter(mail -> mail.getMessage().equals(message))
                .findFirst()
                .orElse(null);

        if (mailToRemove != null) {
            inbox.remove(mailToRemove);
            trash.add(mailToRemove);
        }
    }

    public String findLatestMessage() {
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if (inbox.isEmpty()) {
            return null;
        }
        return inbox.get(inbox.size() - 1).getMessage();
    }

    public String findOldestMessage() {
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

        if (inbox.isEmpty()) {
            return null;
        }
        return inbox.get(0).getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end) {
        // Find the number of mails in the inbox which are received between given dates
        // It is guaranteed that start date <= end date

        return (int) inbox.stream()
                .filter(mail -> mail.getDate().compareTo(start) >= 0 && mail.getDate().compareTo(end) <= 0)
                .count();
    }

    public int getInboxSize() {
        // Return the number of mails in the inbox
        return inbox.size();
    }

    public int getTrashSize() {
        // Return the number of mails in the Trash
        return trash.size();
    }

    public void emptyTrash() {
        // Clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }

    private void moveOldestToTrash() {
        // Move the oldest mail from inbox to trash
        Mail oldestMail = inbox.remove(0);
        trash.add(oldestMail);
    }

    private static class Mail {
        private Date date;
        private String senderId;
        private String message;

        public Mail(Date date, String senderId, String message) {
            this.date = date;
            this.senderId = senderId;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getMessage() {
            return message;
        }
    }
}
