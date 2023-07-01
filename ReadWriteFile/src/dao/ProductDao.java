package dao;

import entity.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static String URL = "jdbc:mysql://localhost:3306/db_phone?user=root";
    private static String USER = "root";
    private static String PASSWORD = "admin@123";
    private static Connection CONNECTION = getCONNECTION();

    List<Product> productList = new ArrayList();

    private static Connection getCONNECTION() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findAll() {
        String strSelect = "SELECT * FROM product_phone";
        try {
            Connection connection = CONNECTION;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(strSelect);

            while (rs.next()) {
                // Lấy giá trị từ ResultSet
                Timestamp insert = rs.getTimestamp("inserted_time");
                Timestamp update = rs.getTimestamp("updated_time");
                // Chuyển đổi thành LocalDateTime
                LocalDateTime insertedTime = insert.toLocalDateTime();
                LocalDateTime updatedTime = update.toLocalDateTime();

                Product product = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("producer"),
                        rs.getString("line_product"),
                        rs.getLong("price"),
                        insertedTime,
                        updatedTime);

                this.productList.add(product);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.productList;
    }

    public Product findById(long id) {
        Product product = new Product();
        String strSelect = "SELECT * FROM product_phone WHERE id =" + id;
        try {
            Connection connection = CONNECTION;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(strSelect);

            if (rs.next()) {
                // Lấy giá trị từ ResultSet
                Timestamp insert = rs.getTimestamp("inserted_time");
                Timestamp update = rs.getTimestamp("updated_time");
                // Chuyển đổi thành LocalDateTime
                LocalDateTime insertedTime = insert.toLocalDateTime();
                LocalDateTime updatedTime = update.toLocalDateTime();

                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setProducer(rs.getString("producer"));
                product.setLineProduct(rs.getString("line_product"));
                product.setPrice(rs.getLong("price"));
                product.setInsertedTime(insertedTime);
                product.setUpdatedTime(updatedTime);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void createProduct(Product product) {
        String strInsert = "INSERT INTO product_phone (id, name, producer, line_product, price, inserted_time, updated_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            product.setBeforeInsertedTime();

            Connection connection = CONNECTION;
            PreparedStatement pstmt = connection.prepareStatement(strInsert);
            pstmt.setLong(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getProducer());
            pstmt.setString(4, product.getLineProduct());
            pstmt.setLong(5, product.getPrice());
            pstmt.setObject(6, product.getInsertedTime());
            pstmt.setObject(7, product.getUpdatedTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        String strUpdate = "UPDATE product_phone SET name=?, producer=?, line_product=?, price=?, updated_time=? WHERE id=" + product.getId();

        try {
            product.setBeforeUpdatedTime();

            Connection connection = CONNECTION;
            PreparedStatement pstmt = connection.prepareStatement(strUpdate);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getProducer());
            pstmt.setString(3, product.getLineProduct());
            pstmt.setLong(4, product.getPrice());
            pstmt.setObject(5, product.getUpdatedTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Product product) {
        String strDelete = "DELETE FROM product_phone WHERE id =" + product.getId();

        try {
            product.setBeforeUpdatedTime();

            Connection connection = CONNECTION;
            PreparedStatement pstmt = connection.prepareStatement(strDelete);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
