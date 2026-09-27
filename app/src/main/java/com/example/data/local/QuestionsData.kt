package com.example.data.local

import com.example.data.model.Question
import com.example.data.model.StageInfo

object QuestionsData {

    val stages: List<StageInfo> = listOf(
        StageInfo(
            stageNumber = 1,
            title = "Etap 1: Başlangıç Seviyesi",
            subtitle = "Genel Kültür & Temel Bilgiler",
            difficultyLabel = "Çok Kolay",
            iconName = "School",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 2,
            title = "Etap 2: Kolay Seviye",
            subtitle = "Türkiye Coğrafyası & Kültür Mirası",
            difficultyLabel = "Kolay",
            iconName = "Landscape",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 3,
            title = "Etap 3: Temel Bilim & Doğa",
            subtitle = "Canlılar, Gezegenler & Doğa Kanunları",
            difficultyLabel = "Kolay - Orta",
            iconName = "Science",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 4,
            title = "Etap 4: Dünya Tarihi & Keşifler",
            subtitle = "Uygarlıklar, İcatlar & Tarihi Anlar",
            difficultyLabel = "Orta",
            iconName = "HistoryEdu",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 5,
            title = "Etap 5: Edebiyat & Sanat",
            subtitle = "Başyapıtlar, Yazarlar & Ressamlar",
            difficultyLabel = "Orta - İleri",
            iconName = "Brush",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 6,
            title = "Etap 6: Spor & Olimpiyatlar",
            subtitle = "Dünya Rekorları & Efsane Sporcular",
            difficultyLabel = "İleri",
            iconName = "SportsSoccer",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 7,
            title = "Etap 7: Sinema & Teknoloji",
            subtitle = "Bilgisayar Öncüleri, Uzay & Kült Filmler",
            difficultyLabel = "Zor",
            iconName = "Memory",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 8,
            title = "Etap 8: İleri Bilim & Felsefe",
            subtitle = "Kuantum, Biyokimya & Felsefi Akımlar",
            difficultyLabel = "Çok Zor",
            iconName = "Psychology",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 9,
            title = "Etap 9: Usta Seviye",
            subtitle = "Az Bilinen Coğrafya & Derin Tarih",
            difficultyLabel = "Uzman",
            iconName = "MilitaryTech",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 10,
            title = "Etap 10: Şampiyonlar Etabı",
            subtitle = "Nobel Ödülleri & Zirve Entelektüel Bilgi",
            difficultyLabel = "Deha",
            iconName = "WorkspacePremium",
            minPassingScore = 7
        )
    )

    val questions: List<Question> = listOf(
        // ================= ETAP 1 (1 - 10) : Çok Kolay =================
        Question(
            id = 1,
            stage = 1,
            category = "Genel Kültür",
            question = "Türkiye Cumhuriyeti'nin başkenti neresidir?",
            options = listOf("İstanbul", "Ankara", "İzmir", "Bursa"),
            correctAnswerIndex = 1,
            explanation = "Ankara, 13 Ekim 1923 tarihinde Türkiye Büyük Millet Meclisi kararıyla Türkiye'nin başkenti ilan edilmiştir.",
            points = 100
        ),
        Question(
            id = 2,
            stage = 1,
            category = "Astronomi",
            question = "Güneş Sistemimizdeki en büyük gezegen hangisidir?",
            options = listOf("Mars", "Satürn", "Jüpiter", "Venüs"),
            correctAnswerIndex = 2,
            explanation = "Jüpiter, Güneş Sistemi'nin kütle ve hacim olarak en büyük gezegenidir; içine yaklaşık 1.300 Dünya sığabilir.",
            points = 100
        ),
        Question(
            id = 3,
            stage = 1,
            category = "Matematik",
            question = "Öklid geometrisinde bir üçgenin iç açılarının toplamı kaç derecedir?",
            options = listOf("90°", "180°", "270°", "360°"),
            correctAnswerIndex = 1,
            explanation = "Düzlem geometrideki tüm üçgenlerin iç açıları toplamı her zaman tam olarak 180 derecedir.",
            points = 100
        ),
        Question(
            id = 4,
            stage = 1,
            category = "Genel Bilim",
            question = "Dünya'nın tek doğal uydusu aşağıdakilerden hangisidir?",
            options = listOf("Ay", "Güneş", "Titan", "Kutup Yıldızı"),
            correctAnswerIndex = 0,
            explanation = "Ay, Dünya'mızın tek doğal uydusu olup ortalama 384.400 kilometre uzaklıktadır.",
            points = 100
        ),
        Question(
            id = 5,
            stage = 1,
            category = "Fizik & Kimya",
            question = "Standart deniz seviyesi atmosfer basıncında saf su kaç santigrat derecede kaynar?",
            options = listOf("90°C", "100°C", "110°C", "120°C"),
            correctAnswerIndex = 1,
            explanation = "1 atmosfer basınç altında saf su 100°C'de kaynar ve 0°C'de donar.",
            points = 100
        ),
        Question(
            id = 6,
            stage = 1,
            category = "Zooloji",
            question = "Zorlu çöl şartlarına dayanıklılığı sebebiyle 'Çöl Gemisi' olarak bilinen hayvan hangisidir?",
            options = listOf("At", "Deve", "Ceylan", "Katır"),
            correctAnswerIndex = 1,
            explanation = "Hörgüçlerinde yağ depolayabilen develer, su ve besin olmadan haftalarca yol alabildikleri için çöl gemisi olarak anılır.",
            points = 100
        ),
        Question(
            id = 7,
            stage = 1,
            category = "Kültür",
            question = "Türk bayrağındaki hilal ve yıldızın rengi nedir?",
            options = listOf("Beyaz", "Sarı", "Gümüş", "Kırmızı"),
            correctAnswerIndex = 0,
            explanation = "Türk bayrağı, kırmızı zemin üzerine beyaz hilal ve beş köşeli yıldızdan oluşur.",
            points = 100
        ),
        Question(
            id = 8,
            stage = 1,
            category = "Genel Bilgi",
            question = "Dünya üzerinde bir takvim yılı kaç ana mevsimden meydana gelir?",
            options = listOf("2", "3", "4", "6"),
            correctAnswerIndex = 2,
            explanation = "Dünya'nın eksen eğikliği ve Güneş etrafındaki dönüşü sayesinde İlkbahar, Yaz, Sonbahar ve Kış olmak üzere 4 mevsim oluşur.",
            points = 100
        ),
        Question(
            id = 9,
            stage = 1,
            category = "Zooloji",
            question = "Kısa mesafelerde saatte 100 km'nin üzerine çıkabilen en hızlı kara hayvanı hangisidir?",
            options = listOf("Aslan", "Antilop", "Çita", "Tazı"),
            correctAnswerIndex = 2,
            explanation = "Çita, 0'dan 100 km/s hıza sadece 3 saniyede ulaşabilen yeryüzündeki en süratli kara canlısıdır.",
            points = 100
        ),
        Question(
            id = 10,
            stage = 1,
            category = "Edebiyat & Tarih",
            question = "Türkiye Cumhuriyeti'nin milli marşı olan İstiklal Marşı'nın şairi kimdir?",
            options = listOf("Namık Kemal", "Mehmet Akif Ersoy", "Tevfik Fikret", "Yahya Kemal Beyatlı"),
            correctAnswerIndex = 1,
            explanation = "İstiklal Marşı, Mehmet Akif Ersoy tarafından 1921 yılında yazılmış ve TBMM tarafından milli marş olarak kabul edilmiştir.",
            points = 100
        ),

        // ================= ETAP 2 (11 - 20) : Kolay =================
        Question(
            id = 11,
            stage = 2,
            category = "Coğrafya",
            question = "Türkiye'nin yüzey alanı bakımından en büyük gölü hangisidir?",
            options = listOf("Tuz Gölü", "Beyşehir Gölü", "Van Gölü", "İznik Gölü"),
            correctAnswerIndex = 2,
            explanation = "Van Gölü, 3.755 km² yüzölçümü ile Türkiye'nin en büyük gölüdür ve aynı zamanda dünyanın en büyük sodalı gölüdür.",
            points = 200
        ),
        Question(
            id = 12,
            stage = 2,
            category = "Kültür & Turizm",
            question = "Peri bacaları ve yer altı şehirleriyle ünlü tarihi Kapadokya bölgesi hangi ilimizin merkezindedir?",
            options = listOf("Nevşehir", "Konya", "Kayseri", "Aksaray"),
            correctAnswerIndex = 0,
            explanation = "Kapadokya'nın kalbi sayılan Göreme, Uçhisar ve peri bacaları ana yerleşimleri Nevşehir il sınırları içindedir.",
            points = 200
        ),
        Question(
            id = 13,
            stage = 2,
            category = "Coğrafya",
            question = "5.137 metre yüksekliğiyle Türkiye'nin en yüksek zirvesi hangi dağdadır?",
            options = listOf("Erciyes Dağı", "Süphan Dağı", "Kaçkar Dağı", "Ağrı Dağı"),
            correctAnswerIndex = 3,
            explanation = "Ağrı Dağı (Ararat), 5.137 metrelik doruğuyla Türkiye'nin ve Avrupa/Batı Asya coğrafyasının en heybetli dağlarındandır.",
            points = 200
        ),
        Question(
            id = 14,
            stage = 2,
            category = "Turizm & Doğa",
            question = "Beyaz traverten terasları ve antik Hierapolis kentiyle ünlü Pamukkale hangi ilimizdedir?",
            options = listOf("Aydın", "Muğla", "Denizli", "Antalya"),
            correctAnswerIndex = 2,
            explanation = "Pamukkale travertenleri, termal suların bıraktığı kalsiyum karbonat çökeltileriyle Denizli ilinde yer alır.",
            points = 200
        ),
        Question(
            id = 15,
            stage = 2,
            category = "Turizm & Doğa",
            question = "Türkiye'de turizme açılan ilk mağara olan meşhur Damlataş Mağarası hangi ilçededir?",
            options = listOf("Bodrum", "Alanya", "Fethiye", "Marmaris"),
            correctAnswerIndex = 1,
            explanation = "Damlataş Mağarası, 1948 yılında liman inşaatı sırasında dinamit patlatılırken Antalya'nın Alanya ilçesinde keşfedilmiştir.",
            points = 200
        ),
        Question(
            id = 16,
            stage = 2,
            category = "Coğrafya",
            question = "Karadeniz'i Marmara Denizi'ne bağlayan uluslararası stratejik su yolu hangisidir?",
            options = listOf("Çanakkale Boğazı", "İstanbul Boğazı", "Cebelitarık Boğazı", "Hürmüz Boğazı"),
            correctAnswerIndex = 1,
            explanation = "İstanbul Boğazı (Boğaziçi), Asya ile Avrupa kıtalarını birbirinden ayıran ve Karadeniz'i Marmara'ya bağlayan doğal su yoludur.",
            points = 200
        ),
        Question(
            id = 17,
            stage = 2,
            category = "Tarım & Coğrafya",
            question = "Türkiye'de yaş çay üretiminin yaklaşık %65'inden fazlasının karşılandığı ilimiz hangisidir?",
            options = listOf("Trabzon", "Artvin", "Rize", "Giresun"),
            correctAnswerIndex = 2,
            explanation = "Rize, bol yağışlı ve ılıman iklimi sayesinde Türkiye'nin çay başkenti konumundadır.",
            points = 200
        ),
        Question(
            id = 18,
            stage = 2,
            category = "Tarih & Arkeoloji",
            question = "Celsus Kütüphanesi ve Artemis Tapınağı kalıntılarına ev sahipliği yapan Efes Antik Kenti hangi ildedir?",
            options = listOf("İzmir", "Çanakkale", "Balıkesir", "Manisa"),
            correctAnswerIndex = 0,
            explanation = "Efes Antik Kenti, İzmir'in Selçuk ilçesinde yer alan UNESCO Dünya Mirası listesindeki antik metropoldür.",
            points = 200
        ),
        Question(
            id = 19,
            stage = 2,
            category = "Tarih & Arkeoloji",
            question = "Kommagene Krallığı'na ait devasa taş kral ve tanrı heykelleriyle ünlü Nemrut Dağı hangi ilimizdedir?",
            options = listOf("Malatya", "Adıyaman", "Şanlıurfa", "Diyarbakır"),
            correctAnswerIndex = 1,
            explanation = "Nemrut Dağı Tümülüsü ve devasa anıt heykeller, Adıyaman'ın Kâhta ilçesinde 2.150 metre rakımda yer almaktadır.",
            points = 200
        ),
        Question(
            id = 20,
            stage = 2,
            category = "Coğrafya",
            question = "Yüzölçümü bakımından Türkiye'nin en büyük ili hangisidir?",
            options = listOf("Sivas", "Ankara", "Konya", "Erzurum"),
            correctAnswerIndex = 2,
            explanation = "Konya, yaklaşık 40.838 km² yüzölçümü ile Türkiye'nin en geniş coğrafi alana sahip ilidir.",
            points = 200
        ),

        // ================= ETAP 3 (21 - 30) : Temel Bilim & Doğa =================
        Question(
            id = 21,
            stage = 3,
            category = "Biyoloji & Anatomi",
            question = "Ağırlık ve yüzey alanı bakımından insan vücudundaki en büyük organ hangisidir?",
            options = listOf("Karaciğer", "Beyin", "Akciğer", "Deri"),
            correctAnswerIndex = 3,
            explanation = "Deri (ten), ortalama yetişkinde yaklaşık 2 metrekare yüzey alanına ve vücut ağırlığının %16'sına ulaşan en büyük organdır.",
            points = 300
        ),
        Question(
            id = 22,
            stage = 3,
            category = "Botanik",
            question = "Klorofil içeren bitkilerin güneş enerjisini kimyasal enerjiye çevirme sürecine ne ad verilir?",
            options = listOf("Fermantasyon", "Fotosentez", "Solunum", "Terleme"),
            correctAnswerIndex = 1,
            explanation = "Fotosentez sayesinde bitkiler karbondioksit ve suyu güneş ışığı altında glikoz ve oksijene dönüştürürler.",
            points = 300
        ),
        Question(
            id = 23,
            stage = 3,
            category = "Kimya",
            question = "Periyodik tabloda 'Au' kimyasal simgesi hangi değerli elementi temsil eder?",
            options = listOf("Gümüş", "Altın", "Bakır", "Platin"),
            correctAnswerIndex = 1,
            explanation = "'Au' simgesi, Latince 'parlayan şafak' anlamına gelen 'Aurum' sözcüğünden türetilmiştir ve altını ifade eder.",
            points = 300
        ),
        Question(
            id = 24,
            stage = 3,
            category = "Atmosfer Bilimi",
            question = "Dünya atmosferinde hacimce en yüksek oranda bulunan gaz hangisidir?",
            options = listOf("Oksijen (%21)", "Azot (%78)", "Argon (%0.9)", "Karbondioksit (%0.04)"),
            correctAnswerIndex = 1,
            explanation = "Soluduğumuz atmosferin yaklaşık %78'ini Azot (Nitrojen), %21'ini Oksijen oluşturur.",
            points = 300
        ),
        Question(
            id = 25,
            stage = 3,
            category = "Fizik",
            question = "Işığın vakum (boşluk) ortamındaki hızı yaklaşık saniyede kaç kilometredir?",
            options = listOf("150.000 km/s", "300.000 km/s", "500.000 km/s", "1.000.000 km/s"),
            correctAnswerIndex = 1,
            explanation = "Işık hızı vakumda tam olarak 299.792.458 m/s, yani kabaca saniyede 300 bin kilometredir.",
            points = 300
        ),
        Question(
            id = 26,
            stage = 3,
            category = "Astronomi",
            question = "Yüzeyindeki demir oksit (pas) tozları nedeniyle gökyüzünde 'Kızıl Gezegen' olarak anılan gezegen hangisidir?",
            options = listOf("Venüs", "Merkür", "Mars", "Uranüs"),
            correctAnswerIndex = 2,
            explanation = "Mars yüzeyindeki yoğun demir oksit mineralleri gezegene kızıl-turuncu rengini verir.",
            points = 300
        ),
        Question(
            id = 27,
            stage = 3,
            category = "Anatomi",
            question = "Sağlıklı bir insan kalbi toplam kaç odacıktan (kulakçık ve karıncık) oluşur?",
            options = listOf("2", "3", "4", "6"),
            correctAnswerIndex = 2,
            explanation = "İnsan kalbi iki kulakçık (atrium) ve iki karıncık (ventrikül) olmak üzere 4 odacıklıdır.",
            points = 300
        ),
        Question(
            id = 28,
            stage = 3,
            category = "Bilim Tarihi",
            question = "Evrensel Kütleçekim Kanunu'nu formüle ederek klasik fiziğin temellerini atan İngiliz bilim insanı kimdir?",
            options = listOf("Galileo Galilei", "Isaac Newton", "Albert Einstein", "Nikola Tesla"),
            correctAnswerIndex = 1,
            explanation = "Sir Isaac Newton, 1687 tarihli 'Principia' eserinde kütleçekim ve hareket yasalarını açıklamıştır.",
            points = 300
        ),
        Question(
            id = 29,
            stage = 3,
            category = "Kimya",
            question = "Sulu çözeltilerin asitlik veya bazlık derecesini gösteren pH cetvelinde 7 değeri neyi belirtir?",
            options = listOf("Kuvvetli Asit", "Kuvvetli Baz", "Nötr Çözelti", "Doymuş Çözelti"),
            correctAnswerIndex = 2,
            explanation = "pH cetvelinde 0-7 arası asidik, tam 7 nötr (örneğin saf su), 7-14 arası ise baziktir.",
            points = 300
        ),
        Question(
            id = 30,
            stage = 3,
            category = "Biyoloji",
            question = "Karıncaların yol izi bırakmak ve tehlike bildirmek için salgıladıkları kimyasal mesaj maddesine ne denir?",
            options = listOf("Enzim", "Hormon", "Feromon", "Lipit"),
            correctAnswerIndex = 2,
            explanation = "Feromonlar, aynı tür bireyler arasında kimyasal koku yoluyla iletişim sağlayan özel salgılardır.",
            points = 300
        ),

        // ================= ETAP 4 (31 - 40) : Dünya Tarihi & Keşifler =================
        Question(
            id = 31,
            stage = 4,
            category = "Tarih & İcatlar",
            question = "Avrupa'da hareketli metal harflerle tipo baskı matbaasını geliştiren Alman mucit kimdir?",
            options = listOf("Johannes Gutenberg", "Alexander Graham Bell", "Thomas Edison", "James Watt"),
            correctAnswerIndex = 0,
            explanation = "Gutenberg, 1440'lı yıllarda Mainz'da geliştirdiği matbaasıyla bilginin kitlesel yayılmasında devrim yapmıştır.",
            points = 400
        ),
        Question(
            id = 32,
            stage = 4,
            category = "Coğrafi Keşifler",
            question = "1492 yılında İspanya Krallığı adına yola çıkarak Amerika kıtasına ulaşan Cenevizli denizci kimdir?",
            options = listOf("Vasco da Gama", "Kristof Kolomb", "Amerigo Vespucci", "Macellan"),
            correctAnswerIndex = 1,
            explanation = "Kristof Kolomb, 1492'de Bahamalar'a ulaşmış fakat Asya kıyılarında olduğunu düşünmüştür.",
            points = 400
        ),
        Question(
            id = 33,
            stage = 4,
            category = "Dünya Tarihi",
            question = "Bastille Hapishanesi'nin basılmasıyla başlayan Fransız İhtilali hangi yılda gerçekleşmiştir?",
            options = listOf("1776", "1789", "1815", "1848"),
            correctAnswerIndex = 1,
            explanation = "14 Temmuz 1789 tarihinde Bastille baskını ile başlayan Fransız İhtilali, milliyetçilik ve eşitlik ilkelerini dünyaya yaymıştır.",
            points = 400
        ),
        Question(
            id = 34,
            stage = 4,
            category = "Coğrafi Keşifler",
            question = "Dünyanın çevresini dolaşan ilk deniz seferini başlatan Portekizli kaşif kimdir?",
            options = listOf("Ferdinand Macellan", "Bartolomeu Dias", "James Cook", "Marco Polo"),
            correctAnswerIndex = 0,
            explanation = "Macellan'ın başlattığı sefer, kendisi Filipinler'de ölünce Juan Sebastián Elcano komutasında başarıyla tamamlanmıştır.",
            points = 400
        ),
        Question(
            id = 35,
            stage = 4,
            category = "Eski Çağ Tarihi",
            question = "Antik Mısır medeniyetinde taşlara ve papirüslere kazınan resim yazısına ne ad verilir?",
            options = listOf("Çivi Yazısı", "Hiyeroglif", "Runik Yazı", "Kalligrafi"),
            correctAnswerIndex = 1,
            explanation = "Hiyeroglif yazısı, 1799'da bulunan Rosetta Taşı sayesinde Fransız dilbilimci Champollion tarafından çözülmüştür.",
            points = 400
        ),
        Question(
            id = 36,
            stage = 4,
            category = "Osmanlı Tarihi",
            question = "Söğüt ve Domaniç merkezli kurulan Osmanlı Beyliği'nin kurucusu ve ilk hükümdarı kimdir?",
            options = listOf("Ertuğrul Gazi", "Osman Gazi", "Orhan Gazi", "I. Murad"),
            correctAnswerIndex = 1,
            explanation = "Osman Gazi (Osman Bey), 1299 yılında bağımsızlığını ilan ederek 600 yılı aşkın sürecek Osmanlı hanedanını kurmuştur.",
            points = 400
        ),
        Question(
            id = 37,
            stage = 4,
            category = "Türk Tarihi",
            question = "İtilaf donanmasının Boğaz'ı geçemeyip geri çekilmek zorunda kaldığı 18 Mart Çanakkale Deniz Zaferi hangi yıldır?",
            options = listOf("1914", "1915", "1916", "1918"),
            correctAnswerIndex = 1,
            explanation = "18 Mart 1915'te Çanakkale Boğazı'nda kazanılan büyük deniz zaferi, Kurtuluş Savaşı meşalesinin temelini atmıştır.",
            points = 400
        ),
        Question(
            id = 38,
            stage = 4,
            category = "Arkeoloji & Tarih",
            question = "Antik Dünyanın Yedi Harikası arasından günümüze kadar büyük ölçüde ayakta kalabilen tek yapı hangisidir?",
            options = listOf("Rodos Heykeli", "Babil'in Asma Bahçeleri", "İskenderiye Feneri", "Keops Piramidi"),
            correctAnswerIndex = 3,
            explanation = "Mısır Gize'deki Keops (Khufu) Piramidi, yaklaşık 4.500 yıldır ayakta duran tek antik harikadır.",
            points = 400
        ),
        Question(
            id = 39,
            stage = 4,
            category = "Uzay Tarihi",
            question = "20 Temmuz 1969'da Apollo 11 göreviyle Ay yüzeyine ayak basan ilk insan kimdir?",
            options = listOf("Yuri Gagarin", "Buzz Aldrin", "Neil Armstrong", "Michael Collins"),
            correctAnswerIndex = 2,
            explanation = "Neil Armstrong Ay'a adım atarken meşhur 'Bir insan için küçük, insanlık için dev bir adım' sözünü söylemiştir.",
            points = 400
        ),
        Question(
            id = 40,
            stage = 4,
            category = "Antik Roma",
            question = "Roma İmparatorluğu'nda gladyatör dövüşleri için inşa edilmiş dev Flavius Amfitiyatrosu'nun adı nedir?",
            options = listOf("Panteon", "Kolezyum", "Forum Romanum", "Circus Maximus"),
            correctAnswerIndex = 1,
            explanation = "Kolezyum (Colosseum), MS 80 yılında İmparator Titus döneminde açılan 50.000 seyirci kapasiteli dev arenadır.",
            points = 400
        ),

        // ================= ETAP 5 (41 - 50) : Edebiyat & Sanat =================
        Question(
            id = 41,
            stage = 5,
            category = "Sanat",
            question = "Paris Louvre Müzesi'nde sergilenen ünlü 'Mona Lisa' (La Gioconda) tablosunun ressamı kimdir?",
            options = listOf("Michelangelo", "Raphael", "Leonardo da Vinci", "Sandro Botticelli"),
            correctAnswerIndex = 2,
            explanation = "İtalyan Rönesans dehası Leonardo da Vinci, Mona Lisa tablosunu sfumato tekniği kullanarak 16. yüzyılın başında yapmıştır.",
            points = 500
        ),
        Question(
            id = 42,
            stage = 5,
            category = "Türk Edebiyatı",
            question = "Raif Efendi ile Maria Puder arasındaki unutulmaz aşkı anlatan 'Kürk Mantolu Madonna' romanının yazarı kimdir?",
            options = listOf("Ahmet Hamdi Tanpınar", "Sabahattin Ali", "Peyami Safa", "Orhan Pamuk"),
            correctAnswerIndex = 1,
            explanation = "Sabahattin Ali'nin 1943 yılında yayımlanan bu başyapıtı Türk edebiyatının en çok okunan klasik romanlarındandır.",
            points = 500
        ),
        Question(
            id = 43,
            stage = 5,
            category = "Resim Sanatı",
            question = "Girdaplı gökyüzü ve servi ağacıyla ünlü 'Yıldızlı Gece' (The Starry Night) tablosu hangi ressama aittir?",
            options = listOf("Claude Monet", "Vincent van Gogh", "Paul Cézanne", "Salvador Dali"),
            correctAnswerIndex = 1,
            explanation = "Hollandalı post-empresyonist ressam Vincent van Gogh, bu eseri 1889 yılında Saint-Rémy akıl hastanesinde yapmıştır.",
            points = 500
        ),
        Question(
            id = 44,
            stage = 5,
            category = "Türk Edebiyatı",
            question = "Batılı anlamda Türk edebiyatının ilk yerli tiyatro eseri kabul edilen 'Şair Evlenmesi' kime aittir?",
            options = listOf("İbrahim Şinasi", "Ziya Paşa", "Namık Kemal", "Recaizade Mahmut Ekrem"),
            correctAnswerIndex = 0,
            explanation = "Şinasi, 1859 yılında Tercüman-ı Ahval gazetesinde tefrika edilen 'Şair Evlenmesi'nde görücü usulü evliliği hicvetmiştir.",
            points = 500
        ),
        Question(
            id = 55 - 10, // 45
            stage = 5,
            category = "Dünya Edebiyatı",
            question = "Raskolnikov adlı gencin ahlak ve vicdan sorgulamasını anlatan 'Suç ve Ceza' romanının yazarı kimdir?",
            options = listOf("Lev Tolstoy", "Fyodor Dostoyevski", "Anton Çehov", "Maksim Gorki"),
            correctAnswerIndex = 1,
            explanation = "Dostoyevski'nin 1866 yılında yayımlanan 'Suç ve Ceza'sı psikolojik realizmin zirve eserlerinden biridir.",
            points = 500
        ),
        Question(
            id = 46,
            stage = 5,
            category = "Heykel Sanatı",
            question = "Dante'nin İlahi Komedya'sından esinlenerek 'Düşünen Adam' (Le Penseur) bronz heykelini yapan Fransız heykeltıraş kimdir?",
            options = listOf("Auguste Rodin", "Camille Claudel", "Alberto Giacometti", "Gian Lorenzo Bernini"),
            correctAnswerIndex = 0,
            explanation = "Auguste Rodin'in 1904 tarihli bu eseri felsefi derin düşünüşün dünyaca kabul gören evrensel simgesi haline gelmiştir.",
            points = 500
        ),
        Question(
            id = 47,
            stage = 5,
            category = "Türk Edebiyatı",
            question = "Çukurova'nın haksızlıklarına karşı başkaldıran efsanevi karakter 'İnce Memed' hangi usta yazarımızın eseridir?",
            options = listOf("Kemal Tahir", "Yaşar Kemal", "Orhan Kemal", "Fakir Baykurt"),
            correctAnswerIndex = 1,
            explanation = "Yaşar Kemal'in 1955'te ilk cildi yayımlanan 4 ciltlik İnce Memed serisi kırktan fazla dünya diline çevrilmiştir.",
            points = 500
        ),
        Question(
            id = 48,
            stage = 5,
            category = "Klasik Müzik",
            question = "İşitme duyusunu neredeyse tamamen kaybetmişken 'Ode to Joy' korolu 9. Senfoni'yi besteleyen müzik dehası kimdir?",
            options = listOf("Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "Ludwig van Beethoven", "Frédéric Chopin"),
            correctAnswerIndex = 2,
            explanation = "Alman besteci Beethoven, 1824 yılında prömiyeri yapılan 9. Senfoni'yi tam sağırlık döneminde bestelemiştir.",
            points = 500
        ),
        Question(
            id = 49,
            stage = 5,
            category = "Sanat",
            question = "İspanya İç Savaşı'nda Guernica kasabasının bombalanmasını kübist tarzda tuvale aktaran ressam kimdir?",
            options = listOf("Salvador Dalí", "Joan Miró", "Pablo Picasso", "Francisco Goya"),
            correctAnswerIndex = 2,
            explanation = "Pablo Picasso, 1937'de yaptığı devasa 'Guernica' tablosuyla savaş karşıtı sanatın en etkili anıtını yaratmıştır.",
            points = 500
        ),
        Question(
            id = 50,
            stage = 5,
            category = "Türk Edebiyatı",
            question = "İdealist genç öğretmen Feride'nin Anadolu'daki yaşam mücadelesini anlatan 'Çalıkuşu' romanının yazarı kimdir?",
            options = listOf("Halide Edib Adıvar", "Yakup Kadri Karaosmanoğlu", "Reşat Nuri Güntekin", "Refik Halit Karay"),
            correctAnswerIndex = 2,
            explanation = "Reşat Nuri Güntekin'in 1922'de yayımlanan 'Çalıkuşu' romanı Türk edebiyatının en sevilen eserlerindendir.",
            points = 500
        ),

        // ================= ETAP 6 (51 - 60) : Spor & Olimpiyatlar =================
        Question(
            id = 51,
            stage = 6,
            category = "Olimpiyatlar",
            question = "Modern Olimpiyat Oyunları tarihte ilk kez hangi yıl ve hangi şehirde düzenlenmiştir?",
            options = listOf("1896 - Atina", "1900 - Paris", "1904 - St. Louis", "1908 - Londra"),
            correctAnswerIndex = 0,
            explanation = "Pierre de Coubertin'in öncülüğünde kurulan Uluslararası Olimpiyat Komitesi ilk modern oyunları 1896'da Atina'da yapmıştır.",
            points = 600
        ),
        Question(
            id = 52,
            stage = 6,
            category = "Türk Spor Tarihi",
            question = "Halterde kendi vücut ağırlığının üç katından fazlasını kaldırarak tarihe geçen 'Cep Herkülü' kimdir?",
            options = listOf("Halil Mutlu", "Naim Süleymanoğlu", "Taner Sağır", "Sedat Artuç"),
            correctAnswerIndex = 1,
            explanation = "Naim Süleymanoğlu, üst üste 3 olimpiyat altını kazanmış ve 46 dünya rekoruna imza atmıştır.",
            points = 600
        ),
        Question(
            id = 53,
            stage = 6,
            category = "Futbol",
            question = "FIFA Dünya Kupası'nı 5 kez kazanarak (1958, 1962, 1970, 1994, 2002) en çok şampiyon olan ülke hangisidir?",
            options = listOf("Almanya", "İtalya", "Arjantin", "Brezilya"),
            correctAnswerIndex = 3,
            explanation = "Brezilya Milli Takımı, 5 şampiyonlukla Dünya Kupası'nı en fazla müzesine götüren futbol ülkesidir.",
            points = 600
        ),
        Question(
            id = 54,
            stage = 6,
            category = "Basketbol",
            question = "Standart bir profesyonel basketbol potasının çemberinin yerden yüksekliği kaç metredir?",
            options = listOf("2,95 m", "3,00 m", "3,05 m", "3,15 m"),
            correctAnswerIndex = 2,
            explanation = "Basketbol potası çemberi uluslararası FIBA ve NBA standartlarına göre yerden tam 10 feet, yani 3,05 metredir.",
            points = 600
        ),
        Question(
            id = 55,
            stage = 6,
            category = "Tenis",
            question = "Avustralya Açık, Fransa Açık (Roland Garros), Wimbledon ve Amerika Açık turnuvalarına verilen genel ad nedir?",
            options = listOf("Grand Slam", "Masters 1000", "ATP Finals", "Davis Cup"),
            correctAnswerIndex = 0,
            explanation = "Bu 4 en prestijli tenis turnuvasının her birine ve dördünün bütününe Grand Slam adı verilir.",
            points = 600
        ),
        Question(
            id = 56,
            stage = 6,
            category = "Atletizm",
            question = "100 metre erkekler dünya rekorunu 9.58 saniye ile elinde bulunduran Jamaikalı efsane atlet kimdir?",
            options = listOf("Tyson Gay", "Usain Bolt", "Yohan Blake", "Carl Lewis"),
            correctAnswerIndex = 1,
            explanation = "Usain Bolt, 2009 Berlin Dünya Atletizm Şampiyonası'nda 9.58 saniyeyle kırılması imkânsıza yakın bir rekor kırmıştır.",
            points = 600
        ),
        Question(
            id = 57,
            stage = 6,
            category = "Yüzme & Olimpiyat",
            question = "Toplam 23'ü altın olmak üzere 28 madalya ile Olimpiyat tarihinin en çok madalya kazanan sporcusu kimdir?",
            options = listOf("Mark Spitz", "Michael Phelps", "Ian Thorpe", "Caeleb Dressel"),
            correctAnswerIndex = 1,
            explanation = "Amerikalı yüzücü Michael Phelps, 2004-2016 yılları arasında 23 altın, 3 gümüş ve 2 bronz madalya kazanmıştır.",
            points = 600
        ),
        Question(
            id = 58,
            stage = 6,
            category = "Türk Futbolu",
            question = "17 Mayıs 2000'de Kopenhag'da Arsenal'i penaltılarla yenerek UEFA Kupası'nı namağlup kazanan ilk Türk kulübü hangisidir?",
            options = listOf("Fenerbahçe", "Beşiktaş", "Galatasaray", "Trabzonspor"),
            correctAnswerIndex = 2,
            explanation = "Galatasaray, Fatih Terim yönetiminde 2000 yılında UEFA Kupası'nı kazanarak Türk futbol tarihinin en büyük kulüp başarısını elde etmiştir.",
            points = 600
        ),
        Question(
            id = 59,
            stage = 6,
            category = "Atletizm",
            question = "Resmi bir maraton koşusunun standart mesafesi kaç kilometre kaç metredir?",
            options = listOf("40 km", "42 km 195 m", "45 km", "42 km 500 m"),
            correctAnswerIndex = 1,
            explanation = "1908 Londra Olimpiyatları'nda kraliyet sarayı önünden stadyum locasına kadar ölçülen 42.195 metre resmi maraton standardı olmuştur.",
            points = 600
        ),
        Question(
            id = 60,
            stage = 6,
            category = "Türk Sporu",
            question = "2020 Tokyo Olimpiyatları'nda okçuluk branşında Türkiye'ye tarihindeki ilk altın madalyayı kazandıran sporcumuz kimdir?",
            options = listOf("Mete Gazoz", "Rıza Kayaalp", "Taha Akgül", "Ferhat Arıcan"),
            correctAnswerIndex = 0,
            explanation = "Mete Gazoz, klasik yay bireysel finalinde İtalyan rakibini mağlup ederek Türk okçuluk tarihinin ilk olimpiyat altınını almıştır.",
            points = 600
        ),

        // ================= ETAP 7 (61 - 70) : Sinema & Teknoloji =================
        Question(
            id = 61,
            stage = 7,
            category = "Sinema Tarihi",
            question = "Sinema tarihinde Akademi Ödülleri'nde (Oscar) 11 heykelcik birden kazanan ilk film hangisidir?",
            options = listOf("Rüzgâr Gibi Geçti", "Ben-Hur (1959)", "Titanic (1997)", "Yüzüklerin Efendisi: Kralın Dönüşü"),
            correctAnswerIndex = 1,
            explanation = "1959 yapımı Ben-Hur filmi 11 Oscar kazanarak bu rekoru kıran ilk yapımdır; sonraları Titanic ve Kralın Dönüşü ona ortak olmuştur.",
            points = 700
        ),
        Question(
            id = 62,
            stage = 7,
            category = "Bilgisayar Bilimi",
            question = "İkinci Dünya Savaşı'nda Nazi 'Enigma' kodunu çözen ve yapay zekanın babası sayılan İngiliz dahi matematikçi kimdir?",
            options = listOf("Alan Turing", "John von Neumann", "Charles Babbage", "Claude Shannon"),
            correctAnswerIndex = 0,
            explanation = "Alan Turing, Bletchley Park'ta Bombe makinesini tasarlamış ve ünlü 'Turing Testi' makalesiyle yapay zekanın temelini atmıştır.",
            points = 700
        ),
        Question(
            id = 63,
            stage = 7,
            category = "İnternet Tarihi",
            question = "1989 yılında CERN'de çalışırken World Wide Web'i (HTML, HTTP ve URL protokolleri) icat eden bilim insanı kimdir?",
            options = listOf("Steve Jobs", "Tim Berners-Lee", "Bill Gates", "Linus Torvalds"),
            correctAnswerIndex = 1,
            explanation = "Sir Tim Berners-Lee, bilgi paylaşımını kolaylaştırmak için web'i geliştirmiş ve patent almayarak insanlığa ücretsiz sunmuştur.",
            points = 700
        ),
        Question(
            id = 64,
            stage = 7,
            category = "Teknoloji Öncüleri",
            question = "Babbage'ın Mekanik Analitik Motoru için ilk algoritmayı kaleme alan ve tarihteki ilk bilgisayar programcısı sayılan kişi kimdir?",
            options = listOf("Ada Lovelace", "Grace Hopper", "Marie Curie", "Hedy Lamarr"),
            correctAnswerIndex = 0,
            explanation = "Lord Byron'ın kızı olan Ada Lovelace, 1843 yılında Bernoulli sayılarını hesaplayan algoritmasıyla ilk programcı kabul edilir.",
            points = 700
        ),
        Question(
            id = 65,
            stage = 7,
            category = "Sinema",
            question = "J.R.R. Tolkien'in fantastik başyapıtı 'Yüzüklerin Efendisi' üçlemesini Yeni Zelanda'da beyaz perdeye aktaran yönetmen kimdir?",
            options = listOf("Steven Spielberg", "James Cameron", "Peter Jackson", "Christopher Nolan"),
            correctAnswerIndex = 2,
            explanation = "Yeni Zelandalı yönetmen Peter Jackson, sinema tarihinin en başarılı ve ödüllü fantastik film üçlemesine imza atmıştır.",
            points = 700
        ),
        Question(
            id = 66,
            stage = 7,
            category = "Uzay & Teknoloji",
            question = "Hubble'ın halefi olan dev James Webb Uzay Teleskobu (JWST), Dünya'dan 1.5 milyon km ötede hangi denge noktasına yerleştirilmiştir?",
            options = listOf("Lagrange L1", "Lagrange L2", "Lagrange L3", "Lagrange L4"),
            correctAnswerIndex = 1,
            explanation = "JWST, Dünya ve Güneş'in kütleçekim kuvvetlerinin dengelendiği L2 noktasında aşırı soğuk kalkanıyla evrenin ilk ışıklarını gözlemler.",
            points = 700
        ),
        Question(
            id = 67,
            stage = 7,
            category = "Yazılım Dünyası",
            question = "1991 yılında Helsinki Üniversitesi'nde öğrenciyken açık kaynaklı Linux işletim sistemi çekirdeğini başlatan yazılımcı kimdir?",
            options = listOf("Richard Stallman", "Linus Torvalds", "Ken Thompson", "Dennis Ritchie"),
            correctAnswerIndex = 1,
            explanation = "Linus Torvalds, Linux çekirdeğini geliştirerek modern internet sunucularının, süper bilgisayarların ve Android'in temelini atmıştır.",
            points = 700
        ),
        Question(
            id = 68,
            stage = 7,
            category = "Animasyon Sineması",
            question = "1995 yılında Pixar tarafından yapılan ve sinema tarihinin tamamen bilgisayar animasyonuyla üretilen ilk uzun metraj filmi hangisidir?",
            options = listOf("Toy Story (Oyuncak Hikayesi)", "Shrek", "A Bug's Life (Bir Böceğin Yaşamı)", "Monsters, Inc."),
            correctAnswerIndex = 0,
            explanation = "Yönetmenliğini John Lasseter'ın yaptığı Oyuncak Hikayesi, 3D CGI animasyon sinemasında yeni bir çağ başlatmıştır.",
            points = 700
        ),
        Question(
            id = 69,
            stage = 7,
            category = "Moleküler Biyoloji",
            question = "1953 yılında Rosalind Franklin'in X-ışını kırınımı fotoğrafından faydalanarak DNA'nın ikili sarmal modelini çözen ikili kimdir?",
            options = listOf("Watson ve Crick", "Fleming ve Pasteur", "Mendel ve Darwin", "Bohr ve Planck"),
            correctAnswerIndex = 0,
            explanation = "James Watson ve Francis Crick, 'Fotoğraf 51' verilerini kullanarak DNA'nın çift sarmal (double helix) yapısını keşfetmiştir.",
            points = 700
        ),
        Question(
            id = 70,
            stage = 7,
            category = "Popüler Sinema",
            question = "1999 yapımı 'The Matrix' filminde Keanu Reeves'in canlandırdığı seçilmiş kişi Neo'nun simülasyondaki sivil adı nedir?",
            options = listOf("John Wick", "Thomas Anderson", "Agent Smith", "Cypher Reagan"),
            correctAnswerIndex = 1,
            explanation = "Neo, simüle edilmiş Matrix dünyasında saygın bir yazılım şirketinde Thomas A. Anderson adıyla çalışmaktadır.",
            points = 700
        ),

        // ================= ETAP 8 (71 - 80) : İleri Bilim & Felsefe =================
        Question(
            id = 71,
            stage = 8,
            category = "Felsefe",
            question = "'Devlet' diyaloğundaki meşhur Mağara Alegorisi ile duyular dünyası ile idealar dünyasını ayıran antik filozof kimdir?",
            options = listOf("Sokrates", "Platon (Eflatun)", "Aristoteles", "Epiküros"),
            correctAnswerIndex = 1,
            explanation = "Platon, mağarada zincirli insanların duvardaki gölgeleri gerçek sandığı metaforuyla görünüş ile idealar arasındaki farkı anlatmıştır.",
            points = 800
        ),
        Question(
            id = 72,
            stage = 8,
            category = "Kuantum Fiziği",
            question = "Bir atomaltı parçacığın konumu ve momentumunun aynı anda mutlak kesinlikle ölçülemeyeceğini belirten kuantum ilkesi nedir?",
            options = listOf("Pauli Dışlama İlkesi", "Heisenberg Belirsizlik İlkesi", "Schrödinger Dalga Denklemi", "Fermi Paradoksu"),
            correctAnswerIndex = 1,
            explanation = "Werner Heisenberg, 1927'de ölçüm işleminin kendisinin kuantum durumunu etkilediğini matematiksel olarak kanıtlamıştır.",
            points = 800
        ),
        Question(
            id = 73,
            stage = 8,
            category = "Hücre Biyolojisi",
            question = "Tüm canlı hücrelerde mRNA şifrelerini okuyarak amino asitlerden protein sentezleyen zarsız organel hangisidir?",
            options = listOf("Mitokondri", "Ribozom", "Golgi Aygıtı", "Lizozom"),
            correctAnswerIndex = 1,
            explanation = "Ribozomlar, rRNA ve proteinlerden oluşan hücresel protein fabrikalarıdır.",
            points = 800
        ),
        Question(
            id = 74,
            stage = 8,
            category = "Felsefe",
            question = "Metodik şüpheciliğin sonucunda 'Düşünüyorum, öyleyse varım' (Cogito, ergo sum) çıkarımını yapan Fransız düşünür kimdir?",
            options = listOf("René Descartes", "Voltaire", "Jean-Jacques Rousseau", "Blaise Pascal"),
            correctAnswerIndex = 0,
            explanation = "René Descartes, her şeyden şüphe edebileceğini fakat şüphe eden aklın varlığından şüphe edemeyeceğini savunmuştur.",
            points = 800
        ),
        Question(
            id = 75,
            stage = 8,
            category = "İzafiyet Teorisi",
            question = "Einstein'ın Özel Görelilik kuramına göre, yüksek hızlarda hareket eden veya yoğun kütleçekimindeki bir gözlemci için zamanın daha yavaş akması olayına ne denir?",
            options = listOf("Zaman Genişlemesi (Time Dilation)", "Kırmızıya Kayma", "Olay Ufku", "Uzunluk Kısalması"),
            correctAnswerIndex = 0,
            explanation = "Özel ve Genel Görelilik uyarınca zaman mutlak değildir; hız arttıkça veya kütleçekim kuvvetlendikçe saatler daha yavaş işler.",
            points = 800
        ),
        Question(
            id = 76,
            stage = 8,
            category = "Endokrinoloji",
            question = "Kandaki kalsiyum seviyesi düştüğünde kemiklerden kalsiyum salınımını uyararak seviyeyi yükselten hormon hangisidir?",
            options = listOf("Kalsitonin", "Parathormon (PTH)", "İnsülin", "Tiroksin"),
            correctAnswerIndex = 1,
            explanation = "Paratiroid bezinden salgılanan Parathormon kalsiyumu artırırken, tiroit bezinden salgılanan Kalsitonin kalsiyumu düşürür.",
            points = 800
        ),
        Question(
            id = 77,
            stage = 8,
            category = "Termodinamik",
            question = "İzole bir sistemde toplam entropinin (düzensizliğin) asla azalmayacağını, zaman okunun yönünü belirten termodinamik yasası hangisidir?",
            options = listOf("Sıfırıncı Yasa", "Birinci Yasa", "İkinci Yasa", "Üçüncü Yasa"),
            correctAnswerIndex = 2,
            explanation = "Termodinamiğin İkinci Yasası, evrende kendi haline bırakılan süreçlerin maksimum entropiye doğru ilerlediğini belirtir.",
            points = 800
        ),
        Question(
            id = 78,
            stage = 8,
            category = "Nobel Tarihi",
            question = "İnsülinin yapısını çözerek ve DNA dizileme yöntemini bularak iki kez Nobel Kimya Ödülü alan tek bilim insanı kimdir?",
            options = listOf("Linus Pauling", "Frederick Sanger", "Kary Mullis", "John Bardeen"),
            correctAnswerIndex = 1,
            explanation = "İngiliz biyokimyager Frederick Sanger 1958 ve 1980 yıllarında kimya dalında iki ayrı Nobel Ödülü kazanmıştır.",
            points = 800
        ),
        Question(
            id = 79,
            stage = 8,
            category = "Felsefe",
            question = "'Böyle Buyurdu Zerdüşt', 'Güç İstenci' ve 'Üstinsan' (Übermensch) doktrinleriyle tanınan Alman filozof kimdir?",
            options = listOf("Immanuel Kant", "G.W.F. Hegel", "Arthur Schopenhauer", "Friedrich Nietzsche"),
            correctAnswerIndex = 3,
            explanation = "Nietzsche, 19. yüzyılın geleneksel ahlak ve metafizik kabullerini sarsan en etkili varoluşçu düşünürlerdendir.",
            points = 800
        ),
        Question(
            id = 80,
            stage = 8,
            category = "Kozmoloji",
            question = "Planck uydusu ve mikrodalga arka plan ışınımı verilerine göre evrenimiz yaklaşık kaç milyar yıl önce oluşmuştur?",
            options = listOf("4,5 Milyar Yıl", "10,2 Milyar Yıl", "13,8 Milyar Yıl", "20 Milyar Yıl"),
            correctAnswerIndex = 2,
            explanation = "Modern astrofizik hesaplamalarına göre Büyük Patlama yaklaşık 13,787 ± 0,020 milyar yıl önce meydana gelmiştir.",
            points = 800
        ),

        // ================= ETAP 9 (81 - 90) : Usta Seviye (Uzman) =================
        Question(
            id = 81,
            stage = 9,
            category = "Siyasi Coğrafya",
            question = "Dünyada denize kıyısı olmayan komşu ülkelerle tamamen çevrili ('çift karasal / doubly landlocked') iki ülkeden biri Özbekistan, diğeri hangisidir?",
            options = listOf("İsviçre", "Lihtenştayn", "Andorra", "Lüksemburg"),
            correctAnswerIndex = 1,
            explanation = "Dünyada yalnızca iki ülke (Lihtenştayn ve Özbekistan) denize ulaşmak için en az iki sınır geçmek zorundadır.",
            points = 900
        ),
        Question(
            id = 82,
            stage = 9,
            category = "Hukuk & Tarih",
            question = "1215 yılında İngiltere Kralı Yurtsuz John tarafından imzalanan ve hukukun üstünlüğünün temeli sayılan tarihi ferman nedir?",
            options = listOf("Habeas Corpus", "Magna Carta Libertatum", "Bill of Rights", "Westphalia Barışı"),
            correctAnswerIndex = 1,
            explanation = "Magna Carta (Büyük Sözleşme), tarihte kralın yetkilerini yasayla sınırlayan ilk anayasal belge niteliğindedir.",
            points = 900
        ),
        Question(
            id = 83,
            stage = 9,
            category = "Fiziki Coğrafya",
            question = "1.642 metre ile dünyanın en derin gölü olan ve dünya donmamış tatlı suyunun %20'sini barındıran Baykal Gölü hangi ülkededir?",
            options = listOf("Kanada", "Rusya (Sibirya)", "Moğolistan", "Kazakistan"),
            correctAnswerIndex = 1,
            explanation = "Sibirya'daki Baykal Gölü, hem dünyanın en derin hem de yaklaşık 25 milyon yıllık yaşıyla en eski tatlı su gölüdür.",
            points = 900
        ),
        Question(
            id = 84,
            stage = 9,
            category = "Osmanlı Tarihi",
            question = "1831 yılında Osmanlı Devleti'nin ilk resmi Türkçe gazetesi olan 'Takvim-i Vekayi' hangi padişah döneminde çıkarılmıştır?",
            options = listOf("III. Selim", "II. Mahmud", "Abdülmecid", "II. Abdülhamid"),
            correctAnswerIndex = 1,
            explanation = "Yenilikçi reformlarıyla bilinen II. Mahmud döneminde devlet işlerini halka duyurmak amacıyla Takvim-i Vekayi kurulmuştur.",
            points = 900
        ),
        Question(
            id = 85,
            stage = 9,
            category = "Coğrafya Harikaları",
            question = "979 metre toplam yükseklik ve 807 metre kesintisiz düşüşle dünyanın en yüksek şelalesi olan Angel (Salto Ángel) hangi ülkededir?",
            options = listOf("Brezilya", "Kolombiya", "Venezuela", "Peru"),
            correctAnswerIndex = 2,
            explanation = "Angel Şelalesi, Venezuela'nın Canaima Ulusal Parkı'ndaki Auyán-tepui dağından dökülmektedir.",
            points = 900
        ),
        Question(
            id = 86,
            stage = 9,
            category = "Diplomasi Tarihi",
            question = "MÖ 1259 yılında Hitit Kralı III. Hattuşili ile Mısır Firavunu II. Ramses arasında imzalanan tarihin ilk yazılı barış antlaşması hangisidir?",
            options = listOf("Kadeş Antlaşması", "Kallias Barışı", "Nisibis Antlaşması", "Antalkidas Barışı"),
            correctAnswerIndex = 0,
            explanation = "Kadeş Antlaşması'nın kil tablet kopyası günümüzde İstanbul Arkeoloji Müzeleri'nde sergilenmekte olup BM binasında da bir kopyası asılıdır.",
            points = 900
        ),
        Question(
            id = 87,
            stage = 9,
            category = "Okyanus Bilimi",
            question = "Büyük Okyanus'taki Mariana Çukuru'nun en derin noktası olan Challenger Çukuru'nun derinliği yaklaşık kaç metredir?",
            options = listOf("7.500 m", "9.200 m", "11.000 m", "14.500 m"),
            correctAnswerIndex = 2,
            explanation = "Challenger Deep, yaklaşık 10.994 metre (yaklaşık 11 km) derinliğiyle yerkabuğundaki en derin okyanus çukurudur.",
            points = 900
        ),
        Question(
            id = 88,
            stage = 9,
            category = "Selçuklu Tarihi",
            question = "1176 yılında II. Kılıç Arslan komutasındaki Türkiye Selçukluları ile Bizans İmparatorluğu arasında yapılan ve Anadolu'nun kesin Türk yurdu olduğunu tescilleyen savaş hangisidir?",
            options = listOf("Malazgirt Savaşı", "Pasinler Savaşı", "Miryokefalon Savaşı", "Yassıçemen Savaşı"),
            correctAnswerIndex = 2,
            explanation = "Denizli yakınlarındaki Miryokefalon Zaferi, Bizans'ın Türkleri Anadolu'dan atma ümidini tamamen sona erdirmiştir.",
            points = 900
        ),
        Question(
            id = 89,
            stage = 9,
            category = "Coğrafya",
            question = "Afrika kıtasının açıklarında yer alan ve florasının %90'ı endemik türlerden oluşan dünyanın dördüncü büyük adası hangisidir?",
            options = listOf("Zanzibar", "Madagaskar", "Yeşil Burun (Cabo Verde)", "Mauritius"),
            correctAnswerIndex = 1,
            explanation = "Madagaskar, yaklaşık 587.000 km² alanı ve lemurlar gibi eşsiz canlılarıyla biyolojik bir izolasyon cennetidir.",
            points = 900
        ),
        Question(
            id = 90,
            stage = 9,
            category = "Jeoloji Tarihi",
            question = "1883 yılında Endonezya'da patlayan, sesi 4.800 km öteden duyulan ve kaydedilmiş en şiddetli patlamalardan biri olan yanardağ hangisidir?",
            options = listOf("Vezüv", "Krakatoa", "Tambora", "Pinatubo"),
            correctAnswerIndex = 1,
            explanation = "Krakatoa Yanardağı patlaması Hiroşima bombasından binlerce kat güçlü şok dalgaları üretmiş ve küresel iklimi soğutmuştur.",
            points = 900
        ),

        // ================= ETAP 10 (91 - 100) : Şampiyonlar Etabı (Deha) =================
        Question(
            id = 91,
            stage = 10,
            category = "Bilim Tarihi",
            question = "1903'te Fizik, 1911'de Kimya olmak üzere iki farklı bilim alanında Nobel kazanan tarihteki tek kişi kimdir?",
            options = listOf("Marie Curie", "Albert Einstein", "Niels Bohr", "Max Planck"),
            correctAnswerIndex = 0,
            explanation = "Marie Skłodowska-Curie, radyoaktivite çalışmalarıyla fizikte, polonyum ve radyumun keşfiyle kimyada iki ayrı Nobel kazanmıştır.",
            points = 1000
        ),
        Question(
            id = 92,
            stage = 10,
            category = "Matematik",
            question = "Clay Matematik Enstitüsü'nün 7 Milenyum Probleminden biri olan ve 2002 yılında dahi Rus matematikçi Grigori Perelman tarafından kanıtlanan tek problem hangisidir?",
            options = listOf("Riemann Hipotezi", "Poincaré Sanısı", "Navier-Stokes Varlığı", "P vs NP Problemi"),
            correctAnswerIndex = 1,
            explanation = "Grigori Perelman, topolojinin 100 yıllık en zor bilmecesi Poincaré Sanısı'nı çözmüş, 1 milyon dolarlık ödülü ve Fields Madalyası'nı reddetmiştir.",
            points = 1000
        ),
        Question(
            id = 93,
            stage = 10,
            category = "Genetik & Biyoloji",
            question = "İnsan Genom Projesi sonuçlarına göre, insan hücresindeki haploid kromozom takımında yaklaşık kaç milyar baz çifti (A, T, G, C) bulunur?",
            options = listOf("1 Milyar", "3 Milyar", "10 Milyar", "25 Milyar"),
            correctAnswerIndex = 1,
            explanation = "İnsan genomu yaklaşık 3,2 milyar baz çifti ve yaklaşık 20.000 protein kodlayan genden oluşmaktadır.",
            points = 1000
        ),
        Question(
            id = 94,
            stage = 10,
            category = "Coğrafya & İklim",
            question = "Şili'nin kuzeyinde yer alan ve bazı meteoroloji istasyonlarında kayıtlara geçmiş hiçbir yağmur damlası görülmemiş dünyanın en kurak çölü hangisidir?",
            options = listOf("Sahra Çölü", "Gobi Çölü", "Atacama Çölü", "Kalahari Çölü"),
            correctAnswerIndex = 2,
            explanation = "Atacama Çölü, aşırı kuruluğu ve berrak gökyüzü sebebiyle ALMA ve VLT gibi dünyanın en güçlü uzay gözlemevlerine ev sahipliği yapar.",
            points = 1000
        ),
        Question(
            id = 95,
            stage = 10,
            category = "Ekonomi & Mantık",
            question = "Matematikçi John Nash'e 1994 Nobel Ekonomi Ödülü'nü kazandıran ve stratejik karar alma süreçlerini modelleyen matematiksel teorinin adı nedir?",
            options = listOf("Oyun Teorisi (Nash Dengesi)", "Kaos Teorisi", "Marjinal Fayda Teorisi", "Genel Denge Modeli"),
            correctAnswerIndex = 0,
            explanation = "Nash Dengesi (Nash Equilibrium), hiçbir oyuncunun tek taraflı strateji değiştirerek kazanç sağlayamayacağı kararlı durumu ifade eder.",
            points = 1000
        ),
        Question(
            id = 96,
            stage = 10,
            category = "Astrofizik",
            question = "1965 yılında Bell Laboratuvarları'nda anten gürültüsünü araştırırken Kozmik Mikrodalga Arka Plan Işıması'nı (CMBR) keşfedip Big Bang'i kanıtlayan araştırmacılar kimlerdir?",
            options = listOf("Arno Penzias ve Robert Wilson", "Edwin Hubble ve Milton Humason", "Stephen Hawking ve Roger Penrose", "Carl Sagan ve Frank Drake"),
            correctAnswerIndex = 0,
            explanation = "Penzias ve Wilson, evrenin doğumundan kalan 2.7 Kelvin sıcaklığındaki yankıyı keşfederek 1978 Nobel Fizik Ödülü'nü almışlardır.",
            points = 1000
        ),
        Question(
            id = 97,
            stage = 10,
            category = "Eskiçağ Edebiyatı",
            question = "Uruk Kralı'nın ölümsüzlük arayışı ve Nuh Tufanı'nın en eski anlatımını içeren Mezopotamya kökenli tarihin bilinen en eski destanı hangisidir?",
            options = listOf("İlyada Destanı", "Mahabharata", "Gılgamış Destanı", "Enuma Eliş"),
            correctAnswerIndex = 2,
            explanation = "Gılgamış Destanı, Asurbanipal Kütüphanesi'ndeki kil tabletlerde çivi yazısıyla bulunmuş insanlık tarihinin ilk büyük edebi başyapıtıdır.",
            points = 1000
        ),
        Question(
            id = 98,
            stage = 10,
            category = "Gezegen Bilimi",
            question = "Güneş Sistemi'ndeki gezegenlerin neredeyse tamamı saatin tersi yönünde dönerken, kendi ekseni etrafında saat yönünde (tersine / retrograd) dönen gezegen hangisidir?",
            options = listOf("Mars", "Venüs", "Jüpiter", "Neptün"),
            correctAnswerIndex = 1,
            explanation = "Venüs'ün retrograd dönüşü nedeniyle üzerinde Güneş batıdan doğar ve doğudan batar. Bir Venüs günü bir Venüs yılından daha uzundur!",
            points = 1000
        ),
        Question(
            id = 99,
            stage = 10,
            category = "Sayılar Teorisi",
            question = "Pierre de Fermat'nın 1637'de yazdığı ve 'xⁿ + yⁿ = zⁿ denkleminin n>2 için tam sayı çözümü yoktur' diyen teoremini 1994'te kanıtlayan İngiliz matematikçi kimdir?",
            options = listOf("Andrew Wiles", "Terence Tao", "Kurt Gödel", "Alexander Grothendieck"),
            correctAnswerIndex = 0,
            explanation = "Sir Andrew Wiles, Taniyama-Shimura-Weil konjektürünün modüler eliptik eğriler kısmını ispatlayarak 350 yıllık Fermat'nın Son Teoremi'ni çözmüştür.",
            points = 1000
        ),
        Question(
            id = 100,
            stage = 10,
            category = "Parçacık Fiziği",
            question = "CERN'deki Büyük Hadron Çarpıştırıcısı'nda 2012 yılında varlığı kanıtlanan ve temel parçacıklara kütle kazandırdığı için popüler basında 'Tanrı Parçacığı' olarak anılan skaler bozon hangisidir?",
            options = listOf("W Bozonu", "Z Bozonu", "Higgs Bozonu", "Gluon"),
            correctAnswerIndex = 2,
            explanation = "Peter Higgs ve François Englert tarafından 1964'te öngörülen Higgs Bozonu'nun CERN'de keşfedilmesi parçacık fiziği Standart Modeli'nin tacıdır.",
            points = 1000
        )
    )

    fun getQuestionsForStage(stageNumber: Int): List<Question> {
        return questions.filter { it.stage == stageNumber }
    }
}
