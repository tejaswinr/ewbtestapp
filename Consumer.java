package main.java.com.citi.ewb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Consumer {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Consumer.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.helloworld}")
  public void Consume(TradeFile tradeFile) {
    //LOGGER.info("received payload='{}'", tradeFile);
    latch.countDown();
  }
}

