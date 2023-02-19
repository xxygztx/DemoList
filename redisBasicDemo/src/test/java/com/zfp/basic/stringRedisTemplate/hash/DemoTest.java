package com.zfp.basic.stringRedisTemplate.hash;

import com.zfp.basic.entity.Student;
import com.zfp.basic.untils.ForeachObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
class DemoTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private HashDemo hashDemo;

    @Test
    public void testPut(){
        stringRedisTemplate.opsForHash().put("hash","name","lisi");
        stringRedisTemplate.opsForHash().put("hash","age","2");
    }
    final String key ="hash:student:";

    @Test
    public void testHash(){
        Student student = new Student();
        student.setName("lisi");
        student.setAge(23);
        student.setClassId(1L);
        student.setTeacherId(45L);
        student.setClassId(2L);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        hashDemo.set(key+"1",student);

        Student student1 = hashDemo.get(key + "1", Student.class);
        System.out.println(student1);
    }


}