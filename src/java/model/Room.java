/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phangiabao
 */
public class Room {
    private int roomId;
    private String name;
    private String type;
    private int price;
    private int slot;

    public Room() {
    }

    public Room(int roomId, String name, String type, int price) {
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Room(int roomId, String name, String type, int price, int slot) {
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.slot = slot;
    }
    

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return roomId +"";
    }

    
    
}
