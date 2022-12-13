/*

Copyright 2022 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/


import java.util.Objects;

/** Classe che implementa una matrice identità. */
public class MatriceIdentità extends AbsMatrice {

  /** La dimensione della matrice. */
  private final int dim;

  // AF: è la matrice identità di dimensione pari a dim
  // RI: dim > 0

  /**
   * Costruisce una matrice identità data la sua dimensione.
   *
   * @param dim la dimensione.
   * @throws IllegalArgumentException se la dimensione non è positiva.
   */
  public MatriceIdentità(final int dim) {
    if (dim <= 0) throw new IllegalArgumentException("La dimensoine dev'essere positiva.");
    this.dim = dim;
  }

  @Override
  public int dim() {
    return dim;
  }

  @Override
  public int val(final int i, final int j) {
    requireValidIJ(i, j);
    return i == j ? 1 : 0;
  }

  @Override
  public Matrice per(final int alpha) {
    if (alpha == 0) return new MatriceNulla(dim());
    int[] tmp = new int[dim];
    for (int i = 0; i < dim; i++) tmp[i] = alpha;
    return new MatriceDiagonale(tmp);
  }

  @Override
  public Matrice più(final Matrice B) {
    Objects.requireNonNull(B, "La matrice non può essere null.");
    if (!conforme(B)) throw new IllegalArgumentException("Le matrici non sono conformi.");
    if (B instanceof MatriceNulla) return this;
    return new MatriceDensa(this).più(B);
  }

  @Override
  public Matrice per(final Matrice B) {
    Objects.requireNonNull(B, "La matrice non può essere null.");
    if (!conforme(B)) throw new IllegalArgumentException("Le matrici non sono conformi.");
    return B;
  }

  @Override
  public Vettore per(final Vettore v) {
    Objects.requireNonNull(v, "Il vettore non può essere null.");
    if (!conforme(v))
      throw new IllegalArgumentException("Il vettore e la matrice non sono conformi.");
    return v;
  }

}