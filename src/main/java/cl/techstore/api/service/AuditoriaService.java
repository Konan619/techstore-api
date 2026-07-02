package cl.techstore.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cl.techstore.api.dto.AuditoriaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.time.Instant;

@Service
public class AuditoriaService {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.url:}")
    private String queueUrl;

    public AuditoriaService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
        this.objectMapper = new ObjectMapper();
    }

    public void enviarEvento(String accion, Long productoId, String nombre, String usuario) {
        try {
            if (queueUrl == null || queueUrl.isBlank()) {
                System.out.println("SQS no configurado. Evento no enviado.");
                return;
            }

            AuditoriaDTO auditoria = new AuditoriaDTO(
                    accion,
                    productoId,
                    nombre,
                    usuario,
                    Instant.now().toString()
            );

            String mensaje = objectMapper.writeValueAsString(auditoria);

            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(mensaje)
                    .build();

            sqsClient.sendMessage(request);

            System.out.println("Evento enviado a SQS: " + mensaje);

        } catch (Exception e) {
            System.out.println("Error enviando evento a SQS: " + e.getMessage());
        }
    }
}