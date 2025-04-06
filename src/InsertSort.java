import java.util.Comparator;

public class InsertSort<T> implements IOrdenador<T> {
    private long comparacoes = 0;
    private long movimentacoes = 0;
    private double tempo = 0;

    public T[] ordenar(T[] dados, Comparator<T> criterio) {
        comparacoes = movimentacoes = 0;
        long inicio = System.nanoTime();

        for (int i = 1; i < dados.length; i++) {
            T chave = dados[i];
            int j = i - 1;
            movimentacoes++;
            while (j >= 0 && criterio.compare(dados[j], chave) > 0) {
                comparacoes++;
                dados[j + 1] = dados[j];
                movimentacoes++;
                j--;
            }
            if (j >= 0) comparacoes++;
            dados[j + 1] = chave;
            movimentacoes++;
        }

        tempo = (System.nanoTime() - inicio) / 1_000_000.0;
        return dados;
    }

    public long getComparacoes() { return comparacoes; }
    public long getMovimentacoes() { return movimentacoes; }
    public double getTempoOrdenacao() { return tempo; }
}