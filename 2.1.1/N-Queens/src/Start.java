import java.util.Scanner;

public class Start {
	
	void startProgram() {
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Digite o tamanho do tabuleiro: ");
		int n = scanner.nextInt();
		Board b = new Board(n);
		while(!b.achou) {
			b.generateBoard();
			b.hillClimbing(b, b.calculateCollision());
		}
		System.out.println("Outra configuração?(s/n): "); 
		Scanner scanner2 = new Scanner(System.in); 
		String prox = scanner2.nextLine();
		if(prox.equals("s")) this.startProgram();
		return;
	}
	
	public static void main(String[] args) {
		Start e = new Start();
		e.startProgram();
		return;
		}
}
