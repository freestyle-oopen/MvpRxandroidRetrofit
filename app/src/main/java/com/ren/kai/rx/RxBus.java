package com.ren.kai.rx;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.ren.kai.utils.Logger;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * 用RxJava实现的EventBus
 *
 * @author baixiaokang
 */
public class RxBus {
    private static volatile RxBus mInstance;

    @SuppressWarnings("rawtypes")
    private final ConcurrentHashMap<Object, List<Subject>> subjectMapper;
    private final ConcurrentHashMap<Object, List<Subscription>> subscriptionMapper;

    public static synchronized RxBus getDefault() {
        if (null == mInstance) {
            synchronized (RxBus.class) {
                mInstance = new RxBus();
            }
        }
        return mInstance;
    }

    private RxBus() {
        subjectMapper = new ConcurrentHashMap<Object, List<Subject>>();
        subscriptionMapper = new ConcurrentHashMap<Object, List<Subscription>>();
    }

    /**
     * 订阅事件源
     *
     * @param tag
     * @param mAction1
     * @return
     */
    public Subscription onEvent(@NonNull Object tag, Action1<Object> mAction1) {
        List<Subscription> subscriptionList = subscriptionMapper.get(tag);
        if (null == subscriptionList) {
            subscriptionList = new ArrayList<Subscription>();
            subscriptionMapper.put(tag, subscriptionList);
        }
        Subscription subscription = register(tag).observeOn(AndroidSchedulers.mainThread()).subscribe(mAction1, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        subscriptionList.add(subscription);
        return subscription;
    }

    /**
     * 注册事件源
     *
     * @param tag
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<Subject>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        Logger.d("register", tag + "  size:" + subjectList.size());
        return subject;
    }

    /**
     * 移除某个事件
     *
     * @param tag
     * @param subscription
     * @return
     */
    public void remove(Object tag, Subscription subscription) {
        boolean unsubscribe = false;
        synchronized (this) {
            if (subscription.isUnsubscribed() || subscription == null) {
                return;
            }
            List<Subscription> subscriptionList = subscriptionMapper.get(tag);
            if (null != subscriptionList) {
                unsubscribe = subscriptionList.remove(subscription);
            }
        }
        if (unsubscribe) {
            // if we removed successfully we then need to call unsubscribe onShrinkScreen it (outside of the lock)
            subscription.unsubscribe();
        }
    }

    /**
     * 移除所有事件
     *
     * @param tag
     * @return
     */
    public void clear(Object tag) {
        List<Subscription> subscriptions = subscriptionMapper.get(tag);
        synchronized (this) {
            if (subscriptions == null) {
                return;
            } else {
                subjectMapper.remove(tag);
            }
        }
        unsubscribeFromAll(subscriptions);
    }

    /**
     * 取消监听
     *
     * @param tag
     * @return
     */
    @SuppressWarnings("rawtypes")
    public void unregister(@NonNull Object tag) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            Logger.d("unregister", tag + "  size:" + subjects.size());
            subjectMapper.remove(tag);
        }
        clear(tag);
    }

    public void post(@NonNull Object event) {
        post(event.getClass().getName(), event);
    }

    /**
     * 触发事件
     *
     * @param event
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void post(@NonNull Object tag, @NonNull Object event) {
        Logger.d("post", "eventName: " + tag);
        List<Subject> subjectList = subjectMapper.get(tag);
        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(event);
                Logger.d("onEvent", "eventName: " + tag);
            }
        }
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasEvents() {
        return null != subjectMapper || subjectMapper.isEmpty();
    }

    private static void unsubscribeFromAll(Collection<Subscription> subscriptions) {
        if (subscriptions == null) {
            return;
        }
        List<Throwable> es = null;
        for (Subscription s : subscriptions) {
            try {
                s.unsubscribe();
            } catch (Throwable e) {
                if (es == null) {
                    es = new ArrayList<Throwable>();
                }
                es.add(e);
            }
        }
        Exceptions.throwIfAny(es);
    }

    private static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }
}
