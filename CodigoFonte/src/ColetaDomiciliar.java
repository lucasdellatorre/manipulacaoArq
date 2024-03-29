public class ColetaDomiciliar
{
    private String dataExtracao;
    private String categoria;
    private String codLogradouro;
    private String diasColeta;
    private int imparFim;
    private int imparInicio;
    private String lado;
    private String nomeLogradouro;
    private int parFim;
    private int parInicio;
    private String area;

    public ColetaDomiciliar
            (String dataExtracao, String categoria, String codLogradouro,
             String diasColeta, int imparFim, int imparInicio, String lado,
             String nomeLogradouro, int parFim, int parInicio, String area)
    {
        this.dataExtracao = dataExtracao;
        this.categoria = categoria;
        this.codLogradouro = codLogradouro;
        this.diasColeta = diasColeta;
        this.imparFim = imparFim;
        this.imparInicio = imparInicio;
        this.lado = lado;
        this.nomeLogradouro = nomeLogradouro;
        this.parFim = parFim;
        this.parInicio = parInicio;
        this.area = area;
    }

    public String toString()
    {
        return "Data Extracao: " + dataExtracao +
                " | Categoria: " + categoria +
                " | Codigo do Logradouro: " + codLogradouro +
                " | Dias de Coleta: " + diasColeta +
                " | Impar Fim: " + imparFim +
                " | Impar Inicio: " + imparInicio +
                " | Lado: " + lado +
                " | Nome do Logradouro: " + nomeLogradouro +
                " | Par Fim: " + parFim +
                " | Par Inicio: " + parInicio +
                " | Area: " + area;
    }
    public String getDataExtracao() {
        return dataExtracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCodLogradouro() {
        return codLogradouro;
    }

    public String getDiasColeta() {
        return diasColeta;
    }

    public int getImparFim() {
        return imparFim;
    }

    public int getImparInicio() {
        return imparInicio;
    }

    public String getLado() {
        return lado;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public int getParFim() {
        return parFim;
    }

    public int getParInicio() {
        return parInicio;
    }

    public String getArea() {
        return area;
    }
}


