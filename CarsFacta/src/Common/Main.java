package Common;

import Controllers.CarStoreController;
import Controllers.Controller;
import Providers.AccessoryProvider;
import Providers.BodyProvider;
import Providers.EngineProvider;
import Stores.AccessoryStore;
import Stores.BodyStore;
import Stores.CarStore;
import Stores.EngineStore;
import View.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        validate();
        repaint();
    }

    private static class ConfigData{
        public boolean isSuccess;
        public int[] Sizes;

        public ConfigData(){
            Sizes = new int[9];
        }
    }

    private static Controller _controller;
    private static View _view;

    private static EngineStore _engineStore;
    private static BodyStore _bodyStore;
    private static AccessoryStore _accessoryStore;
    private static CarStore _carStore;

    private static EngineProvider[] _engineProviders;
    private static BodyProvider[] _bodyProviders;
    private static AccessoryProvider[] _accessoryProviders;
    private static Dealer[] _dealers;
    private static Worker[] _workers;

    private static Factory _factory;
    private static CarStoreController _carStoreController;

    private static Timer _timer;
    private static ArrayList<Thread> _threads;

    Main(){
        _controller = new Controller();
        _view = new View(_controller);
        _timer = new Timer(50,this);
        _timer.start();
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                for (Thread thread: _threads) {
                    thread.stop();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        setTitle("Fabric");
        setLayout(new GridLayout(0,1,5,5));
        setSize(1000, 1000);

        JPanel slidersPanel = new JPanel(new GridLayout(0,2,5,10));

        JLabel engineProvLabel = new JLabel("Engine Provider Delay");
        JLabel bodyProvLabel = new JLabel("Body Provider Delay");
        JLabel accessoryProvLabel = new JLabel("Accessory Provider Delay");
        JLabel dealerProvLabel = new JLabel("Dealer Delay");

        JSlider engineProviderDelay = new JSlider(1,20,4);
        JSlider bodyProviderDelay = new JSlider(1,20,5);
        JSlider accessoryProviderDelay = new JSlider(1,20,6);
        JSlider dealerDelay = new JSlider(1,20,7);

        engineProviderDelay.setPaintLabels(true);
        engineProviderDelay.setMajorTickSpacing(2);
        engineProviderDelay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                _controller.ChangeEngineProviderDelay(value);
            }
        });

        bodyProviderDelay.setPaintLabels(true);
        bodyProviderDelay.setMajorTickSpacing(2);
        bodyProviderDelay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                _controller.ChangeBodyProviderDelay(value);
            }
        });

        accessoryProviderDelay.setPaintLabels(true);
        accessoryProviderDelay.setMajorTickSpacing(2);
        accessoryProviderDelay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                _controller.ChangeAccessoryProviderDelay(value);
            }
        });

        dealerDelay.setPaintLabels(true);
        dealerDelay.setMajorTickSpacing(2);
        dealerDelay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                _controller.ChangeDealerDelay(value);
            }
        });

        slidersPanel.add(engineProvLabel);
        slidersPanel.add(engineProviderDelay);

        slidersPanel.add(bodyProvLabel);
        slidersPanel.add(bodyProviderDelay);

        slidersPanel.add(accessoryProvLabel);
        slidersPanel.add(accessoryProviderDelay);

        slidersPanel.add(dealerProvLabel);
        slidersPanel.add(dealerDelay);

        add(slidersPanel);
        add(_view);

        _view.repaint();
        _view.revalidate();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args){
        Main main = new Main();
        _view.repaint();
        _view.revalidate();

        ConfigData config = ReadConfig("src\\config.txt");
        if(!config.isSuccess){
            return;
        }

        Initialize(config);
        LinkWithController();
        RunThreads();
    }

    private static void LinkWithController(){
        _controller._bodyProviders = _bodyProviders;
        _controller._dealers = _dealers;
        _controller._engineProviders = _engineProviders;
        _controller._accessoryProviders = _accessoryProviders;
        _controller._workers = _workers;

        _controller._bodyStore = _bodyStore;
        _controller._engineStore = _engineStore;
        _controller._carStore = _carStore;
        _controller._accessoryStore = _accessoryStore;

        _controller._factory = _factory;
        _controller._carStoreController = _carStoreController;
    }

    private static void Initialize(ConfigData config){
        _bodyStore = new BodyStore(config.Sizes[0]);
        _engineStore = new EngineStore(config.Sizes[1]);
        _accessoryStore = new AccessoryStore(config.Sizes[2]);

        _factory = new Factory();
        _carStoreController = new CarStoreController(_factory);
        _carStore = new CarStore(config.Sizes[3], _carStoreController);

        _accessoryProviders = new AccessoryProvider[config.Sizes[4]];
        for (int i=0;i<_accessoryProviders.length;i++){
            _accessoryProviders[i] = new AccessoryProvider(6000,_accessoryStore);
        }

        _bodyProviders = new BodyProvider[config.Sizes[5]];
        for (int i=0;i<_bodyProviders.length;i++){
            _bodyProviders[i] = new BodyProvider(5000,_bodyStore);
        }

        _engineProviders = new EngineProvider[config.Sizes[6]];
        for (int i=0;i<_engineProviders.length;i++){
            _engineProviders[i] = new EngineProvider(4000,_engineStore);
        }

        _workers = new Worker[config.Sizes[8]];
        for (int i=0;i<_workers.length;i++){
            _workers[i] = new Worker(_accessoryStore,_bodyStore,_engineStore,_factory,_carStore);
        }

        _dealers = new Dealer[config.Sizes[8]];
        for (int i=0;i<_dealers.length;i++){
            _dealers[i] = new Dealer(7000,_carStore);
        }
    }

    private static void RunThreads(){
        _threads = new ArrayList<Thread>();
        for (int i=0;i<_accessoryProviders.length;i++){
            _threads.add(new Thread(_accessoryProviders[i]));
        }
        for (int i=0;i<_bodyProviders.length;i++){
            _threads.add(new Thread(_bodyProviders[i]));
        }
        for (int i=0;i<_engineProviders.length;i++){
            _threads.add(new Thread(_engineProviders[i]));
        }
        for (int i=0;i<_dealers.length;i++){
            _threads.add(new Thread(_dealers[i]));
        }
        for (int i=0;i<_workers.length;i++){
            _threads.add(new Thread(_workers[i]));
        }

        for (Thread thr: _threads) {
            thr.start();
        }
    }

    private static ConfigData ReadConfig(String configFile){
        BufferedReader reader;
        ConfigData configData = new ConfigData();
        try {
            reader = new BufferedReader(new FileReader(configFile));
            String line;
            int currentN = 0;
            while ((line = reader.readLine()) != null) {
                line.trim();
                String[] args = line.split("=");
                configData.Sizes[currentN] = Integer.parseInt(args[1]);
                currentN++;
            }
            configData.isSuccess = true;
        } catch (Exception exception) {
            exception.printStackTrace();
            configData.isSuccess = false;
        }
        return configData;
    }
}
