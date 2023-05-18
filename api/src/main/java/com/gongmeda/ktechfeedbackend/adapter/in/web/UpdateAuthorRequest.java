package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.UpdateAuthorCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UpdateAuthorRequest {

    private String name;
    private String logoUrl;
    private String blogUrl;
    private String rssUrl;
    private String description;
    private Map<String, String> links;

    public UpdateAuthorCommand toCommand() {
        return new UpdateAuthorCommand(name, logoUrl, blogUrl, rssUrl, description, links);
    }
}
