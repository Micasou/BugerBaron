import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Alex Orozco
 * UW TCSS341 Data Structures
 */
public class Main {
	 private Main() {
	        throw new IllegalStateException();
	 }
	 private static String theOrder;
	public static void main(final String[] theArgs) throws IOException {
		int order = 1;
		//Stack Overflow : 5868369
		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(1);
		out.close();
		File file = new File( "../BurgerBaronDataStructuresA1/src/test.txt");
		FileInputStream fstream = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
				System.out.println("Processing Order "+order +": "+strLine);
				System.out.print("[");
				parseLine(strLine);
				System.out.print("]");
				System.out.println("\n ");
			    order++;
			}
		br.close();
		//testMyStack();
		//testBurger();
		//testParseLine();
	}
	private static void parseLine(String line) 
	{
		Burger burgerOrder = null;
		String order[] = line.split(" ");
		int pattyNumber = 0;
		int pattyType = 0;
		String groups[] = {"Veggies","Sauce", "Cheese"}; //the groups to compare to
		boolean baronBurger = false;
		boolean without= false;
		boolean but = false;
		boolean foundWith = false;
		for (String i: order)
		{
			if (foundWith)
			{
				if( i.equalsIgnoreCase("No"))
					without = true;
				else if(i.equalsIgnoreCase("but"))
					but = true;
				else if(stringInArray(groups,i) && !without && !but) //we found with, we are to include categories
					burgerOrder.addCategory(i);
				else if(stringInArray(groups,i) && without && !but) //we found with, we are to remove categories
					burgerOrder.removeCategory(i);
				else if(without && !but) //remove ingredient, not at but yet
					burgerOrder.removeIngredient(i);
				else if(!without && !but) // add ingredient, not at but yet
					burgerOrder.addIngredient(i);
				else if(without && but)  //add ingredient, post but 
					burgerOrder.addIngredient(i);
				else  if(!without && but) //remove ingredient, post but 
					burgerOrder.removeIngredient(i);
			}
			else
			{
				if ( i.equalsIgnoreCase("Double"))
					pattyNumber = 1;
				else if(i.equalsIgnoreCase("Triple"))
					pattyNumber = 2;
				else if ( i.equalsIgnoreCase("Baron"))
					baronBurger = true;
				else if ( i.equalsIgnoreCase("With"))
					foundWith = true;
				else if ( i.equalsIgnoreCase("Burger"))
					burgerOrder = new Burger(baronBurger);
				else if(i.equalsIgnoreCase("Chicken"))
					pattyType = 1;
				else if(i.equalsIgnoreCase("Veggie"))
					pattyType = 2;
			}
			
		}
		while(pattyNumber > 0)
		{
			pattyNumber--;
			burgerOrder.addPatty();
		}
		if(pattyType == 1)
			burgerOrder.changePatties("Chicken");
		else if(pattyType == 2)
			burgerOrder.changePatties("Veggie");
		theOrder = burgerOrder.toString();
		System.out.print(burgerOrder.toString());
	}
	private static boolean stringInArray(String strArr[], String target)
	{
		for( String i: strArr)
			if( i.equalsIgnoreCase(target))
				return true;
		return false;
	}
	private static void testParseLine()
	{
		String testLines[] = {
				"Baron Burger",
				"Burger",
				"Baron Burger with no Veggies Sauce but Baron-Sauce",
				"Triple Chicken Burger with Onions Cheese but Cheddar"};
		for( String i: testLines)
		{
			System.out.println(i);
			parseLine(i);
		}
	}
	private static void testBurger() //tests burger class.
	{
		String[] burgerToppings = {
				"Pickle",
				"Mayonnaise",
				"Baron-Sauce",
				"Lettuce",
				"Tomato",
				"Onions",
				"Pepperjack",
				"Mozzarella",
				"Cheddar",
				"Mushrooms",
				"Mustard",
				"Ketchup"
			};
		Burger order = new Burger(true);
		for( String i: burgerToppings)
		{
			order.removeIngredient(i);
			System.out.println(order.toString());
		}
		for( String i: burgerToppings)
		{
			order.addIngredient(i);
			System.out.println(order.toString());
		}
		order = new Burger(false);
		for( String i: burgerToppings)
		{
			order.addIngredient(i);
			System.out.println(order.toString());
		}
		order.removeCategory("Sauce");
		System.out.println(order.toString());
		order.removeCategory("Veggies");
		System.out.println(order.toString());
		order.removeCategory("Cheese");
		System.out.println(order.toString());
	}
	private static void testMyStack() 
	{ //testing the push and pop of the myStack
		String[] burgerToppings = {
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
		MyStack test = new MyStack();
		for( String i: burgerToppings)
		{
			test.push(i);
			System.out.println(test.toString());
			System.out.println(test.size());
		}
		while(!test.isEmpty())
			System.out.println(test.pop());
		
		for(int i = burgerToppings.length-1; i >= 0; i--)
		{
			test.push(burgerToppings[i]);
			System.out.println(test.toString());
		}
	}
}
