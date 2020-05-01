import java.awt.desktop.SystemSleepEvent;
import java.util.*;

public class MenuLogin {
    private List<String> opcoesLogin;
    private List<String> opcoesRegister;
    private int opcao;

    public MenuLogin(){
        this.opcoesLogin = new ArrayList<>();
        this.opcoesRegister = new ArrayList<>();

        opcoesLogin.add("1.Entrar ");
        opcoesLogin.add("2.Registar ");

        opcoesRegister.add("1.Como utilizador ");
        opcoesRegister.add("2.Como voluntário ");
        opcoesRegister.add("3.Como loja ");
        opcoesRegister.add("4.Como empresa transportadora ");

        this.opcao = 0;

    }

    public int getOpcao() {
        return this.opcao;
    }

    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    public void showMenuInicial(){
        System.out.println("--------BEM VINDO AO TRAZ AQUI--------");
        System.out.println("Selecione a opção pretendida: ");
        for(String s: this.opcoesLogin) System.out.println(s);
    }

    public void showMenuRegisto(){
        for(String s: this.opcoesRegister) System.out.println(s);
    }

    public void showMenus(){
        showMenuInicial();
        Scanner input = new Scanner(System.in);
        this.opcao = input.nextInt();
        if(this.opcao == 1){
            showMenuLogin();
        }
        else if(this.opcao == 2){
            showMenuRegisto();
            System.out.println("Selecione o tipo de utilizador: ");
            this.opcao = input.nextInt();
            ArrayList<String> res = new ArrayList<>();
            res = showRegisto(this.opcao);
            switch (res.get(0)){
                case "Utilizador":
                    Utilizador u = new Utilizador(res.get(1),res.get(2),res.get(3),res.get(4),Double.parseDouble(res.get(5)),Double.parseDouble(res.get(6)));
            }
        }

    }

    public ArrayList<String> showMenuLogin(){
        Scanner input = new Scanner(System.in);
        ArrayList<String> res = new ArrayList<>();

        System.out.println("\nInsira o seu email: \n");
        res.add(input.next());
        System.out.println("\nInsira a sua password: \n");
        res.add(input.next());

        this.opcao = -1;
        return res;
    }

    public ArrayList<String> showRegisto(int op){
        Scanner input = new Scanner(System.in);
        ArrayList<String> res = new ArrayList<>();
        Random rand = new Random();
        if(op == 1){
            String type = "Utilizador";
            res.add(type);
            System.out.println("Insira o seu endereço de email: ");
            String email = input.next();
            res.add(email);
            System.out.println("Insira a sua password: ");
            String password = input.next();
            res.add(password);
            String codigo = "u" + rand.nextInt(99);
            res.add(codigo);
            System.out.println("Insira o seu nome: ");
            String nome = input.next();
            res.add(nome);
            System.out.println("Insira a sua latitude: ");
            double latitude = input.nextDouble();
            res.add(String.valueOf(latitude));
            System.out.println("Insira a sua longitude: ");
            double longitude= input.nextDouble();
            res.add(String.valueOf(longitude));


        }
        return res;
    }







}
