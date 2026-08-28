# 2.1 — Primitive Tipler ve Bellek Yerleşimi (Stack vs Heap)

## Ne işe yarar
Java'nın 8 temel veri tipini, aralıklarını ve bir değişkenin
stack'te mi heap'te mi tutulduğunu belirleyen kuralı açıklar.

## Özet
- 8 primitive tip: boolean, byte, short, int, long, float, double, char.
- byte(1B) < short(2B) < int(4B) < long(8B) — bellek boyutuna göre sıralı.
- int en yaygın kullanılan tam sayı tipi; byte/short sadece milyonlarca
  kayıtta bellek tasarrufu gerektiğinde tercih edilir.
- Bir tam sayı sınırını (max/min) aşarsa Java hata VERMEZ, sessizce
  "wraparound" yapar (overflow/underflow). Bu sessiz bir mantık hatasıdır.
- long literal'lerine L eklenmeli, yoksa Java sayıyı int sanır ve
  int'in aralığını aşarsa derleme hatası verir.
- Local variable (metot içinde tanımlı, parametreler dahil) → STACK.
  Metot bitince anında silinir (LIFO mantığı, hızlı).
- Class field'ı (sınıfın içinde, metot dışında tanımlı) → HEAP,
  nesnenin bellek bloğunun içinde. Nesne yaşadığı sürece kalıcı.
- Default değerler SADECE field'lar için geçerli (nesne oluşunca
  otomatik atanır, örn: int → 0, double → 0.0). Local değişkenler
  default değer ALMAZ — değer atamadan kullanmaya çalışırsan Java
  derleme hatası verir.

## Kod
int max = Integer.MAX_VALUE;   // 2147483647
int overflow = max + 1;         // -2147483648 (sessiz overflow!)

long distance = 3000000000L;    // L şart, yoksa derleme hatası

public class Ogrenci {
private int numara;          // HEAP (field)
private double not1;         // HEAP (field)

    public void notGuncelle(double yeniNot) {  // yeniNot -> STACK
        double fark = yeniNot - this.not1;      // fark -> STACK
        this.not1 = yeniNot;
    }
}

## Tuzak
Overflow hiçbir hata/uyarı vermeden yanlış sonuç üretir. Büyük
sayılarla işlem yapıyorsan (özellikle toplama/çarpma döngülerinde),
int'in sınırına yaklaşıp yaklaşmadığını kontrol etmek gerekir.

## Egzersiz (bakmadan yaz)
Bir class yaz, içinde iki field (bir int, bir double) ve bir metot
olsun. Metodun içinde en az bir local değişken tanımla. Sonra her
değişkenin (field'lar dahil) stack'te mi heap'te mi olduğunu yorum
satırı olarak yaz. main metodundan bu class'tan bir nesne oluşturup
metodu çağır, çalıştır.