import java.util.Scanner;
public class Tictactoe
{
    public static void main(String[] args)
    {
        char[][]board=new char[3][3];
        initializeBoard(board);
        Scanner scanner=new Scanner(System.in);
        while(true)
        {
            printBoard(board);
            makehumanmove(board,scanner);
            if(isGameover(board))
            {
                printBoard(board);
                break;
            }
            printBoard(board);
            makeaimove(board);
            boolean isGameover = false;
            if(isGameover)
            {
                printBoard(board);
                break;
            }
        }
        scanner.close();
    }
    public static void initializeBoard(char[][]board)
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                board[i][j]=' ';
            }
        }
    }
    public static void printBoard(char[][]board)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++) {
                System.out.println(board[i][j]);
                if (j < 2) {
                    System.out.println("|");
                }
            }
            System.out.println();
            if(i<2)
            {
                System.out.println("-------------");
            }
        }
    }
    public static boolean isBoardFull(char[][]board)
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if (board[i][j]==' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isGameover(char[][]board)
    {
        return isBoardFull(board)||hasPlayerWon(board,'X')||hasPlayerWon(board,'O');

    }
    public static boolean hasPlayerWon(char[][]board,char player)
    {
        return((board[0][0]==player && board[0][1]==player && board[0][2]==player)
                ||(board[1][0]==player) && board[1][1]==player  && board[1][2]==player)
                ||(board[2][0] == player && board[2][1] == player && board[2][2] == player)
                ||(board[0][0] == player && board[1][0] == player && board[2][0] == player)
                ||(board[0][1] == player && board[1][1] == player && board[2][1] == player)
                || (board[0][2] == player && board[1][2] == player && board[2][2] == player)
                || (board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
    public static <beta> int[]minmax(char[][]board, int depth, boolean isMaximizing, int alpha,int beta)
    {
        if (isGameover(board))
        {
            int score=evaluate(board);
            return new int[]{score,-1,-1};
        }
        int[]bestMove=new int[]
                {isMaximizing?Integer.MIN_VALUE:Integer.MAX_VALUE,-1,-1};
                for (int i=0;i<3;i++)
                {
                    for (int j=0;j<3;j++)
                    {
                        if(board[i][j]==' ')
                        {
                            board[i][j]=isMaximizing?'O':'X';
                            int[] currentMove=minmax(board,depth+1,!isMaximizing,alpha,beta);
                            board[i][j]=' ';
                            if(isMaximizing)
                            {
                                if(currentMove[0]>bestMove[0])
                                {
                                    bestMove[0]=currentMove[0];
                                    bestMove[1]=i;
                                    bestMove[2]=j;

                                }
                                alpha=Math.max(alpha,bestMove[0]);
                            }
                            else
                            {
                                if (currentMove[0]<bestMove[0])
                                {
                                    bestMove[0]=currentMove[0];
                                    bestMove[1]=i;
                                    bestMove[2]=j;
                                }
                                beta=Math.min(beta,bestMove[0]);
                            }
                            if (beta<=alpha)
                            {
                                break;
                            }

                        }
                    }
                }
                return bestMove;
    }
    public static int evaluate(char[][]board)
    {
        if(hasPlayerWon(board,'O'))
        {
            return 10;
        }
        else if(hasPlayerWon(board,'X'))
        {
            return -10;
        }
        else
        {
            return 0;
        }
    }
    public static void makeaimove(char[][]board)
    {
        int[] bestMove=minmax(board,0,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
        board[bestMove[1]][bestMove[2]]='O';
    }
    public static void makehumanmove(char[][]board,Scanner scanner)
    {
        while(true)
        {
            System.out.println("Enter your move(1-9):");
            int move= scanner.nextInt()-1;
            int row=move/3;
            int col=move%3;
            if(move>=0&&move<=8&&board[row][col]==' ')
            {
                board[row][col]='X';
                break;
            }
            else
            {
                System.out.println("invalid move!!!Please try again.");
            }
        }
    }
}
