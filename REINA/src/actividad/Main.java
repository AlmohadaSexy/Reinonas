package actividad;

import java.util.Scanner;

public class Main {

	public static Reina[][] tablero;
	public static Reina r1, r21, r22, r23;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Filas: ");
		int n = sc.nextInt();
		System.out.print("Columnas: ");
		int m = sc.nextInt();
		tablero = new Reina[n][m];
		
		//rellenar
		for(int ii = 0; ii < tablero.length; ii++) {
			for(int jj = 0; jj < tablero[0].length; jj++) {
				tablero[ii][jj] = null;
			}
		}
			/**
			 * da fallo en:
			 *	x = 0, y = 3
			 *	x = 2, y = 0
			 */
		int x = (int)(Math.random() * n);
		int y = (int)(Math.random() * m);
		r1 = new Reina(x, y, "R1");
		tablero[r1.x][r1.y] = r1;
//		colocarReinas();
//		colocarReinas2();	
		imprimir();
		recolocaR();
//		imprimir();
//		recolocaR(2);
//		imprimir();
//		recolocaR(3);
		imprimir();
	}

	private static void imprimir() {
		System.out.println("Solucion: ");
		for(int ii = 0; ii < tablero.length; ii++) {
			for(int jj = 0; jj < tablero[0].length; jj++) {
				if(tablero[ii][jj] == null) {
					System.out.print("[\t\t] ");
				}else {
					System.out.print("[\t" + tablero[ii][jj] + "\t] ");
				}
			}
			System.out.println();
			System.out.println();
		}
	}
	
	private static void colocaR(Reina r2x) {
		tablero[r2x.x][r2x.y] = r2x;
	}
	
	private static void recolocaR() {
		r21 = new Reina("R21");
		loopr21:
		for(int ii = 0; ii < tablero.length; ii++) {
			for(int jj = 0; jj < tablero[0].length; jj++) {
				if(!(r1.x == ii && r1.y == jj) && (daJaque(ii,jj) /*&& !(coinciden(ii, jj, r22) || coinciden(ii, jj, r23))*/)) {
					r21.x = ii;
					r21.y = jj;
					colocaR(r21);
					break loopr21;
				}
			}
		}
		r22 = new Reina("R22");
		loopr22:
		for(int ii = 0; ii < tablero.length; ii++) {
			for(int jj = 0; jj < tablero[0].length; jj++) {
				if(!(r1.x == ii && r1.y == jj) && ((daJaque(ii,jj)) && ((!coinciden(ii, jj, r21) /*|| r1EstaEnElPutoMedio(ii, jj, r21)*/) /*|| coinciden(ii, jj, r23)*/))) {
					r22.x = ii;
					r22.y = jj;
					colocaR(r22);
					break loopr22;
				} else if(r1EstaEnElPutoMedio(ii, jj, r21) && daJaque(ii,jj)){
					r22.x = ii;
					r22.y = jj;
					colocaR(r22);
					break loopr22;
				}
			}
		}
		r23 = new Reina("R23");
		loopr23:
		for(int ii = 0; ii < tablero.length; ii++) {
			for(int jj = 0; jj < tablero[0].length; jj++) {
				if(!(r1.x == ii && r1.y == jj) && ((daJaque(ii,jj) && !coinciden(ii, jj, r21) && !coinciden(ii, jj, r22) ))) {
					r23.x = ii;
					r23.y = jj;
					colocaR(r23);
					break loopr23;
				} else if(r1EstaEnElPutoMedio(ii,jj,r21) && !coinciden(ii, jj, r22)){
					r23.x = ii;
					r23.y = jj;
					colocaR(r23);
					break loopr23;
				} else if(r1EstaEnElPutoMedio(ii, jj, r22) && !coinciden(ii, jj, r21)) {
					r23.x = ii;
					r23.y = jj;
					colocaR(r23);
					break loopr23;
				}
			}
		}
	}
	private static boolean daJaque(int ii, int jj) {
		if (r1.x == ii || r1.y == jj || Math.abs(ii - r1.x) == Math.abs(jj - r1.y)){
			return true;
		}
		return false;
	}
	private static boolean r1EstaEnElPutoMedio(int ii, int jj, Reina r2x) {
		if(((r2x.x < r1.x && r1.x < ii) && r1.y == jj)) {
			System.out.println("1.-" + ii + " " + jj);
			return true;
		} else if((r2x.y < r1.y && r1.y < jj) && r1.x == ii) {
			System.out.println("2.-" + ii + " " + jj);
			return true;
		} else if((r2x.x > r1.x && r1.x > ii) && r1.y == jj) {
			System.out.println("3.-" + ii + " " + jj);
			return true;
		} else if((r2x.y > r1.y && r1.y > jj) && r1.x == ii) {
			System.out.println("4.-" + ii + " " + jj);
			return true;
		} else if((Math.abs(r2x.x - r1.x) == Math.abs(r2x.y - r1.y)) && (Math.abs(ii - r1.x) == Math.abs(jj - r1.y)) && (Math.abs(ii - r1.x) == Math.abs(jj - r1.y))) {
			if((r2x.y < r1.y && r1.y < jj) && (r2x.x < r1.x && r1.x < ii)) {
				System.out.println("5.-" + ii + " " + jj);
				return true;
			} else if((r2x.y > r1.y && r1.y > jj) && (r2x.x > r1.x && r1.x > ii)) {
				System.out.println("6.-" + ii + " " + jj);
				return true;
			} else if((r2x.y < r1.y && r1.y < jj) && (r2x.x > r1.x && r1.x > ii)) {
				System.out.println("7.-" + ii + " " + jj);
				return true;
			} else if((r2x.y > r1.y && r1.y > jj) && (r2x.x < r1.x && r1.x < ii)) {
				System.out.println("8.-" + ii + " " + jj);
				return true;
			}
		}
		return false;
	}
	private static boolean coinciden(int ii, int jj, Reina r2x) {
		if(/*(r2x.x == ii && r2x.y == jj) || */r2x.x == ii || r2x.y == jj || Math.abs(ii - r2x.x) == Math.abs(jj - r2x.y)) {
			return true;
		}
		return false;
	}
}
