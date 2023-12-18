package org.example.security.webhook;

import com.stripe.model.Event;
import org.json.JSONObject;

public record StripeEvent(JSONObject json, String type) {
}
