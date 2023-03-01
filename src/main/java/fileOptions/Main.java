//package fileOptions;
//import java.util.Scanner;
//
//public class Main{
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        int n  = scanner.nextInt();
//        int[] nums = new int[n];
//        for(int i = 0 ; i < n ; i ++){
//            nums[i] = scanner.nextInt();
//        }
//        int maxNumOfDao = returnNumOfDao(nums,0,n-1);
//        System.out.println(maxNumOfDao);
//        //int numOfSystem = returNumOfSystrm(nums);
//        //System.out.println(numOfSystem);
//    }
//
//
//
//
//
//
//    //计算这套系统最多可以拦截多少导弹
//    public static int returnNumOfDao(int[] nums ,int start, int end){
//        if(start >= end){
//            return 1;
//        }
//        //数组中求最大值的索引
//        int indexOfMax = getIndexOfMax(nums,start,end);
//        return Math.max( returnNumOfDao(nums,start,indexOfMax-1) , (returnNumOfDao(nums,indexOfMax+1,end)+1) );
//    }
//    //数组中求最大值的索引，左闭右闭
//    private static int getIndexOfMax(int[] nums ,int start, int end){
//        int result = 0 ;//最大值的索引
//        int max = 0 ;
//        for(int i = start ; i <= end ; i ++ ){
//            if(nums[i]> max){
//                result = i ;
//                max = nums[i];
//            }
//        }
//        return result;
//
//    }
//
//
//}
