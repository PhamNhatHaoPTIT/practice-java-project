package com.interview.practice;

import java.util.PriorityQueue;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);

		int n = reader.nextInt();
		int m = reader.nextInt();

		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
		reader.nextLine();
		for (int i = 0; i < n; i++) {
			queue.add(reader.nextInt());
		}

		int sum = 0;
		for (int i = 0; i < m; i++) {
			int numb = queue.poll();
			sum+=numb;
			queue.add(numb/2);
		}
		System.out.println(sum);
	}

}
