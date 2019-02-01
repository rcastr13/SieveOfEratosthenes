import java.util.ArrayList;
import java.util.Arrays;

public class SieveOfEratosthenes{
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("Sieve of Eratosthenes\n\nRandomized number of primes (From 5-500):\n");

			int rand = (int)(Math.random() * 538) + 2;
			sieve(rand);

			System.out.println("\nFor more info. on how to use this, enter `java SieveOfEratosthenes help`");
		}else if(args[0].toUpperCase().equals("HELP")){
			System.out.println("Sieve of Eratosthenes\n\nTo find the prime numbers within 2-n simply enter `java SieveOfEratosthenes n`\nOtherwise, it will randomly choose a number to find the prime numbers within the range of 5-538.");
		}else{
			try{
				if(Integer.parseInt(args[0]) < 2){
					System.out.println("Too small.");
					System.exit(0);
				}else{
					System.out.println("Sieve of Eratosthenes\n\n");
					sieve(Integer.parseInt(args[0]));
				}
			}catch(Exception e){
				System.out.println("Not an integer, too many inputs, or n > 538.");
				e.printStackTrace();
			}
		}
	}

	public static void sieve(int num){
		//Generate two identical array lists to remove composite numbers.
		ArrayList<Integer> primes = new ArrayList<Integer>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		long startTime, endTime, totalTime;

		try{
////		XXX RECURSIVE METHODS.
			System.out.println("RECURSIVE METHODS\nFor a given positiove integer n, output the first n primes:");
			startTime = System.nanoTime();
			result = recursion(primes, 2, num);
			endTime = System.nanoTime();
			totalTime = endTime - startTime;

			System.out.print("e.g. n = " + num + "\nOutput: ");
			primes.forEach(elem -> System.out.print(elem + " "));
			System.out.println("\n\nExecution time: " + (totalTime / Math.pow(10, 9)) + " seconds.\n\n\n");



			//Populate the numbers and primes array from 2 to num.
			System.out.println("For a given integer n > 1, list all the primes not exceeding n:");
			for(int i = 2; i <= num; i++){
				numbers.add(i);
			}
			primes = new ArrayList<Integer>(numbers);

			startTime = System.nanoTime();
			result = recursion2(primes, numbers, 0);
			endTime = System.nanoTime();
			totalTime = endTime - startTime;

			System.out.print("e.g. n = " + num + "\nOutput: ");
			result.forEach(elem -> System.out.print(elem + " "));
			System.out.println("\n\nExecution time: " + (totalTime / Math.pow(10, 9)) + " seconds.\n\n\n");
		}catch(Exception e){
			e.printStackTrace();
		}




		try{
////		XXX ITERATIVE METHODS.
			System.out.println("ITERATIVE METHODS\nFor a given positiove integer n, output the first n primes:");

			startTime = System.nanoTime();
			result = iteration1(num);
			endTime = System.nanoTime();
			totalTime = endTime - startTime;

			System.out.print("e.g. n = " + num + "\nOutput: ");
			result.forEach(elem -> System.out.print(elem + " "));
			System.out.println("\n\nExecution time: " + (totalTime / Math.pow(10, 9)) + " seconds.\n\n");



			System.out.println("For a given integer n > 1, list all the primes not exceeding n:");
			numbers.clear();
			for(int i = 2; i <= num; i++){
				numbers.add(i);
			}

			startTime = System.nanoTime();
			result = iteration2(numbers);
			endTime = System.nanoTime();
			totalTime = endTime - startTime;

			System.out.print("e.g. n = " + num + "\nOutput: ");
			result.forEach(elem -> System.out.print(elem + " "));
			System.out.println("\n\nExecution time: " + (totalTime / Math.pow(10, 9)) + " seconds.");
		}catch(Exception e){
			e.printStackTrace();
		}
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

	public static ArrayList<Integer> iteration1(int num){
		ArrayList<Integer> primes = new ArrayList<Integer>();

		for(int i = 2; primes.size() < num; i++){
			boolean isPrime = true;
			int possiblePrime = i;

			for(int j = 2; j < i; j++){
				if(i % j == 0){
					isPrime = false;
					continue;
				}
			}

			if(isPrime) primes.add(possiblePrime);
		}

		return primes;
	}

	public static ArrayList<Integer> iteration2(ArrayList<Integer> numbers){
		ArrayList<Integer> primes = new ArrayList<Integer>();

		for(int i = 2; i < numbers.get(numbers.size() - 1); i++){
			boolean isPrime = true;

			for(int j = 2; j < numbers.get(numbers.size() - 1); j++){
				if(i % j == 0 && i != j){
					isPrime = false;
					break;
				}
			}

			if(isPrime) primes.add(i);
		}

		return primes;
	}
}
