package Providers;

import Details.Body;
import Stores.BodyStore;

import static java.lang.Thread.sleep;

public class BodyProvider implements Runnable {
    private int _delay;
    private BodyStore _targetStore;
    private int _providingCount;

    public BodyProvider(int delay, BodyStore targetStore){
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
            Body body = new Body();
            _targetStore.Push(body);
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
