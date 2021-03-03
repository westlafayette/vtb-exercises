import service.ShopService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ShopService shopService = new ShopService();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            String s = scanner.nextLine();

            String[] values = s.split("\\s+");

            switch (values[0]) {
                case "/showProductsByPerson" -> shopService.showProductsByPerson(values[1]);

                case "/findPersonsByProductTitle" -> shopService.findPersonsByProductTitle(values[1]);

                case "/removePerson" -> shopService.removePerson(values[1]);

                case "/removeProduct" -> shopService.removeProduct(values[1]);

                case "/buy" -> shopService.buy(values[1], values[2]);

                case "/check" -> shopService.check(values[1]);
            }
        }
    }
}
