package src.main.java.home_work3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Arrays;
/*
1 Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее арифметическое из этого списка.
2 Пусть дан произвольный список целых чисел, удалить из него четные числа
3* Реализовать алгоритм сортировки слиянием
*/
public class Main {
    public static void main(String[] args) {
    minMaxMean();
    deleteEvens();
    task3();
    }

    private static void minMaxMean() {
        ArrayList<Integer> list = getArrList(10);
        int min = list.get(0);
        int max = min;
        double mean = min;
        for (int item : list) {
            mean+=item;
            if (min>item){
                min=item;
            } else if (max<item) {
                max=item;
            }
        }
        mean /= list.size();
        System.out.println("Задача №1");
        System.out.println("Сгенерированный список: ");
        System.out.println(list);
        System.out.printf("min = %d, max = %d, mean = %.2f\n",min,max,mean);
        
    }
        private static void deleteEvens() {
        ArrayList<Integer> list = getArrList(20);
        Iterator iterList = list.iterator();
        System.out.println("Задача №2");
        System.out.println("Сгенерированный список: ");
        System.out.println(list);
        while(iterList.hasNext()){
            if (isEven((int)iterList.next())) iterList.remove();
        }
        System.out.println("Список без четных чисел: ");
        System.out.println(list);
    }
    private static void task3(){
        ArrayList<Integer> list = getArrList(15);
        int[] intList = list.stream().mapToInt(i -> i).toArray();
        System.out.println("Задача №3");
        System.out.println("Сгенерированный список: ");
        System.out.println(list);
        int[] result = mergeSort(intList);
        System.out.println("Список после сортировки: ");
        System.out.println(Arrays.toString(result));

    }

    static ArrayList<Integer> getArrList(int N){
    ArrayList<Integer> list = new ArrayList<>(N);
    Random rnd = new Random();
    for (int i = 0; i < N; i++) {
        list.add(i,rnd.nextInt(10));
    }
    return list;
    }

    public static boolean isEven(int N){
        return (N%2==0);
    }

    public static int[] mergeSort(int[] sortArr) {
        int[] buffer1 = Arrays.copyOf(sortArr, sortArr.length);
        int[] buffer2 = new int[sortArr.length];
        int[] result = mergeSortInner(buffer1, buffer2, 0, sortArr.length);
        return result;
    }

    public static int[] mergeSortInner(int[] buffer1, 
    int[] buffer2, int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        //уже отсортирован
        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);

        //слияние
        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2]
                    ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }

}