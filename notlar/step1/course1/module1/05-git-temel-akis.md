# 1.5 — Git: Staging Area ve Temel Akış

## Ne işe yarar
Git'in üç aşamalı yapısını (Working Directory → Staging Area →
Repository) ve git status'un bu aşamaları nasıl gösterdiğini açıklar.

## Özet
- Working Directory: dosyalarını düzenlediğin yer. Değişiklik burada
  olur, Git henüz "kaydetmiş" sayılmaz.
- Staging Area (index): bir sonraki commit'e neyin dahil olacağını
  seçtiğin ara adım. git add ile buraya taşınır.
- Repository (local): git commit ile kalıcı bir kayıt (snapshot)
  oluşturulan yer. Bu senin bilgisayarında.
- origin/main (remote): GitHub'daki kopya. git push, local
  repository'i buraya senkronlar.
- git status dört farklı mesaj verir, hangi aşamada olduğunu söyler:
  "nothing to commit, working tree clean"     → hepsi senkron
  "Changes not staged for commit"             → Working Directory'de
  "Changes to be committed"                   → Staging Area'da
  "ahead of origin/main by N commit"          → local'de var, push
  edilmedi

## Komutlar
git status              // şu an hangi aşamadayım
git add dosya.java      // Working Directory → Staging Area
git add .               // her değişen/yeni dosyayı stage'e al
git commit -m "mesaj"   // Staging Area → Local Repository
git push                // Local Repository → GitHub (origin)

## Tuzak
git add ile stage'lenen bir dosyayı sonra tekrar düzenlersen,
staging area'daki hâli GÜNCELLENMEZ — o an dondurulmuş bir
kopyadır. Son değişikliği de dahil etmek için git add'i tekrar
çalıştırman gerekir.

## .gitignore
Proje kök klasörüne konur, ignore edilecek dosyanın içine değil.
İçinde klasör/dosya kalıpları listelenir:
.idea/      → bu klasörü tamamen yok say
out/        → derlenmiş dosyaların klasörü
*.class     // * joker karakter, ".class" ile biten her dosya
.gitignore'un kendisi de commit'lenip push edilmeli — yoksa kural
sadece senin bilgisayarında geçerli olur, repoyu paylaşınca gitmez.

## Egzersiz (bakmadan yaz)
Bir dosyada değişiklik yap. git status'u üç kez çalıştır: değişiklik
sonrası, git add sonrası, git commit sonrası. Her seferinde çıkan
mesajın hangi aşamayı gösterdiğini yaz. Sonra push edip son bir kez
git status çalıştır.