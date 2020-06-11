package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;

public class MTProtoContext extends TLContext {

    @Override
    protected void registerClasses() {
        registerClass(MTPing.CLASS_ID, MTPing.class);
        registerClass(MTPingDelayDisconnect.CLASS_ID, MTPingDelayDisconnect.class);
        registerClass(MTPong.CLASS_ID, MTPong.class);
        registerClass(MTMsgsAck.CLASS_ID, MTMsgsAck.class);
        registerClass(MTNewSessionCreated.CLASS_ID, MTNewSessionCreated.class);
        registerClass(MTBadMessageNotification.CLASS_ID, MTBadMessageNotification.class);
        registerClass(MTBadServerSalt.CLASS_ID, MTBadServerSalt.class);
        registerClass(MTMessageCopy.CLASS_ID, MTMessageCopy.class);
        registerClass(MTMsgNewDetailedInfo.CLASS_ID, MTMsgNewDetailedInfo.class);
        registerClass(MTMsgDetailedInfo.CLASS_ID, MTMsgDetailedInfo.class);
        registerClass(MTMsgResendReq.CLASS_ID, MTMsgResendReq.class);
        registerClass(MTMsgResendAnsReq.CLASS_ID, MTMsgResendAnsReq.class);
        registerClass(MTMessagesContainer.CLASS_ID, MTMessagesContainer.class);
        registerClass(MTRpcError.CLASS_ID, MTRpcError.class);
        registerClass(MTRpcDropAnswer.CLASS_ID, MTRpcDropAnswer.class);
        registerClass(MTRpcResult.CLASS_ID, MTRpcResult.class);
        registerClass(MTRpcAnswerUnknown.CLASS_ID, MTRpcAnswerUnknown.class);
        registerClass(MTRpcAnswerDroppedRunning.CLASS_ID, MTRpcAnswerDroppedRunning.class);
        registerClass(MTRpcAnswerDropped.CLASS_ID, MTRpcAnswerDropped.class);
        registerClass(MTDestroySession.CLASS_ID, MTDestroySession.class);
        registerClass(MTDestroySessionOk.CLASS_ID, MTDestroySessionOk.class);
        registerClass(MTDestroySessionNone.CLASS_ID, MTDestroySessionNone.class);
        registerClass(MTHttpWait.CLASS_ID, MTHttpWait.class);
        registerClass(MTGetFutureSalts.CLASS_ID, MTGetFutureSalts.class);
        registerClass(MTFutureSalt.CLASS_ID, MTFutureSalt.class);
        registerClass(MTFutureSalts.CLASS_ID, MTFutureSalts.class);
        registerClass(MTMsgsAllInfo.CLASS_ID, MTMsgsAllInfo.class);
        registerClass(MTMsgsStateInfo.CLASS_ID, MTMsgsStateInfo.class);
        registerClass(MTMsgsStateReq.CLASS_ID, MTMsgsStateReq.class);
    }

}