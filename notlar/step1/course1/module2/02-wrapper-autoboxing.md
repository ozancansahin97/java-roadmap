# 2.2 — Wrapper Class'lar, Autoboxing/Unboxing, Parsing

## Ne işe yarar
Primitive tiplerin nesne (object) hâlini, bunların hiyerarşisini,
aralarındaki otomatik dönüşümü ve String'den sayıya çevirme
(parsing) işlemini açıklar.

## Özet
- Her primitive'in bir wrapper class'ı var: int→Integer, double→Double,
  char→Character, boolean→Boolean, vb. (java.lang paketinde).
- Hiyerarşi: Object (en tepe, herkesin atası) → Number (abstract,
  sadece sayısal wrapper'ların ortak ataları: Integer, Double, Long...)
  ve ayrıca doğrudan Object'ten türeyen Character, Boolean.
- abstract class demek, o class'tan doğrudan nesne üretilemez demek
  (new Number() YAZILAMAZ). Sadece somut alt sınıfları kullanılır
  (new Integer(5) gibi).
- Number'ın tüm alt sınıflarına miras kalan ortak metotlar var:
  intValue(), doubleValue() gibi — bir sayıyı başka bir sayı tipine
  çevirmeyi sağlar.
- Primitive default değeri 0/false'tur (sadece field'larda). Wrapper
  default değeri NULL'dur (field olarak tanımlıysa). Local değişkenler
  (primitive ya da wrapper fark etmez) hiç default almaz, atanmadan
  kullanılamaz.
- Autoboxing: primitive → wrapper otomatik dönüşüm (derleyici gizlice
  Integer.valueOf() çağırır).
- Unboxing: wrapper → primitive otomatik dönüşüm (derleyici gizlice
  .intValue() gibi bir metot çağırır).
- TUZAK: null olan bir wrapper'ı unbox etmeye çalışmak
  NullPointerException (NPE) fırlatır.
- Wrapper karşılaştırmada == YANLIŞ (referans/adres karşılaştırır),
  .equals() DOĞRU (değer karşılaştırır). Küçük sayılarda (-128..127)
  Java önbellek (cache) kullandığı için == bazen yanılgıyla true
  dönebilir — bu yüzden hep .equals() kullanılmalı.
- PARSING: dış kaynaktan (kullanıcı girdisi, dosya, form) gelen veri
  her zaman String olarak gelir. Integer.parseInt(String) ve
  Double.parseDouble(String) ile bunu gerçek bir sayıya çevirirsin.
- TUZAK: parseInt/parseDouble'a sayı olmayan bir metin verirsen
  NumberFormatException fırlatır (NullPointerException'dan farklı
  bir hata türü — bu "biçim yanlış" der, NPE "boş bir şeye eriştin" der).

## Kod
int primitiveSkor = 95;
Integer wrapperSkor = primitiveSkor;      // autoboxing
int extractedSkor = wrapperSkor;           // unboxing

Integer nullSkor = null;
int patlar = nullSkor;                     // NullPointerException!

Integer a = 500, b = 500;
a == b            // false (farklı nesne adresleri)
a.equals(b)       // true  (aynı değer)

String yasMetni = "25";
int yas = Integer.parseInt(yasMetni);      // "25" -> 25
double fiyat = Double.parseDouble("199.99");

String bozuk = "yirmi bes";
int hata = Integer.parseInt(bozuk);        // NumberFormatException!

## Tuzak
İki ayrı riskli nokta var:
1. Bir Integer/Double/vb. değişkenin null olabileceğini unutup
   doğrudan unboxing yapmak → NullPointerException.
2. Dış kaynaktan gelen bir metnin gerçekten sayı formatında
   olduğuna güvenip parseInt/parseDouble çağırmak → sayı değilse
   NumberFormatException.
   İkisi de "veri güvenilir değilse önce kontrol et" prensibiyle
   önlenir (ileride try-catch ile göreceğiz).

## Egzersiz (bakmadan yaz)
1. Bir Integer değişken tanımla, null bırak, int'e atamaya çalış,
   NullPointerException'ı gözlemle.
2. İki Integer (aynı büyük sayı, örn 1000) tanımlayıp hem == hem
   .equals() ile karşılaştır, farkını yorum satırı olarak yaz.
3. Bir String tanımla ("42"), Integer.parseInt ile int'e çevir,
   üzerinde bir toplama işlemi yap, sonucu yazdır.
4. Sayı olmayan bir String'i parseInt'e ver, NumberFormatException'ı
   gözlemle.