package Stores;

import Details.Body;
import Details.Detail;
import Details.Engine;

import java.util.Stack;

public class BodyStore{
    private Stack<Body> _storage;
    private int _size;

    public BodyStore(int size){
        _size = size;
        _storage = new Stack<Body>();
    }

    public synchronized void Push(Body body) {
        while(IsFull()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        _storage.add(body);
        System.out.println("BodyStore pushed, count: " + _storage.size());
        if(IsFull()){
            System.out.println("BodyStore FULL");
        }
        notify();
    }

    public synchronized Body Extract() {
        while (IsEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Body body = _storage.pop();
        System.out.println("BodyStore popped, count: " + _storage.size());
        if(IsEmpty()){
            System.out.println("BodyStore EMPTY");
        }
        notify();
        return body;
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
