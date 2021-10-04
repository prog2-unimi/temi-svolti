import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
              rivestimento.add(new PiastrellaRomboidale(line.nextInt(), line.nextInt(), line.nextInt()));
              break;
            case 'T':
              rivestimento.add(new PiastrellaTriangolare(line.nextInt(), line.nextInt(), line.nextInt()));
              break;
            case 'P':
              final List<Pavimentazione.Componente> componenti = new ArrayList<>();
              while (line.hasNextInt())
                componenti.add(new Pavimentazione.Componente(line.nextInt(), rivestimento.get(line.nextInt())));
              final Pavimentazione p = new Pavimentazione(componenti);
              pavimentazione.add(p);
              rivestimento.add(p);
              break;
            default:
              throw new IllegalArgumentException();
          }
        }
      for (final Pavimentazione p : pavimentazione)
        System.out.println(p.superficie() + "\t" + p.costo());
    }
  }
}