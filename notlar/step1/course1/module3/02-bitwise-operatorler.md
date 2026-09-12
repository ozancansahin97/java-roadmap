# 3.2 — Bitwise ve Bit-Shift Operatörler

## Ne işe yarar
Sayıların bellekteki ikili (binary) temsilini doğrudan manipüle
etmeyi, ve bunun performans-kritik senaryolarda (bit flag'ler, veri
paketleme, hızlı matematik) nasıl kullanıldığını açıklar. Günlük
kodlamada nadiren gerekir ama Java'nın kendi iç kütüphaneleri
(HashMap gibi) bunu kullanır.

## Özet
TEMEL BITWISE OPERATÖRLER (iki sayının bitlerini pozisyon pozisyon
karşılaştırır):
- & (AND): ikisi de 1 ise 1
- | (OR): en az biri 1 ise 1
- ^ (XOR): sadece biri 1 ise (farklıysa) 1
- ~ (complement, tekli/unary): tüm bitleri ters çevirir.
  ~n = -(n+1) (two's complement nedeniyle)

KAYDIRMA OPERATÖRLERİ:
- << (sola kaydır): n << k, n × 2^k ile aynı. Sağdan sıfır doldurur.
- >> (işaretli sağa kaydır): işaret bitini korur (negatifse 1,
  pozitifse 0 ile doldurur). n >> k, n'in 2^k'ye bölümüne yakın.
- >>> (işaretsiz sağa kaydır): işareti HİÇ önemsemez, HER ZAMAN
  0 ile doldurur. Negatif sayıyı devasa bir pozitife çevirebilir.
- TUZAK: 32 basamak kaydırma sıfır vermez! JVM kaydırma miktarını
  maskeler (int için &0x1F), yani x << 32, x << 0 gibi davranır
  (sayı DEĞİŞMEZ).

HEX (0x) GÖSTERİMİ: 16 tabanlı sayı sistemi, 4 bit = 1 hex hane.
0xFF = ikili 11111111 = decimal 255. & 0xFF, "son 8 biti tut,
gerisini sil" demek.

BIT FLAG'LERİ (izin yönetimi): her izin bir bit pozisyonu
(1<<0, 1<<1, 1<<2...) olarak tanımlanır, tek bir int'te 32 farklı
durum saklanabilir (ayrı boolean'lar yerine bellek tasarrufu):
Aç:      flags |= MASK;
Kapat:   flags &= ~MASK;
Çevir:   flags ^= MASK;
Kontrol: (flags & MASK) != 0

VERİ PAKETLEME: birden fazla küçük değeri (örn 4 tane 8-bit kanal)
tek bir int'e sıkıştırma: << ile her değeri kendi konumuna kaydır,
| ile birleştir. Açarken: >>> ile hizala, & 0xFF ile izole et.

BİT NUMARALARI:
- n & (n-1) == 0 (n>0 iken): n'in 2'nin kuvveti olup olmadığını
  hızlıca kontrol eder (2'nin kuvvetlerinde tek bir 1 biti vardır).
- x & (M-1), M 2'nin kuvvetiyken x % M ile AYNI sonucu verir ama
  çok daha hızlıdır (HashMap'in iç kodunda kullanılır).
- XOR ile 3 satırda geçici değişken olmadan takas yapılabilir
  (a^=b; b^=a; a^=b;) — ilginç ama pratikte önerilmez, okunabilirlik
  düşer.

## Kod
// Temel bitwise
int a = 45, b = 27;
a & b   // 9
a | b   // 63
a ^ b   // 54
~a      // -46

// Kaydırma - işaret farkı
int negatif = -20;
negatif >> 2    // -5  (işaret korunur)
negatif >>> 2   // 1073741819  (işaret kaybolur, dev pozitif)

// Bit flag
public static final int READ = 1 << 0;
public static final int WRITE = 1 << 1;
int izinler = READ | WRITE;
boolean yazabilirMi = (izinler & WRITE) != 0;
izinler &= ~WRITE;    // WRITE'ı kapat

// Power of two kontrolü
boolean isPowerOfTwo(int n) {
return n > 0 && (n & (n - 1)) == 0;
}

// printf formatlama
System.out.printf("0x%08X%n", sayi);  // hex, 8 karakter, sifirla doldur

## Tuzak
>> ile >>> karıştırmak, özellikle negatif/işaretsiz veri işlerken
sessiz veri bozulmasına yol açar — hata vermez, sadece yanlış (ve
genelde devasa) bir sayı üretir. Ağ/protokol verisi işlerken hangisi
gerektiğine dikkat etmek gerekir.

## Egzersiz (bakmadan yaz)
1. İki sayı seç, & | ^ ~ işlemlerini elle bit bit hesapla, sonra
   kodla doğrula.
2. Bir negatif sayıyı hem >> hem >>> ile kaydır, farkı gözlemle ve
   neden farklı olduğunu yorum satırıyla açıkla.
3. 4 farklı bit flag tanımlayıp (1<<0'dan 1<<3'e), açma/kapama/
   kontrol işlemlerinin hepsini uygula.
4. isPowerOfTwo fonksiyonunu kendi yaz (bakmadan), birkaç sayıyla test et.