import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {


    }
}

// this is almost exactly what my first attempt was except use LinkedList not ArrayList and convert
// LinkedList to array
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // sytax is like this. passing 2 elements from (a,b) frmo intervals[][], compare and sort them
        // by index 0
        //Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));  VARIATION ON ABOVE
        // similarly
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}

// 94/170 test cases
//class Solution {
//    public int[][] merge(int[][] intervals) {
//        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
//        int index = 0;
//        for (int i = 1; i<intervals.length; i++){
//            if(intervals[i][0] <= intervals[index][1]){
//                intervals[index][1] = intervals[i][1];
//                int x = Math.min(intervals[index][0], intervals[i][0]);
//                intervals[index][0] = x;
//            } else{
//                index++;
//                intervals[index] = intervals[i];
//            }
//        }
//        return Arrays.copyOf(intervals, index+1);
//    }
//}
//// stuck [[2,3],[4,5],[6,7],[8,9],[1,10]]. would need to backtrack all the way to index 0, rewrite value of interval
// at 0 then reset index back to 0. seems inefficient

//class Solution {
//    public int[][] merge(int[][] intervals) {
//        ArrayList<Integer[]> ans = new ArrayList<Integer[]>();
//        int index = 0;
//        ans.add(intervals[0]);
//        for (int[] i = 1; i<intervals.length; i++){
//            if(intervals[i][0] < ans[index][1]){
//                int x = Math.min(ans[index][0]);
//                int y = intervals[i][1];
//                ans[index] = [x,y];
//            }
//        }
//    }
//}
// conversion troubles. lets try similar concept but mutating and return a subArray



// sort? examples already sorted
// take min start and max of ends and merge
// reuse of new interval required
// [1,3] [2,6] [3,7]
// create new arrayList since length unknown
// use of index1 and index2 vars where 1 loops
// intervals and 2 will increment only if no overlap.
// [1,3] [2,6] [3,7] : 1 and 2 = 0. then 1 = 1, 2 = 0. 1 = 2, 3 = 0
// since this would merge all into index 0
