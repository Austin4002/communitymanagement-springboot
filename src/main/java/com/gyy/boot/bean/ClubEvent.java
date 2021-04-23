package com.gyy.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClubEvent {
    private String id;
    private String clubId;
    private String eventId;
}
