import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TrazAqui {


    public void menu() throws InterruptedException {
        int f=1;
        int k = -1;
        Scanner scanner = new Scanner(System.in);

        while (f != 0) {
            clearScreen();
            showMenuInicial();

            try{
                k = scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Insira um dígito\n");
                TimeUnit.SECONDS.sleep(1);
                scanner.next();
            }

            //Dando uma string como input continua a dizer opção inválida
            switch (k){
                case 1:
                    clearScreen();
                    Registo();
                    break;
                case 2:
                    clearScreen();
                    Login();
                    break;
                case 0:
                    f = 0;
                    break;
                default:
                    System.out.println("Opção inválida");
                    TimeUnit.SECONDS.sleep(1);
                    break;

            }
        }
        System.out.println("Obrigado por ter usado a nossa aplicação");
    }

    private void Registo(){
        int k=0;
        BDGeral bd = new BDGeral();
        Scanner scan = new Scanner(System.in);
        showMenuRegisto();
        try{
            k = scan.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Insira um dígito\n");
            scan.next();
        }
        switch (k){
            case 1:
                insereUser(bd);
                break;
            case 2:
                insereVoluntario(bd);
                break;
            case 3:
                insereLoja(bd);
                break;
            case 4:
                insereTransporte(bd);
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    private static void Login() throws InterruptedException {
        int k=0;
        BDGeral bd = new BDGeral();
        Scanner scan = new Scanner(System.in);
        showMenuLogin();
        String email = "", password = "";
        try{
            k = scan.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Insira um dígito\n");
            scan.next();
        }
        switch (k){
            case 1:
                MenuUser(bd);
                break;
            case 2:
                LogVoluntario();
                break;
            case 3:
                LogLoja();
                break;
            case 4:
                LogEmpresa();
                break;
            default:
                System.out.println("Opção inválida");
        }
    }


    public static Voluntario loginVoluntario(BDGeral bd, String email, String password){
       Voluntario aux;
       aux = bd.getVoluntarios().tryLogin(email,password);
       return aux;
    }

    public static EmpresaTransportes loginTransportes(BDGeral bd, String email, String password){
        EmpresaTransportes aux;
        aux = bd.getTransportes().tryLogin(email,password);
        return aux;
    }

    public static Loja loginLoja(BDGeral bd, String email, String password){
        Loja aux;
        aux = bd.getLojas().tryLogin(email, password);
        return aux;
    }


    private static void LogVoluntario() throws InterruptedException {
        clearScreen();
        BDGeral bd = new BDGeral();
        Scanner scan = new Scanner(System.in);
        String email = "", password = "";
        System.out.println("----------------LOGIN VOLUNTÁRIO----------------");
        System.out.println("Insira o seu email: ");
        email = scan.next();
        System.out.println("Insira a sua password: ");
        password = scan.next();
        Voluntario u = loginVoluntario(bd, email, password);
        TimeUnit.SECONDS.sleep(1);
    }

    private static void LogLoja() throws InterruptedException {
        clearScreen();
        BDGeral bd = new BDGeral();
        Scanner scan = new Scanner(System.in);
        String email = "", password = "";
        System.out.println("----------------LOGIN LOJA----------------");
        System.out.println("Insira o seu email: ");
        email = scan.next();
        System.out.println("Insira a sua password: ");
        password = scan.next();
        Loja u = loginLoja(bd, email, password);
        TimeUnit.SECONDS.sleep(2);
    }

    private static void LogEmpresa() throws InterruptedException {
        clearScreen();
        BDGeral bd = new BDGeral();
        Scanner scan = new Scanner(System.in);
        String email = "", password = "";
        System.out.println("----------------LOGIN EMPRESA TRANSPORTES----------------");
        System.out.println("Insira o seu email: ");
        email = scan.next();
        System.out.println("Insira a sua password: ");
        password = scan.next();
        EmpresaTransportes u = loginTransportes(bd, email, password);

    }

    private static void MenuUser(BDGeral bd) throws InterruptedException {
        int k = -1;
        Scanner scan = new Scanner(System.in);


        u = LogUser(bd);
       if (u != null) {
            try {
                k = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Insira um dígito\n");
                scan.next();
            }
            switch (k) {
                case 1:
                    novaEncomenda(u, bd);
                    break;
                case 2:

                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }
    }


    private static void novaEncomenda(Utilizador u, BDGeral bd){
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        String enc_med = "", nome = "", produto = "", local = "";
        Double latitude = 0.0, longitude = 0.0, raio = 0.0, custo = 0.0;
        int quantidade = 0;
        int nif = 0, nrMinimo = 0;
        boolean medico = false;
        String codigo = "";
        try {
            System.out.println("Os seguintes campos são de preenchimento obrigatório: ");
            System.out.println("Insira o produto a comprar: ");
            produto = input.next();
            if (bd.getProdutos().existe(produto)) {
                quantidade = input.nextInt();
                System.out.println("Escreva verdadeiro se se trata de uma encomenda médica ou falso caso contrário");
                enc_med = input.next();
                if (enc_med.equals("verdadeiro")) medico = true;
                LinhaEncomenda enc = new LinhaEncomenda(bd.getProdutos().getProdutos().get(produto).getCodigo(), produto, quantidade,bd.getProdutos().getProdutos().get(produto).getPreco() * quantidade, medico);

            }
        }
        catch(InputMismatchException e){
            input.next();
            System.out.println("Input inválido");
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
