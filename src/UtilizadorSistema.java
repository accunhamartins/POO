import java.io.Serializable;

public abstract class  UtilizadorSistema implements Serializable {
    private String email;
    private String password;
    private String typeUser;


    //Construtores
    public UtilizadorSistema(){
        this.email = "";
        this.password = "";
        this.typeUser = "";

    }

    public  UtilizadorSistema(String email, String password, String typeUser){
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;

    }

    public UtilizadorSistema(UtilizadorSistema a){
        this.email = a.getEmail();
        this.password = a.getPassword();
        this.typeUser = a.getTypeUser();

    }


    //Getters
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTypeUser() {
        return typeUser;
    }

    //Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public abstract UtilizadorSistema clone();

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        UtilizadorSistema u = (UtilizadorSistema) obj;
        return this.password.equals(u.getPassword()) &&
                this.typeUser.equals(u.getTypeUser()) &&
                this.email.equals(u.getEmail());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(" <--- O utilizador é: ").append("\n");
        sb.append(this.typeUser).append("\n");
        sb.append("\n");


        return sb.toString();
    }

}
