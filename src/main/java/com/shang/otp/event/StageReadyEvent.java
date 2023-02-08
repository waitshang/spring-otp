package com.shang.otp.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/1 14:44
 */
public class StageReadyEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public StageReadyEvent(Stage source) {
        super(source);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
