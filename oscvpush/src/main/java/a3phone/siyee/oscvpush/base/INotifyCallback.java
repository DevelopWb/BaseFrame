package a3phone.siyee.oscvpush.base;

import a3phone.siyee.oscvpush.model.Message;

/**
 * 通知点击callback
 */
public interface INotifyCallback {

    /**
     * 通知下来之后
     * @param msg
     */
    void onMessage(Message msg);

    /**
     * 通知栏被点击之后
     * @param msg
     */
    void onMessageClicked(Message msg);

    /**
     * 通知透传消息
     * @param msg
     */
    void onMessageThrough(Message msg);

}
