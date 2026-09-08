import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankaHesabi {
    final static double MINIMUM_BAKIYE = 0.0;
    final static double FAIZ_ORANI = 0.02;

    double bakiye;

    public static void main(String[] args) {
        BankaHesabi hesap = new BankaHesabi();
        hesap.bakiye = 0;

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> islemGecmisi = new ArrayList<>();
        boolean devam = true;

        while (devam) {
            System.out.println("\n--- Banka Hesabı ---");
            System.out.println("1. Yatır");
            System.out.println("2. Çek");
            System.out.println("3. Bakiye görüntüle");
            System.out.println("4. Çıkış");
            System.out.println("5. Faiz uygula");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    System.out.print("Yatırılacak tutar: ");
                    try {
                        double yatirilan = scanner.nextDouble();
                        hesap.bakiye += yatirilan;
                        System.out.println("Yeni bakiye: " + hesap.bakiye);
                        islemGecmisi.add("Yatırma: " + yatirilan + " TL, Yeni bakiye: " + hesap.bakiye);
                    } catch (InputMismatchException e) {
                        System.out.println("Geçersiz miktar, tekrar deneyin.");
                        scanner.next();
                    }
                    break;
                case 2:
                    System.out.print("Çekilecek tutar: ");
                    try {
                        double cekilen = scanner.nextDouble();
                        if (hesap.bakiye - cekilen < MINIMUM_BAKIYE) {
                            System.out.println("Yetersiz bakiye.");
                        } else {
                            hesap.bakiye -= cekilen;
                            System.out.println("Yeni bakiye: " + hesap.bakiye);
                            islemGecmisi.add("Çekme: " + cekilen + " TL, Yeni bakiye: " + hesap.bakiye);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Geçersiz miktar, tekrar deneyin.");
                        scanner.next();
                    }
                    break;
                case 3:
                    System.out.println("Güncel bakiye: " + hesap.bakiye);
                    break;
                case 4:
                    System.out.println("\nİşlem Özeti");
                    if (islemGecmisi.isEmpty()) {
                        System.out.println("Hiç işlem yapılmadı");
                    } else {
                        for (String islem : islemGecmisi) {
                            System.out.println(islem);
                        }
                    }
                    devam = false;
                    System.out.println("Çıkış yapılıyor...");
                    break;
                case 5:
                    double faizTutari = hesap.bakiye * FAIZ_ORANI;
                    hesap.bakiye = hesap.bakiye + faizTutari;
                    System.out.println("Faiz uygulandı. Yeni bakiye: " + hesap.bakiye);
                    islemGecmisi.add("Faiz: " + faizTutari + " TL, Yeni bakiye: " + hesap.bakiye);
                    break;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        }

        scanner.close();
    }
}
