package it.beije.ananke.tombola;
import java.util.Arrays;
//import java.util.Arrays;
import java.util.Random;

public class Utility {


		private static boolean search(int[] col, int num) {
			for(int j = 0; j < 2; j++) {
				if (col[j] == num)
					return true;
				
			}
			return false;
		}


		public static int rand(int min, int max) {
			
			Random rand = new Random();
			int randomNum = rand.nextInt((max - min) + 1) + min;
			return randomNum;
			
		}


		public static int[] colDaDue() {
			int[] vColDaDue  = new int[3];
			int x = 0;
			for(int i=0; i<3; i++) {
				do
				x = Utility.rand(0, 8);
				while(Utility.search(vColDaDue, x));
				vColDaDue[i] = x;
			}
			Arrays.sort(vColDaDue);
			return vColDaDue;
			
		}


	
}
