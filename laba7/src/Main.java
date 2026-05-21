import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int currentQuestion = 0;
    static Map<String, Integer> scores = new HashMap<>();

    static String[][] questions = {
            {"Твоя любимая еда?",
                    "Сыр", "Mouse",
                    "Курица", "Cat",
                    "Дошик", "Student"},
            {"Тебе задали домашнее задание, что ты сделаешь?",
                    "Буду балдеть", "Student",
                    "Сделаю через ИИ", "Mouse",
                    "У меня лапки", "Cat"},
            {"Как ты добираешься до своего любимого лучшего в мире учебного заведения?",
                    "На 98 любимом", "Student",
                    "На воздушном шаре", "Mouse",
                    "На велосипеде", "Cat"},
            {"Сколько пар отсидишь?",
                    "4 пары, не прийду(многа)", "Student",
                    "Заболел", "Mouse",
                    "4 пары, отсижу 2", "Cat"},
            {"Что ты делаешь после пар?",
                    "Смотрю тик ток", "Student",
                    "Иду хавать", "Mouse",
                    "Иду домой", "Cat"},
            {"Насколько ты кент?",
                    "Кирюха кент", "Student",
                    "Скинь лабу пж", "Mouse",
                    "Спасибо большое огромное", "Cat"},
            {"Как ты здороваешься с преподавателем?",
                    "Здороваюсь за руку", "Student",
                    "Говорю привет", "Mouse",
                    "Салам алекум", "Cat"},
            {"Сколько?",
                    "67", "Student",
                    "42", "Mouse",
                    "0", "Cat"}
    };

    public static void main(String[] args) {
        resetTest();
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Сервер запущен! http://localhost:8080");

            while (true) {
                Socket client = server.accept();
                handleClient(client);
            }
        } catch (IOException e) {
            System.err.println("Ошибка запуска сервера! " + e.getMessage());
        }
    }

    private static void handleClient(Socket client) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"))
        ) {
            String requestLine = in.readLine();
            if (requestLine == null) return;

            String headerLine;
            while ((headerLine = in.readLine()) != null && !headerLine.isEmpty()) {
            }

            if (requestLine.contains("GET /?answer=")) {
                String[] parts = requestLine.split(" ");
                if (parts.length > 1) {
                    String url = parts[1];
                    String answer = url.substring(url.indexOf("=") + 1);
                    scores.put(answer, scores.getOrDefault(answer, 0) + 1);
                    currentQuestion++;
                }
            } else if (requestLine.contains("GET /restart")) {
                resetTest();
            }

            String htmlResponse = generateHtml();

            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: text/html; charset=UTF-8\r\n");
            out.write("Connection: close\r\n");
            out.write("\r\n");
            out.write(htmlResponse);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void resetTest() {
        currentQuestion = 0;
        scores.clear();
        String[] personality = {"Student", "Cat", "Mouse"};
        for (String d : personality) {
            scores.put(d, 0);
        }
    }

    private static String generateHtml() {
        StringBuilder html = new StringBuilder();

        String cssAndJs = """
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 20px;
                position: relative;
                overflow-x: hidden;
                background: linear-gradient(135deg, #1a1a2e, #16213e, #0f3460);
            }
            
            body::before {
                content: '';
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: repeating-linear-gradient(
                    0deg,
                    rgba(0, 0, 0, 0.3) 0px,
                    rgba(0, 0, 0, 0.3) 2px,
                    transparent 2px,
                    transparent 6px
                );
                pointer-events: none;
                z-index: 1;
            }
            
            .container {
                background: rgba(0, 0, 0, 0.85);
                backdrop-filter: blur(10px);
                border-radius: 20px;
                padding: 40px;
                box-shadow: 0 0 50px rgba(255, 0, 100, 0.3);
                max-width: 600px;
                width: 100%;
                text-align: center;
                position: relative;
                z-index: 2;
                border: 2px solid rgba(255, 0, 150, 0.4);
            }
            
            h1 {
                color: #fff;
                margin-bottom: 20px;
                font-size: 28px;
                letter-spacing: 2px;
            }
            
            .progress {
                color: #00ffcc;
                font-size: 14px;
                margin-bottom: 20px;
                font-weight: bold;
            }
            
            .question-text {
                font-size: 20px;
                color: #fff;
                margin-bottom: 30px;
                line-height: 1.5;
                font-weight: bold;
            }
            
            .btn {
                display: inline-block;
                background: linear-gradient(45deg, #ff0066, #ff3300);
                color: white;
                padding: 12px 30px;
                margin: 10px;
                text-decoration: none;
                border-radius: 25px;
                transition: all 0.1s ease;
                font-weight: bold;
                font-size: 16px;
                border: none;
                cursor: pointer;
                box-shadow: 0 0 15px rgba(255, 0, 102, 0.3);
            }
            
            .btn:hover {
                transform: scale(1.05);
                box-shadow: 0 0 25px rgba(255, 0, 102, 0.6);
            }
            
            .btn:active {
                transform: scale(0.95);
            }
            
            .btn-restart {
                background: linear-gradient(45deg, #ff0066, #9900ff);
            }
            
            strong {
                color: #ff0066;
                font-size: 32px;
                display: block;
                margin: 20px 0;
            }
            
            .vignette {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                box-shadow: inset 0 0 100px rgba(0, 0, 0, 0.5);
                pointer-events: none;
                z-index: 2;
            }
        </style>
        """;

        html.append("<html><head><meta charset='UTF-8'><title>WHO ARE YOU?</title>")
                .append(cssAndJs)
                .append("</head><body><div class='vignette'></div>");
        html.append("<div class='container'>");

        if (currentQuestion < questions.length) {
            String[] qData = questions[currentQuestion];
            html.append("<h1>КТО ТЫ В МИРЕ IT?</h1>");
            html.append("<div class='progress'>Вопрос ")
                    .append(currentQuestion + 1)
                    .append(" из ")
                    .append(questions.length)
                    .append("</div>");
            html.append("<p class='question-text'>")
                    .append(qData[0])
                    .append("</p>");
            html.append("<a href='/?answer=").append(qData[2]).append("' class='btn'>").append(qData[1]).append("</a>");
            html.append("<a href='/?answer=").append(qData[4]).append("' class='btn'>").append(qData[3]).append("</a>");
            html.append("<a href='/?answer=").append(qData[6]).append("' class='btn'>").append(qData[5]).append("</a>");
        } else {
            String winner = "";
            int maxScore = -1;

            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                if (entry.getValue() > maxScore) {
                    maxScore = entry.getValue();
                    winner = entry.getKey();
                }
            }

            String winnerDisplay = "";
            switch(winner) {
                case "Student":
                    winnerDisplay = "СТУДЕНТ";
                    break;
                case "Cat":
                    winnerDisplay = "КОТ";
                    break;
                case "Mouse":
                    winnerDisplay = "МЫШЬ";
                    break;
                default:
                    winnerDisplay = winner;
            }

            html.append("<h1>ТЕСТ ЗАВЕРШЕН</h1>");
            html.append("<p class='question-text'>Поздравляем! Ты:</p>");
            html.append("<strong>").append(winnerDisplay).append("</strong>");
            html.append("<p style='color: #fff; margin: 20px 0;'>Выпей пива</p>");
            html.append("<a href='/restart' class='btn btn-restart'>Пройти заново</a>");
        }
        html.append("</div></body></html>");
        return html.toString();
    }
}