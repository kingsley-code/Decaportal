package com.decagon.recruitmentportal.pojos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GmailDTO {
    private String subject;
    private String toAddresses;
    private String body;
}
