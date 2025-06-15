package com.hzbhd.proxy.keydispatcher.aidl;

import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback;
import android.os.Bundle;

 /**
 * 按键分发管理的跨进程接口类。
 *
 * Created by bhd008 on 20201219.
 */
interface IKeyDispatcherService{
    /**
     * 注册按键监听。
     *
     * @param sourceID 需要注册的源id
     * @param keyTypes 需要注册的按键值（例如：上一曲和下一曲的按键值）
     * @param listener 监听回调实例
     * @return boolean
     */
    boolean registerKeyDispatcherCallback(int appID, IKeyDispatcherCallback callback);//int sourceID,in int[] keyTypes,

    /**
     * 解除注册按键监听。
     *
     * @param sourceID 需要注册的源id
     * @param listener 监听回调实例
     * @return boolean
     */
    boolean unregisterKeyDispatcherCallback(int appId, IKeyDispatcherCallback callback);//int sourceID,

    boolean registerThirdApp(int appId);
    boolean unregisterThirdApp(int appId);

    /**
     * 触发/调用按键发生事件。
     *
     * @param hotKey  触发的按键值（例如：上一曲和下一曲）
     * @param keyState/arg1 按键的动作状态（例如：按下、长按、短按抬起、长按抬起等）
     * @return isBeep/arg2
     */
    void setKeyEvent(int hotKey, int arg1, int arg2, inout Bundle bundle);

}