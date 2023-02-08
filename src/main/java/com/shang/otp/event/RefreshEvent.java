package com.shang.otp.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/7 12:36
 */
public class RefreshEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     */
    public RefreshEvent() {
        super("Refresh");
    }
}
