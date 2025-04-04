package com.example.estudantes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;
import java.util.concurrent.ThreadLocalRandom;

public class FixtureEstudantes {
    public static void main(String[] args) throws IOException, InterruptedException {
        var urlServidor = "http://localhost:8080";

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

        try (var cliente = HttpClient.newHttpClient()) {
            var builder = HttpRequest.newBuilder()
                .uri(URI.create(urlServidor + "/estudantes"))
                .header("Content-Type", "application/json");

            for (var nome : nomes) {
                var corpo = criarCorpoRequisicao(nome, random);
                System.out.println("Cadastrando estudante:\n" + corpo);
                var requisicao = builder.copy().POST(HttpRequest.BodyPublishers.ofString(corpo)).build();
                var resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());
                System.out.println("Resposta (" + resposta.statusCode() + "): " + resposta.body());
            }
        }
    }

    private static String criarCorpoRequisicao(String nome, ThreadLocalRandom random) {
        var email = removerAcentos(nome.toLowerCase().replace(" ", ".")) + "@edge.ufal.br";

        // Escolhe valores aleatórios
        var curso = random.nextBoolean() ? "CIENCIA_COMPUTACAO" : "ENGENHARIA_COMPUTACAO";
        var turma = random.nextInt(1, 5);
        var periodoAtual = random.nextInt(2, 9);
        var anoIngresso = 2020 + random.nextInt(0, 5);
        var mesIngresso = random.nextBoolean() ? 1 : 6;
        var dataDeIngresso = String.format("%04d-%02d", anoIngresso, mesIngresso);

        // Cria o JSON formatado
        return """
            {
                "nome": "%s",
                "email": "%s",
                "curso": "%s",
                "turma": %d,
                "periodoAtual": %d,
                "dataDeIngresso": "%s"
            }
            """.formatted(nome, email, curso, turma, periodoAtual, dataDeIngresso);
    }

    private static String removerAcentos(String value) {
        // https://stackoverflow.com/a/3322174
        return Normalizer.normalize(value, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
