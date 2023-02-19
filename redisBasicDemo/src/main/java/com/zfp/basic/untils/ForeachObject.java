package com.zfp.basic.untils;

import com.zfp.basic.entity.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ForeachObject {
    /**
     * 获取对象中所有的key
     * @param object
     * @param <T>
     * @return
     */
    public static<T> ArrayList<String> getAllField(T object) {
        //获取Class类，并获取所有的字段名称信息数组
        Field[] fields = object.getClass().getDeclaredFields();
        ArrayList<String> keys = new ArrayList<>();
        int i=0;
        for(Field field:fields) {
            String keyName = field.getName();
            keys.add(keyName);
        }
        return keys;
    }

    /**获取对象中所有的key和value并放入Map集合中,
     * key 和 value 全部转为String
     *
     * @param object
     * @param <T>
     * @return
     */
    public static<T> Map<String,String> objectToMap(T object){
        HashMap<String,String> result =new HashMap<>();
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                //设置可以通过反射访问私有变量
                field.setAccessible(true);
                String keyName = field.getName();
                //获取属性的值
                Object keyValue = field.get(object);
                String value= keyValue==null?null:keyValue.toString();
                result.put(keyName,value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     *将map转化为 对象，前提是我们的对象没有基本类型，将我们的String
     * 这个方法是有问题，我们的无法将String 类型转化Wie LocalDateTime
     * @param map
     * @param target
     * @param <T>
     * @return
     */
    public static<T> T mapToObject(Map map ,Class<T> target){
        T t = null;
        try{
            Field[] fields = target.getDeclaredFields();
            //通过反射获取对象
             t = target.newInstance();
            for(Field field:fields){
                field.setAccessible(true);
                //获取每个字段的类型
                Class<?> typeClass = field.getType();
                System.out.println(typeClass);
                Object o = map.get(field.getName());
                //将我们的String类型转化为指定的类型
                Object val = getVal((String) o, typeClass);
                //将给我们的字段设置 所属的对象， 和添加的value。
                field.set(t,val);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }
    /**
     * 将map转化为Object，这个方法的前提是我们的 存储在map中的value的类型和 对象中 字段的value是一样的。
     * */
    public static<T> T mapToObjects(Map<String ,Object> map, Class<T> target){
        Field[] fields = target.getDeclaredFields();
        T t= null;
        try {
             t = target.newInstance();
            for(Field field:fields){
                field.setAccessible(true);
                String name = field.getName();
                Object o = map.get(name);
                field.set(t,o);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T getVal(String val, Class<T> type) {
        // 把val转换成type类型返回 比如说getVal("123",Integer.class) 返回一个123
        T value = null;
        try {
            Constructor<T> constructor = type.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            value = constructor.newInstance(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
