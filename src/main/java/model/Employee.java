package model;

public class Employee {
    private String username;
    private double baseSalary;
    private double hra;
    private double da;

    public double getDa() {
        return da;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getHra() {
        return hra;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }

    public void setDa(double da) {
        this.da = da;
    }

}

