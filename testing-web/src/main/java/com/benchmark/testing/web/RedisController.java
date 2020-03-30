package com.benchmark.testing.web;

import static com.benchmark.testing.transfer.UserStatus.ERROR;
import static com.benchmark.testing.transfer.UserStatus.OK;
import static java.util.stream.Collectors.toMap;

import com.benchmark.testing.transfer.Easy;
import com.benchmark.testing.transfer.EasyUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {

  private final RedisTemplate<String, Object> redisTicketTransferTemplate;
  private final HashOperations<String, Long, EasyUser> test;

  String simpleTestKey = "test1";

  @GetMapping("/list")
  public void addCounter() throws InterruptedException {

    String userId = "2";

    List<EasyUser> of = List.of(EasyUser.of(userId, 1, OK), EasyUser.of(userId, 2, OK), EasyUser.of(userId, 3, OK));

    Map<Long, EasyUser> collect = of.stream()
        .peek(user -> {
          test.put(user.getUserId(), user.getTicketId(), user);
        })
        .collect(toMap(EasyUser::getTicketId, Function.identity(), (first, second) -> first));

    test.putAll(userId, collect);

    test.put(userId, 2L, EasyUser.of(userId, 2, ERROR));

    redisTicketTransferTemplate.expire(userId, 60, TimeUnit.SECONDS);

    var result = test.entries(userId);

    System.out.println(result);
  }

  ObjectMapper objectMapper = new ObjectMapper();


  @GetMapping("/set")
  public void addCounterSet(@RequestParam long id) throws InterruptedException {
    Long size = redisTicketTransferTemplate.opsForSet().size(simpleTestKey);
    //    Long sizeAll = redisTicketTransferTemplate.opsForSet().

    redisTicketTransferTemplate.opsForSet().add(simpleTestKey, Easy.of(id, true));
    redisTicketTransferTemplate.expire(simpleTestKey, 10, TimeUnit.SECONDS);
    System.out.println(size);

    redisTicketTransferTemplate.opsForSet().add(simpleTestKey, Easy.of(id, true));

    //    if (size != null && size > 3) {
    //      Set<Easy> range = redisTicketTransferTemplate.opsForSet().members(simpleTestKey);
    //      log.warn("addCounter={}", range);
    //      log.warn("addCounter={}", range.size());
    //    }
  }
  //
  //  @GetMapping("/test")
  //  public Set<Long> putSet(@RequestParam Set<Long> id) {
  //    String firstkey = "firstkey";
  //    redisSet.opsForValue().set(firstkey, id, Duration.ofSeconds(30));
  //    return redisSet.opsForValue().get(firstkey);
  //  }


}
