package apps.main;

import apps.controller.Controller;
import apps.model.Model;
import apps.view.View;

public class App {
	public static void main(String[] args) {
		Model m = Model.getInstance();
		View v = new View("IDE");
		Controller c = new Controller(m,v);
		c.initController();
		
	}

}
