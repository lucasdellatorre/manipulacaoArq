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
        System.out.println("Informe o nome do arquivo: ");
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
        do
        {
            apresentaMenuOpcoes();
            System.out.print("Digite uma opcao: ");
            op = leInteiro();
            switch (op)
            {
                case 0:
                    System.out.println("Sistema finalizado!");
                    break;
                case 1: inicializa();        break;
                case 2: mostraInformacoes(); break;
                case 3: consultaEndereco();  break;
                case 4: salvaDados();        break;
            }
        }while(op != 0);
    }

    private <T> void printArray(List<T> array)
    {
        for(T tipo : array)
            System.out.println(tipo);
    }

    private void consultaEndereco()
    {
        System.out.print("Informe o endereco: ");
        String endereco = in.nextLine();
        try
        {
            //Limpa a ultima consulta
            ultimaConsulta.clear();
            for(ColetaDomiciliar cd : list)
                if (cd.getNomeLogradouro().equalsIgnoreCase(endereco))
                    ultimaConsulta.add(cd);

            if(ultimaConsulta.isEmpty())
                throw new DadosAbertosException();
            printArray(ultimaConsulta);
        }
        catch(DadosAbertosException da)
        {
            System.err.println(da);
        }
    }

    private void mostraInformacoes()
    {
        StringBuilder msg = new StringBuilder();
        for(ColetaDomiciliar cd : list)
            msg.append(cd).append("\n");
        System.out.println(msg);
    }

    private void salvaDados()
    {
        System.out.print("Informe o nome do arquivo texto que será utilizado para armazenar os dados: ");
        String nomeArq = in.nextLine();
        Path path = Paths.get(nomeArq);
        try(PrintWriter pw = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()));)
        {
            for(ColetaDomiciliar cd : ultimaConsulta)
                pw.format("%s;%s;%s;%s;%d;%d;%s;%s;%d;%d;%s%n", cd.getDataExtracao(), cd.getCategoria(),
                        cd.getCodLogradouro(), cd.getDiasColeta(), cd.getImparFim(), cd.getImparInicio(),
                        cd.getLado(), cd.getNomeLogradouro(), cd.getParFim(), cd.getParInicio(), cd.getArea());
        }
        catch(IOException io)
        {
            System.err.format("Erro de E/S: %s%n", io);
        }

    }

    private void apresentaMenuOpcoes()
    {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║           *Menu*            ║");
        System.out.println("║[0] Sair                     ║");
        System.out.println("║[1] Carregar dados           ║");
        System.out.println("║[2] Apresentar dados         ║");
        System.out.println("║[3] Consultar por endereco   ║");
        System.out.println("║[4] Salvar dados da consulta ║");
        System.out.println("╚═════════════════════════════╝");
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
