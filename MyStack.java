/**
 * @author Alex Orozco
 * UW TCSS341 Data Structures
 */
public class MyStack {
	StackNode myTop;
	private int numberOfItems = 0;
	public boolean isEmpty() {
		return myTop == null;
	}
	public String pop(){
		if (this.isEmpty())
			return "Stack is empty";
		else {
			String temp = (String)myTop.myItem;
			myTop = myTop.nextOption;
			numberOfItems--;
			return temp;
		}
	}
	public String peek() { 
		if (this.isEmpty())
			return "Stack is empty";
		return (String)myTop.myItem;
	}
	public void push(String daName) { 
		StackNode temp = new StackNode(daName);
		numberOfItems++; //bitwise add 1, for faster performance
		temp.nextOption = myTop;
		myTop = temp;
	}
	public int size() {
		return numberOfItems;
	}
	public String toString() {
		StringBuilder daString = new StringBuilder();
		StackNode temp = myTop;
		while(temp != null) {
			daString.append(temp.myItem);
			if ( temp.nextOption != null) {
				daString.append(", "); //add visual features. 
			}
			temp = temp.nextOption;
		}
		return daString.toString();
	}
	private class StackNode {
		private Object myItem;
		private StackNode nextOption;
		public StackNode(Object theItem) {
			myItem = theItem;
			nextOption = null;
		}
	}
}
