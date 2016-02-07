/**
 * @author Alex Orozco
 * UW TCSS341 Data Structures
 */

public class Burger {
	private String[] burgerToppings = { //used to initialize the burger.
		"Pickle",
		"Bun",
		"Mayonnaise",
		"Baron-Sauce",
		"Lettuce",
		"Tomato",
		"Onions",
		"Pepperjack",
		"Mozzarella",
		"Cheddar",
		"Patty",
		"Mushrooms",
		"Mustard",
		"Ketchup",
		"Bun"
	};
	private PattyType myPattyType;
	private MyStack burger;
	private MyStack burgerTemp;
	private MyStack order;
	private MyStack orderTemp;
	private int pattyCount;
	public Burger (boolean theWorks) {
		burger = new MyStack();
		burgerTemp = new MyStack();
		order = new MyStack();
		orderTemp = new MyStack();
		pattyCount = 1;
		myPattyType = PattyType.Beef;
		for(int i = burgerToppings.length-1; i >= 0; i--) //initialize the burger order stack
			burger.push(burgerToppings[i]);
		if (theWorks) //determine which burger to start with.
			for(int i = burgerToppings.length-1; i >= 0; i--)
				order.push(burgerToppings[i]);
		 else 
			for(int i = burgerToppings.length-1; i > 0; i--)
				if (burgerToppings[i] == "Bun" || burgerToppings[i] == "Patty" )
					order.push(burgerToppings[i]);
		
	}
	public Burger () {
		burger = new MyStack();
		burgerTemp = new MyStack();
		order = new MyStack();
		orderTemp = new MyStack();
		pattyCount = 1;
		myPattyType = PattyType.Beef;
		for(int i = burgerToppings.length-1; i >= 0; i--) //initialize the burger order stack
			burger.push(burgerToppings[i]); 
		for(int i = burgerToppings.length-1; i > 0; i--)
			if (burgerToppings[i] == "Bun" || burgerToppings[i] == "Patty" )
				order.push(burgerToppings[i]);
		
	}
	public void changePatties(String pattyType) 
	{
		for( PattyType temp: PattyType.values())
		{
			if(pattyType.equalsIgnoreCase(temp.toString()))
				myPattyType = temp;
		}
	}
	public void addPatty() { //we need to adjust our comparison model since a patty was added.
		pattyCount++;
		while(!burger.peek().equalsIgnoreCase("Patty") && !CheeseType.contains(burger.peek()))
			burgerTemp.push(burger.pop());
		burger.push("Patty");
		this.resetBurger();
		this.addIngredient("Patty");
	}
	public void addCategory(String type)
	{
		if (type.equalsIgnoreCase("Sauce"))
		{
			for( Sauces temp: Sauces.values())
			{
				if(temp == Sauces.BaronSauce)
					this.addIngredient("Baron-Sauce");
				else
					this.addIngredient(temp.toString());
				
			}
		} 
		else if (type.equalsIgnoreCase("Veggies"))
		{
			for( Veggies temp: Veggies.values())
			{
				this.addIngredient(temp.toString());
			}
		}
		else if (type.equalsIgnoreCase("Cheese"))
		{
			for( CheeseType temp: CheeseType.values())
			{
				this.addIngredient(temp.toString());
			}
		}
		
	}
	public void removeCategory(String type){
		if (type.equalsIgnoreCase("Sauce"))
		{
			for( Sauces temp: Sauces.values())
			{
				if(temp == Sauces.BaronSauce)
					this.removeIngredient("Baron-Sauce");
				else
					this.removeIngredient(temp.toString());
			}
		} 
		else if (type.equalsIgnoreCase("Veggies"))
		{
			for( Veggies temp: Veggies.values())
			{
				this.removeIngredient(temp.toString());
			}
		}
		else if (type.equalsIgnoreCase("Cheese"))
		{
			for( CheeseType temp: CheeseType.values())
			{
				this.removeIngredient(temp.toString());
			}
		}
	}
	//TODO must finish adding in appropriate spot.
	public void addIngredient(String type) 
	{
		while(!burger.peek().equalsIgnoreCase(type)) //loop until we find where the item we want to add is found. //n
		{
			if(order.peek().equalsIgnoreCase(burger.peek()))
			{
				orderTemp.push(order.pop());
			}
			burgerTemp.push(burger.pop());
		}
		order.push(type);
		this.resetOrder();
		this.resetBurger();
		
	}
	public void removeIngredient(String type) 
	{
		if(type.equalsIgnoreCase("Patty") && pattyCount > 1)
		{
			pattyCount--;
			while(!burger.peek().equalsIgnoreCase(type) && !burger.isEmpty()) //dig until we find the ingredient 
				burgerTemp.push(burger.pop());//c
			burger.pop();//c
			this.resetBurger();
		}
		while(!order.peek().equalsIgnoreCase(type) && !order.isEmpty()) //dig until we find the ingredient 
			orderTemp.push(order.pop());//c
		order.pop();//c
		this.resetOrder();
		
	}
	public String toString() 
	{
		StringBuilder temp = new StringBuilder(); //c
		while(!order.isEmpty()) //n
		{
			orderTemp.push(order.pop());//c
			if(!orderTemp.peek().equalsIgnoreCase("Patty"))
				temp.append(orderTemp.peek());
			else
			{
				temp.append(myPattyType);
			}
			if (!order.peek().equalsIgnoreCase("Stack is empty"))
				temp.append(", "); //add visual features. 
		}
		this.resetOrder();
		return temp.toString(); //c
	}
	private void resetOrder()
	{
		while( !orderTemp.isEmpty()) //push the other ingredients on //n
			order.push(orderTemp.pop()); //c
	}
	private void resetBurger()
	{
		while( !burgerTemp.isEmpty()) //push the other ingredients on //n
			burger.push(burgerTemp.pop()); //c
	}
	private enum Veggies {
		Pickle,
		Lettuce,
		Tomato,
		Onions,
		Mushrooms;
	}
	private enum Sauces {
		Mayonnaise,
		BaronSauce,
		Mustard,
		Ketchup;
	}
	private enum CheeseType {
		Pepperjack,
		Mozzarella,
		Cheddar;
		
		public static boolean contains(String test) {
		    for (CheeseType c : CheeseType.values()) 
		        if (c.name().equals(test)) 
		            return true;
		    return false;
		}
	}
	public enum PattyType {
		Beef,
		Chicken,
		Veggie;
	}
}
