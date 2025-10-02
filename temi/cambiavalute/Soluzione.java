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


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Classe di test. */
public class Soluzione {

  public static void main(String[] args) {
    List<Importo> versamenti = new ArrayList<>();
    try (Scanner s = new Scanner(System.in)) {
      String line = null;
      while (s.hasNextLine()) {
        line = s.nextLine();
        if (line.charAt(0) == 'A' || line.charAt(0) == 'C' || line.charAt(0) == 'P') break;
        versamenti.add(Importo.valueOf(line));
      }
      CambiaValute cv = new CambiaValute(versamenti);
      String parts[] = null;
      for (; ; ) {
        if (line.length() == 0) return;
        if (line.length() > 2) {
          parts = line.substring(2).split("=");
          parts[0] = parts[0].trim();
          parts[1] = parts[1].trim();
        }
        try {
          switch (line.charAt(0)) {
            case 'A':
              cv.aggiorna(new Cambi.Tasso(Importo.valueOf(parts[0]), Importo.valueOf(parts[1])));
              break;
            case 'C':
              System.out.println(
                  cv.cambia(Importo.valueOf(parts[0]), Valuta.valueOf(parts[1].charAt(0))));
              break;
            case 'P':
              System.out.println("[\n" + cv + "\n]");
              break;
          }
        } catch (IllegalArgumentException e) {
          System.out.println("ERRORE: " + e.getMessage());
        }
        if (!s.hasNextLine()) break;
        line = s.nextLine();
      }
    }
  }
}