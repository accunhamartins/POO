import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmpresaTransportes extends UtilizadorSistema {
    private String codigo;
    private String nome;
    private int nif;
    private double custo_km;
    private String local;
    private double latitude;
    private double longitude;
    private double raioDeAcao;
    private ArrayList<Transportes> transportes;
    private ArrayList<Encomenda> registos;
    private int numeroMinimoEncomenda;
    private boolean transporteMedico;


    public EmpresaTransportes(){
        super();
        this.codigo = " ";
        this.nome = " ";
        this.nif = 0;
        this.custo_km = 0;
        this.local = " ";
        this.latitude = 0;
        this.longitude = 0;
        this.raioDeAcao = 0.0;
        this.transportes = new ArrayList<>();
        this.registos = new ArrayList<>();
        this.numeroMinimoEncomenda = 0;
        this.transporteMedico = false;
    }

    public EmpresaTransportes(String email,String password,String codigo, String nome, int nif, double custo_km, String local,double latitude, double longitude, double raioDeAcao, ArrayList<Transportes> transportes, ArrayList<Encomenda> registos, int numeroMinimoEncomenda, boolean transporteMedico){
        super(email, password, "Transportadora");
        this.codigo = codigo;
        this.nome = nome;
        this.nif = nif;
        this.custo_km = custo_km;
        this.local = local;
        this.latitude = latitude;
        this.longitude = longitude;
        this.raioDeAcao = raioDeAcao;
        this.setTransportes(transportes);
        this.setRegistos(registos);
        this.numeroMinimoEncomenda = numeroMinimoEncomenda;
        this.transporteMedico = transporteMedico;
    }

    public EmpresaTransportes(EmpresaTransportes a){
        super(a);
        this.codigo = a.getCodigo();
        this.nome = a.getNome();
        this.nif = a.getNif();
        this.custo_km = a.getCusto_km();
        this.local = a.getLocal();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.raioDeAcao = a.getRaioDeAcao();
        this.setTransportes(a.getTransportes());
        this.setRegistos(a.getRegistos());
        this.numeroMinimoEncomenda = a.getNumeroMinimoEncomenda();
        this.transporteMedico = a.isTransporteMedico();
    }

    public String getCodigo(){
      return this.codigo;
    }

    public String getNome(){
      return this.nome;
    }

    public int getNif(){
      return this.nif;
    }

    public double getCusto_km(){
      return this.custo_km;
    }

    public String getLocal() {
        return local;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getRaioDeAcao() {
        return raioDeAcao;
    }

    public ArrayList<Transportes> getTransportes(){
        return this.transportes.stream().map(Transportes::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Encomenda> getRegistos() {
        return this.registos.stream().map(Encomenda::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public int getNumeroMinimoEncomenda() {
        return numeroMinimoEncomenda;
    }

    public boolean isTransporteMedico() {
        return this.transporteMedico;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setCusto_km(double custo_km) {
        this.custo_km = custo_km;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setNumeroMinimoEncomenda(int numeroMinimoEncomenda) {
        this.numeroMinimoEncomenda = numeroMinimoEncomenda;
    }

    public void setRaioDeAcao(double raioDeAcao) {
        this.raioDeAcao = raioDeAcao;
    }

    public void setTransportes(ArrayList<Transportes> transportes) {
        this.transportes = new ArrayList<>();
        for(Transportes t: transportes){
            this.transportes.add(t.clone());
        }
    }

    public void setRegistos(ArrayList<Encomenda> registos) {
        this.registos = new ArrayList<>();
        ArrayList<Encomenda> aux = new ArrayList<Encomenda>();
        for(Encomenda e: registos){
            this.registos.add(e.clone());
        }
    }

    public void setTransporteMedico(boolean transporteMedico) {
        this.transporteMedico = transporteMedico;
    }

    public EmpresaTransportes clone(){
        return  new EmpresaTransportes(this);
    }

    public boolean equals(Object obj){
        if (obj == this )return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        EmpresaTransportes e = (EmpresaTransportes) obj;
        return  this.codigo.equals(e.getCodigo()) &&
                this.nome.equals(e.getNome()) &&
                this.nif == e.getNif() &&
                this.custo_km == e.getCusto_km() &&
                this.local.equals(e.getLocal()) &&
                this.latitude == e.getLatitude() &&
                this.longitude == e.getLongitude() &&
                this.raioDeAcao == e.getRaioDeAcao() &&
                this.numeroMinimoEncomenda == e.getNumeroMinimoEncomenda() &&
                this.transportes.equals((e.getTransportes())) &&
                this.registos.equals(e.getRegistos()) &&
                this.transporteMedico == e.isTransporteMedico();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Código de empresa: ");
        sb.append(this.codigo + "\n");
        sb.append("Nome: ");
        sb.append(this.nome + "\n");
        sb.append("Nif: ");
        sb.append(this.nif + "\n");
        sb.append("Custo por km: ");
        sb.append(this.custo_km + "\n");
        sb.append("Local: ");
        sb.append(this.local + "\n");
        sb.append("Latitude: ");
        sb.append(this.latitude + "\n");
        sb.append("Longitude: ");
        sb.append(this.longitude + "\n");
        sb.append("Raio de ação: ");
        sb.append(this.raioDeAcao + "\n");
        sb.append("Transportes: ");
        sb.append(this.transportes.toString());
        sb.append("Registos de encomendas: ");
        sb.append(this.registos.toString());
        sb.append("Número mínimo de encomendas: ");
        sb.append(this.numeroMinimoEncomenda+"\n");
        sb.append("Apta para transportes médicos: ");
        sb.append(this.transporteMedico+"\n");

        return sb.toString();
    }




}
