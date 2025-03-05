package com.example.multiprocesstest.BasicGrammer;

import java.io.*;

class Location implements  Cloneable, Serializable {
    String room;
    Address address;

    public Location(String room, Address address) {
        this.room = room;
        this.address = address;
    }
    @Override
    public Object clone() {
        Location location = null;
        try {
            location = (Location) super.clone();
            location.address = (Address) address.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return location;
    }

    public Location deepcopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(bis);
        return (Location) objectInputStream.readObject();
    }

}

class Address extends Object implements Cloneable{
    String city;
    String street;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}

public class DeepAndShallowCopy {

    static public void testDeepCopy() {
        Address address = new Address("shanghai", "nanjing road");
        Location location = new Location("room1", address);
        Location location2 = (Location) location.clone();
        location2.address.city = "beijing";
        location2.address.street = "wangfujing";
        System.out.println(location.address.city);
        System.out.println(location.address.street);
    }
    public static void main(String[] args) {
        testDeepCopy();
    }
}
