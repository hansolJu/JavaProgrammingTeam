package main;

import controller.Controller;
import model.Model;
import view.GUI;

public class Main {
	public static void main(String[] args) {
		GUI g = new GUI("IDE");
		Controller c = new Controller(g);
		c.initController();
	}
}
