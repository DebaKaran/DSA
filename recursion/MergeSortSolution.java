import java.util.Arrays;

public class MergeSortSolution {

    void mergeSort(int arr[], int l, int r) {
        mergeSortUsingRecursion(arr, l, r);
        
    }
    
    //  1: Time Complexity:
    // O(n log n) - n for merging and log n for dividing the array  
    // 2: Space Complexity:
    // O(n) - for temporary arrays used in merging process
    
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
}
