package Stores;

import Details.Engine;

import java.util.Stack;

public class EngineStore {
    private Stack<Engine> _storage;
    private int _size;

    public EngineStore(int size){
        _size = size;
        _storage = new Stack<Engine>();
    }

    public synchronized void Push(Engine engine) {
        while(IsFull()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        _storage.add(engine);
        System.out.println("EngineStore pushed, count: " + _storage.size());
        if(IsFull()){
            System.out.println("EngineStore FULL");
        }
        notify();
    }

    public synchronized Engine Extract() {
        while (IsEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Engine engine = _storage.pop();
        System.out.println("EngineStore popped, count: " + _storage.size());
        if(IsEmpty()){
            System.out.println("EngineStore EMPTY");
        }
        notify();
        return engine;
    }

    private boolean IsFull() {
        return _storage.size() == _size;
    }
    private boolean IsEmpty(){
        return _storage.size() == 0;
    }
    public int Count(){return _storage.size();}
    public int Size(){return _size;}
}
