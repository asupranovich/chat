package org.asupranovich.chat.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/messages")
    public ResponseEntity<String> postMessage(@RequestBody Message message) {
        String response = clientService.sendMessage(message);
        return ResponseEntity.ok(response);
    }

}
