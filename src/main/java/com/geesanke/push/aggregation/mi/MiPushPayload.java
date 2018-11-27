
package com.geesanke.push.aggregation.mi;

import java.util.Arrays;
import java.util.List;

import com.xiaomi.xmpush.server.Message;

public class MiPushPayload {

    private Message message;
    private List<String> regids;
    private List<String> topics;
    private boolean all = false;

    private MiPushPayload(MiPushPayloadBuilder miPushPayloadBuilder) {
        this.message = miPushPayloadBuilder.message;
        this.regids = miPushPayloadBuilder.regids;
        this.topics = miPushPayloadBuilder.topics;
        this.all = miPushPayloadBuilder.all;
    }
    
    public MiPushPayload() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    public List<String> getRegids() {
        return regids;
    }
    public void setRegids(List<String> regids) {
        this.regids = regids;
    }
    public List<String> getTopics() {
        return topics;
    }
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
    public boolean isAll() {
        return all;
    }
    public void setAll(boolean all) {
        this.all = all;
    }
    public static MiPushPayloadBuilder newInstance() {
        return new MiPushPayloadBuilder();
    }
    public static final class MiPushPayloadBuilder {
        private Message message;
        private List<String> regids;
        private List<String> topics;
        private boolean all;

        private MiPushPayloadBuilder() {
        }

        public MiPushPayloadBuilder addMessage(Message message) {
            this.message = message;
            return this;
        }

        public MiPushPayloadBuilder addRegids(List<String> regids) {
            this.regids = regids;
            return this;
        }

        
        public MiPushPayloadBuilder addRegids(String[] regids) {
            this.regids = Arrays.asList(regids);
            return this;
        }
        
        public MiPushPayloadBuilder addTopics(List<String> topics) {
            this.topics = topics;
            return this;
        }

        public MiPushPayloadBuilder addAll(boolean all) {
            this.all = all;
            return this;
        }

        public MiPushPayload build() {
            return new MiPushPayload(this);
        }
    }

   

}
