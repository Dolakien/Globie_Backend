package com.product.globie.service;

import com.product.globie.payload.request.PaymentRequest;
import com.product.globie.payload.response.PayOSResponse;
import com.product.globie.payload.response.PaymentResponse;
import com.product.globie.payload.response.VNPayResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

public interface PaymentService {
    VNPayResponse vnPayPayment(int orderId, HttpServletRequest request);

    PaymentResponse handleCallback(HttpServletRequest request);

    VNPayResponse changeOrderStatus(int orderId);

    PayOSResponse changeOrderStatusPayOs(int orderId);

    PayOSResponse createPaymentLink(int orderId, HttpServletRequest request) throws Exception;

    PaymentResponse handleCallbackPayOS(HttpServletRequest request);
}
