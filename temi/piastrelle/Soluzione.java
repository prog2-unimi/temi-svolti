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


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** La classe di <em>test</em>. */
public class Soluzione {

  public static void main(String[] args) {
    try (final Scanner s = new Scanner(System.in)) {
      final List<Rivestimento> rivestimento = new ArrayList<>();
      final List<Pavimentazione> pavimentazione = new ArrayList<>();
      while (s.hasNextLine())
        try (final Scanner line = new Scanner(s.nextLine())) {
          switch (line.next().charAt(0)) {
            case 'Q':
              rivestimento.add(new PiastrellaQuadrata(line.nextInt(), line.nextInt()));
              break;
            case 'R':
              rivestimento.add(
                  new PiastrellaRomboidale(line.nextInt(), line.nextInt(), line.nextInt()));
              break;
            case 'T':
              rivestimento.add(
                  new PiastrellaTriangolare(line.nextInt(), line.nextInt(), line.nextInt()));
              break;
            case 'P':
              final List<Pavimentazione.Componente> componenti = new ArrayList<>();
              while (line.hasNextInt())
                componenti.add(
                    new Pavimentazione.Componente(
                        line.nextInt(), rivestimento.get(line.nextInt())));
              final Pavimentazione p = new Pavimentazione(componenti);
              pavimentazione.add(p);
              rivestimento.add(p);
              break;
            default:
              throw new IllegalArgumentException("Errore nel formato.");
          }
        }
      for (final Pavimentazione p : pavimentazione)
        System.out.println(p.superficie() + "\t" + p.costo());
    }
  }
}