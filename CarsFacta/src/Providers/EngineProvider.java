package Providers;

import Details.Engine;
import Stores.EngineStore;

import static java.lang.Thread.sleep;

public class EngineProvider implements Runnable {
    private int _delay;
    private EngineStore _targetStore;
    private int _providingCount;

    public EngineProvider(int delay, EngineStore targetStore){
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
            Engine engine = new Engine();
            _targetStore.Push(engine);
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
