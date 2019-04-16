package net.lzzy.practicesonline.activities.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by lzzy_gxy on 2019/4/12.
 * Description:
 */
public abstract class AbstractStaticHandler<T> extends Handler {
    private final WeakReference<T> context;

    public AbstractStaticHandler(T context){
        this.context=new WeakReference<>(context);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T t=context.get();
        handleMessage(msg,t);
    }
    public abstract void handleMessage(Message msg,T t);
}
