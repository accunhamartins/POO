public class Utilizador{
      private String codigo;
      private String nome;
      private double latitude;
      private double longitude;
      private String email;
      private String password;
      //Encomendas 

      //Construtores
      public Utilizador(){
          this.codigo = " ";
          this.nome = " ";
          this.latitude = 0;
          this.longitude = 0;
          this.email = "";
          this.password = "";
      }

      public Utilizador(String codigo, String nome, double latitude, double longitude, String email, String password){
          this.codigo = codigo;
          this.nome = nome;
          this.latitude = latitude;
          this.longitude = longitude;
          this.email = email;
          this.password = password;
      }

      public Utilizador(Utilizador user){
          this.codigo = user.getCodigo();
          this.nome = user.getNome();
          this.latitude = user.getLatitude();
          this.longitude = user.getLongitude();
          this.email = user.getEmail();
          this.password = user.getPassword();
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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
                  this.longitude == user.getLongitude() &&
                  this.email.equals(user.getEmail()) &&
                  this.password.equals(user.getPassword());
      }

      public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Código: ").append(this.codigo + "\n");
        sb.append("Nome: ").append(this.nome + "\n");
        sb.append("Latitude: ").append(this.latitude + "\n");
        sb.append("Longitude: ").append(this.longitude + "\n");

        return sb.toString();
      }
}
