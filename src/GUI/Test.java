package GUI;

import DTO.TaiKhoanDTO;
import Services.TaiKhoanService;
import Utils.General;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*String username, password;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        username = scanner.nextLine();
        System.out.print("Password: ");
        password = scanner.nextLine();

        TaiKhoanService service = new TaiKhoanService();
        if (service.login(username, password)) {
            System.out.println(General.CURRENT_USER.toString());
        } else System.out.println("Error");

        */

        TaiKhoanService service = new TaiKhoanService();

        System.out.println(service.getTotalCount());
    }
}
