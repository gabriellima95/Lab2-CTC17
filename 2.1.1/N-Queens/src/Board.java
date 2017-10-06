import java.util.Random;

public class Board {
	
	int[][] board;
	private int N;
	private int print=0;
	public boolean achou=false;
	public Board(int _N) {
		//board=new int[N][N];
		N=_N;
		board = generateBoard();
		int i,j;
		//this.write();
	}
	
	int[][] generateBoard(){
		int i;
		Random rnd=new Random();
		int [][] _board=new int[N][N];
		for(i=0;i<N;i++) {
			int j= rnd.nextInt(N);
			_board[j][i]=1;
		}
		return _board;
	}
	
	int calculateCollision(){
		//System.out.println(N);
		int i, j, k, l;
		int result=0;
		for(i=0;i<N;i++) {
			for(j=0;j<N;j++) {
				if(board[i][j]==1) {
					for(k=0;k<N;k++) {
						if(board[i][k]==1 && k!=j)
							result++;
					}
					k=i; l=j;
					//System.out.println(k +" "+l);
					while(k!=N && l!=N) {
						//System.out.println(k +" "+l);
						if(k!=i && board[k][l]==1) result++;
						//System.out.println(k +" "+l);
						k++;l++;
					}
					k=i; l=j;
					while(k!=-1 && l!=N) {
						if(k!=i && board[k][l]==1) result++;
						k--;l++;
					}
					k=i; l=j;
					while(k!=N && l!=-1) {
						if(k!=i && board[k][l]==1) result++;
						k++;l--;
					}
					k=i; l=j;
					while(k!=-1 && l!=-1) {
						if(k!=i && board[k][l]==1) result++;
						k--;l--;
					}
				}
			}
		}
		return result/2;
	}
	
	boolean hillClimbing(Board aux,int predecessor) {
		int i,j,k;
		//System.out.println(aux.calculateCollision());
		//aux.write();
		if(aux.calculateCollision()==0 && print==0) {
			//System.out.println("--------ACABOU-------");
			aux.write();
			//System.out.println(aux.calculateCollision());
			print++;
			achou=true;
			return true;
		}
		for(j=0;j<N;j++) {
			for(i=0;i<N;i++) {
				if(board[i][j]==1) {
					//System.out.println("Rainha em: "+i+" "+j);
					for(k=0;k<N;k++) {
						if(k!=i) {
						//System.out.println("Tentando por a rainha em "+k+" "+j);
						aux.board[k][j]=1;
						aux.board[i][j]=0;
						if(aux.calculateCollision()<predecessor) 
							if(hillClimbing(aux,aux.calculateCollision()))
								return true;
						aux.board[k][j]=0;
						aux.board[i][j]=1;
						}
					}
				}
			}
		}
		//System.out.println("--------ACABOU SEM DAR CERTO-------");
		//this.write();
		return false;
	}
	
	void write() {
		int i,j;
		for(i=0;i<N;i++) {
			for(j=0;j<N;j++)
					System.out.print(board[i][j]);
			System.out.println();
			}
	}
}