package View;

import Controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{
    private Controller _controller;

    public View(Controller controller){
        _controller = controller;
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        DrawScheme(g);
    }

    private void DrawScheme(Graphics g){
        g.setColor(Color.RED);

        //providers
        g.fillRect(50,50,50,50);
        g.fillRect(50,150,50,50);
        g.fillRect(50,250,50,50);

        //stores
        g.fillRect(250,50,50,50);
        g.fillRect(250,150,50,50);
        g.fillRect(250,250,50,50);

        //factory
        g.fillRect(450,150,50,50);

        //car store
        g.fillRect(650,150,50,50);

        //Dealers
        g.fillRect(850,150,50,50);

        //connections
        {
            g.setColor(Color.black);

            g.drawLine(100, 75, 250, 75);
            g.drawLine(100, 175, 250, 175);
            g.drawLine(100, 275, 250, 275);

            g.drawLine(300, 75, 450, 175);
            g.drawLine(300, 175, 450, 175);
            g.drawLine(300, 275, 450, 175);

            g.drawLine(500, 175, 650, 175);

            g.drawLine(700, 175, 850, 175);
        }

        //names
        g.drawString("BodyProviders",50,50);
        g.drawString("EngineProviders",50,150);
        g.drawString("AccessoryProviders",50,250);

        g.drawString("BodyStore",250,50);
        g.drawString("EngineStore",250,150);
        g.drawString("AccessoryStore",250,250);

        g.drawString("Factory",450,150);
        g.drawString("CarStore",650,150);
        g.drawString("Dealers",850,150);

        //store counts and maxes
        g.drawString("Count: " + _controller.GetBodyStoreCount(),250,60);
        g.drawString("Max: " + _controller.GetBodyStoreSize(),250,70);

        g.drawString("Count: " + _controller.GetEngineStoreCount(),250,160);
        g.drawString("Max: " + _controller.GetEngineStoreSize(),250,170);

        g.drawString("Count: " + _controller.GetAccessoryStoreCount(),250,260);
        g.drawString("Max: " + _controller.GetAccessoryStoreSize(),250,270);

        g.drawString("Count: " + _controller.GetCarStoreCount(),650,160);
        g.drawString("Max: " + _controller.GetCarStoreSize(),650,170);

        //providers and dealers counts
        g.drawString("Count: " + _controller.GetBodyProvidersCount(),50,60);
        g.drawString("Count: " + _controller.GetEngineProvidersCount(),50,160);
        g.drawString("Count: " + _controller.GetAccessoryProvidersCount(),50,260);
        g.drawString("Count: " + _controller.GetDealerCount(),850,160);

        //factory
        g.drawString("Task count: " + _controller.GetTaskCount(),450,160);
        g.drawString("Cars done count: " + (_controller.GetCarStoreCount()+_controller.GetDealerCount()),450,170);
    }
}
