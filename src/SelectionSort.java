import java.util.Comparator;

public class SelectionSort<T> implements IOrdenador<T> {
    private long comparacoes = 0;
    private long movimentacoes = 0;
    private double tempo = 0;

    public T[] ordenar(T[] dados, Comparator<T> criterio) {
        comparacoes = movimentacoes = 0;
        long inicio = System.nanoTime();
        int n = dados.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (criterio.compare(dados[j], dados[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                T temp = dados[i];
                dados[i] = dados[min];
                dados[min] = temp;
                movimentacoes += 3;
            }
        }
        tempo = (System.nanoTime() - inicio) / 1_000_000.0;
        return dados;
    }

    public long getComparacoes() { return comparacoes; }
    public long getMovimentacoes() { return movimentacoes; }
    public double getTempoOrdenacao() { return tempo; }
}