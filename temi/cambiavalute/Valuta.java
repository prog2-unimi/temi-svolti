/*

Copyright 2025 Massimo Santini

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


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Enumerazione che rappresenta le valute con relativo nome e simbolo. */
public enum Valuta {
  USD("Dollaro", '$'),
  EUR("Euro", '€'),
  CHF("Franco", '₣'),
  TRY("Lira", '₺'),
  INR("Rupia", '₹'),
  GBP("Sterlina", '£'),
  JPY("Yen", '¥');

  /* Mappa che consente di rintracciare una valuta dato il suo simbolo. */
  static final Map<Character, Valuta> ENUM_MAP;

  static {
    Map<Character, Valuta> map = new HashMap<>();
    for (Valuta instance : Valuta.values()) {
      if (map.containsKey(instance.simbolo))
        throw new IllegalArgumentException("Simbolo duplicato.");
      map.put(instance.simbolo, instance);
    }
    ENUM_MAP = Collections.unmodifiableMap(map);
  }


  /** Il nome della valuta (non può essere <code>null</code>, o vuoto). */
  public final String nome;

  /** Il simbolo della valuta. */
  public final char simbolo;


  private Valuta(String nome, char simbolo) {
    if (Objects.requireNonNull(nome, "Il nome non può essere null.").isEmpty())
      throw new IllegalArgumentException("Il nome non può essere vuoto.");
    this.nome = nome;
    this.simbolo = simbolo;
  }

  /**
   * Consente di ottenere una valuta dato il suo simbolo.
   *
   * @param simbolo il simbolo della valuta da cercare.
   * @return la valuta.
   * @throws IllegalArgumentException se il simbolo non corrisponde ad alcuna valuta nota.
   */
  public static Valuta valueOf(char simbolo) {
    Valuta valuta = ENUM_MAP.get(simbolo);
    if (valuta == null)
      throw new IllegalArgumentException("Il simbolo non corrisponde ad una valuta nota.");
    return valuta;
  }


  @Override
  public String toString() {
    return "" + simbolo;
  }
}