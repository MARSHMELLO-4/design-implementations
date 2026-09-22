package com.design.UrlShortner.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TicketId {

    @Id
    Long id;

    int start;
    int end;
    int current;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
