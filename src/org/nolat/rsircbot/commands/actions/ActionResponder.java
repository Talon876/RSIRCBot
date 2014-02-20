package org.nolat.rsircbot.commands.actions;

public interface ActionResponder<T extends ActionResponse> {
        public void onResponseSuccess(T response);
        public void onResponseFailure(String error);
}