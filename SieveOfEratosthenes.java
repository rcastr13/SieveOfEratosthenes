import java.util.ArrayList;
import java.util.Arrays;

public class SieveOfEratosthenes{
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("Sieve of Eratosthenes\nRandomized number of primes (From 5-500):\n");
			int rand = (int)(Math.random() * 500) + 5;
			sieve(rand);
			System.out.println("For more info. on how to use this, enter `java SieveOfEratosthenes help`");
		}else if(args[0].toUpperCase().equals("HELP")){
			System.out.println("Sieve of Eratosthenes\n\nTo find the prime numbers within 2-n simply enter `java SieveOfEratosthenes n`\nOtherwise, it will randomly choose a number to find the prime numbers within the range of 5-500.");
		}else{
			try{
				sieve(Integer.parseInt(args[0]));
			}catch(Exception e){
				System.out.println("Not integer or too many inputs.");
			}
		}
	}

	public static void sieve(int num){
		//Generate two identical array lists to remove composite numbers.
		ArrayList<Integer> primes = new ArrayList<Integer>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();

		System.out.println("For a given positiove integer n, output the first n primes:");
		result = recursion(primes, 2, num);

		System.out.print("e.g. n = " + num + "\nOutput: ");
		primes.forEach(elem -> System.out.print(elem + " "));
		System.out.println("\n\n\n");

		//Populate the numbers and primes array from 2 to num.
		System.out.println("For a given integer n > 1, list all the primes not exceeding n:");
		for(int i = 2; i <= num; i++){
			//System.out.print(i + " ");
			numbers.add(i);
		}
		primes.clear(); //Remove past primes in the ArrayList.
		primes = new ArrayList<Integer>(numbers);
		//System.out.println();

		result = recursion2(primes, numbers, 0);

		//System.out.print("\nPrimes: ");
		System.out.print("e.g. n = " + num + "\nOutput: ");
		result.forEach(elem -> System.out.print(elem + " "));
		System.out.println();
	}

	public static ArrayList<Integer> recursion(ArrayList<Integer> primes, int prime, int size){
		boolean isPrime = true;

		if(primes.size() == size){
			return primes;
		}else{
			for(int i = 2; i < prime; i++){
				if(prime % i == 0 && prime != 2){
					isPrime = false;
					break;
				}
			}
		}

		if(isPrime) primes.add(prime);

		return recursion(primes, ++prime, size);
	}

	public static ArrayList<Integer> recursion2(ArrayList<Integer> primes, ArrayList<Integer> numbers, int index){
		//	BASE CASE:
		if(numbers.get(index) == numbers.get(numbers.size() - 1)){
			return primes;
		}else{
			//1. Take the first number and remove all multiples of it from the primes array.
			// Stop the multiplication when the number becomes larger than OR equal to the last number in the array.
			for(int i = 2; (numbers.get(index) * i) <= numbers.get(numbers.size() - 1); i++){
				if(primes.contains(numbers.get(index) * i)) primes.remove(primes.indexOf(numbers.get(index) * i));
			}

			//2. Increment the index to start on the next prime number (It is prime since it is the first instance
			// of its kind without the need of multiplying it) and repeat the process with the newly modified primes
			// array.
			return recursion2(primes, primes, ++index);
		}
	}
}
