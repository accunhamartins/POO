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
                    break;
                case "Voluntário":
                    Voluntario v = new Voluntario(res.get(1),res.get(2),res.get(4),res.get(3),Boolean.parseBoolean(res.get(5)),Double.parseDouble(res.get(6)),Double.parseDouble(res.get(7)),null,Double.parseDouble(res.get(8)),null);
                    break;
                case "Loja":
                    Loja l = new Loja(res.get(1),res.get(2),res.get(3),res.get(4),Boolean.parseBoolean(res.get(5)),0,0,Double.parseDouble(res.get(8)),Double.parseDouble(res.get(6)),Double.parseDouble(res.get(7)),null);
                    break;
                case "Empresa Transportadora":
                    EmpresaTransportes t = new EmpresaTransportes(res.get(1),res.get(2),res.get(3),res.get(4), Integer.parseInt(res.get(5)),Double.parseDouble(res.get(6)),res.get(7),Double.parseDouble(res.get(8)),Double.parseDouble(res.get(9)),Double.parseDouble(res.get(10)),null,null,Integer.parseInt(res.get(11)),Boolean.parseBoolean(res.get(12)));
                    break;
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
            double longitude = input.nextDouble();
            res.add(String.valueOf(longitude));
        }
        if(op == 2){
            String type = "Voluntário";
            res.add(type);
            System.out.println("Insira o seu endereço de email: ");
            String email = input.next();
            res.add(email);
            System.out.println("Insira a sua password: ");
            String password = input.next();
            res.add(password);
            String codigo = "v" + rand.nextInt(99);
            res.add(codigo);
            System.out.println("Insira o seu nome: ");
            String nome = input.next();
            res.add(nome);
            System.out.println("Insira a sua disponibilidade(true/false): ");
            boolean disponibilidade = input.nextBoolean();
            res.add(String.valueOf(disponibilidade));
            System.out.println("Insira a sua latitude: ");
            double latitude = input.nextDouble();
            res.add(String.valueOf(latitude));
            System.out.println("Insira a sua longitude: ");
            double longitude = input.nextDouble();
            res.add(String.valueOf(longitude));
            System.out.println("Insira o seu raio de ação: ");
            double raio_acao = input.nextDouble();
            res.add(String.valueOf(raio_acao));
        }
        if(op == 3){
            String type = "Loja";
            res.add(type);
            System.out.println("Insira o seu endereço de email: ");
            String email = input.next();
            res.add(email);
            System.out.println("Insira a sua password: ");
            String password = input.next();
            res.add(password);
            String codigo = "l" + rand.nextInt(99);
            res.add(codigo);
            System.out.println("Insira o seu nome: ");
            String nome = input.next();
            res.add(nome);
            System.out.println("Insira se possui informação real do tempo de espera na fila (true/false): ");
            boolean tempo_espera = input.nextBoolean();
            res.add(String.valueOf(tempo_espera));
            System.out.println("Insira a sua latitude: ");
            double latitude = input.nextDouble();
            res.add(String.valueOf(latitude));
            System.out.println("Insira a sua longitude: ");
            double longitude = input.nextDouble();
            res.add(String.valueOf(longitude));
            System.out.println("Insira o tempo de atendimento médio: ");
            double tempo_atendimento_medio = input.nextDouble();
            res.add(String.valueOf(tempo_atendimento_medio));
        }
        if(op == 4){
            String type = "Empresa Transportadora";
            res.add(type);
            System.out.println("Insira o seu endereço de email: ");
            String email = input.next();
            res.add(email);
            System.out.println("Insira a sua password: ");
            String password = input.next();
            res.add(password);
            String codigo = "t" + rand.nextInt(99);
            res.add(codigo);
            System.out.println("Insira o seu nome: ");
            String nome = input.next();
            res.add(nome);
            System.out.println("Insira o seu nif: ");
            int nif = input.nextInt();
            res.add(String.valueOf(nif));
            System.out.println("Insira o seu cuto por km: ");
            double custo_km = input.nextDouble();
            res.add(String.valueOf(custo_km));
            System.out.println("Insira o seu local: ");
            String local = input.next();
            res.add(local);
            System.out.println("Insira a sua latitude: ");
            double latitude = input.nextDouble();
            res.add(String.valueOf(latitude));
            System.out.println("Insira a sua longitude: ");
            double longitude = input.nextDouble();
            res.add(String.valueOf(longitude));
            System.out.println("Insira o seu raio de ação: ");
            double raio_acao = input.nextDouble();
            res.add(String.valueOf(raio_acao));
            System.out.println("Insira o número de encomendas que pode transportar ao mesmo tempo: ");
            int numero_encomendas = input.nextInt();
            res.add(String.valueOf(numero_encomendas));
            System.out.println("Insira se possui permissão para transporte de encomendas médicas (true/false): ");
            boolean transporteMedico = input.nextBoolean();
            res.add(String.valueOf(transporteMedico));
        }
        return res;
    }







}
