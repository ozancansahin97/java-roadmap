public class ShortCircuitDeneme {
    public static boolean kontrolEt() {
        System.out.println("kontrolEt CALISTI");
        return true;
    }

    public static void main(String[] args) {
        boolean sonuc = true || kontrolEt();
        System.out.println("Sonuc: " + sonuc);
    }
}