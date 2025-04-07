public abstract class Produto {
    protected String identificador;
    protected String descricao;
    protected double precoCusto;
    protected double margemLucro;

    public Produto(String id, String desc, double preco, double margem) {
        this.identificador = id;
        this.descricao = desc;
        this.precoCusto = preco;
        this.margemLucro = margem;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPreco() {
        return this.precoCusto;
    }

    public abstract double valorDeVenda();
    public abstract String gerarDadosTexto();

    public static Produto criarDeTexto(String linha) {
        String[] partes = linha.split(";");

        String tipo = partes.length > 0 ? partes[0] : "1";
        String id = partes.length > 1 ? partes[1] : "0000";
        String descricao = partes.length > 2 ? partes[2] : "SEM DESCRIÇÃO";
        double preco = 0.0;
        double margem = 0.3;

        try {
            if (partes.length > 3)
                preco = Double.parseDouble(partes[3].replace(",", "."));
            if (partes.length > 4)
                margem = Double.parseDouble(partes[4].replace(",", "."));
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter preço ou margem na linha: " + linha);
        }

        if (tipo.equals("2")) {
            try {
                if (partes.length > 5) {
                    java.time.LocalDate validade = java.time.LocalDate.parse(partes[5],
                            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    return new ProdutoPerecivel(id, descricao, preco, margem, validade);
                } else {
                    java.time.LocalDate validadePadrao = java.time.LocalDate.now().plusMonths(1);
                    System.err.println("Validade ausente. Definida como: " + validadePadrao);
                    return new ProdutoPerecivel(id, descricao, preco, margem, validadePadrao);
                }
            } catch (Exception e) {
                System.err.println("Erro ao interpretar validade. Usando padrão.");
                java.time.LocalDate validadePadrao = java.time.LocalDate.now().plusMonths(1);
                return new ProdutoPerecivel(id, descricao, preco, margem, validadePadrao);
            }
        } else {
            return new ProdutoNaoPerecivel(id, descricao, preco, margem);
        }
    }
}