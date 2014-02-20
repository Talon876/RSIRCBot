package org.nolat.rsircbot.tools.json;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WitResponse {

    @SerializedName("msg_id")
    private String msgId;

    @SerializedName("msg_body")
    private String msgBody;

    @SerializedName("outcome")
    private WitOutcome outcome;

    public class WitOutcome {

        @SerializedName("intent")
        private String intent;

        @SerializedName("confidence")
        private double confidence;

        @SerializedName("entities")
        private Map<String, JsonObject> entities;

        public String getIntent() {

            return intent;
        }
        public double getConfidence() {
            return confidence;
        }

        public Map<String, JsonObject> getEntities() {
            return entities;
        }

        public JsonObject getEntity(String entity) {
            return entities.get(entity);
        }

        public String getFirstEntityValueOr(String entity, String defaultValue) {

            JsonObject ent = entities.get(entity);
            if(ent == null) {
                return defaultValue;
            }

            if(ent.getAsJsonObject() == null) {
                System.out.println("json entity object was null");
            }
            if(ent.getAsJsonArray() == null) {
                System.out.println("json entity array was null");
            }

            return ent.get("value").getAsString();
        }

        @Override
        public String toString() {
            return "Intent: [" + getIntent() + "]";
        }


//        /**
//         * Get the value of an entity or return a default value
//         * @param entity the key to check the entities for
//         * @param otherwise if the key doesn't have a value, use this as the default
//         * @return entities[entity] or otherwise if null
//         */
//        public String getEntityValueOr(int idx, String entity, String otherwise) {
//            Entity ent = getEntity(entity).get(idx);
//            if(ent == null) {
//                return otherwise;
//            } else {
//                return ent.getValue();
//            }
//        }
//
//        public String getFirstEntityValueOr(String entity, String otherwise) {
//            return getEntityValueOr(0, entity, otherwise);
//        }
//
//        public List<Entity> getEntity(String entity) {
//            return entities.get(entity);
//        }
//
//        public Entity getFirstEntity(String entity) {
//            return entities.get(entity).get(0);
//        }

    }

//    public class Entity {
//        public int end;
//        public int start;
//        public String value;
//        public String body;
//
//        public int getEnd() {
//            return end;
//        }
//
//        public int getStart() {
//            return start;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public String getBody() {
//            return body;
//        }
//
//        @Override
//        public String toString() {
//            return "{" + body + " as " + value + "}";
//        }
//    }

    public String getMsgId() {
        return msgId;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public WitOutcome getOutcome() {
        return outcome;
    }

}
