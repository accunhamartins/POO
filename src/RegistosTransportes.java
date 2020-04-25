
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RegistosTransportes {

    private Map<String, Transporte> transportes;

    private RegistosTransportes(){
        this.transportes = new TreeMap<>();
    }

    private RegistosTransportes(Map<String, Transporte> transporte){
        setTransportes(transporte);
    }

    private RegistosTransportes(RegistosTransportes r){
        setTransportes(r.getTransportes());
    }

    public Map<String, Voluntario> getTransportes() {
        return this.transportes.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public void setTransportes(Map<String, Voluntario> transportes) {
        this.transportes = new TreeMap<>();
        transportes.entrySet().forEach(e -> this.transportes.put(e.getKey(), e.getValue().clone()));
    }


    public RegistosTransportes clone(){
        return new RegistosTransportes(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Total de Transportes: ").append("\n");
        sb.append(this.transportes);

        return sb.toString();
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        RegistosVoluntario r = (RegistosVoluntario) obj;
        return this.transportes.equals(r.getTransportes());
    }


    public void add(Transporte t){
      this.transportes.put(t.getCodigo(), t.clone());
    }
}
