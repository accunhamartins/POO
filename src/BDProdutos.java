import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

    public class BDProdutos implements Serializable {
        private Map<String, Produto> produtos;
        private Set<String> codigos;

        public BDProdutos() {
            this.produtos = new HashMap<>();
            this.codigos = new TreeSet<>();
        }

        public BDProdutos(Map<String, Produto> produtos, Set<String> codigos) {
            setProdutos(produtos);
            setCodigos(codigos);
        }

        public BDProdutos(BDProdutos r) {
            setProdutos(r.getProdutos());
            setCodigos(r.getCodigos());
        }

        public Map<String, Produto> getProdutos() {
            return this.produtos.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
        }

        public void setProdutos(Map<String, Produto> produtos
        ) {
            this.produtos = new HashMap<>();
            produtos.entrySet().forEach(e -> this.produtos.put(e.getKey(), e.getValue().clone()));
        }

        public Set<String> getCodigos() {
            return this.codigos.stream().collect(Collectors.toSet());
        }

        public void setCodigos(Set<String> codigos) {
            this.codigos = new TreeSet<>();
            for (String s : codigos) this.codigos.add(s);
        }


        public BDProdutos clone() {
            return new BDProdutos(this);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Total de Produtos: ").append("\n");
            sb.append(this.produtos);

            return sb.toString();
        }

        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            BDProdutos r = (BDProdutos) obj;
            return this.produtos.equals(r.getProdutos());
        }

        public boolean existe(String v) {
            return this.produtos.keySet().contains(v);
        }

        public boolean existeCodigo(String s) {
            return this.codigos.contains(s);
        }

        public void add(Produto v) {
            this.produtos.put(v.getDescricao(), v.clone());
        }
    }
