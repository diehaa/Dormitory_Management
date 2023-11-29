-- Create a new database called 'QLKTX'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'QLKTX'
)
CREATE DATABASE QLKTX
GO

CREATE TABLE Admin (
    adminId INT PRIMARY KEY,
    username NVARCHAR(50),
    password NVARCHAR(50),
    name NVARCHAR(100),
    email VARCHAR(100),
    role NVARCHAR(50),
    phone NVARCHAR(20),
    avatar NVARCHAR(100)
);

CREATE TABLE Room (
    roomId INT PRIMARY KEY,
    name NVARCHAR(100),
    type NVARCHAR(50),
    price FLOAT
);


CREATE TABLE Users (
    usersId INT PRIMARY KEY,
    username NVARCHAR(50),
    password NVARCHAR(50),
    name NVARCHAR(100),
    email NVARCHAR(100),
    phone NVARCHAR(20),
    parentName NVARCHAR(100),
    parentPhone NVARCHAR(20),
    balance FLOAT,
    avatar NVARCHAR(100),
    roomId INT,
    FOREIGN KEY (roomId) REFERENCES Room(roomId)
);


CREATE TABLE Payment (
    paymentId INT PRIMARY KEY,
    usersId INT,
    total FLOAT,
    statuses NVARCHAR(50),
    FOREIGN KEY (usersId) REFERENCES Users(usersId)
);

CREATE TABLE Application (
    applicationId INT PRIMARY KEY,
    usersId INT,
    title NVARCHAR(100),
    reason NVARCHAR(500),
    files NVARCHAR(100),
    statuses NVARCHAR(50),
    comment NVARCHAR(500),
    FOREIGN KEY (usersId) REFERENCES Users(usersId)
);


-- Thêm dữ liệu vào bảng Admin
INSERT INTO Admin (adminId, username, password, name, email, role, phone)
VALUES (1,'tramy', '123', N'Trà My', 'tramy@gmail.com', 'admin', '0123456789'),
	   (2,'pgb', '123', N'Phan Gia Bảo', 'pgb@gmail.com', 'admin', '0246856778');


-- Thêm dữ liệu vào bảng Room
INSERT INTO Room (roomId, name, type, price)
VALUES (1, N'Room 1', N'for', 650000),
       (2, N'Room 2', N'six', 950000);

-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (usersId, username, password, name, email, phone, parentName, parentPhone, balance, roomId)
VALUES (1, 'dvh', '123', N'Đặng Văn Hải', 'dvh@gmail.com', '0987654321', N'Đặng Quốc', 'o987654321', 0, 1),
       (2, 'ntv', '123', N'Nguyễn Văn Tú', 'nvt@gmail.com', '0987654322', N'Nguyễn Hoàn', '0987654322', 0, 2);


