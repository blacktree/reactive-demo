package org.mvnsearch.rsocket.responder;

import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * RSocket responder
 *
 * @author linux_china
 */
@SpringBootApplication
public class RsocketServer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RsocketServer.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("Starting the RSocket Server");
        RSocketFactory.receive()
                .acceptor((setup, sendingSocket) -> Mono.just(new RSocketResponderHandler(setup, sendingSocket)))
                .transport(TcpServerTransport.create("localhost", 42252))
                .start()
                .subscribe();
    }
}
