package Common;
import Details.Accessory;
import Details.Body;
import Details.Engine;


public class Car {
    private Engine _engine;
    private Accessory _accessory;
    private Body _body;

    private int _id;

    public Car(Engine engine, Accessory accessory, Body body){
        _engine = engine;
        _accessory = accessory;
        _body = body;
        _id = IdGiver.Get();
    }

    public int getId(){return _id;}
}
