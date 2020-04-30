public class Utilizador extends UtilizadorSistema{
      private String codigo;
      private String nome;
      private double latitude;
      private double longitude;
      //Encomendas 

      //Construtores
      public Utilizador(){
          super();
          this.codigo = " ";
          this.nome = " ";
          this.latitude = 0;
          this.longitude = 0;

      }

      public Utilizador(String email, String password, String codigo, String nome, double latitude, double longitude){
          super(email,password,"Utilizador");
          this.codigo = codigo;
          this.nome = nome;
          this.latitude = latitude;
          this.longitude = longitude;
      }

      public Utilizador(Utilizador user){
          super(user);
          this.codigo = user.getCodigo();
          this.nome = user.getNome();
          this.latitude = user.getLatitude();
          this.longitude = user.getLongitude();
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
}
