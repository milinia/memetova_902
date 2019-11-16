//отсортировать массив методом вставки

public class InsertionSort{
 	public static void main(String[] args) {
 		Integer[] array = new Integer[] {2,9,5,13,67,8,45};
 		int x = 1;

 		for (int i = 0; i < array.length - 1; i++){

 			for (int j = 0; j < x; j++){

 				if (array[j] > array[x]){

 					array[j] = array[j] + array[x];
 					array[x] = array[j] - array[x];
 					array[j] = array[j] - array[x];
 				}
 			}
 			x++;
 		}

 		for (int i = 0; i < array.length; i++){
 			System.out.println(array[i]);
 		}

 	}
 }

