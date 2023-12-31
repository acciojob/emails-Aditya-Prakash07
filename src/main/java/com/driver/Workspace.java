package com.driver;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail {

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        // Add the meeting to the calendar
        calendar.add(meeting);
        // Sort the calendar based on meeting start times
        Collections.sort(calendar, (m1, m2) -> m1.getStartTime().compareTo(m2.getStartTime()));
    }

    public int findMaxMeetings() {
        // Find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        int maxMeetings = 0;
        LocalTime lastEndTime = LocalTime.MIN;

        for (Meeting meeting : calendar) {
            if (meeting.getStartTime().compareTo(lastEndTime) >= 0) {
                maxMeetings++;
                lastEndTime = meeting.getEndTime();
            }
        }

        return maxMeetings;
    }
}
