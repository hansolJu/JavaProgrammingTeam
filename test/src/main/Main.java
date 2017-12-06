package main;

import controller.Controller;
import model.Model;
import view.GUI;

public class Main {
	public static void main(String[] args) {
		Model m = Model.getInstance();
		GUI v = new GUI("IDE");
		Controller c = new Controller(m,v);
		c.initController();
	}
}
