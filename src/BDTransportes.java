import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BDTransportes implements Serializable {

    private Map<String, EmpresaTransportes> transportes;
    private Set<String> codigos;

    public BDTransportes(){
        this.transportes = new HashMap<>();
        this.codigos = new TreeSet<>();
    }

    public BDTransportes(Map<String, EmpresaTransportes> transporte, Set<String> codigos){
        setTransportes(transporte);
        setCodigos(codigos);
    }

    public BDTransportes(BDTransportes r){
        setTransportes(r.getTransportes());
        setCodigos(r.getCodigos());
    }

    public Map<String, EmpresaTransportes> getTransportes() {
        return this.transportes.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public Set<String> getCodigos() {
        return this.codigos.stream().collect(Collectors.toSet());
    }

    public void setCodigos(Set<String> codigos) {
        this.codigos = new TreeSet<>();
        for(String s: codigos) this.codigos.add(s);
    }

    public void setTransportes(Map<String, EmpresaTransportes> transportes) {
        this.transportes = new HashMap<>();
        transportes.entrySet().forEach(e -> this.transportes.put(e.getKey(), e.getValue().clone()));
    }


    public BDTransportes clone(){
        return new BDTransportes(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Total de Empresas de transporte: ").append("\n");
        sb.append(this.transportes);

        return sb.toString();
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        BDTransportes r = (BDTransportes) obj;
        return this.transportes.equals(r.getTransportes());
    }

    public boolean existe(EmpresaTransportes v){
        return this.transportes.keySet().contains(v.getEmail());
    }

    public boolean existeCodigo(String s){
        return this.codigos.contains(s);
    }

    public void add(EmpresaTransportes t){
        this.transportes.put(t.getEmail(), t.clone());
    }


}
