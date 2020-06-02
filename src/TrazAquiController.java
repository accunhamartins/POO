import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TrazAquiController implements Serializable {
    private BDGeral bd;
    private TrazAquiView view;

    public void setView(TrazAquiView view){this.view = view;}

    public void setBd(BDGeral bd){
        this.bd = bd;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void mainFlow() throws IOException, ClassNotFoundException, InterruptedException {
        int op;
        Input input = new Input();
        this.view.printHeader();
        this.view.showMenuInicial();
        do{
            op = input.lerInt();
            switch(op){
                case 0:
                    return;
                case 1:
                    clearScreen();
                    registosFlow();
                    break;
                case 2:
                    clearScreen();
                    loginFlow();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        while (op != 0);
    }

    private void registosFlow(){
        int op;
        Random random = new Random();
        Input input = new Input();
        clearScreen();
        String password = "", nome = "", email = "", local = "";
        Double latitude = 0.0, longitude = 0.0, raio = 0.0 ,tempo_espera = 0.0, custo = 0.0;
        String codigo = "";
        int nif = 0, nrMinimo = 0;
        boolean medico = false;
        this.view.showMenuRegisto();
        do {
            op = input.lerInt();
            switch (op) {
                case 0:
                    clearScreen();
                    this.view.showMenuInicial();
                    return;
                case 1:
                    try {
                        clearScreen();
                        this.view.headRegistoUser();
                        this.view.preenchimentoObrg();
                        this.view.email();
                        email = input.lerString();
                        this.view.password();
                        password = input.lerString();
                        this.view.nome();
                        nome = input.lerString();
                        this.view.latitude();
                        latitude = input.lerDouble();
                        this.view.longitude();
                        longitude = input.lerDouble();
                        codigo = "u" + random.nextInt(99);
                        while (this.bd.getUtilizadores().existeCodigo(codigo)) codigo = "u" + random.nextInt(99);
                    } catch (InputMismatchException e) {
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addUser(new Utilizador(email, password, codigo, nome, latitude, longitude, new ArrayList<>()));
                    break;
                case 2:
                    try {
                        clearScreen();
                        this.view.headRegistoVoluntario();
                        this.view.preenchimentoObrg();
                        this.view.email();
                        email = input.lerString();
                        this.view.password();
                        password = input.lerString();
                        this.view.nome();
                        nome = input.lerString();
                        this.view.latitude();
                        latitude = input.lerDouble();
                        this.view.longitude();
                        longitude = input.lerDouble();
                        this.view.raio_acao();
                        raio = input.lerDouble();
                        codigo = "v" + random.nextInt(99);
                        while (bd.getVoluntarios().existeCodigo(codigo)) codigo = "v" + random.nextInt(99);
                    } catch (InputMismatchException e) {
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addVoluntario(new Voluntario(email, password, nome, codigo, true, latitude, longitude, LocalDate.now(), raio, new ArrayList<>()));
                    break;
                case 3:
                    try {
                        clearScreen();
                        this.view.headRegistoLoja();
                        this.view.preenchimentoObrg();
                        this.view.email();
                        email = input.lerString();
                        this.view.password();
                        password = input.lerString();
                        this.view.nome();
                        nome = input.lerString();
                        this.view.latitude();
                        latitude = input.lerDouble();
                        this.view.longitude();
                        longitude = input.lerDouble();
                        this.view.tempo_espera();
                        tempo_espera = input.lerDouble();
                        codigo = "l" + random.nextInt(99);
                        while (bd.getLojas().existeCodigo(codigo)) codigo = "l" + random.nextInt(99);
                    } catch (InputMismatchException e) {
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addLoja(new Loja(email, password, codigo, nome, tempo_espera, latitude, longitude, new ArrayList<>()));
                    break;
                case 4:
                    try {
                        clearScreen();
                        this.view.headRegistoEmpresa();
                        this.view.preenchimentoObrg();
                        this.view.email();
                        email = input.lerString();
                        this.view.password();
                        password = input.lerString();
                        this.view.nome();
                        nome = input.lerString();
                        this.view.custo_km();
                        custo = input.lerDouble();
                        this.view.latitude();
                        latitude = input.lerDouble();
                        this.view.longitude();
                        longitude = input.lerDouble();
                        this.view.local();
                        local = input.lerString();
                        this.view.raio_acao();
                        raio = input.lerDouble();
                        this.view.nif();
                        nif = input.lerInt();
                        this.view.nr_min_encomendas();
                        nrMinimo = input.lerInt();
                        this.view.encomendas_medicas();
                        medico = input.lerBoolean();;
                        codigo = "t" + random.nextInt(99);
                        while (bd.getTransportes().existeCodigo(codigo)) codigo = "t" + random.nextInt(99);
                    }
                    catch(InputMismatchException e){
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addTransporte(new EmpresaTransportes(email, password, codigo, nome, nif,custo,local,latitude,longitude,raio, new ArrayList<>(), nrMinimo, medico));
                    break;
                default:
                    System.out.println("Opcao inválida");
                    break;
            }
        }
        while (op != 0);
    }

    private void loginFlow() throws InterruptedException {
        int op;
        Input input = new Input();
        clearScreen();
        this.view.showMenuLogin();
        do {
            op = input.lerInt();
            switch (op) {
                case 0:
                    clearScreen();
                    this.view.showMenuInicial();
                    return;
                case 1:
                    userFlow();
                    break;
                case 2:
                    voluntarioFlow();
                    break;
                case 3:
                    lojaFlow();
                    break;
                case 4:
                    empresaFlow();
                    break;
                default:
                    System.out.println("Opcao inválida");
                    break;
            }
        }
        while (op != 0);
    }


    private void userFlow() throws InterruptedException {
        int op;
        Input input = new Input();
        String email = "", password = "", produto = "";
        int quantidade = 0;
        boolean enc_med = false;

        clearScreen();
        this.view.headLoginUser();
        this.view.email();
        email = input.lerString();
        this.view.password();
        password = input.lerString();
        Utilizador u = bd.loginUser(email, password);
        TimeUnit.SECONDS.sleep(1);
        if (!u.equals(null)) {
            clearScreen();
            this.view.showMenuUser();
            do {
                op = input.lerInt();
                switch (op) {
                    case 0:
                        clearScreen();
                        this.view.showMenuLogin();
                        return;
                    case 1:
                        this.view.headNovaEncomenda();
                        this.view.preenchimentoObrg();
                        this.view.produto();
                        produto = input.lerString();
                        if (bd.getProdutos().existe(produto)) {
                            this.view.quantidade();
                            quantidade = input.lerInt();
                            this.view.encomendas_medicas2();
                            enc_med = input.lerBoolean();
                            LinhaEncomenda enc = new LinhaEncomenda(bd.getProdutos().getProdutos().get(produto).getCodigo(), produto, quantidade,bd.getProdutos().getProdutos().get(produto).getPreco() * quantidade, enc_med);
                        break;
                    case 2:

                        break;
                    default:
                        System.out.println("Opcao inválida");
                        break;
                }
            }
            while (op != 0);
        }
    }


}
