package dao;

import entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReadWriterFile {
    private String filePath = "E:\\Java-core\\ReadWriteFile\\data.txt";

    public ProductReadWriterFile() {
    }

    public List<Product> findAllProductFile() throws IOException {
        List<Product> productList = new ArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                // lấy ra vị trí
                int idStart = line.indexOf("{id=") + new String("{id=").length();
                int idEnd = line.indexOf(",name='");

                int nameStart = idEnd + new String(",name='").length();
                int nameEnd = line.indexOf("',producer='");

                int producerStart = nameEnd + new String("',producer='").length();
                int producerEnd = line.indexOf("',lineProduct='");

                int lineProductStart = producerEnd + new String("',lineProduct='").length();
                int lineProductEnd = line.indexOf("',price=");

                int priceStart = lineProductEnd + new String("',price=").length();
                int priceEnd = line.indexOf("}");

                // cắt chuỗi
                long id = Long.parseLong(line.substring(idStart, idEnd));
                String name = line.substring(nameStart, nameEnd);
                String producer = line.substring(producerStart, producerEnd);
                String lineProduct = line.substring(lineProductStart, lineProductEnd);
                long price = Long.parseLong(line.substring(priceStart, priceEnd));

                Product product = new Product(id, name, producer, lineProduct, price);
                productList.add(product);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> findByName(String nameProduct) throws IOException {
        List<Product> productList = new ArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(nameProduct)) {

                    // lấy ra vị trí
                    int idStart = line.indexOf("{id=") + new String("{id=").length();
                    int idEnd = line.indexOf(",name='");

                    int nameStart = idEnd + new String(",name='").length();
                    int nameEnd = line.indexOf("',producer='");

                    int producerStart = nameEnd + new String("',producer='").length();
                    int producerEnd = line.indexOf("',lineProduct='");

                    int lineProductStart = producerEnd + new String("',lineProduct='").length();
                    int lineProductEnd = line.indexOf("',price=");

                    int priceStart = lineProductEnd + new String("',price=").length();
                    int priceEnd = line.indexOf("}");

                    // cắt chuỗi
                    long id = Long.parseLong(line.substring(idStart, idEnd));
                    String name = line.substring(nameStart, nameEnd);
                    String producer = line.substring(producerStart, producerEnd);
                    String lineProduct = line.substring(lineProductStart, lineProductEnd);
                    long price = Long.parseLong(line.substring(priceStart, priceEnd));

                    Product product = new Product(id, name, producer, lineProduct, price);
                    productList.add(product);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        return productList;
    }

    public void createProductFile(Product product) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(product.toString());
            writer.close();
            System.out.printf("\nOK");
        } catch (IOException e) {
            System.out.printf("ERROR");
            e.printStackTrace();
        }
    }

    public void updateProductFile(long idUpdate, Product productNew) {
        boolean isDelete = false;
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            String line;
            String newContent = "";
            byte[] tasking;
            while ((line = file.readLine()) != null) {
                int idStart = line.indexOf("{id=") + new String("{id=").length();
                int idEnd = line.indexOf(",name='");
                long id = Long.parseLong(line.substring(idStart, idEnd));
                if (id == idUpdate) {
                    isDelete = true;
                    newContent += productNew.toString() + "\n";
                    continue;
                }
                newContent += line + "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(newContent);
            file.close();
            writer.close();
            if (isDelete) {
                System.out.printf("\nOK\n");
            } else {
                System.out.printf("\nNot available\n");
            }
        } catch (IOException e) {
            System.out.printf("ERROR");
            e.printStackTrace();
        }
    }

    public void deleteProductFile(Product product) {
        boolean isDelete = false;
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            String line;
            String newContent = "";
            byte[] tasking;
            while ((line = file.readLine()) != null) {
                int idStart = line.indexOf("{id=") + new String("{id=").length();
                int idEnd = line.indexOf(",name='");
                long id = Long.parseLong(line.substring(idStart, idEnd));
                if (id == product.getId()) {
                    isDelete = true;
                    continue;
                }
                newContent += line + "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(newContent);
            file.close();
            writer.close();
            if (isDelete) {
                System.out.printf("\nOK\n");
            } else {
                System.out.printf("\nNot available\n");
            }
        } catch (IOException e) {
            System.out.printf("ERROR");
            e.printStackTrace();
        }
    }
}
