package org.nolat.rsircbot.commands.actions;

public class AlchResponse extends ActionResponse{
 
        public final String itemName;
        public final int natPrice;
        public final int itemPrice;
        public final int alchPrice;
 
        public AlchResponse(String itemName, int natPrice, int itemPrice, int alchPrice) {
                this.itemName = itemName;
                this.natPrice = natPrice;
                this.itemPrice = itemPrice;
                this.alchPrice = alchPrice;
        }
 
        public int getCostBuyingBoth() {
                return itemPrice + natPrice - alchPrice;
        }
 
        public int getCostBuyingJustNat() {
                return natPrice - alchPrice;
        }
 
        public int getCostBuyingJustItem() {
                return itemPrice - alchPrice;
        }
}