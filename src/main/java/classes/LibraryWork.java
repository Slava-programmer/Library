package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryWork {
    static int id = 0;
    Scanner scanner = new Scanner(System.in);
    static ArrayList<Book> booksArray = new ArrayList<>();
    static ArrayList<Visitor> visitorArray = new ArrayList<>();

    public void start() {


        booksArray.add(new Book("Евгений Онегин", "Пушкин"));
        booksArray.add(new Book("Как закалялась сталь", "Островский"));
        booksArray.add(new Book("Алые паруса", "Грин"));
        booksArray.add(new Book("Чайка по имени Джонатан Ливингстон", "Бах"));

        visitorArray.add(new Visitor("Иванов"));
        visitorArray.add(new Visitor("Макаров"));
        visitorArray.add(new Visitor("Чичиков"));
        visitorArray.add(new Visitor("Щеглов"));
        mainMenu();

    }

    public void mainMenu() {
        while (true) {
            System.out.println("""
                    1. Показать все книги в бибилиотеке;
                    2. Добавить новые книги;
                    3. Показать список всех читателей;
                    4. Добавить нового читателя;
                    5. Выдать книгу читателю;
                    6. Забрать обратно книгу у читателя;
                    7. Показать список всех книг, выданных читателям;
                    8. Ещё какая-то менюшка;
                    9. Для красоты;
                    0. Выход из программы.
                    """);
            switch (scanner.nextLine()) {
                case "1":
                    printBooks();
                    break;
                case "2":
                    addBooks();
                    break;
                case "3":
                    printVisitors();
                    break;
                case "4":
                    addVisitors();
                    break;
                case "5":
                    givingBookToVisitor();
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "0":
                    new FileIO().fileOutput(booksArray, visitorArray);
                    System.exit(0);
            }
        }
    }

    public void printBooks() {
        System.out.println("Список книг:");
        for (int i = 0; i < booksArray.size(); i++) {
            System.out.println((i + 1) + ". Наименование книги: " + booksArray.get(i).getName() + "\n   Автор книги: " + booksArray.get(i).getAuthor());
        }

    }

    public void printAvailableBooks() {
        System.out.println("Список доступных к выдаче книг:");
        for (int i = 0; i < booksArray.size(); i++) {
            if (!booksArray.get(i).isIssue()) {
                System.out.println((i + 1) + ". Наименование книги: " + booksArray.get(i).getName() + "\n   Автор книги: " + booksArray.get(i).getAuthor());
            }
        }
    }

    public void printVisitors() {
        System.out.println("Список читателей:");
        for (int i = 0; i < visitorArray.size(); i++) {
            System.out.println((i + 1) + ". Фамилия читателя: " + visitorArray.get(i).getName() + "\n   Читательский билет №(0 если билета нет):" + visitorArray.get(i).getId());
        }
    }

    public void addBooks() {
        while (true) {
            System.out.println("Добавление книг в библиотеку.\nВведите название книги или \"0\"(ноль), если передумали добавлять");
            String nameTMP = scanner.nextLine();
            if (nameTMP.equals("0")) {
                break;
            }
            System.out.println("Введите фамилию автора книги :");
            String authorTMP = scanner.nextLine();
            for (Book book : booksArray) {
                if (book.getName().contains(nameTMP)) {
                    if (book.getAuthor().contains(authorTMP)) {
                        System.out.println("Такая книга уже есть в библиотеке.");
                        break;
                    }
                }
            }
            booksArray.add(new Book(nameTMP, authorTMP));
            System.out.println("Книга " + nameTMP + " автора " + authorTMP + " добавлена в библиотеку");
        }
    }

    public void addVisitors() {
        System.out.println("Фамилия нового читателя:");
        String nameTMP = scanner.nextLine();
        for (Visitor visitor : visitorArray) {
            if (visitor.getName().contains(nameTMP)) {
                System.out.println("Такой читатель у нас уже есть.");
                break;
            }
        }
        visitorArray.add(new Visitor(nameTMP));
    }

    public void givingBookToVisitor() {
        while (true) {
            printVisitors();
            System.out.println("Выдаём книгу читателю.\nВведите номер читателя из списка (или \"0\" для выхода в предыдущее меню)");
            int numVisitor = selectMenuItem(visitorArray);
            System.out.println("Ок, пришёл читатель " + visitorArray.get(numVisitor).getName() + "\nКакую книгу ему выдаём?");
            printAvailableBooks();
            int numBook = selectMenuItem(booksArray);
            //Процесс записи книжки к читателю и читателя в карточку книжки
            if (booksArray.get(numBook).isIssue()) {
                System.out.println("Эта книга уже у кого-то из читателей, выбирайте из списка, пожалуйста");
                break;
            }
            System.out.println("Значит так. Читатель " + visitorArray.get(numVisitor).getName() + " берёт книгу " + booksArray.get(numBook).getName());
            booksArray.get(numBook).setIssue(true);
            visitorArray.get(numVisitor).setBooksInUse(booksArray.get(numBook).getName());
            booksArray.get(numBook).setReadsBook(visitorArray.get(numVisitor).getName());
            if (visitorArray.get(numVisitor).getId() == 0) {
                id++;
                visitorArray.get(numVisitor).setId((id));
                System.out.println(visitorArray.get(numVisitor).getId() + " " + id);
                System.out.println("Читателю выдан билет № " + visitorArray.get(numVisitor).getId());
            } else {
                System.out.println("Книга записана на читательский билет " + visitorArray.get(numVisitor).getId());
            }
        }
    }

    public int selectMenuItem(ArrayList arrayList) {
        int num = -1;
        while (true) {
            try {
                num = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Это вообще не число, попробуйте ввести числовое значение.");
                continue;
            }
            if (num == 0) {
                // TODO в вызывающий метод не должно ничего передаваться, нужно выходить на уровень выше
                break;
            } else if (num < 1 || num > arrayList.size()) {
                System.out.println("Вы ввели неправильное значение." +
                        " должно быть число от 1 до " + arrayList.size());
                continue;
            }
            return num - 1;
        }
        return num - 1;
    }
}
