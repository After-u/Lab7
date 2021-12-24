package Controllers;

import Common.Dealer;
import Common.Factory;
import Common.Worker;
import Providers.AccessoryProvider;
import Providers.BodyProvider;
import Providers.EngineProvider;
import Stores.AccessoryStore;
import Stores.BodyStore;
import Stores.CarStore;
import Stores.EngineStore;

public class Controller {
    public AccessoryStore _accessoryStore;
    public BodyStore _bodyStore;
    public EngineStore _engineStore;
    public CarStore _carStore;

    public Worker[] _workers;
    public AccessoryProvider[] _accessoryProviders;
    public BodyProvider[] _bodyProviders;
    public EngineProvider[] _engineProviders;
    public Dealer[] _dealers;

    public Factory _factory;
    public CarStoreController _carStoreController;

    public void ChangeEngineProviderDelay(int delay){
        for (EngineProvider provider: _engineProviders) {
            provider.SetDelay(delay * 1000);
        }
    }

    public void ChangeBodyProviderDelay(int delay){
        for (BodyProvider provider: _bodyProviders) {
            provider.SetDelay(delay * 1000);
        }
    }

    public void ChangeAccessoryProviderDelay(int delay){
        for (AccessoryProvider provider: _accessoryProviders) {
            provider.SetDelay(delay * 1000);
        }
    }

    public void ChangeDealerDelay(int delay){
        for (Dealer dealer: _dealers) {
            dealer.SetDelay(delay * 1000);
        }
    }

    public int GetBodyStoreSize(){
        return _bodyStore.Size();
    }
    public int GetAccessoryStoreSize(){
        return _accessoryStore.Size();
    }
    public int GetCarStoreSize(){
        return _carStore.Size();
    }
    public int GetEngineStoreSize(){
        return _engineStore.Size();
    }

    public int GetBodyStoreCount(){
        return _bodyStore.Count();
    }
    public int GetAccessoryStoreCount(){
        return _accessoryStore.Count();
    }
    public int GetCarStoreCount(){
        return _carStore.Count();
    }
    public int GetEngineStoreCount(){
        return _engineStore.Count();
    }

    public int GetBodyProvidersCount(){
        int count = 0;
        for (BodyProvider provider: _bodyProviders) {
            count+= provider.Count();
        }
        return count;
    }
    public int GetEngineProvidersCount(){
        int count = 0;
        for (EngineProvider provider: _engineProviders) {
            count+= provider.Count();
        }
        return count;
    }
    public int GetAccessoryProvidersCount(){
        int count = 0;
        for (AccessoryProvider provider: _accessoryProviders) {
            count+= provider.Count();
        }
        return count;
    }
    public int GetDealerCount(){
        int count = 0;
        for (Dealer dealer: _dealers) {
            count+= dealer.Count();
        }
        return count;
    }

    public int GetTaskCount(){
        return _factory.Count();
    }
}
