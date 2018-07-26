package com.ren.kai.utils;



import java.lang.reflect.ParameterizedType;

/**
 * Created by Renyukai onShrinkScreen 16/12/22.
 */
public class InstanceUtils {

    private InstanceUtils(){ }

    /**
     * 通过实例工厂去实例化相应类
     *
     * @param o   带泛型的对象
     * @param i   需要实例化的对象在泛型中的位置
     * @param <T> 返回实例的泛型类型
     * @return
     */

    public static <T> T getInstance(Object o, int i) {
        if (o.getClass().getGenericSuperclass() instanceof ParameterizedType) {
            Class mClass = (Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i];
            return getInstance(mClass);
        }
        return null;
    }

    /**
     * 通过实例工厂去实例化相应类
     *
     * @param <T> 返回实例的泛型类型
     * @return
     */

    public static <T> T getInstance(Class clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
