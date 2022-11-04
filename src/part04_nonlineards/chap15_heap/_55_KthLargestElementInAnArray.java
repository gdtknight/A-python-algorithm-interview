package part04_nonlineards.chap15_heap;

import java.util.Arrays;

public class _55_KthLargestElementInAnArray {
  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static int solution(int[] arr, int k) {
    int[] maxHeap = new int[arr.length + 1];

    maxHeap[1] = arr[0];
    int elemCnt = 1;

    for (int i = 1; i < arr.length; i++) {
      maxHeap[++elemCnt] = arr[i];
      int curIdx = elemCnt;

      while (curIdx / 2 > 0) {
        int parentIdx = curIdx / 2;

        if (maxHeap[parentIdx] < maxHeap[curIdx]) {
          swap(maxHeap, curIdx, parentIdx);
          curIdx = parentIdx;
          continue;
        }
        break;
      }
    }

    int answer = 0;
    while (k > 0) {
      int curIdx = 1;
      answer = maxHeap[curIdx];
      maxHeap[curIdx] = maxHeap[elemCnt];
      maxHeap[elemCnt--] = 0;

      while (curIdx * 2 <= elemCnt || curIdx * 2 + 1 <= elemCnt) {
        int leftIdx = curIdx * 2;
        int rightIdx = curIdx * 2 + 1;
        if (rightIdx > elemCnt) {
          if (maxHeap[curIdx] < maxHeap[leftIdx]) {
            swap(maxHeap, curIdx, leftIdx);
            curIdx = leftIdx;
            continue;
          }
          break;
        } else {
          int maxVal = Math.max(maxHeap[leftIdx], maxHeap[rightIdx]);
          if (maxHeap[curIdx] < maxVal) {
            if (maxVal == maxHeap[leftIdx]) {
              swap(maxHeap, curIdx, leftIdx);
              curIdx = leftIdx;
            } else {
              swap(maxHeap, curIdx, rightIdx);
              curIdx = rightIdx;
            }
            continue;
          }
          break;
        }
      }

      k--;
    }

    return answer;
  }

  public static int solution2(int[] arr, int k) {
    Arrays.sort(arr);
    return arr[arr.length - k];
  }

  public static void main(String[] args) {
    int[] arr = { 100, 101, 45, 46, 47, 48, 23, 222, 29, 10, 19, 58, 67, 49, 3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 8, 9, 11, 13,
        15, 17, 11, 2, 3, 9, 8, 7, 5, 5, 3 };
    int k = 4;

    double beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
    int answer = solution(arr, k);
    double afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

    double secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

    System.out.println("Answer :" + answer);
    System.out.println("시간차이(ms) : " + secDiffTime);

    System.out.println();

    beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
    answer = solution2(arr, k);
    afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

    secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

    System.out.println("Answer :" + answer);
    System.out.println("시간차이(ms) : " + secDiffTime);
  }
}
