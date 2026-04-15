package org.example.eventsystemassignment.be;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class Event {

    private int id;
    private String eventName;
    private LocalTime startTime;
    private Date startDate;
    private String location;
    private String extraNotes;
    private LocalTime endTime;
    private Date endDate;
    private String locationGuidance;

    public Event(int id, String eventName, LocalTime startTime, Date startDate, String location, String extraNotes, LocalTime endTime, Date endDate, String locationGuidance){
        this.id = id;
        this.eventName = eventName;
        this.startTime = startTime;
        this.startDate = startDate;
        this.location = location;
        this.extraNotes = extraNotes;
        this.endTime = endTime;
        this.endDate = endDate;
        this.locationGuidance = locationGuidance;
    }

    public Event(String eventName, LocalTime startTime, Date startDate, String location, String extraNotes, LocalTime endTime, Date endDate, String locationGuidance){
        this.eventName = eventName;
        this.startTime = startTime;
        this.startDate = startDate;
        this.location = location;
        this.extraNotes = extraNotes;
        this.endTime = endTime;
        this.endDate = endDate;
        this.locationGuidance = locationGuidance;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getEventName(){
        return eventName;
    }
    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    public LocalTime getStartTime(){
        return startTime;
    }
    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }

    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public String getExtraNotes() {
        return extraNotes;
    }
    public void setExtraNotes(String extraNotes){
        this.extraNotes = extraNotes;
    }

    public LocalTime getEndTime(){
        return endTime;
    }
    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getLocationGuidance(){
        return locationGuidance;
    }
    public void setLocationGuidance(String locationGuidance){
        this.locationGuidance = locationGuidance;
    }
}
