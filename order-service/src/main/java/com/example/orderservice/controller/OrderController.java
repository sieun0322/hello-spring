package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
@Slf4j
public class OrderController {
    private Environment env;
    private OrderService orderService;
    private KafkaProducer kafkaProducer;
    private OrderProducer orderProducer;


    @Autowired
    public OrderController(Environment env, OrderService orderService,KafkaProducer kafkaProducer,OrderProducer orderProducer) {
        this.env = env;
        this.orderService = orderService;
        this.kafkaProducer = kafkaProducer;
        this.orderProducer = orderProducer;
    }

    @GetMapping("/health_check")
    public String status(){
        return String.format("It's Working in Order Service on Port %s"
        ,env.getProperty("local.server.port"));
    }
    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId
                                                        ,@RequestBody RequestOrder orderDetails){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        /*jpa*/
        //OrderDto createOrder = orderService.createOrder(orderDto);
        //ResponseOrder responseOrder = mapper.map(createOrder,ResponseOrder.class);

        /**/
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDetails.getQty()*orderDetails.getUnitPrice());

        //kafka message 전송
        kafkaProducer.send("example-catalog-topic",orderDto);
        orderProducer.send("orders",orderDto);
        ResponseOrder responseOrder = mapper.map(orderDto,ResponseOrder.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId")String userId){
        log.info("order before");
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();

        orderList.forEach(v->{
            result.add(new ModelMapper().map(v,ResponseOrder.class));
        });
        log.info("order after");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
