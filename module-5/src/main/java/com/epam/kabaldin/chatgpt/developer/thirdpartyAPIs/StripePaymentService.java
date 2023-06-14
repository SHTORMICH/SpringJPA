package com.epam.kabaldin.chatgpt.developer.thirdpartyAPIs;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class StripePaymentService {
    private static final String STRIPE_SECRET_KEY = "your_stripe_secret_key";

    public static void main(String[] args) {
        // Set your Stripe secret key
        Stripe.apiKey = STRIPE_SECRET_KEY;

        // Create a payment intent
        try {
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency("usd")
                    .setAmount(1000L) // Amount in cents
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(createParams);
            System.out.println("PaymentIntent created: " + paymentIntent.getId());
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
