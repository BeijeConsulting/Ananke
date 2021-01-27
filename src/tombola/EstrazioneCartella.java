package tombola;

import java.util.*;
import java.util.Random;

public class EstrazioneCartella {

	ArrayList<Integer> numeri = new ArrayList<>();

	public EstrazioneCartella() {
		for (int i = 0; i < 90; i++)
			numeri.add(i + 1);

	}
	private int maxVect(int arr[]) {
		int max=0;
		int index=0;
		for(int i=0;i<9;i++) {
			if(max<arr[i]) {
			max=arr[i];
			index=i;
			}
		}
		return index;
	}
public int getNumeroSR(int i) {
	Random r=new Random();
	boolean stop=false;
	int num=0;
	int disp[]=new int[9];
	
	for(int z=1;z<9;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[0]++;
	}
	for(int z=10;z<19;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[1]++;
	}
	for(int z=20;z<29;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[2]++;
	}
	for(int z=30;z<39;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[3]++;
	}
	for(int z=40;z<49;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[4]++;
	}
	
	for(int z=50;z<59;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[5]++;
	}
	for(int z=60;z<69;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[6]++;
	}
	for(int z=70;z<79;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[7]++;
	}
	for(int z=80;z<=90;z++) {
		if(numeri.contains(Integer.valueOf(z)))
			disp[8]++;
	}

	int app=maxVect(disp)+1;
	if(i!=app) {
	return -1;
	}
	else {
	
	switch(i) {
	case 1:
for(int j=0;j<10;j++)
	if(numeri.contains(j)) {
		stop=true;
		break;
	}
		if(stop) {
		while(!numeri.contains(num))
		num= (r.nextInt(9)+1);
		}else {
			num=-1;
		}
		
		break;
	case 2:
		for(int j=10;j<20;j++)
			if(numeri.contains(j)) {
				stop=true;
				break;
			}
		if(stop) {
		while(!numeri.contains(num))
		num= (r.nextInt(10)+10);
		}else {
			num=-1;
		}

		
		break;
	case 3:
		for(int j=20;j<30;j++)
			if(numeri.contains(j)) {
				stop=true;
				break;
			}
		if(stop) {
		while(!numeri.contains(num))
		num= (r.nextInt(10)+20);
		}else {
			num=-1;
		}
		break;
	case 4:
		for(int j=30;j<40;j++)
			if(numeri.contains(j)) {
				stop=true;
				break;
			}
		if(stop) {
		
		while(!numeri.contains(num))
		num= (r.nextInt(10)+30);
		}else {
			num=-1;
		}
		break;
	case 5:
		for(int j=40;j<50;j++)
			if(numeri.contains(j)) {
				stop=true;
				break;
			}
		if(stop) {
		while(!numeri.contains(num))
		num= (r.nextInt(10)+40);
		}else {
			num=-1;
		}
		break;
	case 6:
		for(int j=50;j<60;j++)
			if(numeri.contains(j)) {
				stop=true;
				break;
			}
		if(stop) {

		while(!numeri.contains(num))
		num= (r.nextInt(10)+50);
		}else {
			num=-1;
		}
		break;
	case 7:
		for(int j=60;j<70;j++)
			if(numeri.contains(j)) {
				stop=true;
				break;
			}
		if(stop) {

		while(!numeri.contains(num))
		num= (r.nextInt(10)+60);
		}else {
			num=-1;
		}
		break;
	case 8:
		for(int j=70;j<80;j++)
			if(numeri.contains(j)) {
				stop=true;
				break;
			}
		if(stop) {

		while(!numeri.contains(num))
		num= (r.nextInt(10)+70);
		}else {
			num=-1;
		}
		break;
	case 9:
		for(int j=80;j<=90;j++)
			if(numeri.contains(j)) {
				stop=true;
				break;
			}
		if(stop) {

		while(!numeri.contains(num))
		num= (r.nextInt(11)+80);
		}else {
			num=-1;
		}
		break;
	}
	}
	if(stop)
	numeri.remove((Integer)num);
	
	return num;
	
	
}
	public int getNumero(int i) {
		Random r=new Random();
		boolean stop=false;
		int num=0;
		switch(i) {
		case 1:
	for(int j=0;j<10;j++)
		if(numeri.contains(j)) {
			stop=true;
			break;
		}
			if(stop) {
			while(!numeri.contains(num))
			num= (r.nextInt(9)+1);
			}else {
				num= (r.nextInt(9)+1);
			}
			
			break;
		case 2:
			for(int j=10;j<20;j++)
				if(numeri.contains(j)) {
					stop=true;
					break;
				}
			if(stop) {
			while(!numeri.contains(num))
			num= (r.nextInt(10)+10);
			}else {
				num= (r.nextInt(10)+10);
			}
			
			break;
		case 3:
			for(int j=20;j<30;j++)
				if(numeri.contains(j)) {
					stop=true;
					break;
				}
			if(stop) {
			while(!numeri.contains(num))
			num= (r.nextInt(10)+20);
			}else {
				num= (r.nextInt(10)+20);
			}
			break;
		case 4:
			for(int j=30;j<40;j++)
				if(numeri.contains(j)) {
					stop=true;
					break;
				}
			if(stop) {
			
			while(!numeri.contains(num))
			num= (r.nextInt(10)+30);
			}else {
				num= (r.nextInt(10)+30);
			}
			break;
		case 5:
			for(int j=40;j<50;j++)
				if(numeri.contains(j)) {
					stop=true;
					break;
				}
			if(stop) {
			while(!numeri.contains(num))
			num= (r.nextInt(10)+40);
			}else {
				num= (r.nextInt(10)+40);
			}
			break;
		case 6:
			for(int j=50;j<60;j++)
				if(numeri.contains(j)) {
					stop=true;
					break;
				}
			if(stop) {

			while(!numeri.contains(num))
			num= (r.nextInt(10)+50);
			}else {
				num= (r.nextInt(10)+50);
			}
			break;
		case 7:
			for(int j=60;j<70;j++)
				if(numeri.contains(j)) {
					stop=true;
					break;
				}
			if(stop) {

			while(!numeri.contains(num))
			num= (r.nextInt(10)+60);
			}else {
				num= (r.nextInt(10)+60);
			}
			break;
		case 8:
			for(int j=70;j<80;j++)
				if(numeri.contains(j)) {
					stop=true;
					break;
				}
			if(stop) {

			while(!numeri.contains(num))
			num= (r.nextInt(10)+70);
			}else {
				num= (r.nextInt(10)+70);
			}
			break;
		case 9:
			for(int j=80;j<=90;j++)
				if(numeri.contains(j)) {
					stop=true;
					break;
				}
			if(stop) {

			while(!numeri.contains(num))
			num= (r.nextInt(11)+80);
			}else {
				num= (r.nextInt(11)+80);
			}
			break;
		}
		if(stop)
		numeri.remove((Integer)num);
		
		return num;
		
	}
public void ripristinaCartella() {
	numeri.clear();
	for (int i = 0; i < 90; i++)
		numeri.add(i + 1);
}

 public void aggiungiNumero(int x) {
	 numeri.add(x);
 }
}