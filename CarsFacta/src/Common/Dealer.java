package Common;

import Stores.CarStore;
import static java.lang.Thread.sleep;

public class Dealer implements Runnable {
    private int _delay;
    private CarStore _targetStore;
    private int _carsCount;

    public Dealer(int delay, CarStore targetStore){
        _delay = delay;
        _targetStore = targetStore;
        _carsCount = 0;
    }

    @Override
    public void run() {
        Buy();
    }

    private void Buy(){
        try {
            sleep(_delay);
            _targetStore.Extract();
            _carsCount++;
            Buy();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void SetDelay(int delay){
        _delay = delay;
    }
    public int Count(){return _carsCount;}
}
