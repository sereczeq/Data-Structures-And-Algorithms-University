package tut3b;

public class task1
{
	
	class Queue<E>
	{
		
		class Element
		{
			
			E object;
			Element next;
			
			public Element(E obj)
			{
				
				object = obj;
				
			}
			
			
			public Element(E obj, Element next)
			{
				
				object = obj;
				this.next = next;
				
			}
			
		}
		
	}
	
}
