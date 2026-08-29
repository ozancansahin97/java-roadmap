# 2.3 — Type Casting: Widening ve Narrowing

## Ne işe yarar
Farklı primitive tipler arasında değer aktarırken hangi
dönüşümlerin güvenli (otomatik) hangilerinin riskli (açık cast
gerektiren) olduğunu açıklar.

## Özet
- Boyut sıralaması (küçükten büyüğe): byte < short < int < long
  float < double. char ayrı bir daldan int'e katılır (aşağıya bak).
- WIDENING (genişletme): küçük tipten büyük tipe otomatik dönüşüm.
  Genelde risksiz, Java kendisi yapar, syntax gerekmez.
  (int baseSkor = 42; double d = baseSkor; -> 42.0)
- NARROWING (daraltma): büyük tipten küçük tipe dönüşüm. Java
  OTOMATİK YAPMAZ, açık cast gerekir: int x = (int) birDouble;
- Narrowing'in iki riski:
    1. Ondalık KESME (yuvarlama değil!): (int) 9.81 -> 9
    2. OVERFLOW: hedef tipin sınırını aşan değer "wraparound" yapar:
       (int) 3000000000L -> -1294967296
- char NEDEN AYRI: byte/short/int/long "signed" (negatif değer
  alabilir), char "unsigned" (sadece 0-65535, negatif YOK, çünkü
  bir Unicode kod noktasını temsil eder). Bu yüzden byte/short ile
  char arasında OTOMATİK dönüşüm YOKTUR (ör: byte'tan char'a atama
  derleme hatası verir: "possible lossy conversion"). char, kendi
  ayrı bacağından doğrudan int'e widening yapar.
- long -> float DE WIDENING SAYILIR ama boyut küçülüyormuş gibi
  görünür (long=8 byte, float=4 byte). Sebep: float bilimsel
  gösterim mantığıyla çalışır (işaret + üs + basamaklar), bu sayede
  daha az bit ile ÇOK DAHA GENİŞ bir ARALIĞA ulaşır, ama HASSASİYETİ
  (precision) düşüktür. Yani overflow olmaz ama sayının son
  basamakları sessizce yuvarlanabilir (uzun bir long değeri float'a
  atarsan son haneler kaybolabilir).
- boolean SAYISAL TİPLERİN DIŞINDADIR. int<->boolean gibi hiçbir
  dönüşüm (ne widening ne narrowing) YAPILAMAZ. İnternette bunu
  zincire dahil eden diyagramlar hatalıdır.
- MIXED-TYPE İFADELER: farklı tipler aynı işlemde kullanıldığında,
  Java HER ZAMAN küçük olanı büyük olana genişletir (sıra önemli
  değil, sadece boyut/tip önemli).
- Açık cast yazmak "bu riski kabul ediyorum" demektir, Java bir
  daha uyarmaz.

## Kod
// Widening (otomatik, risksiz - genel durum)
int baseSkor = 42;
double genisSkor = baseSkor;          // 42.0

// Widening ama hassasiyet kaybı riski taşıyan özel durum
long buyukSayi = 9223372036854775807L;
float floatHali = buyukSayi;          // 9.223372E18 (son haneler kayboldu!)

// char'ın ayrı davranışı
byte b = 10;
char c = b;                            // DERLEME HATASI: lossy conversion

// Narrowing (açık cast gerekir)
double olcum = 9.81;
int kesilmis = (int) olcum;           // 9 (kesme, yuvarlama değil)

long buyukPop = 3000000000L;
int sikismis = (int) buyukPop;        // -1294967296 (overflow!)

// Mixed-type: küçük olan büyüğe genişler, sıra önemsiz
short adet = 5;
double fiyat = 19.99;
double toplam = adet * fiyat;         // short -> double'a genişler

## Tuzak
İki farklı sessiz risk var:
1. Narrowing'de (int) gibi bir cast: ondalık kesme + overflow riski.
2. Widening'de bile (özellikle long->float, long->double gibi çok
   büyük sayılarda): sayı taşmaz ama HASSASİYET kaybedilebilir,
   Java hiçbir uyarı vermez çünkü teknik olarak widening "güvenli"
   sayılır.
   İnternetten bulunan diyagramlara körü körüne güvenme (örn: boolean'ı
   zincire dahil eden hatalı görseller var) — resmi kaynakla
   (Java Language Specification, JDK dokümantasyonu) doğrula.

## Egzersiz (bakmadan yaz)
1. Bir int'i bir double'a widening ile ata, yazdır (X.0 formatını gözlemle).
2. Bir double (örn 7.77) değerini (int) ile cast et, kesme (yuvarlama
   değil) olduğunu doğrula.
3. int'in sınırını aşan bir long değeri (int)'e cast et, overflow'u gözlemle.
4. Bir byte'ı bir char'a atamayı dene, derleme hatasını gözlemle,
   hata mesajını yorum satırı olarak not al.
5. long'un maksimum değerini bir float'a ata, çıktıdaki bilimsel
   gösterimi ve hassasiyet kaybını gözlemle. olarak açıkla.