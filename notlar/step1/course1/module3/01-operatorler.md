# 3.1 — Aritmetik, İlişkisel, Mantıksal Operatörler

## Ne işe yarar
İş mantığını (business logic) ifade etmek için kullanılan üç
operatör ailesini: matematik yapan, karşılaştıran ve birden fazla
koşulu birleştiren operatörleri açıklar.

## Özet
- ARİTMETİK: +, -, *, /, ve % (modulo/kalan). % iki sayıyı böler,
  KALANI verir (17 % 5 = 2). Döngüsel/tekrarlayan durumlarda kullanılır.
- İLİŞKİSEL: >, <, >=, <=, ==, !=. Sonuç HER ZAMAN boolean.
  == ile = KARIŞTIRILMAMALI: = atama yapar, == karşılaştırma yapar.
- MANTIKSAL:
  && (AND) : ikisi de true olmalı, biri false ise sonuç false.
  || (OR)  : en az biri true olmalı, ikisi de false ise sonuç false.
  !  (NOT) : tersine çevirir (true->false, false->true).
- SHORT-CIRCUIT (kısa devre) DAVRANIŞI:
    - a || b: a zaten true ise, b HİÇ ÇALIŞTIRILMAZ.
    - a && b: a zaten false ise, b HİÇ ÇALIŞTIRILMAZ.
    - Bu davranış hem performans sağlar hem de güvenlik: örn.
      (isim != null && isim.length() > 3) yazınca, isim null olduğunda
      sağ taraf hiç çalışmaz, NullPointerException oluşmaz.
    - SIRA ÖNEMLİ: güvenlik kontrolünü her zaman && zincirinin SOLUNA
      yaz (önce null kontrolü, sonra kullanım).

## Kod
// Modulo
int kalan = 100 % 45;              // 10

// İlişkisel
boolean sonuc = (75.00 >= 50.00);  // true

// Mantıksal + short-circuit güvenliği
String isim = null;
if (isim != null && isim.length() > 3) {   // null kontrolü SOLDA
// güvenli, çünkü isim != null false ise buraya hiç girilmez
}

// İki aşamalı iş kararı örneği (kredi onayı)
boolean isEligible = (creditScore >= 700) && (income >= 50000) && (!hasDefaultHistory);
boolean finalApproval = isEligible || (hasCoSigner && creditScore >= 650);

## Tuzak
&& veya || zincirinde güvenlik kontrolünü (null kontrolü gibi) SAĞA
yazmak tehlikelidir — short-circuit solu önce değerlendirdiği için,
sıra ters olursa kontrol işe yaramadan hataya düşülür:
isim.length() > 3 && isim != null   // YANLIŞ SIRA, isim null ise çöker!
isim != null && isim.length() > 3   // DOĞRU SIRA, güvenli

## Egzersiz (bakmadan yaz)
1. Bir toplam dakika değerini alıp % ile saat:dakika formatına
   çeviren küçük bir hesaplama yaz (örn: 130 dakika -> 2 saat 10 dakika).
2. Bir metodun sadece belirli bir koşulda çağrıldığını kanıtlayan
   bir short-circuit örneği yaz (System.out.println ile "çalıştım"
   yazan bir yardımcı metot kullan).
3. Bir null değişken üzerinde, güvenli sırayla yazılmış bir &&
   kontrolü kur, NullPointerException almadığını doğrula.