	package project1;
		
	import java.util.Random;

	public class Bui {
		
		public static int[] unSorted;
		public static int[] selectionSorted;
		public static int[] insertionSorted;
		public static int[] quickSorted;
		public static int[] mergeSorted;
	
		public static void main(String[] args) {
	
			// TODO Auto-generated method stub
			
			
			/*
			genData(10);
			 insertionSorted = new int[unSorted.length];
		      for(int i = 0; i< insertionSorted.length;i++){
		
		      insertionSorted[i] = unSorted [i];
		
		      }
			
			insertionSort(insertionSorted,insertionSorted.length-1);
			
			for (int i=0;i<unSorted.length;i++){
				System.out.print(unSorted[i] + ", ");
			}
			System.out.println();
			for (int i=0;i<insertionSorted.length;i++){
				
				System.out.print(insertionSorted[i] + ", ");
			}
			*/
			
			
			
			
			//Size 10
			for (int i=1;i<5;i++){
				genData (10);
				sortTime(unSorted,i);
				}
			
			//Size 100
			for (int i=1;i<5;i++){
				genData (100);
				sortTime(unSorted,i);
				}
			
			//Size 1000
			for (int i=1;i<5;i++){
				genData (1000);
				sortTime(unSorted,i);
				}
			
			//Size 10,000
			for (int i=1;i<5;i++){
				genData (10000);
				sortTime(unSorted,i);
				}
			
			//Size 100,000
			for (int i=1;i<5;i++){
				genData (100000);
				sortTime(unSorted,i);
				}
			
			//Size 1,000,000
			for (int i=1;i<5;i++){
				genData (1000000);
				sortTime(unSorted,i);
				}
			
			//Size 10,000,000 
			for (int i=1;i<5;i++){
				genData (10000000);
				sortTime(unSorted,i);
				}
		}
		
	
	
	public static int[] genData (int N){
		
		unSorted = new int[N];
		Random rand = new Random();
		
		for(int i=0;i<N;i++){
			unSorted[i] = rand.nextInt(2147483647);
		}
			return unSorted;
		}
	
	
	public static long sortTime (int[] data, int choice){
	
	
		long startTime = 0;
		long endTime = 0;
		long timeElapsed = 0;
		
		switch(choice){
	
		case 1:
		
			if(unSorted.length<999999){
		startTime = System.currentTimeMillis();
		
		selectionSort(unSorted, unSorted.length);
		
		endTime = System.currentTimeMillis();

		timeElapsed = endTime - startTime;
		
		System.out.println("Sort time for "+ unSorted.length + " Selection Sort: "+ timeElapsed);
		break;
			}
			else{
				System.out.println("Unable to sort selection sort!!!");
				break;
			}
		
		case 2:
			if(unSorted.length<999999){
			insertionSorted = new int[unSorted.length];
		      for(int i = 0; i< insertionSorted.length;i++){
		
		      insertionSorted[i] = unSorted [i];
		      }
			
			startTime = System.currentTimeMillis();
			insertionSort(insertionSorted, insertionSorted.length-1);
			endTime = System.currentTimeMillis();
			
			timeElapsed = endTime - startTime;
			System.out.println("Sort time for "+ unSorted.length + " Insertion Sort: "+ timeElapsed);
			
			break;
			}
			else{
				System.out.println("Unable to sort insertion sort!!!");
				break;
			}
	
	
		case 3:
	
			quickSorted = new int[unSorted.length];
		      for(int i = 0; i< quickSorted.length;i++){
		
		      quickSorted[i] = unSorted [i];
		      }
			
			startTime = System.currentTimeMillis();
			quicksort(quickSorted, 0, quickSorted.length-1);
			endTime = System.currentTimeMillis();
			
			timeElapsed = endTime - startTime;
			System.out.println("Sort time for "+ unSorted.length + " Quick Sort: "+ timeElapsed);			
			break;
			
			
		case 4:
			mergeSorted = new int[unSorted.length];
		      for(int i = 0; i< mergeSorted.length;i++){
		
		      mergeSorted[i] = unSorted [i];
		      }
			
			startTime = System.currentTimeMillis();
	
			mergeSort(mergeSorted, 0, mergeSorted.length-1);
	
			endTime = System.currentTimeMillis();
	
			timeElapsed = endTime - startTime;
			System.out.println("Sort time for "+ unSorted.length + " Merge Sort: "+ timeElapsed);
			
			break;
			
		}
		return timeElapsed;


	
	/**
	
	* switch(){
	
	* case("Whatever sort method");
	
	* startTime = System.currentTimeMillis();
	
	* -Sort Method-
	
	* endTime = System.currentTimeMillies();
	
	* }
	
	*/
	
	
		
		}
	
	
	public static void selectionSort(int numbers [], int numbersSize) {
	
	      int i = 0;
	      int j = 0;
	      int indexSmallest = 0;
	      int temp = 0;  // Temporary variable for swap
	
	      //Copy the array into a new array
	      selectionSorted = new int[numbersSize];
	      for(i = 0; i< numbersSize;i++){
	      selectionSorted[i] = numbers[i];
	      }
	
	      for (i = 0; i < numbersSize; ++i) {
	
	        // Find index of smallest remaining element
	        indexSmallest = i;
	        for (j = i + 1; j < numbersSize; ++j) {
	        	
	            if (selectionSorted[j] < selectionSorted[indexSmallest]) {
	              indexSmallest = j;
	            }
	        }

	        // Swap numbers[i] and numbers[indexSmallest]
	
	        temp = selectionSorted[i];	
	        selectionSorted[i] = selectionSorted[indexSmallest];
	        selectionSorted[indexSmallest] = temp;
	      }
	  }
	
	
	public static void insertionSort(int numbers [], int numbersSize) {
	      int i = 0;
	      int j = 0;
	      int temp = 0;  // Temporary variable for swap

	      for (i = 1; i < numbersSize; ++i) {
	         j = i;
	         // Insert numbers[i] into sorted part 
	         // stopping once numbers[i] in correct position
	         while (j > 0 && numbers[j] < numbers[j - 1]) {

	            // Swap numbers[j] and numbers[j - 1]
	            temp = numbers[j];
	            numbers[j] = numbers[j - 1];
	            numbers[j - 1] = temp;
	            --j;
	         }
	      }
	   }
	
	
	/*-------------------------Quick sort start----------------------------**/
	
	public static int partition(int numbers [], int i, int k) {
	      int l = 0;
	      int h = 0;
	      int midpoint = 0;
	      int pivot = 0;
	      int temp = 0;
	      boolean done = false;

	      /* Pick middle element as pivot */
	      midpoint = i + (k - i) / 2;
	      pivot = numbers[midpoint];

	      l = i;
	      h = k;

	      while (!done) {

	         /* Increment l while numbers[l] < pivot */
	         while (numbers[l] < pivot) {
	            ++l;
	         }

	         /* Decrement h while pivot < numbers[h] */
	         while (pivot < numbers[h]) {
	            --h;
	         }

	         /* If there are zero or one items remaining,
	            all numbers are partitioned. Return h */
	         if (l >= h) {
	            done = true;
	         } else {
	            /* Swap numbers[l] and numbers[h],
	               update l and h */
	            temp = numbers[l];
	            numbers[l] = numbers[h];
	            numbers[h] = temp;

	            ++l;
	            --h;
	         }
	      }

	      return h;
	   }

	   public static void quicksort(int numbers[], int i, int k) {
	      int j = 0;

	      /* Base case: If there are 1 or zero entries to sort,
	       partition is already sorted */
	      if (i >= k) {
	         return;
	      }

	      /* Partition the data within the array. Value j returned
	         from partitioning is location of last item in low partition. */
	      j = partition(numbers, i, k);

	      /* Recursively sort low partition (i to j) and
	         high partition (j + 1 to k) */
	      quicksort(numbers, i, j);
	      quicksort(numbers, j + 1, k);

	      return;
	   }
	
	  /*-------------------------Quick sort end----------------------------**/
	
	
	  /*-------------------------Merge sort start--------------------------**/
	
	  public static void merge(int numbers [], int i, int j, int k) {
	
	      int mergedSize = k - i + 1;       // Size of merged partition
	      int mergedNumbers [] = new int[mergedSize]; // Temporary array for merged numbers
	      int mergePos = 0;                 // Position to insert merged number
	      int leftPos = 0;                  // Position of elements in left partition
	      int rightPos = 0;                 // Position of elements in right partition
	
	      leftPos = i;                      // Initialize left partition position
	      rightPos = j + 1;                 // Initialize right partition position
	
	      // Add smallest element from left or right partition to merged numbers
	      while (leftPos <= j && rightPos <= k) {
	        if (numbers[leftPos] < numbers[rightPos]) {
	            mergedNumbers[mergePos] = numbers[leftPos];
	            ++leftPos;
	        } 
	        else {
	            mergedNumbers[mergePos] = numbers[rightPos];
	            ++rightPos;
	        }
	        ++mergePos;
	      }
	
	      // If left partition is not empty, add remaining elements to merged numbers
	      while (leftPos <= j) {
	        mergedNumbers[mergePos] = numbers[leftPos];
	        ++leftPos;
	        ++mergePos;
	      }
	
	      // If right partition is not empty, add remaining elements to merged numbers
	      while (rightPos <= k) {
	        mergedNumbers[mergePos] = numbers[rightPos];
	        ++rightPos;
	        ++mergePos;
	      }
	
	      // Copy merge number back to numbers
	      for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
	        numbers[i + mergePos] = mergedNumbers[mergePos];
	      }
	  }
	
	  public static void mergeSort(int numbers [], int i, int k) {
	      
		  int j = 0;
	      if (i < k) {
	        j = (i + k) / 2;  // Find the midpoint in the partition
	        // Recursively sort left and right partitions
	        mergeSort(numbers, i, j);
	        mergeSort(numbers, j + 1, k);
	
	        // Merge left and right partition in sorted order
	        merge(numbers, i, j, k);
	      }
	  }
	  /*-------------------------Merge sort end--------------------------**/
	}