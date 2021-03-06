package org.mvnsearch.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

/**
 * RSocket client
 *
 * @author linux_china
 */
public class RSocketClient {
    public static void main(String[] args) throws Exception {
        RSocket socket =
                RSocketFactory.connect()
                        .metadataMimeType("text/plain")
                        .dataMimeType("application/json")
                        .transport(TcpClientTransport.create("localhost", 7000))
                        .start()
                        .block();

        socket.requestResponse(DefaultPayload.create("Hello"))
                .map(Payload::getDataUtf8)
                .onErrorReturn("error")
                .subscribe(System.out::println);
        Thread.sleep(1000);
    }
}
