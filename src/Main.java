import java.util.Scanner;

void main() {
        Scanner scanner = new Scanner(System.in);
        Task1 task1 = new Task1();
        int[] array = task1.init(scanner);
        Task2 task2 = new Task2();
        int maxInt = task2.init(scanner, array);
        Task3 task3 = new Task3();
        task3.init(scanner, array);
        Task4 task4 = new Task4();
        task4.init(scanner, array, maxInt);
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

