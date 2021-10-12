/*

Copyright 2021 Massimo Santini

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


public interface Matrice {

  int dim();

  int val(final int i, final int j);

  Matrice per(final int alpha);

  Matrice più(final Matrice B);

  Matrice per(final Matrice B);

  Vettore per(final Vettore v);

  default boolean conforme(final Vettore v) {
    return dim() == v.dim();
  }

  default boolean conforme(final Matrice M) {
    return dim() == M.dim();
  }

  default void requireValidIJ(final int i, final int j) {
    if (0 <= i && i < dim() && 0 <= j && j < dim()) return;
    throw new IndexOutOfBoundsException("Gli indici eccedono la dimensione della matrice.");
  }

}