package org.example;

public class PhoneContact {
    private String name;
    private String number;

    public PhoneContact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "PhoneContact{" +
               "name='" + name + '\'' +
               ", number='" + number + '\'' +
               '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
