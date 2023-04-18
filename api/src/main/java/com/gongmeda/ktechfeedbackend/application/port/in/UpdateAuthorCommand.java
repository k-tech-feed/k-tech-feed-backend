package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class UpdateAuthorCommand extends SelfValidating<AddAuthorCommand> {

    private String name;
    private String logoUrl;
    private String blogUrl;
    private String RSSUrl;
    private String description;
    private Map<String, String> links;

    public UpdateAuthorCommand(
        String name,
        String logoUrl,
        String blogUrl,
        String RSSUrl,
        String description,
        Map<String, String> links
    ) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.blogUrl = blogUrl;
        this.RSSUrl = RSSUrl;
        this.description = description;
        this.links = links;
        this.validateSelf();
    }
}
