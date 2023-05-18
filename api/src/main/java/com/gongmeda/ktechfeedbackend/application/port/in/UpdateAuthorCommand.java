package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Value
public class UpdateAuthorCommand extends SelfValidating<AddAuthorCommand> {

    private String name;
    private String logoUrl;
    private String blogUrl;
    private String rssUrl;
    private String description;
    private Map<String, String> links;

    public UpdateAuthorCommand(
        String name,
        String logoUrl,
        String blogUrl,
        String rssUrl,
        String description,
        Map<String, String> links
    ) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.blogUrl = blogUrl;
        this.rssUrl = rssUrl;
        this.description = description;
        this.links = links;
        this.validateSelf();
    }
}
