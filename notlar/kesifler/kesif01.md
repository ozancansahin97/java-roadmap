# Keşifler — Banka Simülasyonu Projesi

## Scanner (kullanıcı girdisi okuma)
- `Scanner scanner = new Scanner(System.in);` ile klavyeden okuma başlatılır.
- `.nextInt()`, `.nextDouble()` belirtilen tipte veri okur.
- Locale'e (bölgesel ayara) duyarlı: Türkçe sistemde ondalık ayracı
  VİRGÜL bekleniyor, nokta yazınca InputMismatchException fırlıyor.

## switch-case
- Bir değişkenin birden fazla olası değerine göre dallanma.
  if-else zincirinin düzenli alternatifi.
- Her case sonunda break; unutulmamalı, yoksa bir sonraki case'e
  "düşer" (fall-through).

## try-catch
- try içindeki kod hata fırlatabilir. catch, o hatayı yakalayıp
  programın çökmesini önler.
- catch (InputMismatchException e) gibi, sadece belirtilen hata
  türünü yakalar.
- Scanner'da geçersiz girdi sonrası scanner.next() ile bozuk veriyi
  "temizlemek" gerekir, yoksa bir sonraki okuma da bozuk veriyi
  görmeye devam eder.

## ArrayList
- Dizinin (array) büyüyebilen hâli. Kaç eleman olacağı baştan
  bilinmediğinde kullanılır.
- ArrayList<String> gibi, <...> içinde ne tip tutacağı belirtilir.
- .add(), .isEmpty() sık kullanılan metotlar.
- for-each (for (String x : liste)) dizilerde olduğu gibi
  ArrayList'te de çalışıyor.

## Gözlem
- double ile yapılan tekrarlı faiz hesaplarında küçük yuvarlama
  hataları birikiyor (6166.3589999999995 gibi). Gerçek finansal
  yazılımda BigDecimal tercih edilir (ileride öğrenilecek).