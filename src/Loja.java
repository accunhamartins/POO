import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Loja extends UtilizadorSistema implements Serializable {
    private String codigo;
    private String nome;
    private double tempo_espera;
    private double latitude;
    private double longitude;
    private ArrayList<Encomenda> encomendas_recebidas;

    public Loja (){
        super();
        this.codigo = " ";
        this.nome = " ";
        this.tempo_espera = 0;
        this.latitude = 0;
        this.longitude = 0;
        this.encomendas_recebidas = new ArrayList<>();
    }

    public Loja (String email, String password, String codigo, String nome, double tempo_espera, double latitude, double longitude, ArrayList<Encomenda> encomendas_recebidas){
        super(email, password, "Loja");
        this.codigo = codigo;
        this.nome = nome;
        this.tempo_espera = tempo_espera;
        this.latitude = latitude;
        this.longitude = longitude;
        setEncomendas_recebidas(encomendas_recebidas);
    }

    public Loja (Loja loja){
        super(loja);
        this.codigo = loja.getCodigo();
        this.nome = loja.getNome();
        this.tempo_espera = loja.getTempo_espera();
        this.latitude = loja.getLatitude();
        this.longitude = loja.getLongitude();
        setEncomendas_recebidas(loja.getEncomendas_recebidas());
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public double getTempo_espera() {
        return tempo_espera;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public ArrayList<Encomenda> getEncomendas_recebidas(){
        ArrayList<Encomenda> aux = new ArrayList<>();
        for(Encomenda s: this.encomendas_recebidas){
            aux.add(s);
        }
        return aux;
    }

    public void setCodigo(String codigo){
      this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTempo_espera(double tempo_espera) {
        this.tempo_espera = tempo_espera;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setEncomendas_recebidas(ArrayList<Encomenda> encomendas_recebidas) {
        this.encomendas_recebidas = new ArrayList<>();
        for(Encomenda s: encomendas_recebidas){
            this.encomendas_recebidas.add(s);
        }
    }

    public Loja clone(){
        return new Loja(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loja Loja = (Loja) o;
        return  this.codigo.equals(Loja.getCodigo()) &&
                this.tempo_espera == Loja.getTempo_espera() &&
                Loja.getLatitude() == this.latitude &&
                Loja.getLongitude() == this.longitude &&
                this.nome.equals(Loja.nome) &&
                this.encomendas_recebidas.equals(Loja.encomendas_recebidas);
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Loja: ").append("\n");
        sb.append("Código da loja: ").append(this.codigo).append('\n');
        sb.append("Nome da loja: ").append(this.nome).append('\n');
        sb.append("Tempo de espera: ").append(this.tempo_espera).append('\n');
        sb.append("Latitude: ").append(this.latitude).append('\n');
        sb.append("Longitude: ").append(this.longitude).append('\n');
        sb.append("Lista de encomendas recebidas: ");
        this.encomendas_recebidas.forEach(e -> sb.append(e.toString() + "\n"));
        sb.append(super.toString());
        return sb.toString();
    }

    /**
     * Método que adiciona uma nova encomenda a uma loja
     * @param e
     */
    public void addEncomenda(Encomenda e){
        this.encomendas_recebidas.add(e.clone());
    }

    /**
     * Método que remove uma encomenda do stock
     * @param e
     */
    public void removeEncomenda(Encomenda e){
        this.encomendas_recebidas.remove(e);
    }

    /**
     * Método que devolve uma encomenda, dando o seu código
     * @param cod
     * @return
     * @throws EncomendaNotFoundException
     */
    public Encomenda getEnc(String cod) throws EncomendaNotFoundException{
        for(Encomenda e: this.encomendas_recebidas){
            if(cod.equals(e.getCodigo())) return e.clone();
        }
        throw new EncomendaNotFoundException();
    }

}
