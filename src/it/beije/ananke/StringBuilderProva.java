package it.beije.ananke;

public class StringBuilderProva {
	public static void main (String[] args) {
		StringBuilder sb = new StringBuilder();
//		sb.append("Ciao bello ").append(false).append('s');
//		sb.append(1.0).append("Wella pdio");
//		System.out.println(sb);
//		sb.delete(5, 7);
//		sb.reverse().append(7).reverse();
//		System.out.println(sb);
//		sb.insert(5, "Dove sono finito");
//		System.out.println(sb);
//		
//		sb.append(sb);
//		System.out.println(sb);
//		
//		String string = sb.substring(7,15);
//		System.out.println(string);
//		System.out.println(sb);
//		
//		System.out.println(sb.charAt(15));
//		System.out.println(sb.charAt(0));
		sb.append("Nel mezzo del cammin di nostra vita mi ritrovai per una selva oscura, che la diritta"
				+ "via era smarrita. ahi quanto a dir qual era è cosa dura, esta selva selvaggia e aspra"
				+ "e forte, che nel pensier rinova la paura");
		System.out.println(sb.charAt(205));
		System.out.println(sb.length());
		
		int index = 0;
		int newIndex;
		boolean flag = true;
		while(flag) {
			newIndex = index;
			System.out.println(sb.indexOf("e", newIndex));
			index = sb.indexOf("e",newIndex)+1;
			System.out.println(flag);
			if(index>=sb.length()) {
				flag=false;
			} else if(index < 0) {
				flag = false;
			}
			
		}
		
//		String s1 = "banana cinquantasei";
//		System.out.println(s1);
//		String s2 =s1.replace('a', 'A');
//		System.out.println(s2);
//		System.out.println(s1.equalsIgnoreCase(s2));
	}

}
