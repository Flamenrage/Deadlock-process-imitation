package laba;

import java.util.Random;

public class Main {
	    public static Random numb;

		public static void main(String[] args) {
		numb = new Random();
	    int n = Math.abs(numb.nextInt());
		numb = new Random(n);
		ProcessDispatcher pb = new ProcessDispatcher(false);
		System.out.print("OS start without blocking\n");
		pb.run();
		System.out.print("General Time without blocking " + pb.getGeneralTimeWork() +"\nOS start with blocking\n");
		numb = new Random(n);
		pb = new ProcessDispatcher(true);
		pb.run();
		System.out.printf("General Time with blocking " +
				 pb.getGeneralTimeWork());
	}
}
