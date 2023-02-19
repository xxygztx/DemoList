package com.zfp.basic.untils;

import com.zfp.basic.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.zfp.basic.untils.ForeachObject.*;


@SpringBootTest
class ForeachObjectTest {

    @Test
    public void test(){
        ArrayList<String> allField = getAllField(new Student());
        allField.stream().forEach(System.out::println);

        Student student = new Student();
        student.setName("lisi");
        student.setAge(23);
        student.setClassId(1L);
        student.setTeacherId(45L);
        student.setClassId(2L);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        Map<String, String> map = objectToMap(student);
        for(Map.Entry<String, String> s:map.entrySet()){
            System.out.println(s.getKey());
            System.out.println(s.getValue());
        }
        HashMap maps = new HashMap();
        maps.put("lsii","dfsd");
        Student student1 = mapToObject(maps, Student.class);
        System.out.println(student1);
    }

}