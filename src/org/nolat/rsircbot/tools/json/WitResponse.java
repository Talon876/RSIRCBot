package org.nolat.rsircbot.tools.json;

import java.util.List;
import java.util.Map;

public class WitResponse {

    public String msg_id;
    public String msg_body;
    public Outcome outcome;

    public class Outcome {
        public String intent;
        public double confidence;
        public Map<String, Entity> entities;

        public String getIntent() {
            return intent;
        }

        public double getConfidence() {
            return confidence;
        }

        public Map<String, Entity> getEntities() {
            return entities;
        }

        public List<String> getEntityNames() {
            List<String> entityNames = null;
            for (Map.Entry<String, Entity> entity : entities.entrySet()) {
                entityNames.add(entity.getKey());
            }
            return entityNames;
        }

        public List<Entity> getEntitiesList() {
            List<Entity> entityList = null;
            for (Map.Entry<String, Entity> entity : entities.entrySet()) {
                entityList.add(entity.getValue());
            }
            return entityList;
        }

        @Override
        public String toString() {
            return "Intent: [" + intent + "]";
        }
    }

    public class Entity {
        public int end;
        public int start;
        public String value;
        public String body;

        public int getEnd() {
            return end;
        }

        public int getStart() {
            return start;
        }

        public String getValue() {
            return value;
        }

        public String getBody() {
            return body;
        }

        @Override
        public String toString() {
            return "{" + body + " as " + value + "}";
        }
    }

    public String getMsg_id() {
        return msg_id;
    }

    public String getMsg_body() {
        return msg_body;
    }

    public Outcome getOutcome() {
        return outcome;
    }

}
