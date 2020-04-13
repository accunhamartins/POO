import java.util.ArrayList;

public class EmpresaTransportes {
    private String local;
    private float latitude;
    private float longitude;
    private double raioDeAcao;
    private ArrayList<Transportes> transportes;
    private ArrayList<Encomenda> registos;
    private int numeroMinimoEncomenda;


    public EmpresaTransportes(){
        this.local = "";
        this.latitude = 0;
        this.longitude = 0;
        this.raioDeAcao = 0.0;
        this.transportes = new ArrayList<>();
        this.registos = new ArrayList<>();
        this.numeroMinimoEncomenda = 0;
    }

    public EmpresaTransportes(String local,float latitude, float longitude, double raioDeAcao, ArrayList<Transportes> transportes, ArrayList<Encomenda> registos, int numeroMinimoEncomenda){
        this.local = local;
        this.latitude = latitude;
        this.longitude = longitude;
        this.raioDeAcao = raioDeAcao;
        this.setTransportes(transportes);
        this.setRegistos(registos);
        this.numeroMinimoEncomenda = numeroMinimoEncomenda;
    }

    public EmpresaTransportes(EmpresaTransportes a){
        this.local = a.getLocal();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.raioDeAcao = a.getRaioDeAcao();
        this.setTransportes(a.getTransportes());
        this.setRegistos(a.getRegistos());
        this.numeroMinimoEncomenda = a.getNumeroMinimoEncomenda();
    }

    public String getLocal() {
        return local;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public double getRaioDeAcao() {
        return raioDeAcao;
    }

    public ArrayList<Transportes> getTransportes(){
        ArrayList<Transportes> aux = new ArrayList<>();
        for(Transportes t: this.transportes) aux.add(t);
        return  aux;
    }

    public ArrayList<Encomenda> getRegistos() {
        ArrayList<Encomenda> aux = new ArrayList<>();
        for(Encomenda e: this.registos) aux.add(e);
        return aux;
    }

    public int getNumeroMinimoEncomenda() {
        return numeroMinimoEncomenda;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setNumeroMinimoEncomenda(int numeroMinimoEncomenda) {
        this.numeroMinimoEncomenda = numeroMinimoEncomenda;
    }

    public void setRaioDeAcao(double raioDeAcao) {
        this.raioDeAcao = raioDeAcao;
    }

    public void setTransportes(ArrayList<Transportes> transportes) {
        this.transportes = new ArrayList<>();
        for(Transportes t: transportes) this.transportes.add(t);
    }

    public void setRegistos(ArrayList<Encomenda> registos) {
        this.registos = new ArrayList<>();
        for(Encomenda e: registos) this.registos.add(e);
    }

    public EmpresaTransportes clone(){
        return  new EmpresaTransportes(this);
    }

    public boolean equals(Object obj){
        if (obj == this )return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        EmpresaTransportes e = (EmpresaTransportes) obj;
        return this.local.equals(e.getLocal()) &&
                this.latitude == e.getLatitude() &&
                this.longitude == e.getLongitude() &&
                this.raioDeAcao == e.getRaioDeAcao() &&
                this.numeroMinimoEncomenda == e.getNumeroMinimoEncomenda() &&
                this.transportes.equals((e.getTransportes())) &&
                this.registos.equals(e.getRegistos());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
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

        return sb.toString();
    }


}
