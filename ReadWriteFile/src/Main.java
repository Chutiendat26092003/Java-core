import controller.ProductController;
import dao.ProductDao;
import entity.Product;

import java.io.IOException;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) throws IOException {

        ProductController productController = new ProductController();
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        do {
            System.out.println("\n\n\n1.List Product");
            System.out.println("2.Add Product");
            System.out.println("3.Update Product");
            System.out.println("4.Delete Product");
            System.out.println("5.Search");
            System.out.println("6.Exit\n\n");
            System.out.println("Choose:");
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose){
                case 1:
                    productController.getAll();
                    break;
                case 2:
                    productController.create();
                    break;
                case 3:
                    productController.update();
                    break;
                case 4:
                    productController.delete();
                    break;
                case 5:
                    productController.getByName();
                    break;
            }


        }while (choose != 6);
    }
}
