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


import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Classe astratta che rappresenta un compratore.
 *
 * <p>Un compratore, dato un insieme di bancarelle, può determinare per un data quantità di un certo
 * giocattola un {@link Acquisto} che la realizzi. Può farlo secondo diverse strategie che
 * corrispondono a diverse implementazioni del metodo astratto {@link #compra(int, Giocattolo)}.
 */
public abstract class AbstractCompratore {

  /** L'elenco di bancarelle da cui effettuare gli acquisti. */
  protected final List<Bancarella> bancarelle;

  // RI: bancarelle non è e non contiene <code>null</code>,

  /**
   * Costruisce un compratore a partire dall'elenco di bancarelle da cui acquistare.
   *
   * @param bancarelle le bancarelle.
   */
  public AbstractCompratore(final Collection<Bancarella> bancarelle) {
    Objects.requireNonNull(bancarelle);
    if (bancarelle.isEmpty())
      throw new IllegalArgumentException("Il mercatino deve contenere almeno una bancarella");
    this.bancarelle = List.copyOf(bancarelle);
  }

  /**
   * Restituisce la quantità totale di un giocattolo acquistabile dall'unione di tutte le
   * bancarelle.
   *
   * @param giocattolo il giocattolo.
   * @return la somma delle quantità del giocattolo in tutte le bancarelle.
   * @throws NullPointerException se il giocattolo è <code>null</code>.
   */
  public int quantità(final Giocattolo giocattolo) {
    int quantità = 0;
    for (final Bancarella b : bancarelle) quantità += b.quanti(giocattolo);
    return quantità;
  }

  /**
   * Restituisce un acquisto data una certa quantità di un giocattolo da comprare.
   *
   * @param num il numero di giocattoli da comprare.
   * @param giocattolo il giocattolo da comprare.
   * @return un acquisto del giocattolo e quantità assegnate.
   * @throws NullPointerException se il giocattolo è <code>null</code>.
   * @throws IllegalArgumentException se il numero non è positivio, o eccede la disponibilità
   *     complessiva delle bancarelle.
   */
  public abstract Acquisto compra(final int num, final Giocattolo giocattolo);

  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder();
    for (final Bancarella b : bancarelle) result.append(b + "\n");
    return result.toString();
  }
}