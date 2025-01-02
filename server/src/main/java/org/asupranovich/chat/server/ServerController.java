package org.asupranovich.chat.server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @Autowired
    private ServerService serverService;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessage() {
        return ResponseEntity.ok(serverService.readAllMessages());
    }

    @PostMapping("/messages")
    public ResponseEntity<String> addMessage(@RequestBody Message message) {
        System.out.println("Received message: " + message.getText());
        serverService.storeMessage(message);
        return ResponseEntity.ok("success");
    }

}
