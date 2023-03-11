package de.tu_ilmenau.jdbc.probe;

public class SQLServer implements JDBC{
    @Override
    public void getConnection() {
        System.out.println("link SQLServer success!");
    }
}
