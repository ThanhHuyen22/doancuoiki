CREATE DATABASE QuanLyTiemGiatUi;
GO
USE QuanLyTiemGiatUi;
GO

-- Bảng Khách hàng
CREATE TABLE KhachHang (
    MaKH INT IDENTITY(1,1) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(15) NOT NULL,
    DiaChi NVARCHAR(200),
    NgayDangKy DATE DEFAULT GETDATE()
);
GO

-- Bảng Dịch vụ
CREATE TABLE DichVu (
    MaDV INT IDENTITY(1,1) PRIMARY KEY,
    TenDV NVARCHAR(100) NOT NULL,
    GiaTien DECIMAL(18,2) NOT NULL,
    DonViTinh NVARCHAR(50) NOT NULL,
    MoTa NVARCHAR(255)
);
GO

-- Bảng Đơn hàng
CREATE TABLE DonHang (
    MaDH INT IDENTITY(1,1) PRIMARY KEY,
    MaKH INT NOT NULL,
    NgayNhan DATETIME DEFAULT GETDATE(),
    NgayTra DATETIME,
    TrangThai NVARCHAR(50) DEFAULT N'Mới',
    TongTien DECIMAL(18,2) DEFAULT 0,
    GhiChu NVARCHAR(255),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);
GO

-- Bảng Chi tiết Đơn hàng
CREATE TABLE ChiTietDonHang (
    MaCT INT IDENTITY(1,1) PRIMARY KEY,
    MaDH INT NOT NULL,
    MaDV INT NOT NULL,
    SoLuong INT NOT NULL DEFAULT 1,
    DonGia DECIMAL(18,2) NOT NULL,
    ThanhTien DECIMAL(18,2) NOT NULL,
    MoTa NVARCHAR(255),
    FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH),
    FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV)
);
GO

-- Bảng Thống kê (nếu cần)
CREATE TABLE ThongKe (
    MaThongKe INT IDENTITY(1,1) PRIMARY KEY,
    Ngay DATE DEFAULT GETDATE(),
    TongDoanhThu DECIMAL(18,2) DEFAULT 0,
    SoDonHang INT DEFAULT 0,
    SoKhachHang INT DEFAULT 0
);
GO

----------------------------------------------------------
-- THÊM DỮ LIỆU MẪU
----------------------------------------------------------

-- Thêm dữ liệu Khách hàng
INSERT INTO KhachHang (HoTen, SoDienThoai, DiaChi, NgayDangKy) 
VALUES 
(N'Nguyễn Văn An', '0912345678', N'123 Đường ABC, Quận 1', '2024-01-15'),
(N'Trần Thị Bình', '0987654321', N'456 Đường XYZ, Quận 2', '2024-02-20'),
(N'Lê Văn Cường', '0905123456', N'789 Đường DEF, Quận 3', '2024-03-10'),
(N'Phạm Thị Dung', '0911222333', N'321 Đường GHI, Quận 4', '2024-04-05'),
(N'Hoàng Văn Em', '0922333444', N'654 Đường KLM, Quận 5', '2024-05-12');
GO

-- Thêm dữ liệu Dịch vụ
INSERT INTO DichVu (TenDV, GiaTien, DonViTinh, MoTa) 
VALUES 
(N'Giặt khô', 20000, N'Cái', N'Giặt khô công nghiệp'),
(N'Giặt ướt', 15000, N'Cái', N'Giặt thường'),
(N'Giặt hấp', 25000, N'Cái', N'Giặt và hấp là'),
(N'Giặt theo kg', 40000, N'Kg', N'Giặt theo trọng lượng'),
(N'Ủi đồ', 10000, N'Cái', N'Ủi phẳng'),
(N'Giặt nệm', 80000, N'Cái', N'Giặt nệm lớn'),
(N'Giặt giày', 30000, N'Đôi', N'Giặt và vệ sinh giày'),
(N'Giặt gấu bông', 35000, N'Cái', N'Giặt đồ chơi nhồi bông');
GO

-- Thêm dữ liệu Đơn hàng
INSERT INTO DonHang (MaKH, NgayNhan, NgayTra, TrangThai, TongTien, GhiChu) 
VALUES 
(1, '2024-08-20 08:30:00', '2024-08-22 17:00:00', N'Hoàn thành', 95000, N'Áo sơ mi trắng'),
(2, '2024-08-21 09:15:00', '2024-08-23 16:30:00', N'Đang giặt', 145000, N'Chăn ga gối'),
(3, '2024-08-22 10:00:00', '2024-08-24 18:00:00', N'Mới', 60000, N'Quần âu'),
(4, '2024-08-23 14:20:00', '2024-08-25 17:30:00', N'Đã giặt', 120000, N'Áo khoác'),
(5, '2024-08-24 16:45:00', NULL, N'Mới', 80000, N'Váy dạ hội');
GO

-- Thêm dữ liệu Chi tiết Đơn hàng
INSERT INTO ChiTietDonHang (MaDH, MaDV, SoLuong, DonGia, ThanhTien, MoTa) 
VALUES 
(1, 1, 3, 20000, 60000, N'3 áo sơ mi giặt khô'),
(1, 5, 2, 10000, 20000, N'2 quần ủi đồ'),
(1, 7, 1, 30000, 30000, N'1 đôi giày vệ sinh'),

(2, 4, 2, 40000, 80000, N'2kg đồ giặt'),
(2, 6, 1, 80000, 80000, N'1 nệm giặt'),
(2, 5, 3, 10000, 30000, N'3 áo ủi đồ'),

(3, 2, 4, 15000, 60000, N'4 áo giặt ướt'),

(4, 3, 2, 25000, 50000, N'2 áo giặt hấp'),
(4, 1, 2, 20000, 40000, N'2 quần giặt khô'),
(4, 5, 3, 10000, 30000, N'3 áo ủi đồ'),

(5, 8, 2, 35000, 70000, N'2 gấu bông giặt'),
(5, 5, 1, 10000, 10000, N'1 áo ủi đồ');
GO

-- Cập nhật tổng tiền cho đơn hàng
UPDATE DonHang 
SET TongTien = (
    SELECT SUM(ThanhTien) 
    FROM ChiTietDonHang 
    WHERE ChiTietDonHang.MaDH = DonHang.MaDH
)
WHERE MaDH IN (1, 2, 3, 4, 5);
GO

-- Thêm dữ liệu Thống kê
INSERT INTO ThongKe (Ngay, TongDoanhThu, SoDonHang, SoKhachHang) 
VALUES 
('2024-08-20', 95000, 1, 1),
('2024-08-21', 145000, 1, 1),
('2024-08-22', 60000, 1, 1),
('2024-08-23', 120000, 1, 1),
('2024-08-24', 80000, 1, 1);
GO

----------------------------------------------------------
-- TẠO STORED PROCEDURES
----------------------------------------------------------

-- SP thêm khách hàng mới
CREATE PROCEDURE sp_ThemKhachHang
    @HoTen NVARCHAR(100),
    @SoDienThoai VARCHAR(15),
    @DiaChi NVARCHAR(200) = NULL
AS
BEGIN
    INSERT INTO KhachHang (HoTen, SoDienThoai, DiaChi)
    VALUES (@HoTen, @SoDienThoai, @DiaChi)
    
    SELECT SCOPE_IDENTITY() AS MaKH
END
GO

-- SP thêm đơn hàng mới
CREATE PROCEDURE sp_ThemDonHang
    @MaKH INT,
    @NgayTra DATETIME = NULL,
    @GhiChu NVARCHAR(255) = NULL
AS
BEGIN
    INSERT INTO DonHang (MaKH, NgayTra, GhiChu)
    VALUES (@MaKH, @NgayTra, @GhiChu)
    
    SELECT SCOPE_IDENTITY() AS MaDH
END
GO

-- SP thêm chi tiết đơn hàng
CREATE PROCEDURE sp_ThemChiTietDonHang
    @MaDH INT,
    @MaDV INT,
    @SoLuong INT,
    @MoTa NVARCHAR(255) = NULL
AS
BEGIN
    DECLARE @DonGia DECIMAL(18,2)
    
    SELECT @DonGia = GiaTien 
    FROM DichVu 
    WHERE MaDV = @MaDV
    
    INSERT INTO ChiTietDonHang (MaDH, MaDV, SoLuong, DonGia, ThanhTien, MoTa)
    VALUES (@MaDH, @MaDV, @SoLuong, @DonGia, @DonGia * @SoLuong, @MoTa)
    
    -- Cập nhật tổng tiền đơn hàng
    UPDATE DonHang 
    SET TongTien = (
        SELECT SUM(ThanhTien) 
        FROM ChiTietDonHang 
        WHERE MaDH = @MaDH
    )
    WHERE MaDH = @MaDH
END
GO

-- SP lấy danh sách đơn hàng theo khách hàng
CREATE PROCEDURE sp_GetDonHangByKhachHang
    @MaKH INT
AS
BEGIN
    SELECT 
        dh.MaDH,
        dh.NgayNhan,
        dh.NgayTra,
        dh.TrangThai,
        dh.TongTien,
        dh.GhiChu,
        kh.HoTen,
        kh.SoDienThoai
    FROM DonHang dh
    INNER JOIN KhachHang kh ON dh.MaKH = kh.MaKH
    WHERE dh.MaKH = @MaKH
    ORDER BY dh.NgayNhan DESC
END
GO

-- SP thống kê doanh thu theo ngày
CREATE PROCEDURE sp_ThongKeDoanhThu
    @TuNgay DATE,
    @DenNgay DATE
AS
BEGIN
    SELECT 
        CONVERT(DATE, dh.NgayNhan) AS Ngay,
        COUNT(dh.MaDH) AS SoDonHang,
        SUM(dh.TongTien) AS TongDoanhThu,
        COUNT(DISTINCT dh.MaKH) AS SoKhachHang
    FROM DonHang dh
    WHERE CONVERT(DATE, dh.NgayNhan) BETWEEN @TuNgay AND @DenNgay
    GROUP BY CONVERT(DATE, dh.NgayNhan)
    ORDER BY Ngay
END
GO

----------------------------------------------------------
-- TẠO VIEWS
----------------------------------------------------------

-- View xem đơn hàng chi tiết
CREATE VIEW vw_DonHangChiTiet AS
SELECT 
    dh.MaDH,
    dh.NgayNhan,
    dh.NgayTra,
    dh.TrangThai,
    dh.TongTien,
    dh.GhiChu,
    kh.MaKH,
    kh.HoTen AS TenKhachHang,
    kh.SoDienThoai,
    kh.DiaChi,
    ctdh.MaDV,
    dv.TenDV,
    ctdh.SoLuong,
    ctdh.DonGia,
    ctdh.ThanhTien,
    ctdh.MoTa AS ChiTietMoTa
FROM DonHang dh
INNER JOIN KhachHang kh ON dh.MaKH = kh.MaKH
INNER JOIN ChiTietDonHang ctdh ON dh.MaDH = ctdh.MaDH
INNER JOIN DichVu dv ON ctdh.MaDV = dv.MaDV
GO

-- View thống kê dịch vụ bán chạy
CREATE VIEW vw_DichVuBanChay AS
SELECT 
    dv.MaDV,
    dv.TenDV,
    dv.GiaTien,
    dv.DonViTinh,
    SUM(ctdh.SoLuong) AS TongSoLuong,
    SUM(ctdh.ThanhTien) AS TongDoanhThu
FROM DichVu dv
INNER JOIN ChiTietDonHang ctdh ON dv.MaDV = ctdh.MaDV
GROUP BY dv.MaDV, dv.TenDV, dv.GiaTien, dv.DonViTinh
GO

----------------------------------------------------------
-- KIỂM TRA DỮ LIỆU
----------------------------------------------------------

-- Kiểm tra dữ liệu khách hàng
SELECT * FROM KhachHang;
GO

-- Kiểm tra dữ liệu dịch vụ
SELECT * FROM DichVu;
GO

-- Kiểm tra dữ liệu đơn hàng
SELECT * FROM DonHang;
GO

-- Kiểm tra dữ liệu chi tiết đơn hàng
SELECT * FROM ChiTietDonHang;
GO

-- Kiểm tra view đơn hàng chi tiết
SELECT * FROM vw_DonHangChiTiet;
GO

-- Kiểm tra view dịch vụ bán chạy
SELECT * FROM vw_DichVuBanChay;
GO

PRINT '✅ Database QuanLyTiemGiatUi đã được tạo thành công!';
PRINT '✅ Dữ liệu mẫu đã được thêm vào!';
PRINT '✅ Stored Procedures và Views đã được tạo!';