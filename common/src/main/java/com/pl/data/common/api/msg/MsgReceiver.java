package com.pl.data.common.api.msg;

public interface MsgReceiver <T extends Msg> {
    void process(T msg);
}
