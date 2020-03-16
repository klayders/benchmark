package com.benchmark.testing.web;

import com.benchmark.testing.model.SnapshotCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpServer;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class ApiController {

    @PostConstruct
    public void initTcpServer() {
        TcpServer.create()
                .port(8888)
                .handle((inbound, outbound) ->
                                inbound.receive()
                                        .map(ss -> {
                                            log.info("receiveObject ={}", ss.toString());
                                            return ss;
                                        }).then()
//                        inbound.receiveObject()
//                                .cast(SnapshotCounter.class)
//                                .map(ss -> {
//                                    log.info("receiveObject ={}", ss);
//                                    return ss;
//                        }).then()
                )
                .bind()
                .subscribe();
        log.info("tcp starting");
    }

    @PostMapping
    public String acceptMenu(@RequestBody SnapshotCounter snapshotCounter) {
        log.info("acceptMenu: do nothing, request={}", snapshotCounter);
        return "";
    }
}
