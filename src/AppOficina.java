import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class AppOficina {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        String[] linhas = Files.readAllLines(Path.of("produtos.txt")).toArray(new String[0]);
        Produto[] produtos = new Produto[linhas.length];
        int index = 0;

        for (String linha : linhas) {
            Produto p = Produto.criarDeTexto(linha);
            if (p != null) {
                produtos[index++] = p;
            }
        }
        produtos = Arrays.copyOf(produtos, index); // remove nulos

        Produto[] produtosPorId = Arrays.copyOf(produtos, produtos.length);
        Produto[] produtosPorDescricao = Arrays.copyOf(produtos, produtos.length);
        Produto[] produtosPorPreco = Arrays.copyOf(produtos, produtos.length);
        Produto[] produtosPorNome = Arrays.copyOf(produtos, produtos.length);

        IOrdenador<Produto> ordenador = new Mergesort<>();
        ordenador.ordenar(produtosPorId, ComparadoresProduto.porId());
        ordenador.ordenar(produtosPorDescricao, ComparadoresProduto.porDescricao());
        ordenador.ordenar(produtosPorPreco, ComparadoresProduto.porPreco());
        ordenador.ordenar(produtosPorNome, ComparadoresProduto.porNome());

        int opcao;
        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Listar produtos por ID");
            System.out.println("2 - Listar produtos por descrição");
            System.out.println("3 - Buscar produto por ID");
            System.out.println("4 - Listar produto por preço");
            System.out.println("5 - Listar produto por preço");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 1 -> {
                    for (Produto p : produtosPorId)
                        System.out.println(p);
                }
                case 2 -> {
                    for (Produto p : produtosPorDescricao)
                        System.out.println(p);
                }
                case 3 -> {
                    System.out.print("Digite o ID: ");
                    String chave = teclado.nextLine().trim();
                    Produto resultado = buscaBinaria(produtosPorId, chave);
                    if (resultado != null)
                        System.out.println("Encontrado: " + resultado);
                    else
                        System.out.println("Produto não encontrado.");
                }
                case 4 -> {
                    for (Produto p : produtosPorPreco)
                        System.out.println(p);
                }
                case 5 -> {
                    for (Produto p : produtosPorNome)
                        System.out.println(p);
                }
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        teclado.close();
    }

    public static Produto buscaBinaria(Produto[] vetor, String chave) {
        int ini = 0, fim = vetor.length - 1;
        while (ini <= fim) {
            int meio = (ini + fim) / 2;
            int cmp = vetor[meio].getIdentificador().compareTo(chave);
            if (cmp == 0) return vetor[meio];
            else if (cmp < 0) ini = meio + 1;
            else fim = meio - 1;
        }
        return null;
    }
}