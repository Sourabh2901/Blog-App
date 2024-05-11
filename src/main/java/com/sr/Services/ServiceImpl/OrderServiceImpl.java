package com.sr.Services.ServiceImpl;

//import com.sr.Entities.Order;
//import com.sr.Exception.ResourceNotFoundException;
//import com.sr.Repository.OrderRepo;
//import com.sr.Services.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//    @Autowired
//    private OrderRepo orderRepo;
//
//    @Override
//    public Order createOrder(Order order) {
//        return orderRepo.save(order);
//    }
//
//    @Override
//    public List<Order> getAllOrder() {
//        return orderRepo.findAll();
//    }
//
//    @Override
//    public Order getOrder(int orderId) {
//        Order order = orderRepo.findById(orderId)
//                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found with orderId : "+orderId));
//        return order;
//    }
//
//    @Override
//    public boolean deleteOrder(int orderId) {
//        Order order = orderRepo.findById(orderId)
//                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found with orderId : "+orderId));
//        orderRepo.delete(order);
//        return true;
//    }
//}
