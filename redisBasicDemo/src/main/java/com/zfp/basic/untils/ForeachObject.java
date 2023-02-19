package com.zfp.basic.untils;

import com.zfp.basic.entity.Student;

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
     *将map转化为 对象，前提是我们的 map 的value值的类型和 target对象中的类型是一样的
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
                field.set(t,map.get(field.getName()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }
}
