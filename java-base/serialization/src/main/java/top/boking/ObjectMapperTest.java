package top.boking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public class ObjectMapperTest {
    public static void main(String[] args) throws IOException {
        JavaBean javaBean = new JavaBean();
        javaBean.setName("boking");
        javaBean.setAge(18);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer();
        writer.writeValue(new File("a.txt"),javaBean);
        ObjectReader reader = objectMapper.reader();
        JavaBean javaBean1 = reader.readValue(new File("a.txt"), JavaBean.class);
        System.out.println("javaBean1 = " + javaBean1);

    }

    public static class JavaBean {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
