import java.util.ArrayList;
import java.util.Objects;

public class Lojas {

    private String iD_loja;
    private boolean tempo_espera;
    private double distancia_loja;
    private double velocidade_deslocacao;
    private double tempo_espera_fila;
    private double tempo_atendimento_medio;
    private ArrayList<Encomenda> encomendas_recebidas;

    public Lojas (){
        this.iD_loja = " ";
        this.tempo_espera = false;
        this.distancia_loja = 0;
        this.velocidade_deslocacao = 0;
        this.tempo_espera_fila = 0;
        this.tempo_atendimento_medio = 0;
        this.encomendas_recebidas = new ArrayList<>();
    }

    public Lojas (String iD_loja, boolean tempo_espera, double distancia_loja, double velocidade_deslocacao, double tempo_espera_fila, double tempo_atendimento_medio, ArrayList<Encomenda> encomendas_recebidas){
        this.iD_loja = iD_loja;
        this.tempo_espera = tempo_espera;
        this.distancia_loja = distancia_loja;
        this.velocidade_deslocacao = velocidade_deslocacao;
        this.tempo_espera_fila = tempo_espera_fila;
        this.tempo_atendimento_medio = tempo_atendimento_medio;
        setEncomendas_recebidas(encomendas_recebidas);
    }

    public Lojas (Lojas loja){
        this.iD_loja = loja.getID_loja();
        this.tempo_espera = loja.getTempo_espera();
        this.distancia_loja = loja.getDistancia_loja();
        this.velocidade_deslocacao = loja.getVelocidade_deslocacao();
        this.tempo_espera_fila = loja.getTempo_espera_fila();
        this.tempo_atendimento_medio = loja.getTempo_atendimento_medio();
        setEncomendas_recebidas(loja.getEncomendas_recebidas());
    }

    public String getID_loja() {
        return iD_loja;
    }

    public boolean getTempo_espera() {
        return tempo_espera;
    }

    public double getDistancia_loja() {
        return distancia_loja;
    }

    public double getVelocidade_deslocacao() {
        return velocidade_deslocacao;
    }

    public double getTempo_espera_fila() {
        return tempo_espera_fila;
    }

    public double getTempo_atendimento_medio() {
        return tempo_atendimento_medio;
    }

    public ArrayList<Encomenda> getEncomendas_recebidas(){
        ArrayList<Encomenda> aux = new ArrayList<>();
        for(Encomenda s: this.encomendas_recebidas){
            aux.add(s);
        }
        return aux;
    }

    public void setID_loja(String iD_loja) {
        this.iD_loja = iD_loja;
    }

    public void setTempo_espera(boolean tempo_espera) {
        this.tempo_espera = tempo_espera;
    }

    public void setDistancia_loja(double distancia_loja) {
        this.distancia_loja = distancia_loja;
    }

    public void setVelocidade_deslocacao(double velocidade_deslocacao) {
        this.velocidade_deslocacao = velocidade_deslocacao;
    }

    public void setTempo_espera_fila(double tempo_espera_fila) {
        this.tempo_espera_fila = tempo_espera_fila;
    }

    public void setTempo_atendimento_medio(double tempo_atendimento_medio) {
        this.tempo_atendimento_medio = tempo_atendimento_medio;
    }

    public void setEncomendas_recebidas(ArrayList<Encomenda> encomendas_recebidas) {
        this.encomendas_recebidas = new ArrayList<>();
        for(Encomenda s: encomendas_recebidas){
            this.encomendas_recebidas.add(s);
        }
    }

    public Lojas clone(){
        return new Lojas(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lojas lojas = (Lojas) o;
        return tempo_espera == lojas.getTempo_espera() &&
                lojas.getDistancia_loja() == this.distancia_loja &&
                lojas.getVelocidade_deslocacao() == this.velocidade_deslocacao &&
                lojas.getTempo_espera_fila() == this.tempo_espera_fila &&
                lojas.getTempo_atendimento_medio() == this.tempo_atendimento_medio &&
                this.iD_loja.equals(lojas.iD_loja) &&
                this.encomendas_recebidas.equals(lojas.encomendas_recebidas);
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Lojas: ");
        sb.append("ID da loja: ").append(this.iD_loja).append('\n');
        sb.append("Tempo de espera: ").append(this.tempo_espera).append('\n');
        sb.append("Distância há loja: ").append(this.distancia_loja).append('\n');
        sb.append("Velocidade de deslocacao: ").append(this.velocidade_deslocacao).append('\n');
        sb.append("Tempo de espera na fila: ").append(this.tempo_espera_fila).append('\n');
        sb.append("Tempo médio de atendimento: ").append(this.tempo_atendimento_medio).append('\n');
        sb.append("Lista de encomendas recebidas: ");
        this.encomendas_recebidas.forEach(e -> sb.append(e.toString() + "\n"));
        return sb.toString();
    }

    public double tempo_recolha_encomenda (){
        return (this.distancia_loja*this.velocidade_deslocacao + this.tempo_espera_fila);
    }
}