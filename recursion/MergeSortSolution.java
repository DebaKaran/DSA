import java.util.Arrays;

public class MergeSortSolution {

    void mergeSort(int arr[], int l, int r) {
        //mergeSortUsingRecursion(arr, l, r);

        // Create one temp buffer array for the entire recursion
        int[] temp = new int[arr.length];
        mergeSortUsingRecursion(arr, l, r, temp);
    }
    
    //  1: Time Complexity:
    // O(n log n) - n for merging and log n for dividing the array  
    // 2: Space Complexity:
    // O(n log n) - for temporary arrays used in merging process
    
    void mergeSortUsingRecursion(int arr[], int l, int r) {
        if(l >= r) {
            return;
        }
        int mid = l + ( r - l) / 2;
        
        // sort left and right halves
        mergeSortUsingRecursion(arr, l, mid);
        mergeSortUsingRecursion(arr, mid + 1, r);
        
         // merge them
        merge(arr, l, r, mid);
    }
    
    void merge(int arr[], int l, int r, int mid) {
        int[] left = Arrays.copyOfRange(arr, l, mid + 1);// end is exclusive → add +1
        int[] right = Arrays.copyOfRange(arr, mid + 1, r + 1); //included r
        
        int i = 0; //for left side of array
        int j = 0; //for right side of array
        int k = l;
        
        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        
        while(i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        
         while(j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    //  1: Time Complexity:
    // O(n log n) - n for merging and log n for dividing the array
    // 2: Space Complexity:
    // O(n) - for temporary array used in merging process
    
     void mergeSortUsingRecursion(int[] arr, int l, int r, int[] temp) {
        if (l >= r) return;  // base case

        int mid = l + (r - l) / 2;

        mergeSortUsingRecursion(arr, l, mid, temp);
        mergeSortUsingRecursion(arr, mid + 1, r, temp);

        merge(arr, l, r, mid, temp);
    }

    void merge(int[] arr, int l, int r, int mid, int[] temp) {
        int i = l;
        int j = mid + 1;
        int k = l;

        // Merge into temp
        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // Copy leftovers from left half
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy leftovers from right half
        while (j <= r) {
            temp[k++] = arr[j++];
        }

        // Copy sorted range back into original array
        for (int x = l; x <= r; x++) {
            arr[x] = temp[x];
        }
    }
}
