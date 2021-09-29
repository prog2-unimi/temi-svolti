public abstract class Piastrella implements Rivestimento {
  private final int costoUnitario;

  public Piastrella(final int costoUnitario) {
    if (costoUnitario <= 0)
      throw new IllegalArgumentException("Il costo dev'essere positivo");
    this.costoUnitario = costoUnitario;
  }

  @Override
  public int costo() {
    return costoUnitario;
  }
}