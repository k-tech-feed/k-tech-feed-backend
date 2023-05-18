package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Value
public class AddAuthorCommand extends SelfValidating<AddAuthorCommand> {

    @NotNull
    private String name;
    private String logoUrl;

    @NotNull
    private String blogUrl;

    private String rssUrl;
    private String description;
    private Map<String, String> links;

    public AddAuthorCommand(
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
