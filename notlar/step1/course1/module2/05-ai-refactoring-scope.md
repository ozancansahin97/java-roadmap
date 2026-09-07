# 2.5 — AI ile Değişken İsimlendirme ve Scope Refactoring

## Ne işe yarar
Kötü isimlendirilmiş ve gereksiz geniş kapsamlı değişkenleri, AI
destekli refactoring ile domain'e özgü isimlere ve dar kapsama
dönüştürmeyi açıklar.

## Özet
- Kötü isimlendirme (d, t, c, x gibi tek harfli/belirsiz isimler)
  kodun okunabilirliğini yok eder, hem başkası hem gelecekteki sen
  için.
- İYİ İSİM = domain'e özgü + ne olduğunu değil NE İŞE YARADIĞINI
  anlatır (total değil excessStockTotal gibi).
- SCOPE DARALTMA: bir değişkeni "önce boş tanımla, sonra if-else
  içinde doldur" yerine, mümkünse ternary (? :) ile TANIMLANDIĞI
  ANDA doğru değerini almasını sağla. final ekle çünkü artık
  değişmeyecek.
  KÖTÜ:  double x; if(kosul) { x = a; } else { x = b; }
  İYİ:   final double x = kosul ? a : b;
- FOR-EACH (bonus): for (int eleman : dizi) şeklinde, index (i)
  kullanmadan dizinin her elemanına sırayla erişme. Klasik for'dan
  (int i=0; i<dizi.length; i++) daha güvenli (index hatası riski yok)
  ve daha okunabilir.
- Cursor kullanımı: kod bloğunu seç, Ctrl+L ile chat panelini aç,
  talimat yaz. AI'ın önerisini KÖR KABUL ETME — hangi ipuçlarından
  yola çıktığını sorgula, isimlerin gerçekten doğru anlamı
  taşıdığını kendi mantığınla doğrula.

## Kod
// Scope daraltma örneği
// KÖTÜ:
double indirimOrani;
if (vipMusteri) {
indirimOrani = 0.20;
} else {
indirimOrani = 0.05;
}

// İYİ:
final double indirimOrani = vipMusteri ? 0.20 : 0.05;

// For-each örneği
int[] sayilar = {10, 20, 30};
for (int sayi : sayilar) {          // index yok, direkt eleman
System.out.println(sayi);
}
// klasik for ile aynı işi yapar:
for (int i = 0; i < sayilar.length; i++) {
System.out.println(sayilar[i]);  // index ile erişim
}

## Tuzak
AI'ın önerdiği isim her zaman senin niyetini doğru okumayabilir —
sınırlı ipuçlarına (parametre adının baş harfi, değerin büyüklüğü
gibi) bakarak "tahmin" ediyor. Önerilen ismi kabul etmeden önce
gerçekten doğru anlamı taşıyıp taşımadığını kontrol et.

## Egzersiz (bakmadan yaz)
1. Tek harfli değişkenlerle kötü yazılmış bir metot yaz (kendi
   üret, basit bir hesaplama olsun). AI ile refactor ettir, önerilen
   her ismi sorgula.
2. Bir if-else ile doldurulan bir değişkeni, ternary + final
   kullanarak scope'unu daraltarak yeniden yaz.
3. Bir int dizisi üzerinde hem klasik for hem for-each ile aynı
   toplamı hesapla, ikisinin de aynı sonucu verdiğini doğrula.