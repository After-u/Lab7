package Common;

public class IdGiver {
    private static int _currentId = 0;

    public static int Get(){
        _currentId++;
        return _currentId;
    }
}
