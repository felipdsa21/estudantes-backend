import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;
import java.util.concurrent.ThreadLocalRandom;

public class EnvioEstudantes {
    public static void main(String[] args) throws IOException, InterruptedException {
        var serverUrl = "http://localhost:8080";

        // Nomes gerados aleatoriamente
        var nomes = new String[] {
            "Adriano Barbosa",
            "Adriano Santos",
            "André Cardoso",
            "Antônio Vieira",
            "Bruno de Souza",
            "Fábio Silva",
            "Fernando Batista",
            "Francisco Ramos",
            "Geraldo de Almeida",
            "Geraldo Dias",
            "José Alves",
            "José Mendes",
            "Leonardo Borges",
            "Pedro Fernandes",
            "Raimundo Mendes",
            "Ricardo Lima",
            "Ricardo Teixeira",
            "Rodrigo Batista",
            "Sérgio do Nascimento",
            "Tiago Almeida"
        };

        var random = ThreadLocalRandom.current();

        try (var client = HttpClient.newHttpClient()) {
            for (var nome : nomes) {
                var nomeEmail = nome.toLowerCase().replace(" ", ".");

                // Tira os acentos
                // https://stackoverflow.com/a/3322174
                nomeEmail = Normalizer.normalize(nomeEmail, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

                var email = nomeEmail + "@edge.ufal.br";

                // Escolhe valores aleatórios
                var curso = random.nextBoolean() ? "CIENCIA_COMPUTACAO" : "ENGENHARIA_COMPUTACAO";
                var turma = random.nextInt(1, 5);
                var periodoAtual = random.nextInt(2, 9);
                int anoIngresso = 2020 + random.nextInt(0, 5);
                int mesIngresso = random.nextBoolean() ? 1 : 6;
                var dataDeIngresso = String.format("%04d-%02d", anoIngresso, mesIngresso);

                // Cria o JSON formatado
                String body = """
                    {
                        "nome": "%s",
                        "email": "%s",
                        "curso": "%s",
                        "turma": %d,
                        "periodoAtual": %d,
                        "dataDeIngresso": "%s"
                    }
                    """.formatted(nome, email, curso, turma, periodoAtual, dataDeIngresso);

                System.out.println("Enviando JSON:\n" + body);

                var request = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl + "/estudantes"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Resposta (" + response.statusCode() + "): " + response.body());
            }
        }
    }
}
