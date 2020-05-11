package lab8;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;

class HashTable<E>
{
	
	LinkedList<E> arr[]; // use pure array
	private final static int defaultInitSize = 8;
	private final static double defaultMaxLoadFactor = 0.7;
	private int size;
	private int elemAmount;
	private final double maxLoadFactor;
	
	public HashTable()
	{
		
		this(defaultInitSize);
		
	}
	
	
	public HashTable(int size)
	{
		
		this(size, defaultMaxLoadFactor);
		
	}
	
	
	public HashTable(int initCapacity, double maxLF)
	{
		
		// TODO
		this.maxLoadFactor = maxLF;
		this.size = initCapacity;
		this.arr = new LinkedList[size];
		for (int x = 0; x < size; x++) arr[x] = new LinkedList<E>();
		
	}
	
	
	public boolean add(Object elem)
	{
		
		Document doc = (Document) elem;
		BigInteger bigKey = doc.hashCodeBig();
		int key = bigKey.mod(BigInteger.valueOf(size)).intValue();
		if (arr[key].contains(elem)) return false;
		arr[key].add((E) elem);
		elemAmount++;
		if (elemAmount > (float) size * maxLoadFactor) doubleArray();
		return true;
		
	}
	
	
	private void doubleArray()
	{
		
		size *= 2;
		elemAmount = 0;
		LinkedList[] tempArr = arr;
		arr = new LinkedList[size];
		for (int x = 0; x < size; x++) arr[x] = new LinkedList<E>();
		for (LinkedList x : tempArr) for (Object obj : x) add(obj);
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "";
		for (int x = 0; x < size; x++)
		{
			string += x + ":";
			Iterator it = arr[x].iterator();
			if (it.hasNext())
			{
				string += " " + ((Document) it.next()).getName();
				while (it.hasNext()) string += ", " + ((Document) it.next()).getName();
			}
			string += "\n";
		}
		return string;
		
	}
	
	
	public Object get(Object toFind)
	{
		
		Document doc = (Document) toFind;
		BigInteger bigKey = doc.hashCodeBig();
		int key = bigKey.mod(BigInteger.valueOf(size)).intValue();
		for (Object x : arr[key]) if (x.equals(toFind)) return x;
		return null;
		
	}
	
	
	private int hash(int key)
	{
		
		// return ((key / 100) * 6 + key) % size;
		return key % size;
		
	}
	
}