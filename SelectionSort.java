import java.util.Scanner;
//отсортировать массив методом выборки(ввод массива)
public class SelectionSort{
	public static void main(String[] args){
		Scanner str = new Scanner(System.in);
		System.out.println("Enter the length of array");
		int length = str.nextInt();
		int[] array = new int[length];

		System.out.println("Enter the elements of array");
		for (int i = 0; i < length; i++) {
			array[i] = str.nextInt();
		}

		int number = 0;

		for (int i = 0; i < length - 1; i++){

			int min = array[i];
			
			for (int j = i + 1; j < length; j++) {

				if (array[j] <= min){
					min = array[j];
					number = j; 
				}
			}

			min = array[number];
			array[number] = array[i];
			array[i] = min;
		}

		System.out.println("The sorted array is ");
		for (int i = 0; i < array.length; i++){
 			System.out.println(array[i]);
 		}
	}
}