/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nadiamonika270923;
 import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author User
 */
public class TriviaServer {
    private static final int PORT = 12345;
    private static final String[] pertanyaan = {
        "Apa ibukota Indonesia?",
        "Berapa banyak planet dalam tata surya kita?",
        "Siapakah pembuat java?"
    };

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server berjalan di port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("Terhubung dengan client: " + clientSocket.getInetAddress());

                    out.println("Selamat datang di Server Trivia!");

                    String request;
                    String currentQuestion = null;

                    while ((request = in.readLine()) != null) {
                        if (request.equals("permintaan")) {
                            currentQuestion = getRandomQuestion(out);
                        } else if (request.startsWith("jawaban")) {
                            handleAnswer(request, currentQuestion, out);
                        } else {
                            out.println("Perintah tidak valid.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomQuestion(PrintWriter out) {
        int randomIndex = new Random().nextInt(pertanyaan.length);
        String question = pertanyaan[randomIndex];
        int questionNumber = randomIndex + 1;
        out.println(questionNumber + "#" + question);
        return question;
    }

    private static void handleAnswer(String request, String currentQuestion, PrintWriter out) {
        String[] parts = request.split("#");
        if (parts.length == 2) {
            String nomorPertanyaan = parts[0].split(" ")[1];
            String jawaban = parts[1];
            
            if (currentQuestion != null && currentQuestion.startsWith(nomorPertanyaan)) {
                out.println("Kerja yang bagus!");
            } else {
                out.println("Nomor pertanyaan tidak sesuai dengan yang sekarang.");
            }
        } else {
            out.println("Format jawaban tidak valid.");
        }
    }
}


