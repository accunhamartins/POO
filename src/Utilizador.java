import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilizador extends UtilizadorSistema implements Serializable {
      private String codigo;
      private String nome;
      private double latitude;
      private double longitude;
      private List<Encomenda> encomendas_realizadas;

    //Construtores
      public Utilizador(){
          super();
          this.codigo = " ";
          this.nome = " ";
          this.latitude = 0;
          this.longitude = 0;
          this.encomendas_realizadas = new ArrayList<>();
      }

      public Utilizador(String email, String password, String codigo, String nome, double latitude, double longitude, ArrayList<Encomenda> encomendas_realizadas){
          super(email,password,"Utilizador");
          this.codigo = codigo;
          this.nome = nome;
          this.latitude = latitude;
          this.longitude = longitude;
          setEncomendas(encomendas_realizadas);
      }

      public Utilizador(Utilizador user){
          super(user);
          this.codigo = user.getCodigo();
          this.nome = user.getNome();
          this.latitude = user.getLatitude();
          this.longitude = user.getLongitude();
          setEncomendas(user.getEncomendas());
      }

      //Getters
      public String getCodigo(){
        return this.codigo;
      }

      public String getNome(){
          return this.nome;
      }

      public double getLatitude(){
          return this.latitude;
      }

      public double getLongitude(){
          return this.longitude;
      }

      public ArrayList<Encomenda> getEncomendas(){
          ArrayList<Encomenda> res = new ArrayList<>();
          for(Encomenda e: this.encomendas_realizadas)
              res.add(e);
          return res;
      }

    //Setters
      public void setCodigo(String codigo){
          this.codigo = codigo;
      }

      public void setNome(String nome){
          this.nome = nome;
      }

      public void setLatitude(double latitude){
          this.latitude = latitude;
      }

      public void setLongitude(double longitude){
          this.longitude = longitude;
      }

      public void setEncomendas(ArrayList<Encomenda> enc){
          this.encomendas_realizadas = new ArrayList<>();
          for(Encomenda e: enc)
              this.encomendas_realizadas.add(e);
      }

    //Clone
      public Utilizador clone(){
        return new Utilizador(this);
      }

      //Equals
      public boolean equals(Object o){
          if (o == null || this.getClass() != o.getClass()) return false;
          if (this == o) return true;
          Utilizador user = (Utilizador) o;
          return  this.codigo.equals(user.getCodigo()) &&
                  this.nome.equals(user.getNome()) &&
                  this.latitude == user.getLatitude() &&
                  this.longitude == user.getLongitude();

      }

      public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Código: ").append(this.codigo + "\n");
        sb.append("Nome: ").append(this.nome + "\n");
        sb.append("Latitude: ").append(this.latitude + "\n");
        sb.append("Longitude: ").append(this.longitude + "\n");


        return sb.toString();
      }

    /**
     * Método que imprime as encomendas do utilizador
     * @return
     */
      public String printEncomendas(){
          StringBuilder sb = new StringBuilder();
          if(this.encomendas_realizadas.size() == 0) sb.append("Não existem encomendas realizadas\n");
          else {
              System.out.println("ENCOMENDAS REALIZADAS PELO USER: ");
              this.encomendas_realizadas.forEach(e -> sb.append(e.toString()));
          }
          return sb.toString();
      }

    /**
     * Método que adiciona uma encomenda ao utilizador
     * @param e
     */
      public void addEncomenda(Encomenda e){
          this.encomendas_realizadas.add(e.clone());
      }

}
