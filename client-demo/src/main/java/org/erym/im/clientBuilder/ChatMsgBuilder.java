/**
 * Created by 尼恩 at 疯狂创客圈
 */

package org.erym.im.clientBuilder;

import org.erym.im.common.model.dto.UserDTO;
import org.erym.im.common.model.protocol.ChatMsg;
import org.erym.im.common.model.protocol.ProtoMsg;
import org.erym.im.client.ClientSession;

/**
 * 聊天消息Builder
 */

public class ChatMsgBuilder extends BaseBuilder
{


    private ChatMsg chatMsg;
    private UserDTO user;


    public ChatMsgBuilder(
            ChatMsg chatMsg,
            UserDTO user,
            ClientSession session)
    {
        super(ProtoMsg.HeadType.MESSAGE_REQUEST, session);
        this.chatMsg = chatMsg;
        this.user = user;

    }


    public ProtoMsg.Message build()
    {
        ProtoMsg.Message message = buildCommon(-1);
        ProtoMsg.MessageRequest.Builder cb
                = ProtoMsg.MessageRequest.newBuilder();

        chatMsg.fillMsg(cb);
        return message
                .toBuilder()
                .setMessageRequest(cb)
                .build();
    }

    public static ProtoMsg.Message buildChatMsg(
            ChatMsg chatMsg,
            UserDTO user,
            ClientSession session)
    {
        ChatMsgBuilder builder =
                new ChatMsgBuilder(chatMsg, user, session);
        return builder.build();

    }
}