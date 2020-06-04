import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BDVoluntarios implements Serializable {
    private Map<String, Voluntario> voluntarios;
    private Set<String> codigos;

    public BDVoluntarios(){
        this.voluntarios = new HashMap<>();
        this.codigos = new TreeSet<>();
    }

    public BDVoluntarios(Map<String, Voluntario> voluntario, Set<String> codigos){
        setVoluntarios(voluntario);
        setCodigos(codigos);
    }

    public BDVoluntarios(BDVoluntarios r){
        setVoluntarios(r.getVoluntarios());
        setCodigos(r.getCodigos());
    }

    public Map<String, Voluntario> getVoluntarios() {
        return this.voluntarios.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public void setVoluntarios(Map<String, Voluntario> voluntarios) {
        this.voluntarios = new HashMap<>();
        voluntarios.entrySet().forEach(e -> this.voluntarios.put(e.getKey(), e.getValue().clone()));
    }

    public Set<String> getCodigos() {
        return this.codigos.stream().collect(Collectors.toSet());
    }

    public void setCodigos(Set<String> codigos) {
        this.codigos = new TreeSet<>();
        for(String s: codigos) this.codigos.add(s);
    }


    public BDVoluntarios clone(){
        return new BDVoluntarios(this);
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
        BDVoluntarios r = (BDVoluntarios) obj;
        return this.voluntarios.equals(r.getVoluntarios());
    }

    public boolean existe(Voluntario v){
        return this.voluntarios.keySet().contains(v.getEmail());
    }
    public boolean existeCodigo(String s){
        return this.codigos.contains(s);
    }

    public void add(Voluntario v){
        this.voluntarios.put(v.getEmail(), v.clone());
    }

    public Voluntario tryLogin(String email, String password){
        Voluntario aux = this.voluntarios.get(email);
        if(aux == null) System.out.println("Não existe esse voluntário");
        else{
            if(aux.getPassword().equals(password)){
                System.out.println("Login feito com sucesso");
                return aux;
            }
            else System.out.println("Password incorreta");
        }
        return aux;
    }

    public String printVoluntario(){
        StringBuilder sb = new StringBuilder();
        for(String s: this.voluntarios.keySet()){
            sb.append(this.voluntarios.get(s).getCodigo() + " ---> " + this.voluntarios.get(s).getNome() +" RATE --> "+ this.voluntarios.get(s).getClassificacao() + "\n" );
        }
        return sb.toString();
    }

    public String getEmail(String cod) throws VoluntarioNotFoundException{
        for(String s: this.voluntarios.keySet()){
            if(this.voluntarios.get(s).getCodigo().equals(cod)) return this.voluntarios.get(s).getEmail();
        }
        throw new VoluntarioNotFoundException();
    }

    public void updateVoluntario(Voluntario v, double classificao){
        v.updateRate(classificao);
        this.voluntarios.put(v.getEmail(), v);
    }


}
