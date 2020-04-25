
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RegistosTransportes {

    private Map<String, EmpresaTransportes> transportes;

    public RegistosTransportes(){
        this.transportes = new TreeMap<>();
    }

    public RegistosTransportes(Map<String, EmpresaTransportes> transporte){
        setTransportes(transporte);
    }

    private RegistosTransportes(RegistosTransportes r){
        setTransportes(r.getTransportes());
    }

    public Map<String, EmpresaTransportes> getTransportes() {
        return this.transportes.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public void setTransportes(Map<String, EmpresaTransportes> transportes) {
        this.transportes = new TreeMap<>();
        transportes.entrySet().forEach(e -> this.transportes.put(e.getKey(), e.getValue().clone()));
    }


    public RegistosTransportes clone(){
        return new RegistosTransportes(this);
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
        RegistosTransportes r = (RegistosTransportes) obj;
        return this.transportes.equals(r.getTransportes());
    }


    public void add(EmpresaTransportes t){
      this.transportes.put(t.getCodigo(), t.clone());
    }
}
