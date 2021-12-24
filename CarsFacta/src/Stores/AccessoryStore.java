package Stores;

import Details.Accessory;
import Details.Body;
import Details.Detail;
import Details.Engine;

import java.util.Stack;

public class AccessoryStore {
    private Stack<Accessory> _storage;
    private int _size;

    public AccessoryStore(int size){
        _size = size;
        _storage = new Stack<Accessory>();
    }

    public synchronized void Push(Accessory accessory) {
        while (IsFull()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        _storage.add(accessory);
        System.out.println("AccessoryStore pushed, count: " + _storage.size());
        if(IsFull()){
            System.out.println("AccessoryStore FULL");
        }
        notify();
    }

    public synchronized Accessory Extract() {
        while (IsEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Accessory accessory = _storage.pop();
        System.out.println("AccessoryStore popped, count: " + _storage.size());
        if(IsEmpty()){
            System.out.println("AccessoryStore EMPTY");
        }
        notify();
        return accessory;
    }

    private boolean IsFull() {
        return _storage.size()== _size;
    }
    private boolean IsEmpty(){
        return _storage.size() == 0;
    }
    public int Count(){return _storage.size();}
    public int Size(){return _size;}
}
