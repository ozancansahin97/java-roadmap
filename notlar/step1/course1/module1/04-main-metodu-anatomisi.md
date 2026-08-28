# 1.4 — Hello World ve main Metodunun Anatomisi

## Ne işe yarar
JVM'in bir Java programını çalıştırırken tam olarak neyi aradığını
(main metodunun "imzası") ve her parçanın neden zorunlu olduğunu
açıklar.

## Özet
- JVM programı başlatırken tam şu imzaya sahip bir metot arar:
  public static void main(String[] args)
- public: metoda her yerden erişilebilir olmalı, JVM dışarıdan
  çağırabilsin diye.
- static: metot bir nesneye değil sınıfın kendisine ait. JVM
  new GreetingSystem() yapmadan direkt GreetingSystem.main(...)
  çağırabiliyor. static olmazsa IDE çalıştırmaya bile izin vermez.
- void: metodun geriye değer döndürmediğini belirtir. Eksik olursa
  kod derlenecek geçerli bir metot bile sayılmaz.
- main: isim sabit, JVM tam olarak bu ismi arıyor.
- String[] args: terminalden programa ekstra bilgi geçirmek için
  kullanılan parametre (örn: java GreetingSystem Ozan → args[0]
  = "Ozan").
- IDE'nin "Run" ikonu görünmüyorsa/tıklanamıyorsa, genelde main
  metodunun imzası bozuktur — önce onu kontrol et.

## Kod
public class GreetingSystem {
public static void main(String[] args) {
System.out.println("Welcome to Java development");
}
}

## Tuzak
Auto-complete (Tab ile otomatik tamamlama) bazen yazdığın kelimeyi
değil, IDE'nin tahmin ettiği kelimeyi kabul ettirir. "static" yazarken
Tab'a basmak "void"u silebilir. Kod çalışmıyorsa satırı harf harf
tekrar oku, göz gezdirme.

## Egzersiz (bakmadan yaz)
Boş bir dosyaya, main metodunun beş parçasını (public, static, void,
main, String[] args) tek tek yaz ve her birinin yanına ne işe
yaradığını tek cümleyle not et. Sonra çalışan bir Hello World
programını sıfırdan, bakmadan yaz.

## Test satırı
Bu bir denemedir.