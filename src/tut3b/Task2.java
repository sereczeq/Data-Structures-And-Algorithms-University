package tut3b;

public class Task2
{
	
	static class CyclicBuffer<E>
	{
		
		private E[] objects = null;
		
		private int size = 0;
		private int addIndex = 0;
		private int getIndex = 0;
		
		@SuppressWarnings("unchecked")
		public CyclicBuffer(int size)
		{
			
			this.size = size;
			objects = (E[]) new Object[size];
			
		}
		
		
		public boolean add(E obj)
		{
			
			if (addIndex >= size) return false;
			objects[addIndex] = obj;
			addIndex++;
			return true;
			
		}
		
		
		public E get()
		{
			
			if (getIndex >= addIndex || getIndex > size) return null;
			E temp = objects[getIndex];
			getIndex++;
			return temp;
			
		}
		
		
		public void reset()
		{
			
			addIndex = getIndex = 0;
			
		}
		
		
		public boolean isFull()
		{
			
			return addIndex >= size;
			
		}
		
		
		public boolean isEmpty()
		{
			
			return getIndex >= size;
			
		}
		
		
		public String toString()
		{
			
			String string = "";
			for (E e : objects) string += e + "\n";
			string += "[DEBUG:] add: " + addIndex + ", get: " + getIndex;
			return string;
			
		}
		
	}
	
	static class Main
	{
		
		public static void main(String[] args)
		{
			
			CyclicBuffer<Character> buff = new CyclicBuffer<Character>(35);
			String buffTest = "This is data inputted to the bufferSTOP - size cap, this data should not get printed out";
			
			// "process-producer":
			
			for (char x : buffTest.toCharArray())
			{
				// adding element until buff is full
				if (!buff.add(x)) break;
			}
			
			// "process-consumer":
			
			// getting all the data until buff is empty
			while (!buff.isEmpty())
			{
				System.out.print(buff.get());
			}
			
			buff.reset();
			
		}
		
	}
	
}
