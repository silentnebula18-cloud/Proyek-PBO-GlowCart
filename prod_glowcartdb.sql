USE glowcartdb;

DELIMITER //
CREATE PROCEDURE makeAccAdmin( 
	IN email VARCHAR(50),
    IN username VARCHAR(30),
    IN password VARCHAR(30),
    IN noHP VARCHAR(15)
) 
BEGIN 
    DECLARE iuserID CHAR(5); 
    DECLARE iadminID CHAR(6);
    
    SELECT CONCAT('U', LPAD(MAX(CAST(SUBSTRING(userID,2) AS UNSIGNED)) + 1, 4, '0')) FROM users INTO iuserID;
    SELECT CONCAT('ADM', LPAD(MAX(CAST(SUBSTRING(adminID,4) AS UNSIGNED)) + 1, 3, '0')) FROM admins INTO iadminID;

    INSERT INTO users VALUES (iuserID, email, username, password, noHP);
    INSERT INTO admins VALUES (iuserID, iadminID, 'active');
END // 
DELIMITER ;

DELIMITER //
CREATE PROCEDURE grabAdmin(IN iusername VARCHAR(30), IN ipassword VARCHAR(30)) 
BEGIN 
    SELECT u.userID, u.email, u.username, u.password, u.noHP, a.adminID, a.status
    FROM users u JOIN admins a ON (u.userID = a.userID) 
    WHERE u.username = iusername AND u.password = ipassword;
    
END // 
DELIMITER ;

-- DROP PROCEDURE grabAdmin;

DELIMITER //
CREATE PROCEDURE makeAccMember( 
	IN email VARCHAR(50),
    IN username VARCHAR(30),
    IN password VARCHAR(30),
    IN noHP VARCHAR(15)
) 
BEGIN 
    DECLARE iuserID CHAR(5); 
    DECLARE imemberID CHAR(6);
    
    SELECT CONCAT('U', LPAD(MAX(CAST(SUBSTRING(userID,2) AS UNSIGNED)) + 1, 4, '0')) FROM users INTO iuserID;
    SELECT CONCAT('MBR', LPAD(MAX(CAST(SUBSTRING(memberID,4) AS UNSIGNED)) + 1, 3, '0')) FROM members INTO imemberID;

    INSERT INTO users VALUES (iuserID, email, username, password, noHP);
    INSERT INTO members VALUES (iuserID, imemberID, 0, CURDATE());
END // 
DELIMITER ;

DELIMITER //
CREATE PROCEDURE grabMember(IN iusername VARCHAR(30), IN ipassword VARCHAR(30)) 
BEGIN 
    SELECT u.userID, u.email, u.username, u.password, u.noHP, m.memberID, m.point, m.tglJoin
    FROM users u JOIN members m ON (u.userID = m.userID) 
    WHERE u.username = iusername AND u.password = ipassword;
END // 
DELIMITER ;

-- DROP PROCEDURE grabMember;
 
DELIMITER //
CREATE PROCEDURE grabRvMbList(IN ImemberID CHAR(6)) 
BEGIN 
    SELECT r.reviewID, r.tglReview, r.review, r.rating
    FROM reviews r JOIN mb_rv mr ON r.reviewID = mr.reviewID
    WHERE mr.memberID = ImemberID;
END // 
DELIMITER ;

-- DROP procedure grabRvMbList;

DELIMITER //
CREATE PROCEDURE grabPsList(IN ImemberID CHAR(6))
BEGIN 
    SELECT orderID, orderDate, sendAddress, packingNumber, courier, shippingFee 
    FROM pesanan WHERE memberID = ImemberID;
END // 
DELIMITER ;

-- CALL grabPsList("MBR001");

DELIMITER //
CREATE PROCEDURE grabPsPromo(IN IorderID CHAR(6)) 
BEGIN 
	DECLARE IpromoCode CHAR(6);
    SELECT promoCode FROM pesanan WHERE orderID = IorderID INTO IpromoCode;
    SELECT * FROM promos WHERE promoCode = IpromoCode;
END // 
DELIMITER ;
 
-- CALL grabPsPromo('ORD001');
 
DELIMITER //
CREATE PROCEDURE grabPsDP(IN IorderID CHAR(6)) 
BEGIN 
	SELECT dp.quantity, pr.productID, pr.namaProduct, pr.brand, pr.size, 
    pr.stok, pr.harga, pr.BPOMcode, pr.ingredients, pr.deskripsi, pr.caraPakai
    FROM detailpesanan dp JOIN products pr ON dp.productID = pr.productID
    WHERE dp.orderID = IorderID;
END // 
DELIMITER ;

-- CALL grabPsDP("ORD001");

DELIMITER //
CREATE PROCEDURE grabRvPrList(IN IproductID CHAR(6)) 
BEGIN 
	SELECT r.reviewID, r.tglReview, r.review, r.rating
    FROM reviews r JOIN pr_rv pr ON r.reviewID = pr.reviewID
    WHERE pr.productID = IproductID;
END // 
DELIMITER ;

-- DROP PROCEDURE grabRvPrList;

-- CALL grabRvPrList("PRO006");

DELIMITER //
CREATE PROCEDURE grabAllProd()
BEGIN 
	SELECT productID, namaProduct, brand, size, stok, 
    harga, BPOMcode, ingredients, deskripsi, caraPakai
    FROM products;
END // 
DELIMITER ;
 
-- lihat prod yg ada
-- SHOW PROCEDURE STATUS WHERE Db = DATABASE();

-- Tambah produk baru
DELIMITER //
CREATE PROCEDURE addProduct(
    IN iNamaProduct VARCHAR(50),
    IN iBrand VARCHAR(50),
    IN iSize DOUBLE,
    IN iStok INT,
    IN iHarga DOUBLE,
    IN iBPOMcode VARCHAR(15),
    IN iIngredients VARCHAR(300),
    IN iDeskripsi VARCHAR(300),
    IN iCaraPakai VARCHAR(300)
)
BEGIN
    DECLARE iProductID CHAR(6);
    SELECT CONCAT('PRD', LPAD(MAX(CAST(SUBSTRING(productID,4) AS UNSIGNED)) + 1, 3, '0'))
    FROM products INTO iProductID;

    INSERT INTO products VALUES (iProductID, iNamaProduct, iBrand, iSize, iStok, iHarga, iBPOMcode, iIngredients, iDeskripsi, iCaraPakai);
END //
DELIMITER ;

-- Update produk
DELIMITER //
CREATE PROCEDURE updateProduct(
    IN iProductID CHAR(6),
    IN iNamaProduct VARCHAR(50),
    IN iBrand VARCHAR(50),
    IN iSize DOUBLE,
    IN iStok INT,
    IN iHarga DOUBLE,
    IN iIngredients VARCHAR(300),
    IN iDeskripsi VARCHAR(300),
    IN iCaraPakai VARCHAR(300)
)
BEGIN
    UPDATE products SET
        namaProduct = iNamaProduct,
        brand       = iBrand,
        size        = iSize,
        stok        = iStok,
        harga       = iHarga,
        ingredients = iIngredients,
        deskripsi   = iDeskripsi,
        caraPakai   = iCaraPakai
    WHERE productID = iProductID;
END //
DELIMITER ;

-- Ambil semua promo
DELIMITER //
CREATE PROCEDURE grabAllPromo()
BEGIN
    SELECT promoCode, description, startDate, endDate, discountPct, quota, minPurchase
    FROM promos;
END //
DELIMITER ;

-- Tambah promo baru
DELIMITER //
CREATE PROCEDURE addPromo(
    IN iDescription VARCHAR(300),
    IN iStartDate DATE,
    IN iEndDate DATE,
    IN iDiscountPct DOUBLE,
    IN iQuota INT,
    IN iMinPurchase DOUBLE
)
BEGIN
    DECLARE iPromoCode CHAR(6);
    SELECT CONCAT('PRO', LPAD(MAX(CAST(SUBSTRING(promoCode,4) AS UNSIGNED)) + 1, 3, '0'))
    FROM promos INTO iPromoCode;

    INSERT INTO promos VALUES (iPromoCode, iDescription, iStartDate, iEndDate, iDiscountPct, iQuota, iMinPurchase);
END //
DELIMITER ;

-- Update promo
DELIMITER //
CREATE PROCEDURE updatePromo(
    IN iPromoCode CHAR(6),
    IN iDescription VARCHAR(300),
    IN iDiscountPct DOUBLE
)
BEGIN
    UPDATE promos SET
        description = iDescription,
        discountPct = iDiscountPct
    WHERE promoCode = iPromoCode;
END //
DELIMITER ;

-- Tambah pesanan baru
DELIMITER //
CREATE PROCEDURE addPesanan(
    IN iMemberID CHAR(6),
    IN iSendAddress VARCHAR(200),
    IN iPackingNumber CHAR(7),
    IN iCourier VARCHAR(100),
    IN iShippingFee DOUBLE,
    IN iPromoCode CHAR(6)
)
BEGIN
    DECLARE iOrderID CHAR(6);
    SELECT CONCAT('ORD', LPAD(MAX(CAST(SUBSTRING(orderID,4) AS UNSIGNED)) + 1, 3, '0'))
    FROM pesanan INTO iOrderID;

    INSERT INTO pesanan VALUES (iOrderID, iMemberID, NOW(), iSendAddress, iPackingNumber, iCourier, iShippingFee, iPromoCode);
    SELECT iOrderID;
END //
DELIMITER ;

-- Tambah detail pesanan
DELIMITER //
CREATE PROCEDURE addDetailPesanan(
    IN iOrderID CHAR(6),
    IN iQuantity INT,
    IN iProductID CHAR(6)
)
BEGIN
    INSERT INTO detailpesanan VALUES (iOrderID, iQuantity, iProductID);
    UPDATE products SET stok = stok - iQuantity WHERE productID = iProductID;
END //
DELIMITER ;

-- Tambah review
DELIMITER //
CREATE PROCEDURE addReview(
    IN iMemberID CHAR(6),
    IN iProductID CHAR(6),
    IN iReview VARCHAR(300),
    IN iRating DOUBLE
)
BEGIN
    DECLARE iReviewID CHAR(6);
    SELECT CONCAT('RV', LPAD(MAX(CAST(SUBSTRING(reviewID,3) AS UNSIGNED)) + 1, 4, '0'))
    FROM reviews INTO iReviewID;

    INSERT INTO reviews VALUES (iReviewID, CURDATE(), iReview, iRating);
    INSERT INTO mb_rv VALUES (iMemberID, iReviewID);
    INSERT INTO pr_rv VALUES (iProductID, iReviewID);
END //
DELIMITER ;