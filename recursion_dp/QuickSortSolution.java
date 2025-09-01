public class QuickSortSolution {
    // 1: Time Complexity:
    // O(n log n) on average, O(n^2) in the worst case
    //Worst case: O(n^2)
    //Happens when pivot is always the smallest/largest element (e.g., sorted or reverse sorted array, without randomization).
    // 2: Space Complexity:
    // O(log n) due to recursive stack space
    public void quickSort(int[] arr, int low, int high) {
        //Base condition
       if(low >= high) {
           return;
       }
        
      int pivotIndex = partition(arr, low, high);
      
      quickSort(arr, low, pivotIndex - 1);
      quickSort(arr, pivotIndex + 1, high);
    }

    private int partition(int[] arr, int low, int high) {
        int pivotIndex = low;
        
        int pivotValue = arr[high];
        
        for(int j = low; j < high; j++) {
            if(arr[j] <  pivotValue) {
                swap(arr, pivotIndex, j);
                pivotIndex++;
            }
        }
        
        swap(arr, pivotIndex, high);
        return pivotIndex;
    }
    
    private void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
