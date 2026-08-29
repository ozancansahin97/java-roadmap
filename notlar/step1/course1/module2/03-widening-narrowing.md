# 2.3 — Type Casting: Widening ve Narrowing

## Ne işe yarar
Farklı primitive tipler arasında değer aktarırken hangi
dönüşümlerin güvenli (otomatik) hangilerinin riskli (açık cast
gerektiren) olduğunu açıklar.

## Özet
- Boyut sıralaması (küçükten büyüğe): byte < short/char < int < long
  < float < double.
- WIDENING (genişletme): küçük tipten büyük tipe otomatik dönüşüm.
  Risksiz, Java kendisi yapar, hiçbir syntax gerekmez.
  (int baseSkor = 42; double d = baseSkor; -> 42.0)
- NARROWING (daraltma): büyük tipten küçük tipe dönüşüm. Java
  OTOMATİK YAPMAZ, açık cast (target tip parantez içinde) gerekir:
  int x = (int) birDouble;
- Narrowing'in iki riski:
    1. Ondalık KESME (yuvarlama değil!): (int) 9.81 -> 9, (int) 9.99 -> 9
    2. OVERFLOW: hedef tipin sınırını aşan bir değer "wraparound" yapar,
       anlamsız bir sayı çıkar: (int) 3000000000L -> -1294967296
- Açık cast yazmak "bu riski kabul ediyorum" demektir, Java seni
  bir daha uyarmaz.
- MIXED-TYPE İFADELER: farklı tipler aynı işlemde kullanıldığında,
  Java HER ZAMAN küçük olanı büyük olana genişletir (SIRA ÖNEMLİ
  DEĞİL, sadece boyut önemli). short * double -> short önce double'a
  genişler, sonuç double olur.
- Not: double ile yapılan hesaplamalarda küçük yuvarlama hataları
  görülebilir (örn: 99.94999999999999 yerine 99.95 beklenmesi) —
  floating point'in doğası, ileri bir konu.

## Kod
// Widening (otomatik)
int baseSkor = 42;
double genisSkor = baseSkor;          // 42.0

// Narrowing (açık cast gerekir)
double olcum = 9.81;
int kesilmis = (int) olcum;           // 9 (kesme, yuvarlama değil)

long buyukSayi = 3000000000L;
int sikismis = (int) buyukSayi;       // -1294967296 (overflow!)

// Mixed-type: küçük olan büyüğe genişler, sıra önemsiz
short adet = 5;
double fiyat = 19.99;
double toplam = adet * fiyat;         // short -> double'a genişler

## Tuzak
(int) cast'i "güvenli bir yuvarlama" sanmak yanlış — hem ondalığı
atıyor (yuvarlamıyor) hem de hedef tipin sınırını aşan değerlerde
sessizce overflow'a sebep oluyor. Cast yazmadan önce değerin gerçekten
hedef tipe sığıp sığmadığını kontrol etmek gerekir.

## Egzersiz (bakmadan yaz)
1. Bir int'i bir double'a widening ile ata, yazdır (X.0 formatını gözlemle).
2. Bir double (örn 7.77) değerini (int) ile cast et, sonucun kesme
   yaptığını (yuvarlama değil) doğrula.
3. int'in sınırını aşan bir long değeri (int)'e cast et, overflow'u gözlemle.
4. Bir byte ile bir double'ı çarp, sonucun hangi tip olduğunu ve
   neden o tip olduğunu yorum satırı olarak açıkla.