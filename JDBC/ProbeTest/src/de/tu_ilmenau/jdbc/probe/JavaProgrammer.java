package de.tu_ilmenau.jdbc.probe;

import java.util.ResourceBundle;

public class JavaProgrammer {
    public static void main(String[] args) throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("Info");
        String className = rb.getString("className");
//        System.out.println(className);
        Class c = Class.forName("de.tu_ilmenau.jdbc.probe." + className); //<---注意class的类名
        JDBC jdbc = (JDBC)c.newInstance();
        jdbc.getConnection();
    }
}
