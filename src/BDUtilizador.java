import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BDUtilizador implements Serializable {
    private Map<String, Utilizador> users;
    private Set<String> codigos;

    public BDUtilizador() {
        this.users = new HashMap<>();
        this.codigos = new TreeSet<>();
    }

    public BDUtilizador(Map<String, Utilizador> user, Set<String> codigos) {
        setUsers(user);
        setCodigos(codigos);
    }

    public BDUtilizador(BDUtilizador r) {
        setUsers(r.getUsers());
        setCodigos(r.getCodigos());
    }

    public Map<String, Utilizador> getUsers() {
        return this.users.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public Set<String> getCodigos() {
        return this.codigos.stream().collect(Collectors.toSet());
    }

    public void setCodigos(Set<String> codigos) {
        this.codigos = new TreeSet<>();
        for(String s: codigos) this.codigos.add(s);
    }

    public void setUsers(Map<String, Utilizador> users) {
        this.users = new HashMap<>();
        users.entrySet().forEach(e -> this.users.put(e.getKey(), e.getValue().clone()));
    }


    public BDUtilizador clone() {
        return (new BDUtilizador(this));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total de utilizadores: ").append("\n");
        sb.append(this.users);

        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        BDUtilizador r = (BDUtilizador) obj;
        return this.users.equals((r.getUsers()));
    }

    public boolean existe(Utilizador v){
        return this.users.keySet().contains(v.getEmail());
    }

    public void add(Utilizador u) {
        this.users.put(u.getEmail(), u.clone());
        this.codigos.add(u.getCodigo());
    }

    public boolean existeCodigo(String s){
        return this.codigos.contains(s);
    }

    public Utilizador tryLogin(String email, String password){
        Utilizador aux = this.users.get(email);
        if(aux == null) System.out.println("Não existe esse user");
        else{
            if(aux.getPassword().equals(password)){
                System.out.println("Login feito com sucesso");
                return aux;
            }
            else System.out.println("Password incorreta");
        }
        return aux;
    }
}
