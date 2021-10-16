import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class App
{
    private Scanner in;
    private List<ColetaSeletiva> list;
    public App()
    {
        in = new Scanner(System.in);
        list = new ArrayList<>();
    }

    public void inicializa()
    {
        Path path = Paths.get("coleta_seletiva.csv");
        try(BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset()))
        {
            String line = br.readLine();
            while((line = br.readLine()) != null)
            {
                String[] atributos = line.split(";");
                String dataExtracao = atributos[0];
                String categoria = atributos[1];
                String codLogradouro = atributos[2];
                String diasColeta = atributos[3];
                int imparFim = Integer.parseInt(atributos[4]);
                int imparInic = Integer.parseInt(atributos[5]);
                String lado = atributos[6];
                String nomeLogradouro = atributos[7];
                int parFim = Integer.parseInt(atributos[8]);
                int parInicio = Integer.parseInt(atributos[9]);
                String area = atributos[10];
                list.add(new ColetaSeletiva(dataExtracao, categoria, codLogradouro, diasColeta,
                imparFim, imparInic, lado, nomeLogradouro, parFim, parInicio, area));
            }
        }
        catch(IOException io)
        {
            System.err.format("Erro de E/S: %s%n", e1);
        }
        catch(Exception e)
        {
            System.out.println("Erro: " + e);
            System.out.print("Erro - trace da falha: ");
            e.printStackTrace();
        }
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
                    inicializa();
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
        System.out.println("[0] Sair");
        System.out.println("[1] Carregar dados");
        System.out.println("[2] Apresentar dados");
        System.out.println("[3] Consultar por endereco");
        System.out.println("[4] Salvar dados da consulta");
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
