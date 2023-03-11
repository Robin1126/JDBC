package de.tu_ilmenau.jdbc.probe;

public class Oracle implements JDBC{
    @Override
    public void getConnection() {
        System.out.println("link Oracle success!");
    }
}
