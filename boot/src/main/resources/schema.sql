DROP TABLE IF EXISTS PRICES;
CREATE TABLE IF NOT EXISTS PRICES (
                                      `BRAND_ID` INT NOT NULL,
                                      `START_DATE` TIMESTAMP NOT NULL,
                                      `END_DATE` TIMESTAMP NOT NULL,
                                      `PRICE_LIST` int NOT NULL PRIMARY KEY,
                                      `PRODUCT_ID` int NOT NULL,
                                      `PRIORITY` int NOT NULL,
                                      `PRICE` int NOT NULL,
                                      `CURR` varchar(3) NOT NULL
);