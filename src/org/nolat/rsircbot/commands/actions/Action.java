package org.nolat.rsircbot.commands.actions;

public abstract class Action {
        protected ActionResponder responder;
 
        public Action(ActionResponder responder) {
                this.responder = responder;
        }
}

