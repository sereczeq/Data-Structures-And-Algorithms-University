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
		
		int time;
		
		public Customer()
		{
			
			time = new Random().nextInt(10);
			
		}
		
		
		public Customer(int time)
		{
			
			this.time = time;
			
		}
		
	}
	
	static class Main
	{
		
		static UnboundedQueue<Customer> queue = new UnboundedQueue<Customer>();
		static Official[] office = {new Official("A"), new Official("B"), new Official("C") };
		
		static Thread customers = new Thread()
		{
			
			public void run()
			{
				
				for (int x = 0; x < 1000; x++)
				{
					queue.enqueue(new Customer());
				}
				
			}
			
		};
		
		static Thread officials = new Thread()
		{
			
			public void run()
			{
				
				while (!queue.isEmpty())
				{
					try
					{
						// added this line because it's easier to see queue in real time
						TimeUnit.SECONDS.sleep(1);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (Official official : office) official.tick();
					for (Official official : office)
					{
						if (official.isFree())
						{
							Customer customer = queue.dequeue();
							official.addTask(customer);
							System.out
									.println("Official " + official.name + " took customer with time " + customer.time);
						}
					}
					
				}
				
			}
			
		};
		
		public static void main(String[] args)
		{
			
			customers.run();
			officials.run();
			
		}
		
	}
	
}
