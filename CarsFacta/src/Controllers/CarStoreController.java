package Controllers;

import Common.Factory;
import Stores.CarStore;

public class CarStoreController {
    private Factory _factory;

    public CarStoreController(Factory factory){
        _factory = factory;
    }

    public void OrderCar(int n){
        for (int i=0;i<n;i++){
            _factory.AddTask();
        }
    }

    public void OrderCar(){
        _factory.AddTask();
    }
}
