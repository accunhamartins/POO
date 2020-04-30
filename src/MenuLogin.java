import java.util.ArrayList;
import java.util.List;

public class MenuLogin {
    private List<String> opcoesLogin;
    private List<String> opcoesRegister;
    private int opcao;

    public MenuLogin(){
        this.opcoesLogin = new ArrayList<>();
        this.opcoesRegister = new ArrayList<>();

        opcoesLogin.add("Entrar: ");
        opcoesLogin.add("Registar: ");

        opcoesRegister.add("Como utilizador: ");
        opcoesRegister.add("Como voluntário: ");
        opcoesRegister.add("Como loja: ");
        opcoesRegister.add("Como empresa transportadora: ");

        this.opcao = 0;

    }


}
