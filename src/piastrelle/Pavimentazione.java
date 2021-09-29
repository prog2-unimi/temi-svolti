import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Pavimentazione implements Rivestimento, Iterable<Pavimentazione.Componente> {

  public static class Componente implements Rivestimento {
    public final Rivestimento rivestimento;
    public final int quantità;

    public Componente(final int quantità, final Rivestimento rivestimento) {
      this.rivestimento = Objects.requireNonNull(rivestimento);
      if (quantità <= 0)
        throw new IllegalArgumentException("La quantità dev'essere positiva");
      this.quantità = quantità;
    }

    @Override
    public int costo() {
      return quantità * rivestimento.costo();
    }

    @Override
    public int superficie() {
      return quantità * rivestimento.superficie();
    }
  }

  private final List<Componente> componenti;

  public Pavimentazione(final List<Componente> componenti) {
    Objects.requireNonNull(componenti);
    if (componenti.size() == 0)
      throw new IllegalArgumentException("Ci deve essere almeno una componente");
    for (Componente c: componenti) Objects.requireNonNull(c);
    this.componenti = Collections.unmodifiableList(new ArrayList<>(componenti));
  }

  @Override
  public int costo() {
    int totale = 0;
    for (final Rivestimento r : componenti)
      totale += r.costo();
    return totale;
  }

  @Override
  public int superficie() {
    int totale = 0;
    for (final Rivestimento r : componenti)
      totale += r.superficie();
    return totale;
  }

  @Override
  public Iterator<Pavimentazione.Componente> iterator() {
    return componenti.iterator();
  }
}