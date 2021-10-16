import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class App
{
    private Scanner in;
    private List<ColetaDomiciliar> list;
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
                Scanner sc = new Scanner(line).useDelimiter(";");
                String dataExtracao = sc.next();
                String categoria = sc.next();
                String codLogradouro = sc.next();
                String diasColeta = sc.next();
                int imparFim = Integer.parseInt(sc.next());
                int imparInic = Integer.parseInt(sc.next());
                String lado = sc.next();
                String nomeLogradouro = sc.next();
                int parFim = Integer.parseInt(sc.next());
                int parInicio = Integer.parseInt(sc.next());
                String area = sc.next();
                list.add(new ColetaDomiciliar(dataExtracao, categoria, codLogradouro, diasColeta,
                imparFim, imparInic, lado, nomeLogradouro, parFim, parInicio, area));
            }
        }
        catch(IOException io)
        {
            System.err.format("Erro de E/S: %s%n", io);
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
                    mostraInformacoes();
                    break;
                case 3:
                    break;
                case 4:
                    break;

            }
        }while(op != 0);
    }

    public void mostraInformacoes()
    {
        String msg = null;
        for(ColetaDomiciliar cd : list)
        {
            msg += cd.toString() + "\n";
        }
        if(msg == null)
            throw new NoSuchElementException();
        else
            System.out.println(msg);
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
