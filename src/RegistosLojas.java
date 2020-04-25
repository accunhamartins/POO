
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RegistosLojas{

    private Map<String, Loja> lojas;

    public RegistosLojas(){
        this.lojas = new TreeMap<>();
    }

    public RegistosLojas(Map<String, Loja> loja){
        setLojas(loja);
    }

    public RegistosLojas(RegistosLojas r){
        setLojas(r.getLojas());
    }

    public Map<String, Loja> getLojas() {
        return this.lojas.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public void setLojas(Map<String, Loja> lojas) {
        this.lojas = new TreeMap<>();
        lojas.entrySet().forEach(e -> this.lojas.put(e.getKey(), e.getValue().clone()));
    }


    public RegistosLojas clone(){
        return new RegistosLojas(this);
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
        RegistosLojas r = (RegistosLojas) obj;
        return this.lojas.equals(r.getLojas());
    }


    public void add(Loja l){
      this.lojas.put(l.getCodigo(), l.clone());
    }
}
