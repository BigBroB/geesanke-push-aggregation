package com.geesanke.push.aggregation.oppo;

import com.oppo.push.server.Notification;
import com.oppo.push.server.Target;

public class OpushPayload {

    private Target target;

    private Notification notification;

    private OpushPayload(OpushPayloadBuilder opushPayloadBuilder) {
        this.target = opushPayloadBuilder.target;
        this.notification = opushPayloadBuilder.notification;
    }

    public OpushPayload() { }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public static OpushPayloadBuilder newInstance() {
        return new OpushPayloadBuilder();
    }

    public static final class OpushPayloadBuilder {
        private Target target;
        private Notification notification;

        private OpushPayloadBuilder() {
        }

        public OpushPayloadBuilder addTarget(Target target) {
            this.target = target;
            return this;
        }

        public OpushPayloadBuilder addNotification(Notification notification) {
            this.notification = notification;
            return this;
        }

        public OpushPayload build() {
            return new OpushPayload(this);
        }
    }
    
    
}
