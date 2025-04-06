import java.util.Comparator;

public class Bubblesort<T> implements IOrdenador<T> {
    private long comparacoes = 0;
    private long movimentacoes = 0;
    private double tempo = 0;

    public T[] ordenar(T[] dados, Comparator<T> criterio) {
        comparacoes = movimentacoes = 0;
        long inicio = System.nanoTime();

        for (int i = 0; i < dados.length - 1; i++) {
            for (int j = 0; j < dados.length - i - 1; j++) {
                comparacoes++;
                if (criterio.compare(dados[j], dados[j + 1]) > 0) {
                    T temp = dados[j];
                    dados[j] = dados[j + 1];
                    dados[j + 1] = temp;
                    movimentacoes += 3;
                }
            }
        }

        tempo = (System.nanoTime() - inicio) / 1_000_000.0;
        return dados;
    }

    public long getComparacoes() { return comparacoes; }
    public long getMovimentacoes() { return movimentacoes; }
    public double getTempoOrdenacao() { return tempo; }
}