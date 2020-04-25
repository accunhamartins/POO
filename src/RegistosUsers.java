
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RegistosUsers {

    private Map<String, Utilizador> users;

    public RegistosUsers() {
        this.users = new TreeMap<>();
    }

    public RegistosUsers(Map<String, Utilizador> user) {
        setUsers(user);
    }

    public RegistosUsers(RegistosUsers r) {
        setUsers(r.getUsers());
    }

    public Map<String, Utilizador> getUsers() {
        return this.users.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public void setUsers(Map<String, Utilizador> users) {
        this.users = new TreeMap<>();
        users.entrySet().forEach(e -> this.users.put(e.getKey(), e.getValue().clone()));
    }


    public RegistosUsers clone() {
        return (new RegistosUsers(this));
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
        RegistosUsers r = (RegistosUsers) obj;
        return this.users.equals((r.getUsers()));
    }

    public void add(Utilizador u) {
        this.users.put(u.getCodigo(), u.clone());
    }


}
