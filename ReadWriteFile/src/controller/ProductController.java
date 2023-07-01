package controller;

import dao.ProductReadWriterFile;
import entity.Product;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//   E:\Java-core\ReadWriteFile\data.txt
public class ProductController {

    //List<Product> productList = new ArrayList();
    ProductReadWriterFile productReadWriterFile = new ProductReadWriterFile();
    Scanner sc = new Scanner(System.in);

    public ProductController() throws IOException {
    }

    public void getAll() throws IOException {
        List<Product> productList = productReadWriterFile.findAllProductFile();

        displayHeader();

        for (Product p : productList) {
            displayBody(p);
        }
    }

    public void getByName() throws IOException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        List<Product> productList = productReadWriterFile.findByName(name);

        displayHeader();

        for (Product p : productList) {
            displayBody(p);
        }
    }

    public void displayHeader() {
        System.out.printf("%-25s", "Id ");
        System.out.printf("%-25s", "Name");
        System.out.printf("%-25s", "Producer");
        System.out.printf("%-25s", "Line Product");
        System.out.printf("%-25s", "Price");
        System.out.println();
    }

    public void displayBody(Product p) {
        System.out.printf("%-25s", p.getId());
        System.out.printf("%-25s", p.getName());
        System.out.printf("%-25s", p.getProducer());
        System.out.printf("%-25s", p.getLineProduct());
        System.out.printf("%-25s", p.getPrice());
        System.out.println();
    }

    public void create() {
        System.out.print("Enter ID: ");
        long id = sc.nextLong();

        sc.nextLine(); // Đọc ký tự mới dòng sau khi đọc số long

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter producer: ");
        String producer = sc.nextLine();

        System.out.print("Enter line product: ");
        String lineProduct = sc.nextLine();

        System.out.print("Enter Price: ");
        long price = sc.nextLong();

        Product product = new Product(id, name, producer, lineProduct, price);
        productReadWriterFile.createProductFile(product);
        System.out.println("" + product.toString());
    }

    public void update() {
        System.out.print("Enter the product id you want to update: ");
        long id = sc.nextLong();

        sc.nextLine(); // Đọc ký tự mới dòng sau khi đọc số long

        System.out.print("Enter name new: ");
        String name = sc.nextLine();

        System.out.print("Enter producer new: ");
        String producer = sc.nextLine();

        System.out.print("Enter line product new: ");
        String lineProduct = sc.nextLine();

        System.out.print("Enter Price new: ");
        long price = sc.nextLong();

        Product productNew = new Product(id, name, producer, lineProduct, price);
        productReadWriterFile.updateProductFile(id ,productNew);
        System.out.println("" + productNew.toString());
    }


    public void delete() {
        System.out.print("Enter the product id you want to delete: ");
        long id = sc.nextLong();

        sc.nextLine(); // Đọc ký tự mới dòng sau khi đọc số long

        Product product = new Product(id);
        productReadWriterFile.deleteProductFile(product);
    }

}

