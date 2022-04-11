package com.lab.lab9.models;

public class Laptop {
    private int ID;
    private String Name;
    private int RAM;
    private int Price;
    private String Image;

    public Laptop(int ID, String name, int RAM, int price, String image) {
        this.ID = ID;
        Name = name;
        this.RAM = RAM;
        Price = price;
        this.Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", RAM=" + RAM +
                ", Price=" + Price +
                ", Image='" + Image + '\'' +
                '}';
    }
}
