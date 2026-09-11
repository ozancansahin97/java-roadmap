public class NullKontrolDeneme {
    public static void main(String[] args) {
        String isim = null;

        if (isim != null && isim.length() > 3) {
            System.out.println("Isim uzun");
        } else {
            System.out.println("Kontrol guvenli sekilde atlatildi");
        }
    }
}