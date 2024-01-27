package net.martinburger.sesqa.programming;

public class TextInterface {
	City city;
	
	public void mainMenu() {
		System.out.println("===== MAIN MENU =====");
		System.out.println("1. NEW GAME");
		System.out.println("2. QUIT");
		System.out.println("Please select an option:");
	}
	
	public void gameMenu() {
		System.out.println("1. BUY");
		System.out.println("2. SELL");
		System.out.println("3. FEED");
		System.out.println("4. PLANT");
		System.out.println("5. SHOW STATUS");
		System.out.println("6. QUIT GAME");
	}
	
	public void buyMenu () {
	System.out.println("===== BUY MENU =====");
	System.out.println("City Status:" + city.getAcres() + "acres of land" + city.getBushels() +"of grain"+  city.getPopulation()
			+ "Current price"+ city.getAcres());
	System.out.println("How many acres would you like to buy?" +"<42");
	System.out.println("New Status" +  city.getAcres()+ "of land"+ city.getBushels());
			
	}
	
	public void sellMenu () {
		System.out.println("===== SELL MENU =====");
		System.out.println("City Status:" + city.getAcres()+"of land" +  city.getBushels()+ "of grain"+ city.getPopulation()+
				 "Current price"+ city.getAcres());
		System.out.println("How many acres would you like to sell?"+ "<42");
		System.out.println("New Status:" +city.getAcres() +"of land"+ city.getBushels()+ "of grain"+ city.getPopulation() );
		
	}
	
	public void feedMenu() {
		System.out.println("===== FEED MENU =====");
		System.out.println("City Status:" + city.getAcres()+"of land" +  city.getBushels()+ "of grain"+ city.getPopulation());
		System.out.println("How many bushels do you would like to feed to your residents?"+ "<42");
		System.out.println("New Status:" +city.getAcres() +"of land"+ city.getBushels()+ "of grain"+ city.getPopulation() );
		
	}
	
	public void plantMenu() {
		System.out.println("===== PLANT MENU =====");
		System.out.println("City Status:" + city.getAcres()+"of land" +  city.getBushels()+ "of grain"+ city.getPopulation());
		System.out.println("How many acres of land do you wish to plant with seed?"
		+ "<42");
		System.out.println("New Status:" +city.getAcres() +"of land"+ city.getBushels()+ "of grain"+ city.getPopulation() );

	}
	
	public void statusMenu() {
		System.out.println("===== STATUS MENU =====");
		System.out.println("City Status:" + city.getAcres()+"of land" +  city.getBushels()+ "of grain"+ city.getPopulation());
		
	}
	
}
