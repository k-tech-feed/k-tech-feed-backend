package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.AddAuthorCommand;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class AddAuthorRequest {

    private String name;
    private String logoUrl;
    private String blogUrl;
    private String rssUrl;
    private String description;
    private Map<String, String> links;

    public AddAuthorCommand toCommand() {
        return new AddAuthorCommand(name, logoUrl, blogUrl, rssUrl, description, links);
    }
}
