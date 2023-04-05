package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//represents a log of events for a trip planner
//NOTE -> singleton used because only 1 event log should exist at a given time
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    //constructs a SINGLE event log, no more can be constructed after
    private EventLog() {
        events = new ArrayList<Event>();
    }

    //MODIFIES: this
    //EFFECTS: if null, creates new event log, otherwise returns the log
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    //MODIFIES: this
    //EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    //MODIFIES: this
    //EFFECTS: clears event log and adds this event to the log
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    //EFFECTS: returns iterator for events
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}