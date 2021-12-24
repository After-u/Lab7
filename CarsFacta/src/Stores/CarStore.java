package Stores;

import Common.Car;
import Controllers.CarStoreController;

import java.util.Stack;

public class CarStore{
    private Stack<Car> _storage;
    private int _size;
    private CarStoreController _controller;

    public CarStore(int size, CarStoreController controller){
        _size = size;
        _storage = new Stack<Car>();
        _controller = controller;
        _controller.OrderCar(_size);
    }

    public synchronized void Push(Car car) {
        while(IsFull()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        _storage.add(car);
        notify();
        System.out.println("CarStore pushed, count: " + _storage.size());
        if(IsFull()){
            System.out.println("CarStore FULL");
        }
    }

    public synchronized Car Extract() {
        while (IsEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Car car = _storage.pop();
        _controller.OrderCar();
        System.out.println("CarStore popped, count: " + _storage.size());
        if(IsEmpty()){
            System.out.println("CarStore EMPTY");
        }
        notify();
        return car;
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
