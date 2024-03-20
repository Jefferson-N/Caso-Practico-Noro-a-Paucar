package com.uisrael.trainTravel.models.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Calendario {
    private String title           ;
    private LocalDateTime start    ;
    private LocalDateTime end      ;
    private boolean allDay         ;
    private String backgroundColor ;
    private String borderColor     ;
    private String url ;

    
    public Calendario(String title, LocalDateTime start, LocalDateTime end, boolean allDay, String backgroundColor,
            String borderColor, String url) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.allDay = allDay;
        this.backgroundColor = backgroundColor != null ? backgroundColor: "#28a745";
        this.borderColor = borderColor != null ? borderColor: "#00a65a";
        this.url = url != null ? url: "#";
    }

    public void setBackgroundColor(String backgroundColor){
        this.backgroundColor = 
        backgroundColor != null ? backgroundColor: "#28a745";
    }
    
    public void setBorderColor(String borderColor){
        this.borderColor = 
        borderColor != null ? borderColor: "#00a65a";
    }

    public void setUrl(String url){
        this.url = 
        url != null ? url: "#";
    }
}

