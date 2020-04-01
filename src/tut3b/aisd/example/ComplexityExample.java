package aisd.example;

public class ComplexityExample {
	
	public static int example1(int n){
		int sum=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n/2;j++)
				sum+=i+j;
		return sum;		
	}
	
	public static int example2(int n){
		int sum=0;
		for(int i=0;i<2*n;i++)
			sum+=i;
		for(int j=2*n;j>0;j--)
			sum+=j;
		return sum;		
	}
	
	public static int example3(int n){
		int sum=0;
		for(int i=0;i<n;i++)
			sum+=example1(i);
		return sum;
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
