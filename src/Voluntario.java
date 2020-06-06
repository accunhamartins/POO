import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Voluntario extends UtilizadorSistema implements Serializable {
    private String nome;
    private String codigo;
    private boolean disponivel;
    private boolean transporteMedico;
    private double latitude;
    private double longitude;
    private LocalDate inicio_transporte;
    private double raio_acao;
    private double classificacao;
    private int avaliacoes;
    private ArrayList<Encomenda> historico;

    //Construtores de classe
    //Construtor de classe por omissão
    public Voluntario(){
        super();
        this.nome = " ";
        this.codigo = " ";
        this.disponivel = false;
        this.latitude = 0;
        this.longitude = 0;
        this.inicio_transporte = LocalDate.now();
        this.raio_acao = 0;
        this.classificacao = 0;
        this.avaliacoes = 0;
        this.historico = new ArrayList<>();
    }
    //Construtor de classe por clone
    public Voluntario(Voluntario a){
        super(a);
        this.nome = a.getNome();
        this.codigo = a.getCodigo();
        this.disponivel = a.getDisponibilidade();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.inicio_transporte = a.getInicio_transporte();
        this.raio_acao = a.getRaio_acao();
        this.classificacao = a.getClassificacao();
        this.avaliacoes = a.getAvaliacoes();
        this.setHistorico(a.getHistorico());
    }

    //Construtor parametrizado
    public Voluntario(String email,String password,String a, String b, boolean c, double d, double e, LocalDate f, double g, ArrayList<Encomenda> h, double classificacao, int avaliacoes, boolean transporteMedico){
        super(email, password, "Voluntario");
        this.nome = a;
        this.codigo = b;
        this.disponivel = c;
        this.latitude =  d;
        this.longitude = e;
        this.inicio_transporte = f;
        this.raio_acao = g;
        this.classificacao = classificacao;
        this.avaliacoes = avaliacoes;
        this.setHistorico(h);
        this.transporteMedico = transporteMedico;
    }

    public boolean aceitoTransporteMedicamentos(){
        return this.transporteMedico;
    }

    public void aceitaMedicamentos(boolean state){
        this.transporteMedico = state;
    }

    //Métodos de obtenção de variáveis
    public String getNome(){
      return this.nome;
    }

    public double getClassificacao() {
        return classificacao;
    }
    public String getCodigo(){
      return this.codigo;
    }

    public boolean getDisponibilidade(){
        return this.disponivel;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public double getRaio_acao(){
        return this.raio_acao;
    }

    public LocalDate getInicio_transporte(){
        return this.inicio_transporte;
    }

    public int getAvaliacoes() {
        return avaliacoes;
    }

    public ArrayList<Encomenda> getHistorico(){
        ArrayList<Encomenda> res = new ArrayList<>();
        for(Encomenda s: this.historico) res.add(s);
        return res;
    }

    //Método de definição de variáveis
    public void setNome(String a){
      this.nome = a;
    }

    public void setAvaliacoes(int avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public void setCodigo(String a){
      this.codigo = a;
    }

    public void setDisponibilidade(boolean a){
        this.disponivel = a;
    }

    public void setLatitude(double a){
        this.latitude = a;
    }

    public void setLongitude(double a){
        this.longitude = a;
    }

    public void setInicio_Transporte(LocalDate a){
        this.inicio_transporte = a;
    }

    public void setRaio_acao(double a){
        this.raio_acao = a;
    }

    public void setHistorico(ArrayList<Encomenda> a){
        this.historico = new ArrayList<>();
        for(Encomenda s: a) this.historico.add(s);
    }

    //Método de clonar um objeto
    public Voluntario clone(){
        return new Voluntario(this);
    }

    //public equals
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voluntario v = (Voluntario) o;
        return this.nome.equals(v.getNome())
        && this.codigo.equals(v.getCodigo())
        && this.disponivel ==  v.getDisponibilidade()
        && this.latitude == v.getLatitude()
        && this.longitude == v.getLongitude()
        && this.inicio_transporte.equals(v.getInicio_transporte())
        && this.raio_acao == v.getRaio_acao()
        && this.historico.equals(v.getHistorico());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Nome: ");
        sb.append(this.nome + "\n");
        sb.append("Código de voluntário: ");
        sb.append(this.codigo + "\n");
        sb.append("Disponível: ");
        sb.append(this.disponivel + "\n");
        sb.append("Latitude: ");
        sb.append(this.latitude + "\n");
        sb.append("Longitude: ");
        sb.append(this.longitude + "\n");
        sb.append("Data de início de entrega: ");
        sb.append(this.inicio_transporte + "\n");
        sb.append("Raio de ação: ");
        sb.append(this.raio_acao + "\n");
        sb.append("Registos de encomendas: ");
        sb.append(this.historico.toString());


        return sb.toString();
    }

    public void updateRate(Double classificacao){
        double total = this.classificacao * this.avaliacoes + classificacao;
        this.avaliacoes++;
        this.classificacao = total / this.avaliacoes;
    }

    public void addEncomenda (Encomenda e){
        this.historico.add(e.clone());
    }

    public Encomenda removeEncomenda(String cod){
        for(Encomenda s: this.historico){
            if(cod.equals(s.getCodigo())){
                this.historico.remove(s);
                return s;
            }
        }
        return null;
    }

    public Encomenda getEncomenda(String cod){
        for(Encomenda s: this.historico){
            if(cod.equals(s.getCodigo())) return s;
        }
        return null;
    }
}
