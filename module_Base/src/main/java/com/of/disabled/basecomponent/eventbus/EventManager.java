package com.of.disabled.basecomponent.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/4/11 11:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/11 11:34
 */
public class EventManager {

    private static final EventBus libraryEvent = EventBus.builder().build();

    public static EventBus getEventBus() {
        return libraryEvent;
    }

    public static void removeAllEvent() {
        if (libraryEvent != null) {
            libraryEvent.removeAllStickyEvents();
        }
    }
    public static void sendStringMsg(String str) {
        libraryEvent.post(str);
    }
}
