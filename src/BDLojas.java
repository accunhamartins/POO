import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BDLojas implements Serializable {
    private Map<String, Loja> lojas;
    private Set<String> codigos;

    public BDLojas(){
        this.lojas = new HashMap<>();
        this.codigos = new TreeSet<>();
    }

    public BDLojas(Map<String, Loja> loja, Set<String> codigos){
        setLojas(loja);
        setCodigos(codigos);
    }

    public BDLojas(BDLojas r){
        setLojas(r.getLojas());
        setCodigos(r.getCodigos());
    }

    public Map<String, Loja> getLojas() {
        return this.lojas.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public Set<String> getCodigos() {
        return this.codigos.stream().collect(Collectors.toSet());
    }

    public void setCodigos(Set<String> codigos) {
        this.codigos = new TreeSet<>();
        for(String s: codigos) this.codigos.add(s);
    }

    public void setLojas(Map<String, Loja> lojas) {
        this.lojas = new HashMap<>();
        lojas.entrySet().forEach(e -> this.lojas.put(e.getKey(), e.getValue().clone()));
    }


    public BDLojas clone(){
        return new BDLojas(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Total de Lojas: ").append("\n");
        sb.append(this.lojas);

        return sb.toString();
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        BDLojas r = (BDLojas) obj;
        return this.lojas.equals(r.getLojas());
    }

    public boolean existe(Loja v){
        return this.lojas.keySet().contains(v.getEmail());
    }

    public void updateLoja(Encomenda e, Loja j){
        j.addEncomenda(e);
        this.lojas.put(j.getEmail(), j);
    }

    public boolean existeCodigo(String s){
        return this.codigos.contains(s);
    }

    public void add(Loja l){
        this.lojas.put(l.getEmail(), l.clone());
    }
}
