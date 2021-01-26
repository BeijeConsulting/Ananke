package it.beije.ananke.regnoAnimale;

public abstract class Vertebrati extends Animali {

		public boolean cordati;
		public int numVertebre;
		
		public Vertebrati(boolean cordati, String nome, int età) {
			
			super(nome, età);
			this.cordati = cordati;
			this.numVertebre = 60;
			
		}
		
		public Vertebrati(boolean cordati,int numeroVertebre, String nome, int età) {
			
			super(nome, età);
			this.cordati = cordati;
			this.numVertebre = numeroVertebre;
			
		}
		
		//public abstract String verso();
		
}
