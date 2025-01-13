import java.util.ArrayList;
import java.util.Scanner;

// Interfaccia per elementi riproducibili
interface Riproducibile {
    void play();
}

// Classe astratta per ElementoMultimediale
abstract class ElementoMultimediale {
    protected String titolo;

    public ElementoMultimediale(String titolo) {
        this.titolo = titolo;
    }

    public abstract void show();
    public abstract boolean isRiproducibile();
}

// Classe per RegistrazioneAudio
class RegistrazioneAudio extends ElementoMultimediale implements Riproducibile {
    private int durata;
    private int volume;

    public RegistrazioneAudio(String titolo, int durata, int volume) {
        super(titolo);
        this.durata = durata;
        this.volume = volume;
    }

    public void play() {
        for (int i = 0; i < durata; i++) {
            System.out.println(titolo + "!" + "!".repeat(volume));
        }
    }

    public void abbassaVolume() {
        if (volume > 0) {
            volume--;
        }
    }

    public void alzaVolume() {
        volume++;
    }

    public void show() {
        System.out.println(titolo + " (Audio) - Durata: " + durata + ", Volume: " + volume);
    }

    @Override
    public boolean isRiproducibile() {
        return true;
    }
}

// Classe per Video
class Video extends ElementoMultimediale implements Riproducibile {
    private int durata;
    private int volume;
    private int luminosita;

    public Video(String titolo, int durata, int volume, int luminosita) {
        super(titolo);
        this.durata = durata;
        this.volume = volume;
        this.luminosita = luminosita;
    }

    public void play() {
        for (int i = 0; i < durata; i++) {
            System.out.println(titolo + "!" + "!".repeat(volume) + "*".repeat(luminosita));
        }
    }

    public void aumentaLuminosita() {
        luminosita++;
    }

    public void diminuisciLuminosita() {
        if (luminosita > 0) {
            luminosita--;
        }
    }

    public void show() {
        System.out.println(titolo + " (Video) - Durata: " + durata + ", Volume: " + volume + ", Luminosità: " + luminosita);
    }

    @Override
    public boolean isRiproducibile() {
        return true;
    }
}

// Classe per Immagine
class Immagine extends ElementoMultimediale {
    private int luminosita;

    public Immagine(String titolo, int luminosita) {
        super(titolo);
        this.luminosita = luminosita;
    }

    public void show() {
        System.out.println(titolo + " (Immagine) " + "*".repeat(luminosita));
    }
    
    public void aumentaLuminosita() {
        luminosita++;
    }

    public void diminuisciLuminosita() {
        if (luminosita > 0) {
            luminosita--;
        }
    }

    @Override
    public boolean isRiproducibile() {
        return false;
    }
}

// Classe principale del lettore multimediale
public class LettoreMultimediale {
    private ArrayList<ElementoMultimediale> elementi = new ArrayList<>();

    public void aggiungiElemento(ElementoMultimediale elemento) {
        if (elementi.size() < 5) {
            elementi.add(elemento);
        } else {
            System.out.println("Non è possibile aggiungere più di 5 elementi.");
        }
    }

    public void eseguiElemento(int index) {
        if (index >= 0 && index < elementi.size()) {
            ElementoMultimediale elemento = elementi.get(index);
            if (elemento.isRiproducibile()) {
                ((Riproducibile) elemento).play();
            } else {
                elemento.show();
            }
        } else {
            System.out.println("Indice non valido.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LettoreMultimediale lettore = new LettoreMultimediale();

        // Creazione degli elementi
        for (int i = 0; i < 5; i++) {
            System.out.println("Inserisci il tipo di elemento (1: Audio, 2: Video, 3: Immagine): ");
            int tipo = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            System.out.println("Inserisci il titolo: ");
            String titolo = scanner.nextLine();

            if (tipo == 1) { // Registrazione Audio
                System.out.println("Inserisci la durata: ");
                int durata = scanner.nextInt();
                System.out.println("Inserisci il volume: ");
                int volume = scanner.nextInt();
                lettore.aggiungiElemento(new RegistrazioneAudio(titolo, durata, volume));
            } else if (tipo == 2) { // Video
                System.out.println("Inserisci la durata: ");
                int durata = scanner.nextInt();
                System.out.println("Inserisci il volume: ");
                int volume = scanner.nextInt();
                System.out.println("Inserisci la luminosità: ");
                int luminosita = scanner.nextInt();
                lettore.aggiungiElemento(new Video(titolo, durata, volume, luminosita));
            } else if (tipo == 3) { // Immagine
                System.out.println("Inserisci la luminosità: ");
                int luminosita = scanner.nextInt();
                lettore.aggiungiElemento(new Immagine(titolo, luminosita));
            } else {
                System.out.println("Tipo non valido.");
            }
        }

        // Interazione con l'utente
        int scelta;
        do {
            System.out.println("Scegli un elemento da eseguire (1-5) o 0 per uscire: ");
            scelta = scanner.nextInt();
            if (scelta > 0 && scelta <= 5) {
                lettore.eseguiElemento(scelta - 1);
            }
        } while (scelta != 0);

        scanner.close();
        System.out.println("Programma terminato.");
    }
}