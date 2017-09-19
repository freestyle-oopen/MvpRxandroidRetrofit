package aiyagirl.nanchen.com.myapplication.rx;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 用于管理RxBus和Rxjava相关代码的生命周期处理
 * Created by LiZhanPing onShrinkScreen 17/1/14.
 */
public class RxManager {
    private final RxBus mRxBus;
    private final Set<String> events;
    private CompositeSubscription mCompositeSubscription;// 管理订阅者者

    public RxManager() {
        mRxBus = RxBus.getDefault();
        events = new HashSet<>();
    }

    public void on(String eventType, Action1<Object> action1) {
        events.add(eventType);
        mRxBus.onEvent(eventType, action1);
    }

    public void post(String eventType, Object event) {
        mRxBus.post(eventType, event);
    }

    public void add(Subscription m) {
        if (null == mCompositeSubscription || mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(m);
    }

    public void remove(Subscription subscription) {
        if (null != mCompositeSubscription && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.remove(subscription);
        }
    }

    public boolean hasSubscriptions() {
        return mCompositeSubscription.hasSubscriptions();
    }

    public void clear() {
        unregisterFromALl(events);

        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.clear();
        }
    }

    public void unsubscribe() {
        unregisterFromALl(events);

        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
    }

    public boolean isUnsubscribed() {
        return mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed();
    }

    private void unregisterFromALl(Collection<String> events) {
        if (events != null && !events.isEmpty()) {
            Iterator it = events.iterator();
            while (it.hasNext()) {
                mRxBus.unregister(it.next());
            }
        }
    }
}
