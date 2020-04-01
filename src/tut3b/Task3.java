package tut3b;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Task3
{
	
	private static class Official
	{
		
		public String name;
		int timeLeft = 0;
		
		public Official(String name)
		{
			
			this.name = name;
			
		}
		
		
		// imitates time change / clock tick
		public void tick()
		{
			
			if (timeLeft > 0) timeLeft--;
			
		}
		
		
		public boolean isFree()
		{
			
			return timeLeft == 0;
			
		}
		
		
		public void addTask(Customer customer)
		{
			
			timeLeft = customer.time;
			
		}
		
	}
	
	private static class Customer
	{
		
		int id;
		int time;
		
		public Customer(int id, int time)
		{
			
			this.id = id;
			this.time = new Random().nextInt(time);
			if (this.time == 0) this.time = 1;
			
		}
		
		
		@Override
		public String toString()
		{
			
			return "Customer no." + id + " with time: " + time;
			
		}
		
	}
	
	static class Main
	{
		
		static UnboundedQueue<Customer> queue = new UnboundedQueue<Customer>();
		static Official[] office = {new Official("A"), new Official("B"), new Official("C") };
		static boolean run = true;
		
		static int howManyCustomers = 20;
		static int longestPossibleTaskTime = 5;
		static int newCustomerAddTime = 10;
		
		static Thread customers = new Thread()
		{
			
			public void run()
			{
				
				for (int x = 0; x < howManyCustomers; x++)
				{
					try
					{
						TimeUnit.SECONDS.sleep(new Random().nextInt(newCustomerAddTime));
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					Customer temp = new Customer(x, longestPossibleTaskTime);
					queue.enqueue(temp);
					System.out.println("[QUEUE:] added " + temp);
				}
				run = false;
				
			}
			
		};
		
		static Thread officials = new Thread()
		{
			
			public void run()
			{
				
				while (run || !queue.isEmpty())
				{
					// This updates the officials time
					try
					{
						TimeUnit.SECONDS.sleep(1);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					for (Official official : office) official.tick();
					
					// This assigns new customers to officials
					for (Official official : office)
					{
						if (official.isFree() && !queue.isEmpty())
						{
							Customer customer = queue.dequeue();
							official.addTask(customer);
							System.out.println("[OFFICE:] Official " + official.name + " took " + customer);
						}
					}
					
				}
				System.out.println("\n\n\t\t\tFINISHED!");
				
			}
			
		};
		
		public static void main(String[] args)
		{
			
			/*
			 * Time updates every second
			 * 
			 * This variables may be change to see different results
			 * 
			 */
			howManyCustomers = 20;
			longestPossibleTaskTime = 7;
			newCustomerAddTime = 3;
			
			customers.start();
			officials.start();
			
		}
		
	}
	
}
