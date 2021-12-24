package Details;

import Common.IdGiver;

public class Body extends Detail{
    public Body(){
        _id = IdGiver.Get();
        //System.out.println(this + "Created, id = " + _id);
    }
}
