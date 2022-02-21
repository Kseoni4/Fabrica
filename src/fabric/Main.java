package fabric;

/**
 * Класс для точки входа в программу
 */
public class Main {

    /**
     * Точка входа в программу, метод создаёт новый объект класса {@link ProcessingLine} для последующего запуска
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        ProcessingLine processingLine = new ProcessingLine();
        processingLine.startWorking(1000);
    }


}
