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
			
		int x = (int)(Math.random() * n);
		int y = (int)(Math.random() * m);
		r1 = new Reina(x, y, "R1");
		tablero[x][y] = r1;
		colocarReinas();
//		colocarReinas2();
		imprimir();
		recolocaR(r21);
		imprimir();
		recolocaR(r22);
		imprimir();
		recolocaR(r23);
		boolean coinciden = true;
//		while(coinciden) {
//			if(coincidenR(r21,r22)) {
//				recolocaR(r21);
//			} else if(coincidenR(r22,r23)) {
//				recolocaR(r22);
//			} else if (coincidenR(r23,r21)) {
//				recolocaR(r23);
//			} else {
//				coinciden = false;
//			}
//		}
		imprimir();
	}
	private static boolean coincidenR(Reina r2x1, Reina r2x2) {
		if(r2x1.x == r2x2.x || r2x1.y == r2x2.y || Math.abs(r2x2.x - r2x1.x) == Math.abs(r2x2.y - r2x1.y)) {
			return true;
		}
		return false;
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
	
	private static void colocarReinas() {
		int x = (int)(Math.random() * tablero.length);
		int y = (int)(Math.random() * tablero[0].length);
		while(x == r1.x && y == r1.y) {
			x = (int)(Math.random() * tablero.length);
			y = (int)(Math.random() * tablero[0].length);
		}
		r21 = new Reina(x, y, "R21");
		colocaR(r21);
		x = (int)(Math.random() * tablero.length);
		y = (int)(Math.random() * tablero[0].length);
		while((x == r1.x && y == r1.y) || (x == r21.x && y == r21.y)) {
			x = (int)(Math.random() * tablero.length);
			y = (int)(Math.random() * tablero[0].length);
		}
		r22 = new Reina(x, y, "R22");
		colocaR(r22);
		x = (int)(Math.random() * tablero.length);
		y = (int)(Math.random() * tablero[0].length);
		while((x == r1.x && y == r1.y) || (x == r21.x && y == r21.y) || (x == r22.x && y == r22.y)) {
			x = (int)(Math.random() * tablero.length);
			y = (int)(Math.random() * tablero[0].length);
		}
		r23 = new Reina(x, y, "R23");
		colocaR(r23);
	}
	private static void colocaR(Reina r2x) {
		tablero[r2x.x][r2x.y] = r2x;
	}
	
	private static void recolocaR(Reina r2x) {
		if(r2x.x == r21.x && r2x.y == r21.y) {
			tablero[r2x.x][r2x.y] = null;
			r21 = null;
		} else if(r2x.x == r22.x && r2x.y == r22.y) {
			tablero[r2x.x][r2x.y] = null;
			r22 = null;
		} else if(r2x.x == r23.x && r2x.y == r23.y) {
			tablero[r2x.x][r2x.y] = null;
			r23 = null;
		}
		if(r21 == null) {
			loopr21:
			for(int ii = 0; ii < tablero.length; ii++) {
				for(int jj = 0; jj < tablero[0].length; jj++) {
					if(( r1.x == ii || r1.y == jj || Math.abs(ii - r1.x) == Math.abs(jj - r1.y) ) /*&& !(coinciden(ii, jj, r22) || coinciden(ii, jj, r23))*/) {
						r2x.x = ii;
						r2x.y = jj;
						r21 = r2x;
						colocaR(r21);
						break loopr21;
					}
				}
			}
		} else if(r22 == null) {
			loopr22:
			for(int ii = 0; ii < tablero.length; ii++) {
				for(int jj = 0; jj < tablero[0].length; jj++) {
					if(( r1.x == ii || r1.y == jj || Math.abs(ii - r1.x) == Math.abs(jj - r1.y) ) && !(coinciden(ii, jj, r21) /*|| coinciden(ii, jj, r23)*/)) {
						r2x.x = ii;
						r2x.y = jj;
						r22 = r2x;
						colocaR(r22);
						break loopr22;
					}
				}
			}
		} else if(r23 == null) {
			loopr23:
			for(int ii = 0; ii < tablero.length; ii++) {
				for(int jj = 0; jj < tablero[0].length; jj++) {
					if(( r1.x == ii || r1.y == jj || Math.abs(ii - r1.x) == Math.abs(jj - r1.y) ) && !(coinciden(ii, jj, r21) || coinciden(ii, jj, r22))) {
						if(coinciden(ii,jj,r21) && !coinciden(ii,jj,r22)) {
							
							recolocaR(r21);
						}
						
						
						
						r2x.x = ii;
						r2x.y = jj;
						r23 = r2x;
						colocaR(r23);
						break loopr23;
					} else {
						System.out.println("Solucion pa 3 reinas no disponible");
					}
				}
			}
		}
	}
	
	private static boolean coinciden(int ii, int jj, Reina r2x) {
		if(r2x.x == ii || r2x.y == jj || Math.abs(ii - r2x.x) == Math.abs(jj - r2x.y)) {
			return true;
		}
		return false;
	}
	
	public static void reinasCoinciden() {
		if(r21.x == r22.x || r21.y == r22.y || Math.abs(r21.x - r22.x) == Math.abs(r21.y - r22.y)) {
			recolocaR(r21);
		} else if(r22.x == r23.x || r22.y == r23.y || Math.abs(r22.x - r23.x) == Math.abs(r22.y - r23.y)) {
			recolocaR(r22);
		} else if(r23.x == r21.x || r23.y == r21.y || Math.abs(r23.x - r21.x) == Math.abs(r23.y - r21.y)) {
			recolocaR(r23);
		} else {
			
		}
	}
}
