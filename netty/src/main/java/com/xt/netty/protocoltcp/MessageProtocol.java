package com.xt.netty.protocoltcp;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class MessageProtocol {
    private int len;
    private byte[] content;
}
