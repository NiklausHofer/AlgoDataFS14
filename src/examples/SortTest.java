package examples;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;


/**
 * @author ps
 * Various sort programs for int arrays
 */
public class SortTest {
	
	public static int b [];
	public static Random rand = new Random();  
	public static long cnt;
	
	

	/**
	 * @param a int aray
	 * @return 'true' if 'a' is sorted
	 */
	public static boolean sortCheck(int[] a) {
		for (int i=0;i<a.length-1;i++){
			if (a[i]>a[i+1]) return false; 
		}
		return true;
	}
	
	
	/**
	 * Non optimized bubble sort for an int array 
	 * @param a
	 */
	public static void bubbleSort(int[] a) {
		cnt=0;
		int m = a.length-1;
		for(int i=m; i>0; i--){ 
			for (int k=0; k < i; k++){
				if(a[k]>a[k+1]) swap(a,k,k+1);
			}
			// now a[i] is on its final position!
		}
	}

	/**
	 * swap the array elements a[i] and a[k]
	 * @param a int array 
	 * @param i position in the array 'a'
	 * @param k position in the array 'a'
	 */
	static void swap(int [] a, int i, int k){
		int tmp=a[i];
		a[i]=a[k];
		a[k]=tmp;
		cnt++;
	}
 	
	
	
	
	
	/**
	 * Wrapper which calls the recursive version of the 
	 * quick sort program
	 * @param a the int array to be sorted
	 */
	public static void quickSort(int [] a){
		qSort(a,0,a.length-1);
	}

	/**
	 * recursive version of quick sort (sorts 
	 * the range a[from..to] of the int array 'a')
	 * @param a 
	 * @param from 
	 * @param to
	 */
	private static void qSort(int []a, int from, int to){
		if (from >= to) return; // nothing to do if sequence has length 1 or less
		int piv = partition(a,from,to);
		// now a[to..piv-1] <= a[piv] and 
		// a[piv+1..to]>=a[piv]
		qSort(a,from,piv-1);
		qSort(a,piv,to);
	}

	/**
	 * partitions the range such that all of the elements 
	 * in the range a[from..piv-1] are smaller than a[piv]
	 * and all elements in the range a[piv+1..to] are greater 
	 * or equal than a[piv]
	 * @param a
	 * @param from
	 * @param to
	 * @return the position 'piv' of the pivot
	 */
	private static int partition(int []a, int from, int to){
		// take a random pivot and put it to the middle
		// of the range
		// necessary if data not random
	      int i = from, j = to, med = (from+to)/2;
	      swap(a,rand.nextInt(to-from)+from,med);
	      int pivot = a[med];
	      while (i <= j) {
	            while (a[i] < pivot) i++;
	            while (a[j] > pivot)j--;
	            if (i <= j) {
	            	swap(a,i,j);
	                i++;
	                j--;
	            }
	      }
	      return i;
	}	
	
	/**
	 * @param a sorted array
	 * @param e the element we want to find in 'a'
	 */
	private static int find(int[] a, int e) {
		// returns the position where an element e
		// is found in the sorted array a
		// (-1 if not found)
		int ret = -1;
		int from=0,to=a.length-1,med=(from+to)/2;
		while (to>=from){
			int comp = a[med];
			if (comp==e) return  med; // we found 'e' at a[med]
			// we have a new search region:
			else if(comp < e) from = med+1;
			else to=med-1;
			med = (from+to)/2;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		long t1=0,t2=0,te1=0,te2=0,eTime=0,time=0;
		int n = 20000000;
		// we need a random generator
		Random rand=new Random();
		//rand.setSeed(54326346); // initialize always in the same state
		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();	
		// new array
		int [] a = new int[n];
		// fill it randomly
		for (int i=0;i<a.length;i++) a[i]=rand.nextInt(n);
		cnt=0;  // for statistcs reasons
		// get Time
		te1=System.currentTimeMillis();
		t1 = threadBean.getCurrentThreadCpuTime();
		quickSort(a);
		te2 = System.currentTimeMillis();
		t2 = threadBean.getCurrentThreadCpuTime();
		time=t2-t1;
		eTime=te2-te1;
		System.out.println("CPU-Time usage: "+time/1000000.0+" ms");
		System.out.println("elapsed time: "+eTime+" ms");
		System.out.println("sorted? "+sortCheck(a));
		System.out.println("swap operation needed "+cnt);
        // find
//		te1=System.currentTimeMillis();
//		t1 = threadBean.getCurrentThreadCpuTime();
//		for (int k=0;k<n;k++) find(a,rand.nextInt(n));
//		te2 = System.currentTimeMillis();
//		t2 = threadBean.getCurrentThreadCpuTime();
//		time=t2-t1;
//		eTime=te2-te1;
//		System.out.println("CPU-Time usage: "+time/1000000.0/n+" ms");
//		System.out.println("elapsed time: "+eTime*1.0/n+" ms");
	}


}
