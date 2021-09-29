import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Soluzione {

  public static void main(String[] args) {
    try (final Scanner s = new Scanner(System.in)) {
      final List<Rivestimento> rivestimento = new ArrayList<>();
      while (s.hasNextLine()) {
        final String line = s.nextLine();
        try (final Scanner ss = new Scanner(line)) {
          switch (ss.next().charAt(0)) {
            case 'Q':
              rivestimento.add(new PiastrellaQuadrata(ss.nextInt(), ss.nextInt()));
              break;
            case 'R':
              rivestimento.add(new PiastrellaRomboidale(ss.nextInt(), ss.nextInt(), ss.nextInt()));
              break;
            case 'T':
              rivestimento.add(new PiastrellaTriangolare(ss.nextInt(), ss.nextInt(), ss.nextInt()));
              break;
            case 'P':
              final List<Pavimentazione.Componente> componenti = new ArrayList<>();
              while (ss.hasNextInt())
                componenti.add(new Pavimentazione.Componente(ss.nextInt(), rivestimento.get(ss.nextInt())));
              rivestimento.add(new Pavimentazione(componenti));
              break;
            default:
              throw new IllegalArgumentException();
          }
        }
      }
      for (final Rivestimento r : rivestimento)
        if (r instanceof Pavimentazione)
          System.out.println(r.superficie() + "\t" + r.costo());
    }
  }
}