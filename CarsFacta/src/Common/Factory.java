package Common;

public class Factory {
    private int _taskCount;

    public Factory(){
        _taskCount = 0;
    }

    public synchronized void GetTask(){
        while (_taskCount == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        _taskCount--;
    }

    public synchronized void AddTask(){
        _taskCount++;
        notify();
    }

    public int Count(){return _taskCount;}
}
