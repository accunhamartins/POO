
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RegistosVoluntarios {

    private Map<String, Voluntario> voluntarios;

    public RegistosVoluntarios(){
        this.voluntarios = new TreeMap<>();
    }

    public RegistosVoluntarios(Map<String, Voluntario> voluntario){
        setVoluntarios(voluntario);
    }

    public RegistosVoluntarios(RegistosVoluntarios r){
        setVoluntarios(r.getVoluntarios());
    }

    public Map<String, Voluntario> getVoluntarios() {
        return this.voluntarios.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public void setVoluntarios(Map<String, Voluntario> voluntarios) {
        this.voluntarios = new TreeMap<>();
        voluntarios.entrySet().forEach(e -> this.voluntarios.put(e.getKey(), e.getValue().clone()));
    }


    public RegistosVoluntarios clone(){
        return new RegistosVoluntarios(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Total de Voluntarios: ").append("\n");
        sb.append(this.voluntarios);

        return sb.toString();
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        RegistosVoluntarios r = (RegistosVoluntarios) obj;
        return this.voluntarios.equals(r.getVoluntarios());
    }


    public void add(Voluntario v){
      this.voluntarios.put(v.getEmail(), v.clone());
    }
}
