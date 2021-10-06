/**
 * Una <em>piastrella</em> astratta ed immutabile, che implementa parzialmente
 * l'interfaccia {@link Rivestimento}, la sua rappresentazione è un singolo
 * attributo intero, necessario ad implementare il metodo {@link #costo}.
 */
public abstract class Piastrella implements Rivestimento {

  /**
   * Il costo della piastrella, è sempre positivo.
   */
  private final int costo;

  /**
   * Costruisce una piastrella dato il suo costo.
   *
   * @param costo il costo.
   * @throws IllegalArgumentException se il costo non è positivo.
   */
  public Piastrella(final int costo) {
    if (costo <= 0) throw new IllegalArgumentException("Il costo dev'essere positivo.");
    this.costo = costo;
  }

  @Override
  public int costo() {
    return costo;
  }
}