import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Encomenda {
    private double peso;
    private String comprador;
    private String vendedor;
    private Map<String, Produto> produtos;
    private boolean encomendaMedica;


    public Encomenda(){
        this.peso = 0.0;
        this.comprador = "";
        this.vendedor = "";
        this.produtos = new HashMap<>();
        this.encomendaMedica = true;
    }

    public  Encomenda(double peso, String comprador, String vendedor, HashMap<String, Produto> produtos, boolean encomendaMedica){
        this.peso = peso;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.setProdutos(produtos);
        this.encomendaMedica = encomendaMedica;
    }

    public  Encomenda(Encomenda e){
        this.peso = e.getPeso();
        this.comprador = e.getComprador();
        this.vendedor =  e.getVendedor();
        this.setProdutos(e.getProdutos());
        this.encomendaMedica = e.isEncomendaMedica();
    }

    public double getPeso() {
        return this.peso;
    }

    public String getComprador() {
        return this.comprador;
    }

    public String getVendedor() {
        return this.vendedor;
    }

    public Map<String, Produto> getProdutos(){
        return this.produtos.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    public boolean isEncomendaMedica() {
        return this.encomendaMedica;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public void setProdutos(Map<String, Produto> produtos) {
        this.produtos = new HashMap<>();
        produtos.entrySet().forEach(p -> this.produtos.put(p.getKey(), p.getValue().clone()));
    }

    public void setEncomendaMedica(boolean encomendaMedica) {
        this.encomendaMedica = encomendaMedica;
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Encomenda e = (Encomenda) obj;
        return e.getPeso() == this.getPeso() &&
                this.comprador.equals(e.getComprador()) &&
                this.vendedor.equals(e.getVendedor()) &&
                this.produtos.equals(e.getProdutos()) &&
                this.encomendaMedica == e.isEncomendaMedica();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Peso: ");
        sb.append(this.peso + "\n");
        sb.append("Comprador: ");
        sb.append(this.comprador + "\n");
        sb.append("Vendedor: ");
        sb.append(this.vendedor+"\n");
        sb.append("Produtos: ");
        sb.append(this.produtos);
        sb.append("Encomenda médica: ");
        sb.append(this.encomendaMedica+"\n");

        return sb.toString();
    }

}
