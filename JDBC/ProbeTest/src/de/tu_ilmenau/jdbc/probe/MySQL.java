package de.tu_ilmenau.jdbc.probe;

public class MySQL implements JDBC{
    @Override
    public void getConnection() {
        System.out.println("link MySQL success!");
    }
}
