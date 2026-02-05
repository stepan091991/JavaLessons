import java.util.Scanner;

void main() {
        Scanner scanner = new Scanner(System.in);
//        Task1 task1 = new Task1();
//        int[] array = task1.init(scanner);
//        Task2 task2 = new Task2();
//        int maxInt = task2.init(scanner, array);
//        Task3 task3 = new Task3();
//        task3.init(scanner, array);
//        Task4 task4 = new Task4();
//        task4.init(scanner, array, maxInt);
//        Task5 task5 = new Task5();
//        task5.init(array);
        Task6 task6 = new Task6();
        String string = task6.init(scanner);
        Task7 task7 = new Task7();
        task7.init(string);
        Task8 task8 = new Task8();
        task8.init(string);
        Task9 task9 = new Task9();
        task9.init(string);
        Task10 task10 = new Task10();
        task10.init(string);
}
class Task1 {
    //Создать массив из N элементов (N вводится).
    public int[] init(Scanner scanner){
        System.out.println("Запущен Task1");
        try {
            System.out.println("Введите размер массива: ");
            int size = scanner.nextInt();
            int[] array = new int[size];
            System.out.println("Массив: ");
            for (int i = 0; i < size; i++) {
                System.out.print("ведите " + i + " число: ");
                array[i] = scanner.nextInt();;
            }
            return array;
        }catch (InputMismatchException e){
                System.out.println("Требуется ввод числа!");
            }finally{
            System.out.println("Программа завершенна");
        }
        return null;
    }
}

class Task2{
    //Найти разность максимального и минимального элементов.
    public int init(Scanner scanner, int[] array){
        System.out.println("Запущен Task2");
        try {
            //Найти разность максимального и минимального элементов
            int max = array[0];
            int min = array[0];

            for (int i = 1; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
                if (array[i] < min) {
                    min = array[i];
                }
            }
            System.out.println("Разность max - min: " + (max - min));
            return max;
        }catch (NullPointerException e){
            System.out.println("В Task1 введён некоректный массив!");
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
            System.out.println("Программа завершенна");
        }
        return 0;
    }
}

class Task3{
    //Подсчитать количество чётных элементов.
    int evenCount = 0;
    public void init(Scanner scanner, int[] array) {
        System.out.println("Запущен Task3");
        try{
            for(int i = 0; i < array.length; i++){
                if((array[i] % 2) == 0){
                    evenCount += 1;
                }
            }
            System.out.println("Количество чётных элементов: " + evenCount);
        }catch (NullPointerException e){
            System.out.println("В Task1 введён некоректный массив!");
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
            System.out.println("Программа завершенна");
        }
    }
}

class Task4{
    //Найти индекс максимального элемента.
    public void init(Scanner scanner, int[] array, int maxInt) {
        System.out.println("Запущен Task4");
        try {
            for(int i = 0; i < array.length; i++){
                if(array[i] == maxInt){
                    System.out.println("Индекс максимального элемента: " + i);
                    break;
                }
            }
        }catch (NullPointerException e){
            System.out.println("В Task1 введён некоректный массив!");
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
        System.out.println("Программа завершенна");
        }
    }
}

class Task5{
    //Найти произведение элементов массива.
    public int init(int[] array){
        System.out.println("Запущен Task5");
        try{
            int result = 1;
            for(int i = 0; i < array.length; i++){
                result = result * array[i];
            }
            System.out.println("Произведение = " + result);
            return result;
        }catch (NullPointerException e){
            System.out.println("В Task1 введён некоректный массив!");
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
            System.out.println("Программа завершенна");
        }
        return 0;
    }
}

class Task6{
    //Найти количество вхождений заданного символа.
    public String init(Scanner scanner){
        System.out.println("Запущен Task6");
        try{
            System.out.print("Введиде строку для поиска: ");
            String string = scanner.nextLine();
            System.out.print("Введиде символ для поиска: ");
            String target = scanner.next();
            int occurrencesCount = string.length() - string.replace(target, "").length();
            System.out.println(occurrencesCount);
            return string;
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
            System.out.println("Программа завершенна");
        }
        return "";
    }
}

class Task7{
    //Преобразовать строку в массив символов и вывести его.
    public void init(String string){
        System.out.println("Запущен Task7");
        try{
            char[] charArray = string.toCharArray();
            for(int i = 0; i < charArray.length; i++){
                System.out.println(charArray[i]);
            }
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
            System.out.println("Программа завершенна");
        }
    }
}

class Task8{
    // Заменить все пробелы на символ '_'.
    public void init(String string){
        System.out.println("Запущен Task8");
        try{
            System.out.println(string.replaceAll(" ", "_"));
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
            System.out.println("Программа завершенна");
        }
    }
}

class Task9{
    //Подсчитать количество слов.
    public void init(String string){
        System.out.println("Запущен Task9");
        try{
            System.out.println("Всего слов: " + string.split(" ").length);
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
            System.out.println("Программа завершенна");
        }
    }
}

class Task10{
    //Определить, начинается ли строка с заглавной буквы.
    public void init(String string){
        System.out.println("Запущен Task10");
        try{
            if(Character.isUpperCase(string.toCharArray()[0])){
                System.out.println(string.toCharArray()[0] + " заглавная!");
            }else{
                System.out.println(string.toCharArray()[0] + " НЕ заглавная!");
            }
        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }finally {
            System.out.println("Программа завершенна");
        }
    }
}

//class Task9{
//    public void init(String string){
//        try{
//
//        }catch (Exception e){
//            System.out.println("Ошибка: " + e);
//        }finally {
//            System.out.println("Программа завершенна");
//        }
//    }
//}
