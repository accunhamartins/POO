import java.time.LocalDate;
import java.util.ArrayList;

public class Voluntarios {
  private boolean disponivel;
  private float latitude;
  private float longitude;
  private LocalDate inicio_transporte;
  private float raio_acao;
  private ArrayList<Encomenda> historico;
  float custo;

  //Construtores de classe
  //Construtor de classe por omissão
  public Voluntarios(){
    this.disponivel = false;
    this.latitude = 0;
    this.longitude = 0;
    this.inicio_transporte = LocalDate.now();
    this.raio_acao = 0;
    this.historico = new ArrayList<>();
    this.custo = 0;
  }
  //Construtor de classe por clone
  public Voluntarios(Voluntarios a){
    this.disponivel = a.getDisponibilidade();
    this.latitude = a.getLatitude();
    this.longitude = a.getLongitude();
    this.inicio_transporte = a.getInicio_transporte();
    this.raio_acao = a.getRaio_acao();
    this.setHistorico(a.getHistorico());
    this.custo = a.getCusto();
  }

  //Construtor parametrizado
  public Voluntarios(boolean a, float b, float c, LocalDate d, float e, ArrayList<Encomenda> f, float g){
    this.disponivel = a;
    this.latitude = b;
    this.longitude = c;
    this.inicio_transporte = d;
    this.raio_acao = e;
    this.setHistorico(f);
    this.custo = g;
  }

  //Métodos de obtenção de variáveis
  public boolean getDisponibilidade(){
    return this.disponivel;
  }

  public float getLatitude(){
    return this.latitude;
  }

  public float getLongitude(){
    return this.longitude;
  }

  public float getRaio_acao(){
    return this.raio_acao;
  }

  public LocalDate getInicio_transporte(){
    return this.inicio_transporte;
  }

  public ArrayList<Encomenda> getHistorico(){
    ArrayList<Encomenda> res = new ArrayList<>();
    for(Encomenda s: this.historico) res.add(s);
    return res;
  }

  public float getCusto(){
    return this.custo;
  }

  //Método de definição de variáveis
  public void setDisponibilidade(boolean a){
    this.disponivel = a;
  }

  public void setLatitude(float a){
    this.latitude = a;
  }

  public void setLongitude(float a){
    this.longitude = a;
  }

  public void setInicio_Transporte(LocalDate a){
    this.inicio_transporte = a;
  }

  public void setRaio_acao(float a){
    this.raio_acao = a;
  }

  public void setHistorico(ArrayList<Encomenda> a){
    this.historico = new ArrayList<>();
    for(Encomenda s: a) this.historico.add(s);
  }
  public void setCusto(float a){
    this.custo = a;
  }
  //Método de clonar um objeto
  public Voluntarios clone(){
    return new Voluntarios(this);
  }

  //public equals
  public boolean equals(Object o){
    if (o == this) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Voluntarios v = (Voluntarios) o;
    return this.disponivel ==  v.getDisponibilidade()
      && this.latitude == v.getLatitude()
      && this.longitude == v.getLongitude()
      && this.inicio_transporte.equals(v.getInicio_transporte())
      && this.raio_acao == v.getRaio_acao()
      && this.historico.equals(v.getHistorico())
      && this.custo == v.getCusto();
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Disponível: ");
    sb.append(this.disponivel + "\n");
    sb.append("Latitude: ");
    sb.append(this.latitude + "\n");
    sb.append("Longitude: ");
    sb.append(this.longitude + "\n");
    sb.append("Data de início de entrega: ");
    sb.append(this.inicio_transporte + "\n");
    sb.append("Raio de ação");
    sb.append(this.raio_acao + "\n");
    sb.append("Registos de encomendas: ");
    sb.append(this.historico.toString());
    sb.append("Custo de transporte: ");
    sb.append(this.custo + "\n");

    return sb.toString();
  }
}
