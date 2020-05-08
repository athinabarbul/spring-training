CREATE TABLE IF NOT EXISTS CUSTOMER( ID INT AUTO_INCREMENT PRIMARY KEY,
                        FirstName VARCHAR(45),
                        LastName VARCHAR(45),
                        Username VARCHAR(20),
                        Password VARCHAR(20),
                        EmailAddress VARCHAR(45));

CREATE TABLE IF NOT EXISTS REVENUE( ID INT AUTO_INCREMENT PRIMARY KEY,
                        Location INT,
                        Date Date,
                        Sum DECIMAL(40,30),
                        CONSTRAINT UNIQUE KEY(Location));

CREATE TABLE IF NOT EXISTS LOCATION( ID INT AUTO_INCREMENT PRIMARY KEY,
                        Name VARCHAR(45),
                        AddressCountry VARCHAR(45),
                        AddressCity VARCHAR(45),
                        AddressCounty VARCHAR(45),
                        AddressStreetAddress VARCHAR(60));

CREATE TABLE IF NOT EXISTS SUPPLIER( ID INT AUTO_INCREMENT PRIMARY KEY,
                        Name VARCHAR(45));

CREATE TABLE IF NOT EXISTS PRODUCTCATEGORY( ID INT AUTO_INCREMENT PRIMARY KEY,
                                Name VARCHAR(45),
                                Description VARCHAR(45));

CREATE TABLE IF NOT EXISTS PRODUCT( ID INT AUTO_INCREMENT PRIMARY KEY,
                        Name VARCHAR(45),
                        Description VARCHAR(45),
                        Price DECIMAL(40,30),
                        Weight DECIMAL(20,5),
                        Category INT,
                        Supplier INT,
                        ImageUrl VARCHAR(120),
                        CONSTRAINT FOREIGN KEY (Category) REFERENCES PRODUCTCATEGORY(ID),
                        CONSTRAINT FOREIGN KEY (Supplier) REFERENCES SUPPLIER(ID));

CREATE TABLE IF NOT EXISTS STOCK( Product INT PRIMARY KEY,
                        Location INT,
                        Quantity INT,
                        CONSTRAINT FOREIGN KEY (Product) REFERENCES PRODUCT(ID),
                        CONSTRAINT FOREIGN KEY (Location) REFERENCES LOCATION(ID));

CREATE TABLE IF NOT EXISTS ORDERS( ID INT AUTO_INCREMENT PRIMARY KEY,
                        ShippedFrom INT,
                        Customer INT,
                        CreatedAt TIMESTAMP,
                        AddressCountry VARCHAR(45),
                        AddressCity VARCHAR(45),
                        AddressCounty VARCHAR(45),
                        AddressStreetAddress VARCHAR(60),
                        CONSTRAINT FOREIGN KEY (Customer) REFERENCES CUSTOMER(ID),
                        CONSTRAINT FOREIGN KEY (ShippedFrom) REFERENCES REVENUE(Location),
                        CONSTRAINT FOREIGN KEY (ShippedFrom) REFERENCES LOCATION(ID));

CREATE TABLE IF NOT EXISTS ORDERDETAIL( Orders INT PRIMARY KEY,
                        Product INT,
                        Quantity INT,
                        CONSTRAINT FOREIGN KEY (Orders) REFERENCES ORDERS(ID),
                        CONSTRAINT FOREIGN KEY (Product) REFERENCES PRODUCT(ID));

