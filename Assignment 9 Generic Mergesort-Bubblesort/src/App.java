import java.util.Random;
import java.lang.reflect.Array;

public class App<T extends Comparable<T>> 
{
    public static void main(String[] args) throws Exception 
    {
        //Today's coding pracitce, neater format
        //Specifically keeping code compact
        //Disclaimer: Assignment only talk about comparing bubble/mergesort
        //Instead will measure time from start to finish


        Integer[] arr = generateRandomArray(Integer.class, 10000);
        Integer[] arrTwo = generateRandomArray(Integer.class, 10000);

        // Measure the execution time of BubbleSort
        long startTime = System.nanoTime();
        Bubblesort(arr);
        long endTime = System.nanoTime();
        long bubbleSortTime = (endTime - startTime)/1_000_000;

        // Measure the execution time of BubbleSort
        startTime = System.nanoTime();
        Mergesort(arrTwo, 0, arrTwo.length - 1);
        endTime = System.nanoTime();
        long mergeSortTime = (endTime - startTime)/1_000_000;

        System.out.println("BubbleSort time: " + bubbleSortTime + " miliseconds");
        System.out.println("MergeSort time: " + mergeSortTime + " miliseconds");
    }

    //Create random array
    public static <T extends Comparable<T>> T[] generateRandomArray(Class<T> clazz, int arrayLength) {
        Random random = new Random();
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);
        System.out.println("Creating random array...");
    
        for (int i = 0; i < arrayLength; i++) {
            array[i] = clazz.cast(Integer.valueOf(random.nextInt(1000)));
        }
    
        return array;
    }
    
    public static <T extends Comparable<T>> void Bubblesort(T[] arrayOne){
        T Temp;

        for(int i = 0; i < arrayOne.length; i++){
            for(int j = 0; j < arrayOne.length - 1 - i; j++){

                if(arrayOne[j].compareTo(arrayOne[j+1]) > 0){
                    Temp = arrayOne[j];
                    arrayOne[j] = arrayOne[j+1];
                    arrayOne[j + 1] = Temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void Mergesort(T[] arrayTwo, int Start, int End){
        int Mid;

        if(Start < End){
            Mid = (Start + End)/2;

            Mergesort(arrayTwo, Start, Mid);
            Mergesort(arrayTwo, Mid+1, End);
            Merge(arrayTwo, Start, Mid, End);
        
        }
    }


    // Temp array becomes useless, needs to be replace with
    // T[] TempArr = (T[]) new Comparable[arrayTwo.length];
    // Nevermind getting out of bounds
    // T[] TempArr = (T[]) new Comparable[End - Start + 1];
    public static <T extends Comparable<T>> void Merge(T[] arrayTwo, int Start, int Mid, int End){
        T[] TempArr = (T[]) new Comparable[End - Start + 1];
    
        int i,j,k;
        i = Start;
        j = Mid+1;
        k = 0;
    
        while(i <= Mid && j <= End){
            if(arrayTwo[i].compareTo(arrayTwo[j]) <= 0)
                TempArr[k++] = arrayTwo[i++];
            else
                TempArr[k++] = arrayTwo[j++];
        }
    
        if(i > Mid){
            while(j <= End)
                TempArr[k++] = arrayTwo[j++];
        }
        else{
            while(i <= Mid)
                TempArr[k++] = arrayTwo[i++];
        }
    
        for(i = Start; i <= End; i++){
            arrayTwo[i] = TempArr[i - Start];
        }
    }
}
