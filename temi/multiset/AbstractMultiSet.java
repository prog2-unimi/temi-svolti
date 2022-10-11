import java.util.Iterator;

/**
 * Classe astratta che implementa il metodo {@link #toString() toString} illustrato nella traccia.
 */
public abstract class AbstractMultiSet<E> implements MultiSet<E> {
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    final Iterator<E> ie = iterator();
    while (ie.hasNext()) {
      final E e = ie.next();
      sb.append(e + ": " + multiplicity(e));
      if (ie.hasNext()) sb.append(", "); // la virgola separa dal successivo elemento, se presente
    } 
    sb.append("}");
    return sb.toString();
  }
}