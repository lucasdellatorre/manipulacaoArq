import java.util.Scanner;
public class App
{
    private Scanner in;
    public App()
    {
        in = new Scanner(System.in);
    }

    public void inicializa()
    {

    }

    public void executa()
    {
        int op;
        do {
            apresentaMenuOpcoes();
            System.out.println("Digite uma opcao: ");
            op = leInteiro();
            switch (op)
            {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;

            }
        }while(op != 0);
    }

    private void apresentaMenuOpcoes()
    {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }

    private int leInteiro()
    {
        int numero = -1;
        boolean ok = false;
        while (!ok)
        {
            try
            {
                numero = in.nextInt();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Entrada invalida. Digite um numero inteiro");
            }
            finally
            {
                String aux = in.nextLine();
            }
        }
        return numero;
    }

}
