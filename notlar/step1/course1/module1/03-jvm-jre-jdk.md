# 1.3 — JVM, JRE, JDK Mimarisi

## Ne işe yarar
Java kodunun neden her platformda çalışabildiğini (WORA) ve
derleme ile çalıştırmanın neden ayrı adımlar olduğunu açıklar.

## Özet
- javac (derleyici) .java dosyasını bytecode'a (.class) çevirir.
  Bytecode platformdan bağımsızdır, her yerde aynıdır.
- java komutu JVM'i başlatır. JVM bytecode'u okuyup çalıştığı
  platforma özel makine koduna çevirir. Platforma özel olan JVM'in
  kendisidir, bytecode değil.
- JDK = JRE + geliştirme araçları (javac, jdb, jar). Kod yazan
  herkeste bulunur.
- JRE = JVM + standart kütüphaneler (java.lang, java.util gibi).
  Sadece çalıştırma yeteneği verir, derleyici içermez.
- Hiyerarşi: JDK, JRE'yi içerir; JRE, JVM'i içerir (iç içe kutular).

## Komutlar
javac Main.java     // derler, Main.class üretir (bytecode)
java Main            // JVM'i başlatır, Main.class'ı çalıştırır
// (uzantısız, sadece sınıf adı yazılır)

## Tuzak
.class dosyasını Notepad gibi bir metin editörüyle açmak anlamsız
karakterler gösterir — çünkü o dosya ikili veri (binary), metin
değil. Okunabilir hâlini görmek için IntelliJ'in decompiler'ını
kullanmak gerekir.

## Egzersiz (bakmadan yaz)
JDK, JRE ve JVM'i üçer kelimeyle tanımla (örnek: "JDK = geliştirme
araç kutusu"). Sonra kendi cümlelerinle şu zinciri yaz:
.java dosyası nasıl ekran çıktısına dönüşür, hangi komut hangi
adımda devreye girer?