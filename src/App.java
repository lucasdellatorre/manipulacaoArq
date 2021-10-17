import java.io.*;
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
    private List<ColetaDomiciliar> ultimaConsulta;
    public App()
    {
        in = new Scanner(System.in);
        list = new ArrayList<>();
        ultimaConsulta = new ArrayList<>();
    }

    public void inicializa()
    {
        System.out.println("Informe o do arquivo: ");
        String arq = in.nextLine();
        Path path = Paths.get(arq);
        try(BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset()))
        {
            String line = br.readLine();
            line = br.readLine();
            while((line = br.readLine()) != null)
            {
                Scanner sc = new Scanner(line).useDelimiter(";");
                String dataExtracao = sc.next().trim();
                String categoria = sc.next().trim();
                String codLogradouro = sc.next().trim();
                String diasColeta = sc.next().trim();
                int imparFim = Integer.parseInt(sc.next());
                int imparInic = Integer.parseInt(sc.next());
                String lado = sc.next().trim();
                String nomeLogradouro = sc.next().trim();
                int parFim = Integer.parseInt(sc.next());
                int parInicio = Integer.parseInt(sc.next());
                String area = sc.next().trim();
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
                    consultaEndereco();
                    break;
                case 4:
                    break;

            }
        }while(op != 0);
    }

    private void consultaEndereco()
    {
        System.out.println("Informe o endereco: ");
        String endereco = in.nextLine();
        try
        {
            String msg = null;
            for(ColetaDomiciliar cd : list)
                if (cd.getNomeLogradouro().equalsIgnoreCase(endereco))
                    msg += cd + "\n";

            if(msg == null)
                throw new DadosAbertosException();
        }
        catch(DadosAbertosException da)
        {
            System.err.println(da);
        }
    }

    private void mostraInformacoes()
    {
        try
        {
            String msg = null;
            for(ColetaDomiciliar cd : list)
            {
                msg += cd + "\n";
            }
            if(msg == null)
                throw new DadosAbertosException();
            System.out.println(ultimaConsulta);
        }
        catch(DadosAbertosException da)
        {
            System.err.println(da);
        }
    }

    private void salvaDados()
    {
        System.out.println("Informe o nome do arquivo texto que será utilizado para armazenar os dados: ");
        String nomeArq = in.nextLine();
        Path path = Paths.get(nomeArq);
        try
        {
            BufferedWriter bw = Files.newBufferedWriter(path, Charset.defaultCharset());
            PrintWriter pw = new PrintWriter(bw);
        }
        catch(IOException io)
        {
            System.err.format("Erro de E/S: %s%n", io);
        }

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
