package com.data.filtro.api;

import com.data.filtro.model.*;
import com.data.filtro.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderAPIController {


    @Autowired
    OrderService orderService;
    @Autowired
    PaymentMethodService paymentMethodService;
    @Autowired
    UserService userService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CartService cartService;
    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable int id){
        List<Order> listOrder = orderService.getOrderByUserId(id);
        if (listOrder == null) {
            String message = "Order not found!";
            ErrorResponse err = new ErrorResponse(message, HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listOrder, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            String message = "Order not found!";
            ErrorResponse err = new ErrorResponse(message, HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Order> orders = orderService.getAll();
        if (orders == null) {
            String message = "Orders not found!";
            ErrorResponse err = new ErrorResponse(message, HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

//    @PostMapping("/placeOrder")
//    public ResponseEntity<?> placeOrder(@RequestParam int userId,
//                                        @RequestParam String phone,
//                                        @RequestParam String email,
//                                        @RequestParam String address,
//                                        @RequestParam String city,
//                                        @RequestParam int zip,
//                                        @RequestParam int paymentId){
//        User user = userService.getByUserId(userId);
//        Cart cart = cartService.getCartByUserId(userId);
//        List<CartItem> cartItemList = cartItemService.getCartItemByCartId(cart.getId());
//        if(cartItemList != null){
//            Order order = orderService.placeOrder(user,phone,email,address,city,zip,paymentMethodService.getMethodById(paymentId),
//                    cartItemList);
//
//            cartService.removeCartByCartId(cart.getId());
//            return new ResponseEntity<>("Đặt hàng thành công", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Giỏ hàng trống", HttpStatus.BAD_REQUEST);
//
//    }
//
@PostMapping("/placeOrder")
public ResponseEntity<?> placeOrder(@RequestParam int userId,
                                    @RequestParam String phone,
                                    @RequestParam String email,
                                    @RequestParam String address,
                                    @RequestParam String city,
                                    @RequestParam int zip,
                                    @RequestParam int paymentId) {
    User user = userService.getByUserId(userId);
    if (user != null) {
        Cart cart = cartService.getCartByUserId(user.getId());
        if (cart == null) {
            return new ResponseEntity<>("Cart ko hop le!", HttpStatus.BAD_REQUEST);
        }
        List<CartItem> cartItemList = cart.getCartItemList();
        PaymentMethod payment = paymentMethodService.getMethodById(paymentId);

        Order order = orderService.placeOrder(user, phone, email, address, city, zip, payment,
                cartItemList);
        cartService.removeAllProductInCar(cart);

        return new ResponseEntity<>("Đặt hàng thành công " + order.getId(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Dat hang khong thanh cong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
}


