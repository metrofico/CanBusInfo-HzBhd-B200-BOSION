// IKeyDispatcherCallback.aidl
package com.hzbhd.proxy.keydispatcher.aidl;

// Declare any non-default types here with import statements
import android.os.Bundle;

 /**
 * 按键事件的回调方法定义。
 *
 * Created by Y3518 on 2018/11/16.
 */
interface IKeyDispatcherCallback {
    /**
     * 按键事件的回调方法。
     *
     * @param sourceID 指定哪一个源接收该按键事件
     * @param keyType  按键类型（例如：上一曲或者下一曲的按键值等），参见：SourceConstantsDef.MEDIA_SOURCE_ID
     * @param keyState 按键的动作状态（例如：按下、长按、短按抬起、长按抬起等），参见：KeyConstantsDef.KeyType
     * @param bundle   额外的数据，自定义的类型传递
     * @return boolean
     */
    boolean onKeyEvent(int hotKey, int arg1, int arg2, in Bundle bundle);
}
