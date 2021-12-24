package Details;

import Common.IdGiver;

public class Accessory extends Detail {
    public Accessory(){
        _id = IdGiver.Get();
        //System.out.println(this + "Created, id = " + _id);
    }
}
