package examples;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

public class IntRadixSorter {
	// bucket array
	int [][] buckets = new int[256][];
	int [] cnts = new int[256];
	
	public void bsort(int [] a){
		// initialize the buckets
		for (int i=0; i<256;i++) {
			buckets[i] = new int[a.length/256+1000];
			cnts[i] = 0; 
		}
		bsort(a,0);
	}

	private void expand(int i){
		int [] tmp = new int[2*buckets[i].length];
		for(int k=0;k<buckets[i].length;k++) tmp[k]=buckets[i][k];
		buckets[i]=tmp;
	}
	
	private void bsort(int [] a, int depth){
		if (depth>3) return;  // at most 4 bytes!
		for (int i=0;i<a.length;i++){
			int k=a[i];
			for (int j=0;j<depth;j++) {
				// cut off the last byte
				k=k/256;
			}
			k = k % 256; // 
			// put a[i] in buckets[k]
			if (cnts[k]>=buckets[k].length) expand(k);
			buckets[k][cnts[k]++]=a[i];
		}
		int k=0;  
		
		// now empty all buckets (in the right order!)
		for (int i=0;i<256;i++){
			for (int j=0;j<cnts[i];j++){
				a[k++]=buckets[i][j];
			}
			cnts[i]=0;
		}
		bsort (a,depth+1);  // next sweep
	}

	public boolean check(int [] a){
		// returns true if a is sorted 
		for (int i=0;i<a.length-1;i++){
			if (a[i]>a[i+1]) return false; 
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
		int n = 100000000;
		int [] a = new int[n];
		for (int i=0;i<n;i++) a[i]=rand.nextInt(Integer.MAX_VALUE);
		IntRadixSorter ibs = new IntRadixSorter();
		System.out.println(n+" elements, sorted? ");//+ibs.check(a));
		long before = System.nanoTime();
		ibs.bsort(a);
		long after = System.nanoTime();
		System.out.println("sorted? "+ibs.check(a)+", time: "+(after-before)/1000000.0+" msec");
	}
}
