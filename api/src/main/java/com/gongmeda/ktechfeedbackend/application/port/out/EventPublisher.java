package com.gongmeda.ktechfeedbackend.application.port.out;

public interface EventPublisher {

    void publish(Object event);
}
