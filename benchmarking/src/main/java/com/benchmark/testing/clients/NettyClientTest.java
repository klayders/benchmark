package com.benchmark.testing.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.atomic.AtomicInteger;

import static com.benchmark.testing.utils.ClientUtils.WEB_CLIENT;
import static com.benchmark.testing.utils.CustomUtils.MAX_STEP;
import static com.benchmark.testing.utils.PojoRequestUtils.HARD_CODE_COUNTER;

@Slf4j
public class NettyClientTest {
    public static void main(String[] args) {
        initTcpClient();
    }

    public static void initTcpClient() {
        TcpClient.create()
                .port(8888)
                .handle((inbound, outbound) ->
                        outbound
                                .sendObject(Mono.just(HARD_CODE_COUNTER))
                                .then()
                )
                .connect()
                .subscribe();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void initWeb() {

        AtomicInteger cc = new AtomicInteger();

        for (int i = 0; i < MAX_STEP; i++) {


            WEB_CLIENT
                    .post()
                    .uri("localhost:8080/menu")
                    .body(BodyInserters.fromValue(
                            HARD_CODE_COUNTER
                    ))
                    .exchange()
                    .map(clientResponse -> {
                        cc.incrementAndGet();
                        if (clientResponse.statusCode().is2xxSuccessful()) {
                            log.info("sendUpdateWalletId: status 200");
                        } else if (clientResponse.statusCode().is4xxClientError()) {
                            log.error("sendUpdateWalletId: status 400");
                        } else if (clientResponse.statusCode().is5xxServerError()) {
                            log.error("sendUpdateWalletId: status 500");
                        }
                        return clientResponse;
                    })
                    .subscribe();
        }

        while (cc.get() != MAX_STEP){}

    }

}
