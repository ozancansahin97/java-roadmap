# 2.4 — final Anahtar Kelimesi ve Sabitler

## Ne işe yarar
Bir değişkenin değerini kalıcı olarak kilitlemeyi (final), sabitler
için doğru isimlendirmeyi (SCREAMING_SNAKE_CASE) ve sınıf çapında
paylaşılan sabitleri (static final) açıklar.

## Özet
- final: bir değişkene bir kere değer verildikten sonra, bir daha
  değiştirilmesini ENGELLER. İhlal etmeye çalışmak COMPILE-TIME
  hatası verir (kod hiç çalışmaz).
- İsimlendirme: normal değişkenler camelCase (studentAge), final
  sabitler SCREAMING_SNAKE_CASE (MAX_STUDENTS, tüm harfler büyük,
  kelimeler _ ile ayrılır). Bu, okuyana "bu asla değişmez" sinyali verir.
- static final: sabiti bir CLASS FIELD'ı yapar VE class'ın kendisine
  ait tek bir kopya olmasını sağlar (her nesne ayrı kopya tutmaz,
  bellek israfı önlenir). Profesyonel kodda sabitler genelde böyle
  tanımlanır.
- COMPILE-TIME vs RUNTIME hata formatları:
    - Compile-time: "java: [mesaj]" ile başlar, "Process finished"
      satırı HİÇ görünmez (program çalışmadı bile).
    - Runtime: "Exception in thread "main" ..." ile başlar, stack
      trace gelir, sonda "Process finished with exit code 1" görülür
      (program çalıştı ama hatayla bitti).

## Kod
public class FizikMotoru {
public static final double YERCEKIMI_IVMESI = 9.81;
public static final int SANIYE_DAKIKADA = 60;

    public double dususMesafesiHesapla(double saniye) {
        return 0.5 * YERCEKIMI_IVMESI * (saniye * saniye);
    }

    public static void main(String[] args) {
        FizikMotoru motor = new FizikMotoru();
        double mesafe = motor.dususMesafesiHesapla(3.0);
        System.out.println(mesafe);   // 44.145
    }
}

## Tuzak
final sadece "yeniden atamayı" engeller. final bir değişken bir
NESNEYE referans veriyorsa (ileride Object'lerde göreceğiz), o
nesnenin İÇ İÇERİĞİ hâlâ değişebilir — sadece değişkenin başka bir
nesneyi göstermesi engellenir. (Bu detay ileride OOP modülünde
netleşecek, şimdilik akılda tutmak yeterli.)

## Egzersiz (bakmadan yaz)
1. final ile bir sabit tanımla, sonra ona ikinci kez değer atamayı
   dene, compile-time hatasını gözlemle ve formatını not al.
2. Bir class yaz, içine static final ile iki sabit koy
   (SCREAMING_SNAKE_CASE ile isimlendir), bir metotta bu sabitleri
   kullanarak bir hesaplama yap, sonucu elle doğrula.