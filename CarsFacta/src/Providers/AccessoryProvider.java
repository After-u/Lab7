package Providers;

import Details.Accessory;
import Stores.AccessoryStore;

import static java.lang.Thread.sleep;

public class AccessoryProvider implements Runnable {
    private int _delay;
    private AccessoryStore _targetStore;
    private int _providingCount;

    public AccessoryProvider(int delay, AccessoryStore targetStore){
        _delay = delay;
        _targetStore = targetStore;
        _providingCount = 0;
    }

    public void run(){
        Provide();
    }

    private void Provide(){
        try {
            sleep(_delay);
            Accessory accessory = new Accessory();
            _targetStore.Push(accessory);
            _providingCount++;
            Provide();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void SetDelay(int delay){
        _delay = delay;
    }
    public int Count(){return _providingCount;}
}
