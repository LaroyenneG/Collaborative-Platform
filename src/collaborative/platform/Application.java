package collaborative.platform;

import collaborative.platform.container.MainContainer;
import jade.wrapper.ControllerException;

import java.util.HashMap;

public class Application {

    public static void main(String[] args) {

        try {
            MainContainer mainContainer = new MainContainer(new HashMap<>());

            mainContainer.start();
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }
}
