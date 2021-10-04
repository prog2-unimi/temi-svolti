public abstract class Piastrella implements Rivestimento {

  public final int costoUnitario;

  public Piastrella(final int costoUnitario) {
    if (costoUnitario <= 0) throw new IllegalArgumentException();
    this.costoUnitario = costoUnitario;
  }

  public int costo() {
    return costoUnitario;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}