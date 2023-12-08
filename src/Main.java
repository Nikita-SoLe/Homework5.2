import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Создание списка для хранения дел
        ArrayList<String> lstToDo = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        // Бесконечный цикл для взаимодействия с пользователем
        while (true) {
            String input = scan.nextLine();
            toDoList(input, lstToDo);
        }
    }

    // Метод для обработки команд пользователя и управления списком дел
    static void toDoList(String input, ArrayList<String> list) {
        String[] lstIn = input.split(" ");
        String command = lstIn[0]; // Получаем команду из ввода пользователя

        int numDo = -1;
        if (lstIn.length > 1) numDo = isNum(lstIn[1]); // Проверяем наличие номера дела

        // Получение самого дела из ввода пользователя
        String toDO;
        if (numDo != -1) toDO = String.join(" ", Arrays.copyOfRange(lstIn, 2, lstIn.length));
        else toDO = String.join(" ", Arrays.copyOfRange(lstIn, 1, lstIn.length));

        // Обработка команд пользователя
        switch (command) {
            case "LIST" -> printList(list);
            case "ADD" -> addToDo(numDo, toDO, list);
            case "EDIT" -> editToDo(numDo, toDO, list);
            case "DELETE" -> deleteToDo(numDo, list);
        }
    }

    // Метод для вывода списка дел
    static void printList(ArrayList<String> list) {
        if (list.isEmpty()) System.out.println("Список дел пуст.");
        else {
            System.out.println("Ваш список:");
            for (int i = 1; i < list.size() + 1; i++) {
                System.out.printf("%d: %s\n", i, list.get(i - 1));
            }
        }
    }

    // Метод для проверки, является ли строка числом
    static int isNum(String str) {
        for (char el : str.trim().toCharArray()) {
            if (!Character.isDigit(el)) {
                return -1;
            }
        }
        return Integer.parseInt(str) - 1;
    }

    // Метод для добавления дела в список
    static void addToDo(int numDo, String toDo, ArrayList<String> list) {
        // Проверка на корректность номера дела
        if (numDo > list.size()) {
            System.out.println("Не может быть добавлено под номером " + numDo + 1);
            System.out.println("Ваш список дел короче.");
            System.out.println("Будет добавлено в конец.");
            numDo = list.size() - 1;
        }
        // Добавление дела в список
        if (numDo == -1) {
            list.add(toDo);
        } else {
            list.add(numDo, toDo);
        }
        System.out.println("Добавлено.");
    }

    // Метод для редактирования дела в списке
    static void editToDo(int numDo, String toDo, ArrayList<String> list) {
        if (numDo >= list.size()) {
            System.out.println("Не может быть изменено под номером " + numDo + 1);
            System.out.println("Ваш список дел короче.");
            System.out.println("Будет изменено последнее дело.");
            numDo = list.size() - 1;
        }
        list.remove(numDo);
        list.add(numDo, toDo);
        System.out.println("Изменено.");
    }

    // Метод для удаления дела из списка
    static void deleteToDo(int numDo, ArrayList<String> list) {
        if (numDo >= list.size()) {
            System.out.println("Не может быть удалено под номером " + numDo + 1);
            System.out.println("Ваш список дел короче.");
            System.out.println("Будет удалено последнее дело.");
            numDo = list.size() - 1;
        }
        list.remove(numDo);
        System.out.println("Удалено.");
    }
}
