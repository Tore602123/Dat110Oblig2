package no.hvl.dat110.iotsystem;


import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;


public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		

		Client client = new Client("Display device", "localhost", 8080);
		client.connect();
		client.createTopic("Temperature");
		client.subscribe("Temperature");
		for(int i = 0; i<COUNT; i++){
			Message msg = client.receive();
			System.out.println(msg);
		}
		client.disconnect();
		System.out.println("Display stopping ... ");
		
		
	}
}
