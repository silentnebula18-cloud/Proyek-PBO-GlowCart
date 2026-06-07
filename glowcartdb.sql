CREATE DATABASE glowcartdb;
USE glowcartdb;
 
CREATE TABLE users(
	userID CHAR(5) PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    noHP VARCHAR(15) NOT NULL
);
 
CREATE TABLE admins(
	userID CHAR(5),
    adminID CHAR(6) UNIQUE,
    status VARCHAR(9) NOT NULL,
    
    CONSTRAINT fk_users_admins FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE,
    CONSTRAINT pk_admins PRIMARY KEY (userID, adminID)
);
 
CREATE TABLE members(
	userID CHAR(5),
	memberID CHAR(6) UNIQUE,
    point INT NOT NULL,
    tglJoin DATE NOT NULL,
    
    CONSTRAINT fk_users_members FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE,
    CONSTRAINT pk_members PRIMARY KEY (userID, memberID)
);
  
CREATE TABLE reviews(
	reviewID CHAR(6) PRIMARY KEY,
    tglReview DATE NOT NULL,
    review VARCHAR(300) NOT NULL,
    rating DOUBLE NOT NULL
);
 
CREATE TABLE mb_rv(
	memberID CHAR(6),
    reviewID CHAR(6),
    
    CONSTRAINT fk_mb_rv_members FOREIGN KEY (memberID) REFERENCES members(memberID),
    CONSTRAINT fk_mb_rv_reviews FOREIGN KEY (reviewID) REFERENCES reviews(reviewID),
    CONSTRAINT pk_mb_rv PRIMARY KEY (memberID, reviewID)
);
 
CREATE TABLE products(
	productID CHAR(6) PRIMARY KEY,
    namaProduct VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    size DOUBLE NOT NULL,
    stok INT NOT NULL,
    harga DOUBLE NOT NULL,
    BPOMcode VARCHAR(15) NOT NULL,
    ingredients VARCHAR(300) NOT NULL,
    deskripsi VARCHAR(300) NOT NULL,
    caraPakai VARCHAR(300) NOT NULL
);
 
CREATE TABLE pr_rv(
	productID CHAR(6),
    reviewID CHAR(6),
    
    CONSTRAINT fk_pr_rv_products FOREIGN KEY (productID) REFERENCES products(productID),
    CONSTRAINT fk_pr_rv_review FOREIGN KEY (reviewID) REFERENCES reviews(reviewID),
    CONSTRAINT pk_pr_rv PRIMARY KEY (productID, reviewID)
);
 
CREATE TABLE promos(
	promoCode CHAR(6) PRIMARY KEY,
    description VARCHAR(300) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    discountPct DOUBLE NOT NULL,
    quota INT NOT NULL,
    minPurchase DOUBLE NOT NULL
);
 
CREATE TABLE pesanan(
	orderID CHAR(6) PRIMARY KEY,
    memberID CHAR(6) NOT NULL,
    orderDate DATETIME NOT NULL,
    sendAddress VARCHAR(200) NOT NULL,
    packingNumber CHAR(7) NOT NULL,
    courier VARCHAR(100) NOT NULL,
    shippingFee DOUBLE NOT NULL,
    promoCode CHAR(6),
    
    CONSTRAINT fk_pesanan_members FOREIGN KEY (memberID) REFERENCES members(memberID),
    CONSTRAINT fk_pesanan_promos FOREIGN KEY (promoCode) REFERENCES promos(promoCode)
);
 
CREATE TABLE detailpesanan(
    orderID   CHAR(6),
    quantity  INT NOT NULL,
    productID CHAR(6) NOT NULL,
 
    CONSTRAINT fk_dp_pesanan  FOREIGN KEY (orderID)    REFERENCES pesanan(orderID)   ON DELETE CASCADE,
    CONSTRAINT fk_dp_products FOREIGN KEY (productID)  REFERENCES products(productID),
    CONSTRAINT pk_detailpesanan PRIMARY KEY (orderID, productID)
);
 


SHOW TABLES;
-- DATA users
INSERT INTO users (userID, email, username, password, noHP) VALUES
('U0001', 'sari.dewi@gmail.com',      'saridewi',     'Pass@1234',   '081234567890'),
('U0002', 'budi.santoso@gmail.com',   'budisantoso',  'Budi#5678',   '082345678901'),
('U0003', 'rina.putri@yahoo.com',     'rinaputri',    'Rina!9012',   '083456789012'),
('U0004', 'agus.prasetyo@gmail.com',  'aguspras',     'Agus@3456',   '084567890123'),
('U0005', 'dina.larasati@gmail.com',  'dinalaras',    'Dina#7890',   '085678901234'),
('U0006', 'fajar.nugroho@yahoo.com',  'fajarnug',     'Fajar!1234',  '086789012345'),
('U0007', 'hana.permata@gmail.com',   'hanapermata',  'Hana@5678',   '087890123456'),
('U0008', 'irfan.malik@gmail.com',    'irfanmalik',   'Irfan#9012',  '088901234567'),
('U0009', 'julia.sari@yahoo.com',     'juliasari',    'Julia!3456',  '089012345678'),
('U0010', 'kevin.hartono@gmail.com',  'kevinharto',   'Kevin@7890',  '081123456789'),
('U0011', 'lisa.anggraeni@gmail.com', 'lisaangg',     'Lisa#1234',   '082234567890'),
('U0012', 'miko.setiawan@yahoo.com',  'mikoseti',     'Miko!5678',   '083345678901'),
('U0013', 'nadia.kusuma@gmail.com',   'nadiakusum',   'Nadia@9012',  '084456789012'),
('U0014', 'oki.firmansyah@gmail.com', 'okifirman',    'Oki#3456',    '085567890123'),
('U0015', 'putri.lestari@yahoo.com',  'putrilest',    'Putri!7890',  '086678901234');

-- DATA admins
INSERT INTO admins (userID, adminID, status) VALUES
('U0001', 'ADM001', 'active'),
('U0002', 'ADM002', 'active'),
('U0003', 'ADM003', 'inactive'),
('U0004', 'ADM004', 'active'),
('U0005', 'ADM005', 'inactive'),
('U0006', 'ADM006', 'active'),
('U0007', 'ADM007', 'active'),
('U0008', 'ADM008', 'inactive'),
('U0009', 'ADM009', 'active'),
('U0010', 'ADM010', 'active'),
('U0011', 'ADM011', 'inactive'),
('U0012', 'ADM012', 'active'),
('U0013', 'ADM013', 'active'),
('U0014', 'ADM014', 'inactive'),
('U0015', 'ADM015', 'active');
 
-- DATA members
INSERT INTO members (userID, memberID, point, tglJoin) VALUES
('U0001', 'MBR001', 1200, '2023-01-15'),
('U0002', 'MBR002',  850, '2023-02-20'),
('U0003', 'MBR003', 2300, '2023-03-10'),
('U0004', 'MBR004',  500, '2023-04-05'),
('U0005', 'MBR005', 1750, '2023-05-18'),
('U0006', 'MBR006',  320, '2023-06-22'),
('U0007', 'MBR007', 3100, '2023-07-30'),
('U0008', 'MBR008',  960, '2023-08-14'),
('U0009', 'MBR009', 1450, '2023-09-09'),
('U0010', 'MBR010',  275, '2023-10-01'),
('U0011', 'MBR011', 2800, '2023-11-11'),
('U0012', 'MBR012',  630, '2023-12-03'),
('U0013', 'MBR013', 1100, '2024-01-07'),
('U0014', 'MBR014',  410, '2024-02-19'),
('U0015', 'MBR015', 1980, '2024-03-25');
 
-- DATA reviews
INSERT INTO reviews (reviewID, tglReview, review, rating) VALUES
('RV0001', '2024-01-10', 'Produk bagus, kulit terasa lebih lembap setelah pemakaian rutin.', 4.5),
('RV0002', '2024-01-15', 'Teksturnya ringan dan cepat meresap, cocok untuk kulit berminyak.', 4.0),
('RV0003', '2024-02-03', 'Aroma segar dan tidak menyengat, sangat nyaman dipakai sehari-hari.', 5.0),
('RV0004', '2024-02-20', 'Hasilnya terlihat setelah 2 minggu pemakaian, kulit lebih cerah.', 4.5),
('RV0005', '2024-03-05', 'Kemasan menarik dan higienis, produk sesuai deskripsi.', 3.5),
('RV0006', '2024-03-18', 'Sedikit lengket di awal tapi setelah beberapa menit hilang sendiri.', 3.0),
('RV0007', '2024-04-01', 'Sangat memuaskan! Kulit wajah terasa kenyal dan bersih.', 5.0),
('RV0008', '2024-04-22', 'Harga sebanding dengan kualitas, akan repeat order lagi.', 4.0),
('RV0009', '2024-05-10', 'Cocok untuk kulit sensitif, tidak menyebabkan iritasi sama sekali.', 4.5),
('RV0010', '2024-05-28', 'Packaging pecah saat diterima, tapi produknya sendiri bagus.', 3.0),
('RV0011', '2024-06-15', 'Tidak cocok di kulit saya, muncul sedikit kemerahan.', 2.5),
('RV0012', '2024-06-30', 'Wangi produk sangat enak dan tahan lama.', 4.0),
('RV0013', '2024-07-08', 'Efektif mengurangi bekas jerawat dalam waktu 3 minggu.', 4.5),
('RV0014', '2024-07-20', 'Produk original dan bersegel, pengiriman cepat dan aman.', 5.0),
('RV0015', '2024-08-05', 'Kulit terasa lebih halus setelah 1 bulan pemakaian konsisten.', 4.0);

-- DATA MB_RV
INSERT INTO mb_rv (memberID, reviewID) VALUES
('MBR001', 'RV0001'),
('MBR002', 'RV0002'),
('MBR003', 'RV0003'),
('MBR004', 'RV0004'),
('MBR005', 'RV0005'),
('MBR006', 'RV0006'),
('MBR007', 'RV0007'),
('MBR008', 'RV0008'),
('MBR009', 'RV0009'),
('MBR010', 'RV0010'),
('MBR011', 'RV0011'),
('MBR012', 'RV0012'),
('MBR013', 'RV0013'),
('MBR014', 'RV0014'),
('MBR015', 'RV0015');

-- DATA Product
INSERT INTO products (productID, namaProduct, brand, size, stok, harga, BPOMcode, ingredients, deskripsi, caraPakai) VALUES
('PRD001', 'Moisturizing Cream SPF 30',    'GlowSkin',   50.0,  80, 185000, 'NA18230100XXX', 'Aqua, Glycerin, Niacinamide, SPF 30, Aloe Vera Extract',                         'Pelembap wajah dengan perlindungan SPF 30, menjaga kulit tetap lembap sepanjang hari.',     'Aplikasikan secukupnya pada wajah dan leher setiap pagi setelah toner.'),
('PRD002', 'Brightening Serum Vit C',      'LumiCare',   30.0,  60, 245000, 'NA18230101XXX', 'Ascorbic Acid 10%, Hyaluronic Acid, Niacinamide, Rose Hip Oil',                   'Serum vitamin C untuk mencerahkan dan meratakan warna kulit wajah.',                        'Teteskan 3-4 tetes ke seluruh wajah, gunakan sebelum pelembap.'),
('PRD003', 'Gentle Foam Cleanser',         'PureGlow',   100.0, 120, 95000,  'NA18230102XXX', 'Aqua, Sodium Lauryl Sulfate, Glycerin, Aloe Vera, Chamomile Extract',            'Pembersih wajah berbusa lembut yang membersihkan kotoran tanpa menghilangkan kelembapan.',  'Basahi wajah, ambil secukupnya, busa dan bilas dengan air bersih.'),
('PRD004', 'Exfoliating Toner AHA BHA',    'ClearAct',   150.0, 45,  210000, 'NA18230103XXX', 'Glycolic Acid 5%, Salicylic Acid 2%, Witch Hazel, Panthenol',                    'Toner eksfoliasi untuk mengangkat sel kulit mati dan mengecilkan pori-pori.',               'Tuang pada kapas dan usapkan ke seluruh wajah setiap malam.'),
('PRD005', 'Hydrating Sheet Mask',         'GlowSkin',   25.0,  200, 35000,  'NA18230104XXX', 'Hyaluronic Acid, Centella Asiatica, Ceramide, Beta-Glucan',                      'Sheet mask hidrasi intensif untuk kulit kering dan kusam.',                                 'Tempelkan pada wajah selama 15-20 menit, lepas dan tepuk-tepuk sisa serum.'),
('PRD006', 'Retinol Night Cream 0.3%',     'AgeLess',    50.0,  35,  320000, 'NA18230105XXX', 'Retinol 0.3%, Peptide Complex, Squalane, Vitamin E',                             'Krim malam dengan retinol untuk mengurangi kerutan dan tanda penuaan.',                     'Oleskan tipis pada wajah setiap malam sebelum tidur, hindari area mata.'),
('PRD007', 'Sunscreen SPF 50 PA++++',      'SunShield',  60.0,  95,  165000, 'NA18230106XXX', 'Zinc Oxide, Titanium Dioxide, Niacinamide, Hyaluronic Acid, Aqua',               'Tabir surya ringan dengan perlindungan SPF 50 PA++++ untuk kulit sehari-hari.',             'Aplikasikan merata 15 menit sebelum keluar rumah, ulangi setiap 2-3 jam.'),
('PRD008', 'Acne Spot Treatment Gel',      'ClearAct',   15.0,  75,  89000,  'NA18230107XXX', 'Salicylic Acid 2%, Benzoyl Peroxide 2.5%, Tea Tree Oil, Aloe Vera',              'Gel anti-jerawat untuk mengeringkan jerawat aktif dengan cepat.',                           'Oleskan langsung pada jerawat menggunakan cotton bud, gunakan 1-2 kali sehari.'),
('PRD009', 'Lip Balm Vitamin E & Shea',    'LumiCare',   10.0,  150, 55000,  'NA18230108XXX', 'Shea Butter, Vitamin E, Beeswax, Sweet Almond Oil, Peppermint Extract',          'Lip balm pelembap bibir dengan formula shea butter dan vitamin E.',                         'Oleskan pada bibir kapan saja dibutuhkan, terutama sebelum tidur.'),
('PRD010', 'Micellar Cleansing Water',     'PureGlow',   200.0, 85,  115000, 'NA18230109XXX', 'Aqua, Micelles, Glycerin, Rose Water, Cucumber Extract',                         'Air micellar untuk membersihkan makeup dan kotoran tanpa perlu dibilas.',                   'Tuang pada kapas dan usapkan lembut ke seluruh wajah hingga bersih.'),
('PRD011', 'Eye Cream Caffeine Complex',   'AgeLess',    20.0,  40,  275000, 'NA18230110XXX', 'Caffeine 1%, Peptide, Hyaluronic Acid, Green Tea Extract, Vitamin K',            'Krim mata untuk mengurangi kantong mata, lingkaran hitam, dan kerutan halus.',              'Ketuk-ketuk lembut di area bawah mata menggunakan jari manis, gunakan pagi dan malam.'),
('PRD012', 'Body Lotion Collagen Boost',   'GlowSkin',   250.0, 60,  145000, 'NA18230111XXX', 'Collagen Peptide, Shea Butter, Vitamin C, Aqua, Sweet Almond Oil',               'Losion tubuh dengan kolagen untuk kulit lebih kencang dan bercahaya.',                      'Oleskan ke seluruh tubuh setelah mandi, pijat hingga meresap sempurna.'),
('PRD013', 'Facial Mist Hyaluronic Acid',  'LumiCare',   100.0, 70,  135000, 'NA18230112XXX', 'Hyaluronic Acid, Rose Water, Aloe Vera, Panthenol, Glycerin',                    'Facial mist untuk menyegarkan dan melembapkan kulit kapan saja.',                           'Semprotkan dengan jarak 20 cm dari wajah, bisa digunakan di atas makeup.'),
('PRD014', 'Clay Mask Detox Charcoal',     'ClearAct',   75.0,  55,  125000, 'NA18230113XXX', 'Kaolin Clay, Activated Charcoal, Bentonite, Tea Tree Oil, Glycerin',             'Masker tanah liat untuk detoks pori-pori dan mengontrol minyak berlebih.',                  'Oleskan tipis pada wajah, diamkan 10-15 menit, bilas dengan air hangat.'),
('PRD015', 'Sleeping Mask Overnight Pack', 'GlowSkin',   80.0,  90,  195000, 'NA18230114XXX', 'Ceramide, Centella Asiatica, Lavender Extract, Hyaluronic Acid, Niacinamide',    'Sleeping mask untuk perawatan intensif semalam sehingga kulit bercahaya di pagi hari.',     'Aplikasikan sebagai langkah terakhir skincare malam, bilas keesokan paginya.');
 
 -- DATA PR_RV
 INSERT INTO pr_rv (productID, reviewID) VALUES
('PRD001', 'RV0001'),
('PRD002', 'RV0002'),
('PRD003', 'RV0003'),
('PRD004', 'RV0004'),
('PRD005', 'RV0005'),
('PRD006', 'RV0006'),
('PRD007', 'RV0007'),
('PRD008', 'RV0008'),
('PRD009', 'RV0009'),
('PRD010', 'RV0010'),
('PRD011', 'RV0011'),
('PRD012', 'RV0012'),
('PRD013', 'RV0013'),
('PRD014', 'RV0014'),
('PRD015', 'RV0015');

-- DATA Promos
INSERT INTO promos (promoCode, description, startDate, endDate, discountPct, quota, minPurchase) VALUES
('PRO001', 'Promo Hari Kemerdekaan, diskon spesial 17% untuk semua produk.',   '2024-08-01', '2024-08-17', 17.0, 100, 100000),
('PRO002', 'Flash Sale Akhir Bulan, hemat 20% untuk pembelian diatas 200rb.',  '2024-08-28', '2024-08-31', 20.0,  50, 200000),
('PRO003', 'Promo New Member, diskon 10% untuk member baru.',                  '2024-01-01', '2024-12-31', 10.0, 200,  50000),
('PRO004', 'Glow Fest Sale, diskon 25% untuk produk pilihan.',                 '2024-09-10', '2024-09-15', 25.0,  75, 150000),
('PRO005', 'Promo Harbolnas 11.11, diskon besar-besaran 30% semua item.',      '2024-11-11', '2024-11-11', 30.0, 500, 100000),
('PRO006', 'Birthday Month Promo, diskon 15% khusus bulan ulang tahun.',       '2024-01-01', '2024-12-31', 15.0, 300,  75000),
('PRO007', 'Payday Sale, diskon 12% setiap akhir bulan.',                      '2024-10-25', '2024-10-31', 12.0, 150, 125000),
('PRO008', 'Promo Double Day 12.12, diskon 35% untuk order pertama.',          '2024-12-12', '2024-12-12', 35.0, 200, 100000),
('PRO009', 'Weekend Special, gratis ongkir dan diskon 8% setiap Sabtu-Minggu.','2024-09-01', '2024-09-30',  8.0, 100,  80000),
('PRO010', 'Bundling Promo, beli 3 produk diskon 18%.',                        '2024-07-01', '2024-07-31', 18.0,  80, 250000),
('PRO011', 'Promo Ramadan Sale, diskon 22% selama bulan Ramadan.',             '2025-03-01', '2025-03-30', 22.0, 400, 100000),
('PRO012', 'Promo Lebaran, diskon 20% untuk hampers skincare.',                '2025-03-28', '2025-04-07', 20.0, 250, 200000),
('PRO013', 'Year End Clearance, diskon 40% untuk produk pilihan.',             '2024-12-26', '2024-12-31', 40.0, 120, 150000),
('PRO014', 'Promo Loyal Customer, diskon 15% untuk member dengan 1000+ poin.', '2024-01-01', '2024-12-31', 15.0, 500,  50000),
('PRO015', 'Glow Up January Sale, diskon 10% menyambut tahun baru.',           '2025-01-01', '2025-01-07', 10.0, 300,  75000);

-- DATA Pesanan
INSERT INTO pesanan (orderID, memberID, orderDate, sendAddress, packingNumber, courier, shippingFee, promoCode) VALUES
('ORD001', 'MBR001', '2024-08-05 10:23:00', 'Jl. Mawar No.12, Depok, Jawa Barat 16412',              'PKG0001', 'JNE Regular',   20000, 'PRO001'),
('ORD002', 'MBR002', '2024-08-29 14:05:00', 'Jl. Melati No.7, Bandung, Jawa Barat 40115',            'PKG0002', 'SiCepat HALU',  18000, 'PRO002'),
('ORD003', 'MBR003', '2024-03-12 09:45:00', 'Jl. Anggrek No.3, Surabaya, Jawa Timur 60111',          'PKG0003', 'J&T Express',   25000, 'PRO003'),
('ORD004', 'MBR004', '2024-09-11 16:30:00', 'Jl. Kenanga No.20, Yogyakarta 55281',                   'PKG0004', 'Anteraja',      15000, 'PRO004'),
('ORD005', 'MBR005', '2024-11-11 00:05:00', 'Jl. Dahlia No.5, Semarang, Jawa Tengah 50111',          'PKG0005', 'JNE YES',       30000, 'PRO005'),
('ORD006', 'MBR003', '2024-06-10 11:20:00', 'Jl. Tulip No.9, Medan, Sumatera Utara 20111',           'PKG0006', 'Pos Indonesia',  22000, NULL),
('ORD007', 'MBR007', '2024-10-28 13:55:00', 'Jl. Seroja No.14, Makassar, Sulawesi Selatan 90111',    'PKG0007', 'SiCepat REG',   35000, 'PRO007'),
('ORD008', 'MBR001', '2024-12-12 08:00:00', 'Jl. Cempaka No.6, Tangerang, Banten 15111',             'PKG0008', 'J&T Economy',   16000, 'PRO008'),
('ORD009', 'MBR009', '2024-09-14 17:45:00', 'Jl. Flamboyan No.2, Bogor, Jawa Barat 16111',           'PKG0009', 'Anteraja',      20000, 'PRO009'),
('ORD010', 'MBR007', '2024-07-19 10:10:00', 'Jl. Kamboja No.18, Solo, Jawa Tengah 57111',            'PKG0010', 'JNE Regular',   20000, 'PRO010'),
('ORD011', 'MBR011', '2025-03-15 15:00:00', 'Jl. Palem No.11, Palembang, Sumatera Selatan 30111',    'PKG0011', 'SiCepat HALU',  28000, 'PRO011'),
('ORD012', 'MBR005', '2025-04-02 12:30:00', 'Jl. Bougenville No.4, Pekanbaru, Riau 28111',           'PKG0012', 'Pos Indonesia',  22000, 'PRO012'),
('ORD013', 'MBR013', '2024-12-27 09:00:00', 'Jl. Sakura No.8, Malang, Jawa Timur 65111',             'PKG0013', 'J&T Express',   25000, 'PRO013'),
('ORD014', 'MBR011', '2024-08-20 14:40:00', 'Jl. Teratai No.30, Denpasar, Bali 80111',               'PKG0014', 'Anteraja',      40000, 'PRO014'),
('ORD015', 'MBR015', '2025-01-03 11:15:00', 'Jl. Lavender No.17, Balikpapan, Kalimantan Timur 76111','PKG0015', 'JNE Regular',   45000, 'PRO015');

-- DATA Detailpesanan
INSERT INTO detailpesanan (orderID, quantity, productID) VALUES
('ORD001', 2, 'PRD001'),
('ORD002', 1, 'PRD002'),
('ORD003', 3, 'PRD003'),
('ORD004', 1, 'PRD004'),
('ORD005', 5, 'PRD005'),
('ORD006', 2, 'PRD006'),
('ORD007', 1, 'PRD007'),
('ORD008', 4, 'PRD008'),
('ORD009', 2, 'PRD009'),
('ORD010', 3, 'PRD010'),
('ORD011', 1, 'PRD011'),
('ORD012', 2, 'PRD012'),
('ORD013', 1, 'PRD013'),
('ORD014', 3, 'PRD014'),
('ORD015', 2, 'PRD015');