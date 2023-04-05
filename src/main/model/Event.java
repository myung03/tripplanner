package model;

import java.util.Calendar;
import java.util.Date;

//an event for a TripPlanner
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;


    //creates an event with given description
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    //EFFECTS: return date of event
    public Date getDate() {
        return dateLogged;
    }

    //EFFECTS: return description of event
    public String getDescription() {
        return description;
    }

    //EFFECTS: return boolean if 2 events are equal
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                &&
                this.description.equals(otherEvent.description));
    }

    //EFFECTS: returns hashcode of a given event
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    //EFFECTS: formats an event to a string
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
