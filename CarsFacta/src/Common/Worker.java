package Common;

import Details.Accessory;
import Details.Body;
import Details.Engine;
import Stores.AccessoryStore;
import Stores.BodyStore;
import Stores.CarStore;
import Stores.EngineStore;

public class Worker implements Runnable {
    private AccessoryStore _accessoryStore;
    private BodyStore _bodyStore;
    private EngineStore _engineStore;
    private Factory _factory;
    private CarStore _targetStore;

    public Worker(AccessoryStore accessoryStore,
                  BodyStore bodyStore,
                  EngineStore engineStore,
                  Factory factory,
                  CarStore targetStore){
        _accessoryStore = accessoryStore;
        _bodyStore = bodyStore;
        _engineStore = engineStore;
        _factory = factory;
        _targetStore = targetStore;
    }

    private void Work(){
        _factory.GetTask();

        Engine engine = _engineStore.Extract();
        Accessory accessory = _accessoryStore.Extract();
        Body body = _bodyStore.Extract();

        Car car = new Car(engine,accessory,body);
        _targetStore.Push(car);
        Work();
    }

    @Override
    public void run() {
        Work();
    }
}
