import java.util.Scanner;

public class ReverseArray{
	public static void main(String[] args){
		Scanner str = new Scanner(System.in);
		System.out.println("Enter the length of array");
		int length = str.nextInt();
		int[] array = new int[length];

		System.out.println("Enter the elements of array");
		for (int i = 0; i < length; i++){
			array[i] = str.nextInt();
		}

		System.out.println("Reverse array is ");
		for (int i = length - 1; i >= 0; i--){
			System.out.println(array[i]);
		}
	}
}